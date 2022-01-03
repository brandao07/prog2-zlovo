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
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class SelecionarProdutosController implements Initializable {
    public static Produto produtoSelecionado;
    @FXML
    private Label checkDados;
    @FXML
    private ListView<Produto> produtosList;
    @FXML
    private Spinner<Integer> quantidadeSpinner = new Spinner<>();
    @FXML
    private Label precoLabel;
    @FXML
    private Label dimensaoLabel;
    @FXML
    private Label pesoLabel;
    @FXML
    private Label unidadeLabel;
    @FXML
    private Label checkLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        produtosList.getItems().addAll(Objects.requireNonNull(ProdutoBLL.getProdutos(SelecionarCategoriaController.categoriaSelecionada, SelecionarEmpresaController.empresaSelecionada)));
        ProdutoBLL.changeCellValueProdutoNome(produtosList);
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10);
        valueFactory.setValue(1);
        quantidadeSpinner.setValueFactory(valueFactory);
        ProdutoBLL.changeCellValueProdutoNome(produtosList);
        ProdutoBLL.updatePrecoLabel(produtosList, precoLabel);
        ProdutoBLL.updateDimensaoLabel(produtosList, dimensaoLabel);
        ProdutoBLL.updatePesoLabel(produtosList, pesoLabel);
        ProdutoBLL.updateUnidadeLabel(produtosList, unidadeLabel);
    }

    public void adicionarCarrinho() {
        produtoSelecionado = produtosList.getSelectionModel().getSelectedItem();
        if (produtoSelecionado == null) {
            checkDados.setText("Selecione um produto!");
            return;
        }
        //  produtoSelecionado.setQuantidade(quantidadeSpinner.getValue());
        if (EncomendaBLL.checkProduto(SelecionarEmpresaController.encomenda, produtoSelecionado)) {
            checkDados.setText("Produto já no carrinho!");
            return;
        }
        Produto produto = new Produto(produtoSelecionado.getNome(), produtoSelecionado.getCategoria(), produtoSelecionado.getIdEmpresa(), produtoSelecionado.getId(), produtoSelecionado.getPreco(), produtoSelecionado.getDimensao(), produtoSelecionado.getPeso(), produtoSelecionado.getUnidade(), quantidadeSpinner.getValue());
        SelecionarEmpresaController.encomenda.getProdutosList().add(produto);
        checkLabel.setText("Produto adicionado com sucesso! :)");
    }

    public void anterior(ActionEvent event) {
        SelecionarCategoriaController.categoriaSelecionada = null;
        ControladorGlobal.chamaScene("cliente/SceneSelecionarCategoria.fxml", event);
    }

    public void carrinho(ActionEvent event) {
        ControladorGlobal.chamaScene("cliente/SceneCarrinho.fxml", event);
    }
}
