package com.zlovo.gui;

import com.zlovo.bll.utilizador.UtilizadorBLL;
import com.zlovo.dal.utilizador.Administrador;
import com.zlovo.dal.utilizador.Cliente;
import com.zlovo.dal.utilizador.Empresario;
import com.zlovo.dal.utilizador.Motard;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class LoginController {
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label checkLabel;
    @FXML
    private AnchorPane scene1Pane;

    public void login(ActionEvent event) {
        if (UtilizadorBLL.login(usernameTextField.getText(), passwordField.getText())) {
            if (UtilizadorBLL.getUserLog() instanceof Administrador)
                ControladorGlobal.chamaScene("administrador/SceneMenuAdmin.fxml", event);
            else if (UtilizadorBLL.getUserLog() instanceof Motard)
                ControladorGlobal.chamaScene("motard/SceneMenuMotard.fxml", event);
            else if (UtilizadorBLL.getUserLog() instanceof Cliente)
                ControladorGlobal.chamaScene("cliente/SceneMenuCliente.fxml", event);
            else if (UtilizadorBLL.getUserLog() instanceof Empresario)
                ControladorGlobal.chamaScene("empresario/SceneMenuEmpresario.fxml", event);
        } else
            checkLabel.setText("username ou password inválidos!");
    }

    public void signup(ActionEvent event){
       ControladorGlobal.chamaScene("SceneSelecionaTipoUser.fxml",event);
    }

    public void forgotPassword(ActionEvent event){
        ControladorGlobal.chamaScene("SceneRecuperarPassword.fxml",event);
    }

    public void exit(){
        Stage stage = (Stage) scene1Pane.getScene().getWindow();
        ControladorGlobal.exit(stage);
    }

    public void newWindow(){
        //criar class thread com a scene e instancia-la aqui


    }
}
