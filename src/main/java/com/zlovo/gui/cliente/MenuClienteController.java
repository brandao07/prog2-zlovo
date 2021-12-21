package com.zlovo.gui.cliente;

import com.zlovo.bll.utilizador.UtilizadorBLL;
import com.zlovo.gui.ControladorGlobal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MenuClienteController {
    @FXML
    private Label usernameCliente;

    private String username;

    public void logout(ActionEvent event){
        UtilizadorBLL.setUserLog(null);
        ControladorGlobal.chamaScene("SceneLogin.fxml", event);
    }

    public void usernameCliente (ActionEvent event){
        username = UtilizadorBLL.getUserLog().getUsername();
        usernameCliente.setText("Bem-vindo" + username);
    }

    public void efetuarEncomenda (ActionEvent event){
        ControladorGlobal.chamaScene("cliente/SceneEfetuarEncomenda.fxml", event);
    }

    public void efetuarPagamentos (ActionEvent event){
        ControladorGlobal.chamaScene("cliente/SceneEfetuarPagamentos.fxml", event);
    }

    public void ListarEncomendas (ActionEvent event){
        ControladorGlobal.chamaScene("cliente/SceneListarEncomendas.fxml", event);
    }

}
