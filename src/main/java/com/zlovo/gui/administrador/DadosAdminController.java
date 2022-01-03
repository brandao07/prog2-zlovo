package com.zlovo.gui.administrador;

import com.zlovo.bll.utilizador.UtilizadorBLL;
import com.zlovo.gui.ControladorGlobal;
import com.zlovo.gui.SelecionaTipoUserController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class DadosAdminController {
    @FXML
    private TextField usernameTF;
    @FXML
    private PasswordField passwordTF;
    @FXML
    private Label invalidDados;

    public void confirmar(ActionEvent event) {
        if (!usernameTF.getText().isEmpty() & !passwordTF.getText().isEmpty()) {
            if (UtilizadorBLL.checkUsername(usernameTF.getText())) {
                invalidDados.setText("Username já registado!");
                return;
            }
            SelecionaTipoFuncionarioController.utilizador.setUsername(usernameTF.getText());
            SelecionaTipoFuncionarioController.utilizador.setPassword(passwordTF.getText());
            UtilizadorBLL.criarUtilizador(SelecionaTipoFuncionarioController.utilizador);
            SelecionaTipoFuncionarioController.utilizador = null;
            ControladorGlobal.chamaScene("administrador/SceneMenuAdmin.fxml", event);
        } else invalidDados.setText("Dados inválidos!");
    }

    public void cancelar(ActionEvent event) {
        SelecionaTipoUserController.utilizador = null;
        ControladorGlobal.chamaScene("administrador/SceneMenuAdmin.fxml", event);
    }
}
