package com.zlovo.gui.administrador;

import com.zlovo.gui.ControladorGlobal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class AdicionarCategoriaController {
    @FXML
    private TextField nomeTF;

    public void AdicionarCategoria(ActionEvent event){

    }

    public void anterior (ActionEvent event){
        ControladorGlobal.chamaScene("administrador/SceneMenuAdmin", event);
    }
}
