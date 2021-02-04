package com.dataVisualiz.dataVisualiz.controller;


import com.dataVisualiz.dataVisualiz.model.DadoModel;
import com.dataVisualiz.dataVisualiz.model.EmpresaModel;
import com.dataVisualiz.dataVisualiz.service.DadoService;
import com.dataVisualiz.dataVisualiz.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;




import javax.validation.Valid;
import java.util.*;

@Controller
public class EmpresaController {

    @Autowired
    EmpresaService empresaService;

    @Autowired
    DadoService dadoService;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String getIndex(){
        return "index";
    }



    @RequestMapping(value = "/empresas", method = RequestMethod.GET)
    public ModelAndView getEmpresas(String keyword){
        ModelAndView mv = new ModelAndView("empresas");

        if(keyword!= null){
            List<DadoModel> listDados = dadoService.findByKeyword(keyword);
            mv.addObject("listaDados", listDados);
        }
        else{
            List<DadoModel> listaDados = dadoService.findAll();
            mv.addObject("listaDados",listaDados);
        }
        return mv;
    }

    @RequestMapping(value = "/deletarDado")
    public String deletarDado(long id){
        DadoModel dado = dadoService.findById(id);
        dadoService.deleteById(dado.getId());
        return "redirect:/empresas";
    }

    @RequestMapping(value = "/empresas/adicionarDado", method = RequestMethod.GET)
    public ModelAndView getAdicionarDado(){
        List<EmpresaModel> listaEmpresas = empresaService.findAll();


        if(listaEmpresas.size() != 0){ //caso exista alguma empresa registrada
            ModelAndView mv = new ModelAndView("adicionarDado");
            mv.addObject("listaEmpresas", listaEmpresas);
            List<DadoModel> listaDados = dadoService.findAll();
            mv.addObject("listaDados",listaDados);
            Set<String>listaDadosUnicos = new HashSet<>();
            for(DadoModel dado : listaDados){
                listaDadosUnicos.add(dado.getTipo());
            }
            mv.addObject("listaDadosUnicos",listaDadosUnicos);
            return mv;
        }
        ModelAndView mv = new ModelAndView("erroDeLogica");//caso não tenha nenhuma empresa registrada
        String erro  = "Para adicionar um dado, primeiro é necessário adicionar uma empresa!";
        mv.addObject("erro", erro);
        return mv;
    }

    @RequestMapping(value = "/empresas/adicionarDado", method = RequestMethod.POST)
    public ModelAndView postAdicionarDado(@Valid DadoModel dadoModel, BindingResult result, String empresa, String valorDado) {
        ExpressionParser parser = new SpelExpressionParser();
        try {
            float value = parser.parseExpression(valorDado).getValue(Float.class);
            dadoModel.setValor(value);
        }
        catch (Exception e){
            ModelAndView mv = new ModelAndView("erroDeLogica");
            mv.addObject("erro", "Há um erro na sua expressão matemática");
            System.out.println(result);
            return mv;
        }



        if(result.hasErrors()){
                ModelAndView mv = new ModelAndView("erroDeLogica");
                mv.addObject("erro", "Preencha os dados corretamente");
                System.out.println(result);
                return mv;
            }
            List<EmpresaModel> listaEmpresas= empresaService.findAll();
            EmpresaModel emp = null;
            for(EmpresaModel empresaModel : listaEmpresas){
                if(empresaModel.getNome().equals(empresa)){
                    emp = empresaModel;
                }
            }
            dadoModel.setEmpresaModel(emp);

            List<DadoModel>listaDados = dadoService.findByTipo(dadoModel.getTipo());
            boolean dadoJaRegistrado = false;
            for(DadoModel dado : listaDados){
                if(dado.getLabel() == dadoModel.getLabel() && dado.getEmpresaModel().getNome().equals(dadoModel.getEmpresaModel().getNome())){
                    dadoJaRegistrado = true;
                }
            }
            if(dadoJaRegistrado){
                ModelAndView mv = new ModelAndView("erroDeLogica");
                mv.addObject("erro", "Parece que esse dado já foi registrado");
                return mv;
            }

        dadoService.save(dadoModel);
        ModelAndView mv = new ModelAndView("redirect:/empresas");
        return mv;
    }

    @RequestMapping(value = "/empresas/adicionarEmpresa", method = RequestMethod.GET)
    public String getAdicionarEmpresa(){
        return "adicionarEmpresa";
    }

    @RequestMapping(value = "/empresas/adicionarEmpresa", method= RequestMethod.POST)
    public String PostAdicionarEmpresa(@Valid EmpresaModel empresaModel){
        List<EmpresaModel> listaEmpresa = empresaService.findAll();
        Boolean nomeJaRegistrado = false;
        for(EmpresaModel empresa : listaEmpresa){
            if(empresa.getNome().equals(empresaModel.getNome())){
                nomeJaRegistrado = true;
            }
        }

        if(nomeJaRegistrado==false){
            empresaService.save(empresaModel);
            return "redirect:/empresas";
        }
        return "redirect:/empresas/adicionarEmpresa";
    }

    @RequestMapping(value = "/empresas/editarEmpresa", method = RequestMethod.GET)
    public ModelAndView getExcluirEmpresa(){
        List<EmpresaModel> listaEmpresas = empresaService.findAll();
        if(listaEmpresas.size()!=0){
            ModelAndView mv = new ModelAndView("excluirEmpresa");
            mv.addObject("listaEmpresas", listaEmpresas);
            return mv;
        }
        ModelAndView mv = new ModelAndView("erroDeLogica");
        mv.addObject("erro", "para excluir uma empresa, primeiro é necessário ter uma empresa!");
        return mv;
    }

    @RequestMapping(value = "/empresas/editarEmpresa", method = RequestMethod.POST)
    public String postExcluirEmpresa(String empresa){
        List<EmpresaModel> listaEmpresas = empresaService.findAll();
        EmpresaModel empresaModel = null;
        if(empresa != null){
            for(EmpresaModel emp : listaEmpresas){
                if(emp.getNome().equals(empresa)){
                    empresaModel = emp;
                }
            }
            List<DadoModel>listaDados = dadoService.findAll();
            boolean possuiDados = false;
            for(DadoModel dado : listaDados){
                if(empresaModel==dado.getEmpresaModel()){
                    possuiDados = true;
                }
            }
            if(!possuiDados){
                empresaService.DeleteById(empresaModel.getId());
                return "redirect:/empresas";
            }
        }
        return "redirect:/empresas/editarEmpresa";
    }
    @PostMapping("/editarEmpresa")
    public String postEditarEmpresa(String empresa, String nomeNovo){
        empresaService.updateNomeEmpresa(empresa, nomeNovo);
        return "redirect:/empresas";
    }
}
