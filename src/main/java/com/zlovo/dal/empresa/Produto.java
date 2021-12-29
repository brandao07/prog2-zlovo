package com.zlovo.dal.empresa;

import com.zlovo.dal.empresa.enumerations.TipoUnidade;

import java.io.Serializable;

public class Produto implements Serializable {
//    Atributos
    private String nome;
    private String categoria;
    private int idEmpresa;
    private int id;
    private double preco;
    private double dimensao;
    private double peso;
    private TipoUnidade unidade;
    private int quantidade = 1;

//    Construtor
    public Produto(){}

    //TODO: Falta associar isto
    public Produto(String nome, String categoria, int idEmpresa, int id, double preco, double dimensao, double peso, TipoUnidade unidade, int quantidade) {
        this.nome = nome;
        this.categoria = categoria;
        this.idEmpresa = idEmpresa;
        this.id = id;
        this.preco = preco;
        this.dimensao = dimensao;
        this.peso = peso;
        this.unidade = unidade;
        this.quantidade = quantidade;
    }

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

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
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

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
