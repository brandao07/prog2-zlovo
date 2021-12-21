package com.zlovo.dal.encomenda;

import java.io.Serializable;

public class Trabalho implements Serializable {
//      Atributos
    private final Encomenda encomenda;
    private boolean estado;
    private String descricao;

//    Construtor
    public Trabalho(Encomenda encomenda){
        this.encomenda = encomenda;
        estado = false;
        descricao = null;
    }

//    Getters & Setters
    public Encomenda getEncomenda() {
        return encomenda;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
