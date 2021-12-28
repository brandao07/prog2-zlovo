package com.zlovo.dal.utilizador;

import com.zlovo.dal.empresa.Produto;
import com.zlovo.dal.encomenda.Encomenda;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Cliente extends Utilizador implements Serializable{
//    Atributos
    private final ArrayList<Encomenda> historial;
    private double saldo;

//    Construtor
    public Cliente() {
        saldo = 0;
        historial = new ArrayList<>();
    }

//    Getters & Setters
    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public ArrayList<Encomenda> getHistorial() {
        return historial;
    }
}
