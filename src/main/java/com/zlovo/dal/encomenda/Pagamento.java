package com.zlovo.dal.encomenda;

import com.zlovo.dal.encomenda.enumerations.TipoPagamento;

import java.io.Serializable;

public class Pagamento implements Serializable {
//    Atributos
    private TipoPagamento tipo;
    private double valor;
    private boolean estado;

//    Construtor
    public Pagamento() {
        valor = 0;
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

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
