package com.zlovo.gui.empresario;

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


    public void anterior(ActionEvent event){
        ControladorGlobal.chamaScene("empresario/SceneMenuFuncoesEmp.fxml", event);
    }

    public void criarProduto(ActionEvent event){
       ControladorGlobal.chamaScene("empresario/SceneCriaProdutos.fxml", event);
    }

    public void alteraProduto(ActionEvent event){
       ControladorGlobal.chamaScene("empresario/SceneAlteraProduto.fxml", event);
    }

    public void removeProduto(ActionEvent event){
        ControladorGlobal.chamaScene("empresario/SceneMenuFuncoesEmp.fxml", event);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        myListView.getItems().addAll(Objects.requireNonNull(ProdutoBLL.getProdutos(MenuCategoriasController.categoriaSelecionada)));
        ProdutoBLL.changeCellValueProdutoNome(myListView);
//        ProdutoBLL.updateDadosLabel(myListView, precoLabel,"Preco: " + myListView.getSelectionModel().getSelectedItem().getPreco());
//        ProdutoBLL.updateDadosLabel(myListView, dimensaoLabel, "Dimensão: " + myListView.getSelectionModel().getSelectedItem().getDimensao());
//        ProdutoBLL.updateDadosLabel(myListView, pesoLabel, "Peso: "+ myListView.getSelectionModel().getSelectedItem().getPeso());
//        ProdutoBLL.updateDadosLabel(myListView, unidadeLabel, "Unidade: "+ myListView.getSelectionModel().getSelectedItem().getUnidade());
        categoriaLabel.setText("Categoria: " + MenuCategoriasController.categoriaSelecionada);
    }
}
