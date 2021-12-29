package com.zlovo.dal.encomenda;

import com.zlovo.dal.encomenda.enumerations.TipoEstado;
import com.zlovo.dal.encomenda.enumerations.TipoEstadoEntrega;

import java.io.Serializable;
import java.util.Date;

public class DetalhesEncomenda implements Serializable {
//    Atributos
    private int idMotard;
    private String cliente;
    private TipoEstadoEntrega estadoEntrega;
    private TipoEstado tipoEstado;

//    Construtor
    public DetalhesEncomenda(String cliente){
        this.cliente = cliente;
    }

//    Getters & Setters
    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public TipoEstadoEntrega getEstadoEntrega() {
        return estadoEntrega;
    }

    public void setEstadoEntrega(TipoEstadoEntrega estadoEntrega) {
        this.estadoEntrega = estadoEntrega;
    }

    public TipoEstado getTipoEstado() {
        return tipoEstado;
    }

    public void setTipoEstado(TipoEstado tipoEstado) {
        this.tipoEstado = tipoEstado;
    }

    public int getIdMotard() {
        return idMotard;
    }

    public void setIdMotard(int idMotard) {
        this.idMotard = idMotard;
    }
}
