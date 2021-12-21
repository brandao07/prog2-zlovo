package com.zlovo.gui.motard;

import com.zlovo.bll.utilizador.UtilizadorBLL;
import com.zlovo.gui.ControladorGlobal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MenuMotardController {
    @FXML
    private Label usernameMotard;

    public void logout(ActionEvent event){
        UtilizadorBLL.setUserLog(null);
        ControladorGlobal.chamaScene("SceneLogin.fxml", event);
    }
    //Criar Scene de listar as ordens
    public void listarOrdens(ActionEvent event){
        ControladorGlobal.chamaScene("motard/SceneListarOrdens.fxml", event);
    }

    public void getUsernameMotard (ActionEvent event){
        String username;
        username = UtilizadorBLL.getUserLog().getUsername();
        usernameMotard.setText("Bem-vindo " + username);
    }
}
