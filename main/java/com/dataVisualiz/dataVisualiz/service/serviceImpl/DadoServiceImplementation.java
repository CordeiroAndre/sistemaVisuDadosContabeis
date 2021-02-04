package com.dataVisualiz.dataVisualiz.service.serviceImpl;

import com.dataVisualiz.dataVisualiz.model.DadoModel;
import com.dataVisualiz.dataVisualiz.model.EmpresaModel;
import com.dataVisualiz.dataVisualiz.repository.DadoRepository;
import com.dataVisualiz.dataVisualiz.repository.EmpresaRepository;
import com.dataVisualiz.dataVisualiz.service.DadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DadoServiceImplementation implements DadoService {

    @Autowired
    DadoRepository dadoRepository;

    @Override
    public List<DadoModel> findAll() {
        return dadoRepository.findAll();
    }

    @Override
    public DadoModel findById(long Id) {
        return dadoRepository.findById(Id).get();
    }

    @Override
    public DadoModel save(DadoModel dado) {
        return dadoRepository.save(dado);
    }

    @Override
    public void deleteById(long Id) {
        dadoRepository.deleteById(Id);
    }

    @Override
    public Iterable<EmpresaModel> findByEmpresaModel (EmpresaModel empresaModel){
       return dadoRepository.findByEmpresaModel(empresaModel);
    }

    @Override
    public List<DadoModel> findByKeyword(String keyword){
        return dadoRepository.findByKeyword(keyword);
    }

    @Override
    public List<DadoModel> findByTipo(String tipo) {
        return dadoRepository.findByTipo(tipo);
    }
}
