package com.zlovo.dal.utilizador;

import com.zlovo.dal.encomenda.Trabalho;

import java.io.Serializable;
import java.util.ArrayList;

public class Motard extends Funcionario implements Serializable {

    private final ArrayList<Trabalho> historial;
    //    Atributos
    private boolean disponivel;

    //    Construtor
    public Motard() {
        historial = new ArrayList<>();
        disponivel = true;
    }

//    Getters & Setters

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public ArrayList<Trabalho> getHistorial() {
        return historial;
    }

}

