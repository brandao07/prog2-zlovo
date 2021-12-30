package com.zlovo.dal.encomenda;

import com.zlovo.dal.encomenda.enumerations.TipoPagamento;

import java.io.Serializable;

public class Pagamento implements Serializable {
//    Atributos
    private TipoPagamento tipo;
    private boolean estado;

//    Construtor
    public Pagamento() {
        estado = false;
        tipo = null;
    }

//    Getters & Setters
    public TipoPagamento getTipo() {
        return tipo;
    }

    public void setTipo(TipoPagamento tipo) {
        this.tipo = tipo;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
