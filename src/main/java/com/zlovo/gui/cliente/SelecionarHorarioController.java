package com.zlovo.gui.cliente;

import com.zlovo.bll.EncomendaBLL;
import com.zlovo.dal.encomenda.enumerations.TipoEstado;
import com.zlovo.dal.encomenda.enumerations.TipoHorario;
import com.zlovo.gui.ControladorGlobal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;

import java.net.URL;
import java.util.ResourceBundle;

public class SelecionarHorarioController implements Initializable {
    @FXML
    private ChoiceBox<TipoHorario> tipoHorarioCB;
    @FXML
    private DatePicker date;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tipoHorarioCB.getItems().addAll(TipoHorario.values());
    }

    public void seguinte(ActionEvent event) {
        SelecionarEmpresaController.encomenda.setDataCliente(date.getValue());
        SelecionarEmpresaController.encomenda.setHorario(tipoHorarioCB.getValue());
        SelecionarEmpresaController.encomenda.getDetalhes().setTipoEstado(TipoEstado.AGUARDAR);
        EncomendaBLL.criarEncomenda(SelecionarEmpresaController.encomenda);
        ControladorGlobal.chamaScene("cliente/SceneMenuCliente.fxml", event);
    }

    public void anterior(ActionEvent event) {
        ControladorGlobal.chamaScene("cliente/SceneCarrinho.fxml", event);
    }
}

