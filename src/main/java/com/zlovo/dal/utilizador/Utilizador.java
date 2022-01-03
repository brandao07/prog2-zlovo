package com.zlovo.dal.utilizador;

import com.zlovo.dal.Morada;

import java.io.Serializable;
import java.util.Date;

public abstract class Utilizador implements Serializable {
    //    Atributos
    private int idUtilizador;
    private String username;
    private String password;
    private String nome;
    private String numCC;
    private String nif;
    private String telefone;
    private Morada morada;
    private boolean loginStatus;
    private Date data;

    //    Construtor
    public Utilizador() {
        data = new Date();
        loginStatus = false;
    }

    //    Getters & Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNumCC() {
        return numCC;
    }

    public void setNumCC(String numCC) {
        this.numCC = numCC;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Morada getMorada() {
        return morada;
    }

    public void setMorada(Morada morada) {
        this.morada = morada;
    }

    public boolean isLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(boolean loginStatus) {
        this.loginStatus = loginStatus;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getIdUtilizador() {
        return idUtilizador;
    }

    public void setIdUtilizador(int idUtilizador) {
        this.idUtilizador = idUtilizador;
    }


}
