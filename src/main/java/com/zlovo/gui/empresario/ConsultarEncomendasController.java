package com.zlovo.gui.empresario;

import com.zlovo.bll.EncomendaBLL;
import com.zlovo.dal.encomenda.Encomenda;
import com.zlovo.dal.encomenda.enumerations.TipoEstado;
import com.zlovo.gui.ControladorGlobal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class ConsultarEncomendasController implements Initializable {
    public static Encomenda encomendaSelecionada;
    @FXML
    private ListView<Encomenda> encomendasList;
    @FXML
    private Label emptyLabel;
    @FXML
    private Label clienteLabel;
    @FXML
    private Label dataLabel;
    @FXML
    private Label horarioLabel;
    @FXML
    private Label precoTotalLabel;

    public void anterior(ActionEvent event) {
        ControladorGlobal.chamaScene("empresario/SceneMenuFuncoesEmp.fxml", event);
    }

    public void confirmar(ActionEvent event) {
        if (encomendasList.getSelectionModel().getSelectedItem() == null) {
            emptyLabel.setText("Selecione uma encomenda!");
            return;
        }
        encomendasList.getSelectionModel().getSelectedItem().getDetalhes().setTipoEstado(TipoEstado.CONFIRMADA_POR_PAGAR);
        ControladorGlobal.chamaScene("empresario/SceneConsultarEncomendas.fxml", event);
    }

    public void anular(ActionEvent event) {
        if (encomendasList.getSelectionModel().getSelectedItem() == null) {
            emptyLabel.setText("Selecione uma encomenda!");
            return;
        }
        encomendasList.getSelectionModel().getSelectedItem().getDetalhes().setTipoEstado(TipoEstado.ANULADA);
        ControladorGlobal.chamaScene("empresario/SceneConsultarEncomendas.fxml", event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        encomendasList.getItems().addAll(EncomendaBLL.getPorConfirmarEncomendas());
        if (encomendasList.getItems().isEmpty()) {
            emptyLabel.setText("Sem encomendas por confirmar!");
            return;
        }
        EncomendaBLL.changeCellValueEncomendaNome(encomendasList);
        EncomendaBLL.updateClienteLabel(encomendasList, clienteLabel);
        EncomendaBLL.updateHorarioLabel(encomendasList, horarioLabel);
        EncomendaBLL.updateDataLabel(encomendasList, dataLabel);
        EncomendaBLL.updatePrecoTotalLabel(encomendasList, precoTotalLabel);

    }

    public void verProdutos(ActionEvent event) {
        encomendaSelecionada = encomendasList.getSelectionModel().getSelectedItem();
        if (encomendaSelecionada == null) {
            emptyLabel.setText("Selecione uma encomenda!");
            return;
        }
        ControladorGlobal.chamaScene("empresario/SceneVerProdutos.fxml", event);
    }
}
