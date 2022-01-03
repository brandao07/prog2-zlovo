package com.zlovo.gui;

import com.zlovo.bll.utilizador.UtilizadorBLL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class CriaContaController {
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label checkLabel;

    public void seguinte(ActionEvent event) {
        if (!UtilizadorBLL.checkUsername(usernameTextField.getText()) & !usernameTextField.getText().isEmpty() & !passwordField.getText().isEmpty()) {
            SelecionaTipoUserController.utilizador.setUsername(usernameTextField.getText());
            SelecionaTipoUserController.utilizador.setPassword(passwordField.getText());
            ControladorGlobal.chamaScene("SceneDadosPessoais.fxml", event);
        } else if (usernameTextField.getText().isEmpty())
            checkLabel.setText("username inválido!");
        else if (passwordField.getText().isEmpty())
            checkLabel.setText("password inválida!");
        else if (UtilizadorBLL.checkUsername(usernameTextField.getText()))
            checkLabel.setText("username já registado!");
    }

    public void anterior(ActionEvent event) {
        SelecionaTipoUserController.utilizador = null;
        ControladorGlobal.chamaScene("SceneLogin.fxml", event);
    }

}
