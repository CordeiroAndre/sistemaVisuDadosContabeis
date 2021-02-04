package com.dataVisualiz.dataVisualiz.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "TB_DADO")
public class DadoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private float valor;

    private int label;

    private String tipo;

    @ManyToOne
    private EmpresaModel empresaModel;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public int getLabel() {
        return label;
    }

    public void setLabel(int label) {
        this.label = label;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public EmpresaModel getEmpresaModel() {
        return empresaModel;
    }

    public void setEmpresaModel(EmpresaModel empresaModel) {
        this.empresaModel = empresaModel;
    }
}
