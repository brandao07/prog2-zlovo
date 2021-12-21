package com.zlovo.dal.utilizador;

import com.zlovo.dal.encomenda.Encomenda;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class Cliente extends Utilizador implements Serializable{
//    Atributos
    private final List<Encomenda> historial;
    private double saldo;

//    Construtor
    public Cliente() {
        saldo = 0;
        historial = new LinkedList<>();
    }

//    Getters & Setters
    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public List<Encomenda> getHistorial() {
        return historial;
    }
}
