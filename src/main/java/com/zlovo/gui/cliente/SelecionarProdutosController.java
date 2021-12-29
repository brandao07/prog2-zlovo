package com.zlovo.gui.cliente;

import com.zlovo.bll.empresa.ProdutoBLL;
import com.zlovo.dal.empresa.Produto;
import com.zlovo.gui.ControladorGlobal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class SelecionarProdutosController implements Initializable {
    @FXML
    private Label checkDados;
    @FXML
    private Label checkQuantidade;
    @FXML
    private ListView<Produto> produtosList;
    @FXML
    private Label produtoLabel;
    @FXML
    private TextField quantidadeTF;

    public static Produto produtoselecionado;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        produtosList.getItems().addAll(Objects.requireNonNull(ProdutoBLL.getProduto(SelecionarCategoriaController.categoriaSelecionada, SelecionarEmpresaController.empresaSelecionada)));
        // ver melhor pois podem existir
        ProdutoBLL.changeCellValueProdutoNome(produtosList);
    }

    public void seguinte(ActionEvent event){
        produtoselecionado = produtosList.getSelectionModel().getSelectedItem();
        if (produtoselecionado == null) {
            checkDados.setText("Selecione um produto!");
            return;
        }
        if(!quantidadeTF.getText().isEmpty()){
            checkDados.setText("Insira uma quantidade!");
            return;
        }
        ControladorGlobal.chamaScene("cliente/SceneSelecionarHorario.fxml", event);
    }

    public void anterior(ActionEvent event){
        SelecionarCategoriaController.categoriaSelecionada = null;
        ControladorGlobal.chamaScene("cliente/SceneSelecionarCategoria.fxml", event);

    }
}
