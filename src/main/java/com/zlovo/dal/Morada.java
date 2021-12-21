package com.zlovo.dal;

import java.io.Serializable;

public class Morada implements Serializable {
    // Atributos

    private String rua;
    private int nPorta;
    private String localidade;

    //Construtor

    public Morada(){}

    // Getters & Setters

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getnPorta() {
        return nPorta;
    }

    public void setnPorta(int nPorta) {
        this.nPorta = nPorta;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }
}
