package com.zlovo.gui.cliente;

import com.zlovo.bll.EncomendaBLL;
import com.zlovo.bll.empresa.ProdutoBLL;
import com.zlovo.dal.empresa.Produto;
import com.zlovo.gui.ControladorGlobal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class VerProdutosController implements Initializable {
    @FXML
    private ListView<Produto> produtosList;
    @FXML
    private Label precoLabel;
    @FXML
    private Label quantidadeLabel;
    @FXML
    private Label categoriaLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        produtosList.getItems().addAll(EncomendasPorPagarController.encomendaSelecionada.getProdutosList());
        ProdutoBLL.changeCellValueProdutoNome(produtosList);
        EncomendaBLL.updatePrecoLabel(produtosList, precoLabel);
        EncomendaBLL.updateQuantidadeLabel(produtosList, quantidadeLabel);
        EncomendaBLL.updateCategoriaLabel(produtosList, categoriaLabel);
    }

    public void anterior(ActionEvent event) {
        ControladorGlobal.chamaScene("cliente/SceneEncomendasPorPagar.fxml", event);
    }
}
