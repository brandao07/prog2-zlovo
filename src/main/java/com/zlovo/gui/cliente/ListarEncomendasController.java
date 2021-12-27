package com.zlovo.gui.cliente;

import com.zlovo.bll.utilizador.UtilizadorBLL;
import com.zlovo.gui.ControladorGlobal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class ListarEncomendasController {
    @FXML
    private ListView myListView;

    public void anterior(ActionEvent event){
        ControladorGlobal.chamaScene("cliente/SceneMenuCliente.fxml", event);
    }
}
