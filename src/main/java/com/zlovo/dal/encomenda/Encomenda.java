package com.zlovo.dal.encomenda;

import com.zlovo.dal.empresa.Produto;
import com.zlovo.dal.encomenda.enumerations.TipoHorario;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Encomenda implements Serializable {
    private final ArrayList<Produto> produtosList;
    //    Atributos
    private int id;
    private int idEmpresa;
    private double preco;
    private DetalhesEncomenda detalhes;
    private Pagamento pagamento;
    private LocalDate dataCliente;
    private TipoHorario horario;

    //    Construtor
    public Encomenda(int idEmpresa, String cliente) {
        this.idEmpresa = idEmpresa;
        pagamento = new Pagamento();
        detalhes = new DetalhesEncomenda(cliente);
        produtosList = new ArrayList<>();
    }

    //    Getters & Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public DetalhesEncomenda getDetalhes() {
        return detalhes;
    }

    public void setDetalhes(DetalhesEncomenda detalhes) {
        this.detalhes = detalhes;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    public LocalDate getDataCliente() {
        return dataCliente;
    }

    public void setDataCliente(LocalDate dataCliente) {
        this.dataCliente = dataCliente;
    }

    public TipoHorario getHorario() {
        return horario;
    }

    public void setHorario(TipoHorario horario) {
        this.horario = horario;
    }

    public ArrayList<Produto> getProdutosList() {
        return produtosList;
    }
}

