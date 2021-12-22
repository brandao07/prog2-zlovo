package com.zlovo.gui.administrador;

import com.zlovo.dal.Repositorio;
import com.zlovo.gui.ControladorGlobal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AdicionarCategoriaController {
    @FXML
    private TextField nomeTF;
    @FXML
    private Label invalidDados;

    public void AdicionarCategoria(ActionEvent event){
        if (nomeTF.getText().isEmpty()){
            invalidDados.setText("Campos inválidos!");
            return;
        }
        Repositorio.getRepositorio().getCategoriaSet().add(nomeTF.getText());
        ControladorGlobal.chamaScene("administrador/SceneMenuAdmin.fxml", event);

    }

    public void anterior (ActionEvent event){
        ControladorGlobal.chamaScene("administrador/SceneMenuAdmin.fxml", event);

    }
}
