package com.zlovo.dal.empresa;

import com.zlovo.dal.Morada;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Empresa implements Serializable {
    private final Map<String, ArrayList<Produto>> produtosMap; // K - categoria, V - Lista de Produtos
    //    Atributos
    private int id;
    private String empresarioID;
    private String nome;
    private Morada morada;
    private String telefone;

    //    Construtor
    public Empresa() {
        produtosMap = new HashMap<>();
    }

    //    Getters & Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmpresarioID() {
        return empresarioID;
    }

    public void setEmpresarioID(String empresarioID) {
        this.empresarioID = empresarioID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Morada getMorada() {
        return morada;
    }

    public void setMorada(Morada morada) {
        this.morada = morada;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Map<String, ArrayList<Produto>> getProdutosMap() {
        return produtosMap;
    }

}
