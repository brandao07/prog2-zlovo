package com.zlovo.gui.administrador;

import com.zlovo.bll.EncomendaBLL;
import com.zlovo.dal.encomenda.Encomenda;
import com.zlovo.gui.ControladorGlobal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import java.net.URL;

import java.util.ResourceBundle;

public class SelecionarEncomendaController implements Initializable {
    @FXML
    private ListView<Encomenda> encomendasList;
    @FXML
    private Label checkDados;
    @FXML
    private Label tipoPagamentoLabel;
    @FXML
    private Label horarioLabel;
    @FXML
    private Label dataLabel;
    @FXML
    private Label clienteLabel;

    public static Encomenda encomendaSelecionada;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        encomendasList.getItems().addAll(EncomendaBLL.getConfirmarEncomendas());
        if (encomendasList.getItems().isEmpty()){
            checkDados.setText("Sem encomendas pendentes!");
            return;
        }
        EncomendaBLL.changeCellValueEncomendaNome(encomendasList);
        EncomendaBLL.updateClienteLabel(encomendasList,clienteLabel);
        EncomendaBLL.updateDataLabel(encomendasList,dataLabel);
        EncomendaBLL.updateHorarioLabel(encomendasList,horarioLabel);
        EncomendaBLL.updateMetodoPagamentoLabel(encomendasList,tipoPagamentoLabel);
    }

    public void seguinte(ActionEvent event){
        if (encomendasList.getSelectionModel().getSelectedItem() == null){
            checkDados.setText("Selecione uma encomenda!");
            return;
        }
        encomendaSelecionada = encomendasList.getSelectionModel().getSelectedItem();
        ControladorGlobal.chamaScene("administrador/SceneEnviarMotards.fxml", event);
    }

    public void anterior(ActionEvent evemt){
        ControladorGlobal.chamaScene("administrador/SceneMenuAdmin.fxml", evemt);
    }
}
