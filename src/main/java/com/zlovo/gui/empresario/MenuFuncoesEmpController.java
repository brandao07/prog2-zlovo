package com.zlovo.gui.empresario;

import com.zlovo.bll.utilizador.UtilizadorBLL;
import com.zlovo.gui.ControladorGlobal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MenuFuncoesEmpController {
    @FXML
    private Label usernameEmp;
    private String username;

    public void logout(ActionEvent event){
        UtilizadorBLL.setUserLog(null);
        ControladorGlobal.chamaScene("SceneLogin.fxml", event);
    }

    public void usernameEmp (ActionEvent event){
        username = UtilizadorBLL.getUserLog().getUsername();
        usernameEmp.setText("Bem-vindo" + username);
    }
    //Falta fazer as scenes da funçoes do empresario
}
