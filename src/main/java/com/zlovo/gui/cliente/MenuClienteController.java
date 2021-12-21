package com.zlovo.gui.cliente;

import com.zlovo.bll.utilizador.UtilizadorBLL;
import com.zlovo.dal.Repositorio;
import com.zlovo.gui.ControladorGlobal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuClienteController implements Initializable {
    @FXML
    private Label usernameCliente;


    public void logout(ActionEvent event){
        UtilizadorBLL.setUserLog(null);
        ControladorGlobal.chamaScene("SceneLogin.fxml", event);
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usernameCliente.setText("Bem-vindo " + UtilizadorBLL.getUserLog().getUsername() + " !");
    }
}
