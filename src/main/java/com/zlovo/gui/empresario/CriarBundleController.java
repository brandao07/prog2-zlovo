package com.zlovo.gui.empresario;

import com.zlovo.bll.empresa.EmpresaBLL;
import com.zlovo.bll.empresa.ProdutoBLL;
import com.zlovo.dal.empresa.Bundle;
import com.zlovo.dal.empresa.Produto;
import com.zlovo.dal.empresa.enumerations.TipoUnidade;
import com.zlovo.gui.ControladorGlobal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class CriarBundleController implements Initializable {
    @FXML
    private ListView<Produto> produtosList;
    @FXML
    private TextField nomeTF;
    @FXML
    private TextField precoTF;
    @FXML
    private Label unidadeLabel;
    @FXML
    private Label precoLabel;
    @FXML
    private Label pesoLabel;
    @FXML
    private Label dimensaoLabel;
    @FXML
    private Label checkDados;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        produtosList.getItems().addAll(Objects.requireNonNull(ProdutoBLL.getProdutos(MenuCategoriasController.categoriaSelecionada)));
        ProdutoBLL.changeCellValueProdutoNome(produtosList);
        ProdutoBLL.updatePrecoLabel(produtosList,precoLabel);
        ProdutoBLL.updateDimensaoLabel(produtosList,dimensaoLabel);
        ProdutoBLL.updatePesoLabel(produtosList,pesoLabel);
        ProdutoBLL.updateUnidadeLabel(produtosList,unidadeLabel);
    }

    public void criar(ActionEvent event){
        if (nomeTF.getText().isEmpty() & precoTF.getText().isEmpty()){
            checkDados.setText("Campos Inválidos!");
            return;
        }
        if (produtosList.getSelectionModel().getSelectedItem() == null){
            checkDados.setText("Selecione 1 produto");
            return;
        }
        if(ProdutoBLL.checkProdutoNome(nomeTF.getText(), MenuCategoriasController.categoriaSelecionada)){
            checkDados.setText("Bundle já registado!");
            return;
        }
        Produto produtoSelecionado = produtosList.getSelectionModel().getSelectedItem();
        Bundle bundle = new Bundle();
        bundle.setNome(nomeTF.getText());
        bundle.setPreco(Double.parseDouble(precoTF.getText()));
        bundle.setCategoria(MenuCategoriasController.categoriaSelecionada);
        bundle.setUnidade(TipoUnidade.UNIT);
        bundle.getProdutosBundle().add(produtoSelecionado);
        ProdutoBLL.criarProduto(bundle);
        ControladorGlobal.chamaScene("empresario/SceneMenuBundle.fxml", event);
    }

    public void cancelar(ActionEvent event){
        ControladorGlobal.chamaScene("empresario/SceneMenuBundle.fxml", event);
    }
}
