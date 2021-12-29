package com.zlovo.bll;

import com.zlovo.dal.Repositorio;
import com.zlovo.dal.empresa.Produto;
import com.zlovo.dal.encomenda.Encomenda;
import com.zlovo.dal.encomenda.enumerations.TipoEstado;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class EncomendaBLL {

    public static boolean checkProduto(@NotNull Encomenda encomenda, Produto produto){
        for (Produto p : encomenda.getProdutosList())
            if(p.getId() == produto.getId())
                return true;
        return false;
    }

    public static void updatePrecoLabel(@NotNull ListView<Produto> myListView, Label myLabel){
        myListView.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            double dados = myListView.getSelectionModel().getSelectedItem().getPreco();
            myLabel.setText("Preço: " + dados * myListView.getSelectionModel().getSelectedItem().getQuantidade());
        });
    }

    public static void updateQuantidadeLabel(@NotNull ListView<Produto> myListView, Label myLabel){
        myListView.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            int dados = myListView.getSelectionModel().getSelectedItem().getQuantidade();
            myLabel.setText("Quantidade: " + dados);
        });
    }

    public static void updateCategoriaLabel(@NotNull ListView<Produto> myListView, Label myLabel){
        myListView.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            String dados = myListView.getSelectionModel().getSelectedItem().getCategoria();
            myLabel.setText("Categoria: " + dados);
        });
    }

    public static double somaPrecoProdutosCarrinho(@NotNull ArrayList<Produto> carrinho){
        double result = 0;
        for (Produto p : carrinho)
            result += (p.getPreco() * p.getQuantidade());
        return result;
    }

    public static void criarEncomenda(@NotNull Encomenda encomenda){
        encomenda.setId(Repositorio.getRepositorio().getNumEncomendas() + 1);
        Repositorio.getRepositorio().getEncomendasMap().put(encomenda.getId(),encomenda);
        Repositorio.getRepositorio().setNumEncomendas(encomenda.getId());
    }

    public static @NotNull ArrayList<Encomenda> getPorConfirmarEncomendas(){
        ArrayList<Encomenda> encomendas = new ArrayList<>();
        for (int key : Repositorio.getRepositorio().getEncomendasMap().keySet())
            if (Repositorio.getRepositorio().getEncomendasMap().get(key).getDetalhes().getTipoEstado().equals(TipoEstado.AGUARDAR))
                encomendas.add(Repositorio.getRepositorio().getEncomendasMap().get(key));
        return encomendas;
    }
}
