package com.zlovo.gui.empresario;

import com.zlovo.bll.empresa.EmpresaBLL;
import com.zlovo.bll.empresa.ProdutoBLL;
import com.zlovo.dal.Repositorio;
import com.zlovo.gui.ControladorGlobal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuCategoriasController implements Initializable {
    public static String categoriaSelecionada;
    @FXML
    private ListView<String> myListView;
    @FXML
    private Label categoriaLabel;
    @FXML
    private Label quantidadeProdutosLabel;
    String currentCategoria;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        myListView.getItems().addAll(Repositorio.getRepositorio().getCategoriaSet());
        ProdutoBLL.updateCategoriaLabel(myListView,categoriaLabel);
        ProdutoBLL.updateQuantidadeLabel(myListView,quantidadeProdutosLabel);
    }

    public void listarProdutos(ActionEvent event){
        categoriaSelecionada = myListView.getSelectionModel().getSelectedItem();
        ControladorGlobal.chamaScene("empresario/SceneMenuProdutos.fxml", event);
    }

    public void anterior(ActionEvent event){
        ControladorGlobal.chamaScene("empresario/SceneMenuFuncoesEmp.fxml", event);
    }
}
