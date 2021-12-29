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
    private Date dataPedido;
    private Date dataSaida;
    private Date dataEntrega;

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

    public Date getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(Date dataPedido) {
        this.dataPedido = dataPedido;
    }

    public Date getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(Date dataSaida) {
        this.dataSaida = dataSaida;
    }

    public Date getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(Date dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public int getIdMotard() {
        return idMotard;
    }

    public void setIdMotard(int idMotard) {
        this.idMotard = idMotard;
    }
}
