package com.zlovo.gui.cliente;

import com.zlovo.bll.utilizador.UtilizadorBLL;
import com.zlovo.dal.Repositorio;
import com.zlovo.dal.encomenda.Encomenda;
import com.zlovo.dal.utilizador.Cliente;
import com.zlovo.gui.ControladorGlobal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.controlsfx.control.action.Action;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuClienteController implements Initializable {
    @FXML
    private Label usernameCliente;
    @FXML
    private Label saldoLabel;


    public void logout(ActionEvent event){
        UtilizadorBLL.setUserLog(null);
        ControladorGlobal.chamaScene("SceneLogin.fxml", event);
    }

    public void efetuarEncomenda (ActionEvent event){
        ControladorGlobal.chamaScene("cliente/SceneSelecionarEmpresa.fxml", event);
    }

    public void efetuarPagamentos (ActionEvent event){
        ControladorGlobal.chamaScene("cliente/SceneEncomendasPorPagar.fxml", event);
    }

    public void listarEncomendas (ActionEvent event){
        ControladorGlobal.chamaScene("cliente/SceneListarEncomendas.fxml", event);
    }

    public void adicionarSaldo (ActionEvent event){
        ControladorGlobal.chamaScene("cliente/SceneAdicionarSaldo.fxml", event);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usernameCliente.setText("Bem-vindo " + UtilizadorBLL.getUserLog().getUsername() + " !");
        saldoLabel.setText("Saldo: " + ((Cliente) UtilizadorBLL.getUserLog()).getSaldo());
    }
}
