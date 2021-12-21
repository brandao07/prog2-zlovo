package com.zlovo.dal.encomenda;

import com.zlovo.dal.empresa.Produto;
import com.zlovo.dal.encomenda.enumerations.TipoHorario;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Encomenda implements Serializable {
    //    Atributos
    private int id;
    private double preco;
    private int quantidade;
    private DetalhesEncomenda detalhes;
    private Pagamento pagamento;
    private Date dataCliente;
    private TipoHorario horario;
    private final List<Produto> produtosList;


    //    Construtor
    public Encomenda() {
        pagamento = new Pagamento();
        detalhes = new DetalhesEncomenda();
        produtosList = new LinkedList<>();
    }

    //    Getters & Setters
    public int getId() {
        return id;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
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

    public Date getDataCliente() {
        return dataCliente;
    }

    public void setDataCliente(Date dataCliente) {
        this.dataCliente = dataCliente;
    }

    public TipoHorario getHorario() {
        return horario;
    }

    public void setHorario(TipoHorario horario) {
        this.horario = horario;
    }

    public List<Produto> getProdutosList() {
        return produtosList;
    }
}

