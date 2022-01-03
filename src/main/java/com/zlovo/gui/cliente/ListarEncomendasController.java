package com.zlovo.gui.cliente;

import com.zlovo.bll.EncomendaBLL;
import com.zlovo.bll.utilizador.ClienteBLL;
import com.zlovo.bll.utilizador.UtilizadorBLL;
import com.zlovo.dal.encomenda.Encomenda;
import com.zlovo.dal.utilizador.Cliente;
import com.zlovo.gui.ControladorGlobal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class ListarEncomendasController implements Initializable {

    public static Encomenda encomendaSelecionada;
    @FXML
    private ListView<Encomenda> encomendasList;
    @FXML
    private Label checkLabel;
    @FXML
    private Label precoLabel;
    @FXML
    private Label dataLabel;
    @FXML
    private Label horarioLabel;
    @FXML
    private Label metodoPagamentoLabel;
    @FXML
    private Label estadoLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        encomendasList.getItems().addAll(ClienteBLL.encomendasConfirmadas((Cliente) UtilizadorBLL.getUserLog()));
        if (encomendasList.getItems().isEmpty()) {
            checkLabel.setText("Sem encomendas efetuadas!");
            return;
        }
        EncomendaBLL.changeCellValueEncomendaNome(encomendasList);
        EncomendaBLL.updateHorarioLabel(encomendasList, horarioLabel);
        EncomendaBLL.updateDataLabel(encomendasList, dataLabel);
        EncomendaBLL.updatePrecoTotalLabel(encomendasList, precoLabel);
        EncomendaBLL.updateMetodoPagamentoLabel(encomendasList, metodoPagamentoLabel);
        EncomendaBLL.updateTipoEstadoLabel(encomendasList, estadoLabel);
    }

    public void verProduto(ActionEvent event) {
        encomendaSelecionada = encomendasList.getSelectionModel().getSelectedItem();
        if (encomendaSelecionada == null) {
            checkLabel.setText("Selecione uma encomenda!");
            return;
        }
        ControladorGlobal.chamaScene("cliente/SceneVerProdutos2.fxml", event);
    }

    public void anterior(ActionEvent event) {
        ControladorGlobal.chamaScene("cliente/SceneMenuCliente.fxml", event);
    }
}
