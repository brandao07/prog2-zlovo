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

    public static @NotNull ArrayList<Produto> getProdutos(String categoria){
        ArrayList<Produto> produtos = new ArrayList<>();
        for (String key : EmpresaBLL.getEmpresaLog().getProdutosMap().keySet())
            if (key.equals(categoria))
                for (Produto p : EmpresaBLL.getEmpresaLog().getProdutosMap().get(key))
                    if (!(p instanceof Bundle))
                        produtos.add(p);
        return produtos;
    }

    public static void changeCellValueProdutoNome (@NotNull ListView<Produto> myListView){
        myListView.setCellFactory(new Callback<>() {
            public ListCell<Produto> call(ListView<Produto> param) {
                return new ListCell<>() {
                    @Override
                    public void updateItem(Produto item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            setText(item.getNome());
                        }
                    }
                };
            }
        });
    }

//    public static void updateDadosLabel(@NotNull ListView<Produto> myListView, Label myLabel){
//        myListView.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
//            String dados = myListView.getSelectionModel().getSelectedItem().;
//            myLabel.setText(dados);
//        });
//    }

    public static void updateCategoriaLabel(@NotNull ListView<String> myListView, Label myLabel){
        myListView.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            String dados = myListView.getSelectionModel().getSelectedItem();
            myLabel.setText(dados);
        });
    }

    public static void updateQuantidadeLabel(@NotNull ListView<String> myListView, Label myLabel){
        myListView.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            int dados = EmpresaBLL.quantidadeProdutosCategoria(myListView.getSelectionModel().getSelectedItem());
            myLabel.setText(String.valueOf("Quantidade de Produtos: " + dados));
        });
    }
    // Método que cria produto
    public static void criarProduto(@NotNull Produto produto) {
        produto.setId(Repositorio.getRepositorio().getNumProdutos() + 1);
        Repositorio.getRepositorio().setNumProdutos(produto.getId());
        produto.setIdEmpresa(EmpresaBLL.getEmpresaLog().getId());
        EmpresaBLL.adicionaProduto(produto);
    }

    public static boolean checkProdutoNome(String nome){
        boolean checker = false;
        for (String key : Repositorio.getRepositorio().getLocalidadesEmpresasMap().keySet())
            for (Empresa keyProduto : Repositorio.getRepositorio().getLocalidadesEmpresasMap().get(key))
                if (keyProduto.getNome().equals(nome)) {
                    checker = true;
                    break;
                }
        return checker;
    }

}
