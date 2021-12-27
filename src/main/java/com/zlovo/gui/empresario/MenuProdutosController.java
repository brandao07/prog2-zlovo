package com.zlovo.gui.empresario;

import com.zlovo.bll.empresa.EmpresaBLL;
import com.zlovo.bll.empresa.ProdutoBLL;
import com.zlovo.dal.empresa.Produto;
import com.zlovo.gui.ControladorGlobal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class MenuProdutosController implements Initializable {
    @FXML
    private ListView<Produto> myListView;
    @FXML
    private Label precoLabel;
    @FXML
    private Label dimensaoLabel;
    @FXML
    private Label pesoLabel;
    @FXML
    private Label unidadeLabel;
    @FXML
    private Label categoriaLabel;

    public static Produto produtoSelecionado;

    public void anterior(ActionEvent event){
        ControladorGlobal.chamaScene("empresario/SceneMenuFuncoesEmp.fxml", event);
    }

    public void criarProduto(ActionEvent event){
       ControladorGlobal.chamaScene("empresario/SceneCriaProdutos.fxml", event);
    }

    public void alteraProduto(ActionEvent event){
        produtoSelecionado = myListView.getSelectionModel().getSelectedItem();
        if(produtoSelecionado == null) return;
       ControladorGlobal.chamaScene("empresario/SceneAlteraProduto.fxml", event);
    }

    public void removeProduto(ActionEvent event){
        produtoSelecionado = myListView.getSelectionModel().getSelectedItem();
        if (produtoSelecionado == null) return;
        EmpresaBLL.removeProduto(produtoSelecionado);
        produtoSelecionado = null;
        ControladorGlobal.chamaScene("empresario/SceneMenuProdutos.fxml", event);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        myListView.getItems().addAll(Objects.requireNonNull(ProdutoBLL.getProdutos(MenuCategoriasController.categoriaSelecionada)));
        ProdutoBLL.changeCellValueProdutoNome(myListView);
        ProdutoBLL.updatePrecoLabel(myListView,precoLabel);
        ProdutoBLL.updateDimensaoLabel(myListView,dimensaoLabel);
        ProdutoBLL.updatePesoLabel(myListView,pesoLabel);
        ProdutoBLL.updateUnidadeLabel(myListView,unidadeLabel);
        categoriaLabel.setText("Categoria: " + MenuCategoriasController.categoriaSelecionada);
    }

    public void bundleMenu(ActionEvent event){
        ControladorGlobal.chamaScene("empresario/SceneMenuBundle.fxml", event);
    }
}
