package com.dataVisualiz.dataVisualiz.repository;

import com.dataVisualiz.dataVisualiz.model.EmpresaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface EmpresaRepository extends JpaRepository<EmpresaModel, Long> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update EmpresaModel empresa set empresa.nome = ?2 where empresa.nome = ?1")
    int updateNomeEmpresa( String keyword1, String keyword2);
}
