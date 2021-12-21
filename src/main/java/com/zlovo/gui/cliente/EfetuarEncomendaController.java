package com.zlovo.gui.cliente;

import com.zlovo.bll.utilizador.UtilizadorBLL;
import com.zlovo.gui.ControladorGlobal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

public class EfetuarEncomendaController {
    @FXML
    private ChoiceBox myChoiceBox;
    //criar a scene seleciona os produtos e essas tretas
    public void seguinte(ActionEvent event){
        ControladorGlobal.chamaScene("", event);
    }

    public void logout(ActionEvent event){
        UtilizadorBLL.setUserLog(null);
        ControladorGlobal.chamaScene("SceneLogin.fxml", event);
    }
}
