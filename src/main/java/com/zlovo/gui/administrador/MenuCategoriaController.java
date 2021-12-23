package com.zlovo.gui.administrador;

import com.zlovo.bll.utilizador.AdministradorBLL;
import com.zlovo.dal.Repositorio;
import com.zlovo.gui.ControladorGlobal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuCategoriaController implements Initializable {

    @FXML
    private ListView<String> categoriaList;
    @FXML
    private Label myLabel;
    @FXML
    private Label checkerror;
    String currentCategoria;

    public static String categoriaSelecionada;

    public void adicionar (ActionEvent event){
        categoriaSelecionada = null;
        ControladorGlobal.chamaScene("administrador/SceneAdicionarCategoria.fxml", event);
    }

    public void alterar (ActionEvent event){
        if(categoriaList.getSelectionModel().getSelectedItem() == null){
            checkerror.setText("Selecione uma categoria");
            return;
        }
        categoriaSelecionada = categoriaList.getSelectionModel().getSelectedItem();
        ControladorGlobal.chamaScene("administrador/SceneEditarCategorias.fxml", event);
    }
    public void remover (ActionEvent event) {
        if(categoriaList.getSelectionModel().getSelectedItem() == null){
            checkerror.setText("Selecione uma categoria");
            return;
        }
        categoriaSelecionada = categoriaList.getSelectionModel().getSelectedItem();
        AdministradorBLL.removerCategoria(categoriaSelecionada);
        ControladorGlobal.chamaScene("administrador/SceneMenuAdmin.fxml", event);
    }

    public void anterior (ActionEvent event){
        categoriaSelecionada = null;
        ControladorGlobal.chamaScene("administrador/SceneMenuAdmin.fxml", event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        categoriaList.getItems().addAll(Repositorio.getRepositorio().getCategoriaSet());

        categoriaList.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            currentCategoria = categoriaList.getSelectionModel().getSelectedItem();
            myLabel.setText(currentCategoria);
        });
    }
}
