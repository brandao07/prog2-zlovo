package com.zlovo.gui.administrador;

import com.zlovo.bll.utilizador.UtilizadorBLL;
import com.zlovo.dal.utilizador.Administrador;
import com.zlovo.gui.ControladorGlobal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class PrimeiraVezController {

    @FXML
    private TextField username;
    @FXML
    private PasswordField pass;
    @FXML
    private Label warning;

    public void seguinte(ActionEvent event) {
        Administrador master = new Administrador();
        if (!username.getText().isEmpty() & !pass.getText().isEmpty()){
            master.setUsername(username.getText());
            master.setPassword(pass.getText());
            UtilizadorBLL.criarUtilizador(master);
            ControladorGlobal.chamaScene("SceneLogin.fxml", event);
        }else
            warning.setText("Campos inválidos!");
    }
}
