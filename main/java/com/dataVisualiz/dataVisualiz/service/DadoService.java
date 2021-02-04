package com.dataVisualiz.dataVisualiz.service;

import com.dataVisualiz.dataVisualiz.model.DadoModel;
import com.dataVisualiz.dataVisualiz.model.EmpresaModel;
import com.dataVisualiz.dataVisualiz.repository.EmpresaRepository;

import java.util.List;

public interface DadoService {

    List<DadoModel> findAll();

    DadoModel findById(long Id);

    DadoModel save (DadoModel dado);

    void deleteById(long Id);

    Iterable<EmpresaModel> findByEmpresaModel(EmpresaModel empresaModel);

    List<DadoModel> findByKeyword (String keyword);

    List <DadoModel> findByTipo(String tipo);
}
