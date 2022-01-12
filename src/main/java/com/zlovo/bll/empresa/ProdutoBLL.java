package com.zlovo.bll.empresa;

import com.zlovo.dal.Repositorio;
import com.zlovo.dal.empresa.Bundle;
import com.zlovo.dal.empresa.Empresa;
import com.zlovo.dal.empresa.Produto;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class ProdutoBLL {

    public static @NotNull ArrayList<Produto> getProdutos(String categoria, @NotNull Empresa empresa) {
        ArrayList<Produto> produtos = new ArrayList<>();
        for (String key : empresa.getProdutosMap().keySet())
            if (key.equals(categoria))
                for (Produto p : empresa.getProdutosMap().get(key))
                    if (!(p instanceof Bundle))
                        produtos.add(p);
        return produtos;
    }

    public static void changeCellValueProdutoNome(@NotNull ListView<Produto> myListView) {
        myListView.setCellFactory(new Callback<>() {
            public ListCell<Produto> call(ListView<Produto> param) {
                return new ListCell<>() {
                    @Override
                    public void updateItem(Produto item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) setText(item.getNome());
                    }
                };
            }
        });
    }

    public static void updatePrecoLabel(@NotNull ListView<Produto> myListView, Label myLabel) {
        myListView.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            double dados = myListView.getSelectionModel().getSelectedItem().getPreco();
            myLabel.setText("Preço: " + dados);
        });
    }

    public static void updateDimensaoLabel(@NotNull ListView<Produto> myListView, Label myLabel) {
        myListView.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            String dados = String.valueOf(myListView.getSelectionModel().getSelectedItem().getDimensao());
            myLabel.setText("Dimensão: " + dados);
        });
    }

    public static void updatePesoLabel(@NotNull ListView<Produto> myListView, Label myLabel) {
        myListView.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            String dados = String.valueOf(myListView.getSelectionModel().getSelectedItem().getPeso());
            myLabel.setText("Peso: " + dados);
        });
    }

    public static void updateUnidadeLabel(@NotNull ListView<Produto> myListView, Label myLabel) {
        myListView.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            String dados = String.valueOf(myListView.getSelectionModel().getSelectedItem().getUnidade());
            myLabel.setText("Unidade: " + dados);
        });
    }

    public static void updateCategoriaLabel(@NotNull ListView<String> myListView, Label myLabel) {
        myListView.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            String dados = myListView.getSelectionModel().getSelectedItem();
            myLabel.setText(dados);
        });
    }

    public static void updateQuantidadeLabel(@NotNull ListView<String> myListView, Label myLabel) {
        myListView.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            int dados = EmpresaBLL.quantidadeProdutosCategoria(EmpresaBLL.getEmpresaLog(), myListView.getSelectionModel().getSelectedItem());
            myLabel.setText("Quantidade de Produtos: " + dados);
        });
    }

    // Método que cria produto
    public static void criarProduto(@NotNull Produto produto) {
        produto.setId(Repositorio.getRepositorio().getNumProdutos() + 1);
        Repositorio.getRepositorio().setNumProdutos(produto.getId());
        produto.setIdEmpresa(EmpresaBLL.getEmpresaLog().getId());
        EmpresaBLL.adicionaProduto(produto);
        Repositorio.getRepositorio().setNumProdutosChart(Repositorio.getRepositorio().getNumProdutosChart() + 1);
    }

    public static boolean checkProdutoNome(String nome, String categoria) {
        for (String key : Repositorio.getRepositorio().getLocalidadesEmpresasMap().keySet())
            for (Empresa e : Repositorio.getRepositorio().getLocalidadesEmpresasMap().get(key)) {
                if (!(e.getProdutosMap().containsKey(categoria))) return false;
                for (Produto p : e.getProdutosMap().get(categoria))
                    if (p.getNome().equals(nome)) return true;
            }
        return false;
    }

    public static @Nullable Produto getProduto(String categoria, @NotNull Empresa empresa, Produto produto) {
        for (Produto p : empresa.getProdutosMap().get(categoria))
            if (produto.getNome().equals(p.getNome()))
                return p;
        return null;
    }
}
