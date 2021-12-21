package com.zlovo.dal.utilizador;

import com.zlovo.dal.encomenda.Trabalho;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class Motard extends Funcionario implements Serializable {

//    Atributos
    private boolean disponivel;
    private List<Trabalho> historial;

//    Construtor
    public Motard(){
        historial = new LinkedList<>();
        disponivel = false;
    }

//    Getters & Setters

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public List<Trabalho> getHistorial() {
        return historial;
    }

    public void setHistorial(List<Trabalho> historial) {
        this.historial = historial;
    }
}

