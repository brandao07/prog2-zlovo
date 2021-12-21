package com.zlovo.dal.empresa;

import com.zlovo.dal.empresa.enumerations.TipoUnidade;

import java.io.Serializable;

public class Produto implements Serializable {
//    Atributos
    private String nome;
    private int idEmpresa;
    private int id;
    private double preco;
    private double dimensao;
    private double peso;
    private TipoUnidade unidade;

//    Construtor
    public Produto(){}

//    Getters & Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public TipoUnidade getUnidade() {
        return unidade;
    }

    public void setUnidade(TipoUnidade unidade) {
        this.unidade = unidade;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public double getDimensao() {
        return dimensao;
    }

    public void setDimensao(double dimensao) {
        this.dimensao = dimensao;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }
}
