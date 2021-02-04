package com.dataVisualiz.dataVisualiz.controller;


import com.dataVisualiz.dataVisualiz.model.DadoModel;
import com.dataVisualiz.dataVisualiz.model.EmpresaModel;
import com.dataVisualiz.dataVisualiz.service.DadoService;
import com.dataVisualiz.dataVisualiz.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class GraficoController {

    @Autowired
    EmpresaService empresaService;

    @Autowired
    DadoService dadoService;

    @RequestMapping(value = "/grafico", method = RequestMethod.GET)
    public ModelAndView getGrafico(String keyword){

        List<DadoModel> listaDeDados = dadoService.findAll(); //puxar todos os dados

        if(listaDeDados.size() !=0){ //verificar o sistema possui algum dado registrado
            ModelAndView mv = new ModelAndView("grafico");//adicionar a configuração da view que deve ser renderizada
            Set<String> listaDeTipos = new HashSet<>(); //verificar quais são os tipos de dados presentes no sistema
            for(DadoModel dado : listaDeDados){

                listaDeTipos.add(dado.getTipo());

            }
            mv.addObject("listaTipos", listaDeTipos); //passar os tipos de dados para a view

            if(keyword==null){ //verificar se o usuáro está buscando um tipo de dado especifico
                keyword = listaDeTipos.stream().findFirst().get();
            }
            List<DadoModel> dadosList =dadoService.findByTipo(keyword);
            Set<String>listaEmpresas = new HashSet<>();


            for(DadoModel dado: dadosList){
                EmpresaModel empresa = dado.getEmpresaModel();
                listaEmpresas.add(empresa.getNome());
            }

            mv.addObject("listaEmpresas",listaEmpresas);
            mv.addObject("listaDados",dadosList);
            mv.addObject("tituloDoGrafco", keyword);
            return mv;

        }
        ModelAndView mv = new ModelAndView("erroDeLogica");
        String erro = "Parece que o sistema não possui nenum dado registrado ainda :(";
        mv.addObject("erro", erro);
        return mv;


    }
}
