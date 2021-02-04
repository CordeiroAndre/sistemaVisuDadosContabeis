package com.dataVisualiz.dataVisualiz.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "TB_EMPRESA")
public class EmpresaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank
    private String nome;

    @OneToMany
    List<DadoModel> dados;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<DadoModel> getDados() {
        return dados;
    }

    public void setDados(List<DadoModel> dados) {
        this.dados = dados;
    }
}
