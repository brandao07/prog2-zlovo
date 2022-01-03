package com.zlovo.gui;

import com.zlovo.bll.utilizador.UtilizadorBLL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RecuperarPasswordController {
    private static String username;
    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField nifTextField;
    @FXML
    private Label checkLabel;
    @FXML
    private PasswordField pass1;
    @FXML
    private PasswordField pass2;
    @FXML
    private Label checkLabel2;

    public void verificarDados(ActionEvent event) {
        if (UtilizadorBLL.checkUsernameNif(usernameTextField.getText(), nifTextField.getText()) != null) {
            username = usernameTextField.getText();
            ControladorGlobal.chamaScene("SceneNovaPass.fxml", event);
        } else if (usernameTextField.getText().isEmpty() || nifTextField.getText().isEmpty())
            checkLabel.setText("campo inválido");
    }

    public void cancelar(ActionEvent event) {
        username = null;
        ControladorGlobal.chamaScene("SceneLogin.fxml", event);
    }

    public void resetPassword(ActionEvent event) {
        if (pass1.getText().equals(pass2.getText())) {
            UtilizadorBLL.resetPassword(username, pass1.getText());
            username = null;
            ControladorGlobal.chamaScene("SceneLogin.fxml", event);
        } else checkLabel2.setText("Passwords não coincedem!!");
    }
}
