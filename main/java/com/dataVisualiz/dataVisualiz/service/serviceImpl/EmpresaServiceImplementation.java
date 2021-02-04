package com.dataVisualiz.dataVisualiz.service.serviceImpl;

import com.dataVisualiz.dataVisualiz.model.EmpresaModel;
import com.dataVisualiz.dataVisualiz.repository.EmpresaRepository;
import com.dataVisualiz.dataVisualiz.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpresaServiceImplementation implements EmpresaService {

    @Autowired
    EmpresaRepository empresaRepository;

    @Override
    public List<EmpresaModel> findAll() {
       return empresaRepository.findAll();
    }

    @Override
    public EmpresaModel findById(long Id) {
        return empresaRepository.findById(Id).get();
    }

    @Override
    public EmpresaModel save(EmpresaModel empresaModel) {
        return empresaRepository.save(empresaModel);
    }

    @Override
    public void DeleteById(long Id) {
        empresaRepository.deleteById(Id);
    }

    @Override
    public int updateNomeEmpresa(String antigoNome, String novoNome){
        try {
           return empresaRepository.updateNomeEmpresa(antigoNome, novoNome);
        }
        catch (Exception e){
            System.out.println(e);
        }
        return 0;
    }

}
