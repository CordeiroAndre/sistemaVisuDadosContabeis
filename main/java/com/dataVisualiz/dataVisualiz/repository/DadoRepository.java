package com.dataVisualiz.dataVisualiz.repository;

import com.dataVisualiz.dataVisualiz.model.DadoModel;
import com.dataVisualiz.dataVisualiz.model.EmpresaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DadoRepository extends JpaRepository<DadoModel, Long> {
    Iterable<EmpresaModel> findByEmpresaModel(EmpresaModel empresa);

    @Query(value = "select * from  TB_DADO dado where dado.tipo like %:keyword%", nativeQuery = true)
    List<DadoModel> findByKeyword(@Param("keyword") String keyword);

        List<DadoModel> findByTipo(String tipo);

}
