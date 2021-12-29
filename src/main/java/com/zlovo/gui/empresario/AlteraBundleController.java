package com.zlovo.gui.empresario;
import com.zlovo.bll.empresa.BundleBLL;
import com.zlovo.bll.empresa.EmpresaBLL;
import com.zlovo.bll.empresa.ProdutoBLL;
import com.zlovo.dal.empresa.Bundle;
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

public class AlteraBundleController implements Initializable {
    @FXML
    private TextField nomeTF;
    @FXML
    private TextField precoTF;
    @FXML
    private Label checkDados;
    @FXML
    private Label nomeLabel;
    @FXML
    private Label precoBundleLabel;
    @FXML
    private Label unidadeLabel;
    @FXML
    private Label precoLabel;
    @FXML
    private Label pesoLabel;
    @FXML
    private Label dimensaoLabel;
    @FXML
    private ListView<Produto> produtosList;
    @FXML
    private ListView<Produto> bundleProdutos;

    public void confirmar (ActionEvent event){

        Produto produto = produtosList.getSelectionModel().getSelectedItem();
        Produto bundleProduto = bundleProdutos.getSelectionModel().getSelectedItem();
        Bundle bundle = MenuBundleController.bundleSelecionado;
        if (!nomeTF.getText().isEmpty())
            if(ProdutoBLL.checkProdutoNome(nomeTF.getText(), MenuCategoriasController.categoriaSelecionada)){
                checkDados.setText("Bundle já registado!");
                return;
            }
        if(!nomeTF.getText().isEmpty()) bundle.setNome(nomeTF.getText());
        if(!precoTF.getText().isEmpty()) bundle.setPreco(Double.parseDouble(precoTF.getText()));
        if (BundleBLL.checkProdutoSelecionado(bundle,produto)){
            checkDados.setText("Bundle já possui este produto!");
            return;
        }
        if (produto != null) bundle.getProdutosBundle().add(produto);
        if (bundleProduto != null) bundle.getProdutosBundle().remove(bundleProduto);
        EmpresaBLL.removeProduto(MenuBundleController.bundleSelecionado);
        EmpresaBLL.adicionaProduto(bundle);
        ControladorGlobal.chamaScene("empresario/SceneMenuBundle.fxml", event);
    }

    public void cancelar (ActionEvent event){
        ControladorGlobal.chamaScene("empresario/SceneMenuBundle.fxml", event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nomeLabel.setText("Nome: " + MenuBundleController.bundleSelecionado.getNome());
        precoBundleLabel.setText("Preço: " + MenuBundleController.bundleSelecionado.getPreco());
        produtosList.getItems().addAll(Objects.requireNonNull(ProdutoBLL.getProdutos(MenuCategoriasController.categoriaSelecionada, EmpresaBLL.getEmpresaLog())));
        ProdutoBLL.changeCellValueProdutoNome(produtosList);
        ProdutoBLL.updatePrecoLabel(produtosList,precoLabel);
        ProdutoBLL.updateDimensaoLabel(produtosList,dimensaoLabel);
        ProdutoBLL.updatePesoLabel(produtosList,pesoLabel);
        ProdutoBLL.updateUnidadeLabel(produtosList,unidadeLabel);
        bundleProdutos.getItems().addAll(MenuBundleController.bundleSelecionado.getProdutosBundle());
        ProdutoBLL.changeCellValueProdutoNome(bundleProdutos);
        ProdutoBLL.updatePrecoLabel(bundleProdutos,precoLabel);
        ProdutoBLL.updateDimensaoLabel(bundleProdutos,dimensaoLabel);
        ProdutoBLL.updatePesoLabel(bundleProdutos,pesoLabel);
        ProdutoBLL.updateUnidadeLabel(bundleProdutos,unidadeLabel);
    }
}
