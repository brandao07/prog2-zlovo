package com.zlovo.gui.cliente;

import com.dlsc.formsfx.model.validators.SelectionLengthValidator;
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

public class CarrinhoController implements Initializable {
    @FXML
    private ListView<Produto> produtosCarrinho;
    @FXML
    private Label quantidadeLabel;
    @FXML
    private Label precoLabel;
    @FXML
    private Label categoriaLabel;
    @FXML
    private Label precoFinalLabel;
    @FXML
    private Label carrinhoVazioLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        produtosCarrinho.getItems().addAll(SelecionarEmpresaController.encomenda.getProdutosList());
        ProdutoBLL.changeCellValueProdutoNome(produtosCarrinho);
        if (SelecionarEmpresaController.encomenda.getProdutosList().isEmpty()){
            carrinhoVazioLabel.setText("Carrinho vazio!");
            return;
        }
        EncomendaBLL.updatePrecoLabel(produtosCarrinho, precoLabel);
        EncomendaBLL.updateQuantidadeLabel(produtosCarrinho,quantidadeLabel);
        EncomendaBLL.updateCategoriaLabel(produtosCarrinho,categoriaLabel);
        precoFinalLabel.setText("Preço final: " + EncomendaBLL.somaPrecoProdutosCarrinho(SelecionarEmpresaController.encomenda.getProdutosList()));
    }

    public void anterior(ActionEvent event) {
        ControladorGlobal.chamaScene("cliente/SceneSelecionarCategoria.fxml", event);
    }

    public void seguinte(ActionEvent event) {
        SelecionarEmpresaController.encomenda.setPreco(EncomendaBLL.somaPrecoProdutosCarrinho(SelecionarEmpresaController.encomenda.getProdutosList()));
        ControladorGlobal.chamaScene("cliente/SceneSelecionarHorario.fxml", event);
    }
}
