package com.zlovo.gui.cliente;

import com.zlovo.bll.utilizador.UtilizadorBLL;
import com.zlovo.gui.ControladorGlobal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class EfetuarPagamentosController {
    @FXML
    private ListView myListView;
    //criar a scene que seleciona o metodo do pagamento
    public void seguinte(ActionEvent event){
        ControladorGlobal.chamaScene("", event);
    }

    public void logout(ActionEvent event){
        UtilizadorBLL.setUserLog(null);
        ControladorGlobal.chamaScene("SceneLogin.fxml", event);
    }
}
