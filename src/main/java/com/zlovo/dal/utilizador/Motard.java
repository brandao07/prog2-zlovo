package com.zlovo.dal.utilizador;

import com.zlovo.dal.encomenda.Trabalho;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Motard extends Funcionario implements Serializable {

//    Atributos
    private boolean disponivel;
    private ArrayList<Trabalho> historial;

//    Construtor
    public Motard(){
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

    public void setHistorial(ArrayList<Trabalho> historial) {
        this.historial = historial;
    }
}

