package com.zlovo.gui.motard;

import com.zlovo.bll.utilizador.MotardBLL;
import com.zlovo.bll.utilizador.UtilizadorBLL;
import com.zlovo.dal.utilizador.Motard;
import com.zlovo.gui.ControladorGlobal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuMotardController implements Initializable {
    @FXML
    private Label usernameMotard;
    @FXML
    private Label checkDados;

    public void logout(ActionEvent event){
        ControladorGlobal.chamaScene("SceneLogin.fxml", event);
    }
    //Criar Scene de listar as ordens
    public void listarOrdens(ActionEvent event){
        ControladorGlobal.chamaScene("motard/SceneListarOrdens.fxml", event);
    }

    public void seguinte(ActionEvent event){
        if (MotardBLL.getCurrentTrabalho((Motard) UtilizadorBLL.getUserLog()) == null){
            checkDados.setText("Sem encomendas por entregar!");
            return;
        }
        ControladorGlobal.chamaScene("motard/SceneEncomenda.fxml", event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usernameMotard.setText("Bem-vindo " + UtilizadorBLL.getUserLog().getUsername());
    }
}
