package com.zlovo.bll.empresa;

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

public class BundleBLL {
    public static @NotNull ArrayList<Bundle> getBundles(@NotNull Empresa empresa, String categoria){
        ArrayList<Bundle> bundles = new ArrayList<>();
        for (Produto p : empresa.getProdutosMap().get(categoria))
            if (p instanceof Bundle)
                bundles.add((Bundle) p);
        return bundles;
    }

    public static int quantidadeProdutosBundle(@NotNull Bundle bundle){
        return bundle.getProdutosBundle().size();
    }

    public static void changeCellValueBundleNome (@NotNull ListView<Bundle> myListView){
        myListView.setCellFactory(new Callback<>() {
            public ListCell<Bundle> call(ListView<Bundle> param) {
                return new ListCell<>() {
                    @Override
                    public void updateItem(Bundle item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            setText(item.getNome());
                        }
                    }
                };
            }
        });
    }

    public static void updatePrecoLabel(@NotNull ListView<Bundle> myListView, Label myLabel){
        myListView.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            String dados = String.valueOf(myListView.getSelectionModel().getSelectedItem().getPreco());
            myLabel.setText("Preço: " + dados);
        });
    }

    public static void updateQuantidadeLabel(@NotNull ListView<Bundle> myListView, Label myLabel){
        myListView.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            String dados = String.valueOf(quantidadeProdutosBundle(myListView.getSelectionModel().getSelectedItem()));
            myLabel.setText("Quantidade de Produtos: " + dados);
        });
    }

    public static boolean checkProdutoSelecionado(@NotNull Bundle bundle, Produto produto){
        return bundle.getProdutosBundle().contains(produto);
    }
}
