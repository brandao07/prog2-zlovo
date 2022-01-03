package com.zlovo.gui.cliente;

import com.zlovo.bll.EncomendaBLL;
import com.zlovo.bll.utilizador.ClienteBLL;
import com.zlovo.bll.utilizador.UtilizadorBLL;
import com.zlovo.dal.encomenda.Encomenda;
import com.zlovo.dal.encomenda.enumerations.TipoEstado;
import com.zlovo.dal.utilizador.Cliente;
import com.zlovo.gui.ControladorGlobal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class EncomendasPorPagarController implements Initializable {

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

    public void seguinte(ActionEvent event) {
        if (encomendasList.getSelectionModel().getSelectedItem() == null) {
            checkLabel.setText("Selecione uma encomenda!");
            return;
        }
        encomendaSelecionada = encomendasList.getSelectionModel().getSelectedItem();
        ControladorGlobal.chamaScene("cliente/SceneSelecionarMetodoPagamento.fxml", event);
    }

    public void anular(ActionEvent event) {
        if (encomendasList.getSelectionModel().getSelectedItem() == null) {
            checkLabel.setText("Selecione uma encomenda!");
            return;
        }
        encomendaSelecionada = encomendasList.getSelectionModel().getSelectedItem();
        encomendaSelecionada.getDetalhes().setTipoEstado(TipoEstado.ANULADA);
        ControladorGlobal.chamaScene("cliente/SceneEncomendasPorPagar.fxml", event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        encomendasList.getItems().addAll(ClienteBLL.encomendasPorPagar((Cliente) UtilizadorBLL.getUserLog()));
        if (encomendasList.getItems().isEmpty()) {
            checkLabel.setText("Sem encomendas por pagar!");
            return;
        }
        EncomendaBLL.changeCellValueEncomendaNome(encomendasList);
        EncomendaBLL.updateHorarioLabel(encomendasList, horarioLabel);
        EncomendaBLL.updateDataLabel(encomendasList, dataLabel);
        EncomendaBLL.updatePrecoTotalLabel(encomendasList, precoLabel);
    }

    public void verProdutos(ActionEvent event) {
        encomendaSelecionada = encomendasList.getSelectionModel().getSelectedItem();
        if (encomendaSelecionada == null) {
            checkLabel.setText("Selecione uma encomenda!");
            return;
        }
        ControladorGlobal.chamaScene("cliente/SceneVerProdutos.fxml", event);
    }

    public void anterior(ActionEvent event) {
        ControladorGlobal.chamaScene("cliente/SceneMenuCliente.fxml", event);
    }
}
