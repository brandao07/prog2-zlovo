package com.zlovo.gui.administrador;

import com.zlovo.dal.Repositorio;
import com.zlovo.gui.ControladorGlobal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuCategoriaController implements Initializable {

    @FXML
    private ListView<String> categoriaList;
    private static String categoriaSelecionada;

    public void adicionar (ActionEvent event){
        categoriaSelecionada = null;
        ControladorGlobal.chamaScene("", event);
    }
    public void alterar (ActionEvent event){
        categoriaSelecionada = categoriaList.getSelectionModel().getSelectedItem();
        ControladorGlobal.chamaScene("", event);
    }
    public void remover (ActionEvent event) {
        categoriaSelecionada = categoriaList.getSelectionModel().getSelectedItem();
        ControladorGlobal.chamaScene("administrador/SceneMenuAdmin.fxml", event);
    }

    public void anterior (ActionEvent event){
        categoriaSelecionada = null;
        ControladorGlobal.chamaScene("adiministrador/SceneMenuAdmin.fxml", event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        categoriaList.getItems().addAll(Repositorio.getRepositorio().getCategoriaSet());
    }
}
