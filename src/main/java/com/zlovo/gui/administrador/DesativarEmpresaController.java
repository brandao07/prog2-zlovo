package com.zlovo.gui.administrador;

import com.zlovo.gui.ControladorGlobal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;


public class DesativarEmpresaController {
    @FXML
    private ListView myListView;

    public void anterior(ActionEvent event){
        ControladorGlobal.chamaScene("administrador/SceneMenuAdmin.fxml", event);
    }
}
