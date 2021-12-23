package com.zlovo.gui.empresario;

import com.zlovo.gui.ControladorGlobal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class ConsultarEncomendasController {
    @FXML
    private ListView myListView;

    public void anterior(ActionEvent event){
        ControladorGlobal.chamaScene("empresario/SceneMenuFuncoesEmp.fxml", event);
    }
}
