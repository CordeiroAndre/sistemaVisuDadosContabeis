package com.dataVisualiz.dataVisualiz.service;

import com.dataVisualiz.dataVisualiz.model.EmpresaModel;

import java.util.List;

public interface EmpresaService {

    List<EmpresaModel> findAll();

    EmpresaModel findById(long Id);

    EmpresaModel save(EmpresaModel empresaModel);

    void DeleteById (long Id);

    int updateNomeEmpresa(String keyword1, String keyword2);
}
