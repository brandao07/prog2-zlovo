package com.zlovo.gui.cliente;


import com.zlovo.bll.utilizador.ClienteBLL;
import com.zlovo.bll.utilizador.UtilizadorBLL;
import com.zlovo.dal.encomenda.enumerations.TipoPagamento;
import com.zlovo.dal.utilizador.Cliente;
import com.zlovo.gui.ControladorGlobal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AdicionarSaldoController implements Initializable {

    @FXML
    private Label saldoLabel;
    @FXML
    private ChoiceBox<TipoPagamento> pagamentoCB;
    @FXML
    private TextField saldoTF;
    @FXML
    private Label checkDados;

    public void cancelar(ActionEvent event) {
        ControladorGlobal.chamaScene("cliente/SceneMenuCliente.fxml", event);
    }

    public void confirmar(ActionEvent event) {
        if (pagamentoCB.getValue() == null) {
            checkDados.setText("Escolha um método de transferência!");
            return;
        }
        if (saldoTF.getText().isEmpty() || (Double.parseDouble(saldoTF.getText())) <= 0) {
            checkDados.setText("Valor Inválido!");
            return;
        }
        ((Cliente) UtilizadorBLL.getUserLog()).setSaldo(((Cliente) UtilizadorBLL.getUserLog()).getSaldo() + (Double.parseDouble(saldoTF.getText())));
        ClienteBLL.updateCliente((Cliente) UtilizadorBLL.getUserLog());
        ControladorGlobal.chamaScene("cliente/SceneMenuCliente.fxml", event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pagamentoCB.getItems().add(TipoPagamento.MULTIBANCO);
        pagamentoCB.getItems().add(TipoPagamento.CARTAO_CREDITO);
        saldoLabel.setText("Saldo: " + ((Cliente) UtilizadorBLL.getUserLog()).getSaldo());
    }
}
