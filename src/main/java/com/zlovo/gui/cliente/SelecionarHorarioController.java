package com.zlovo.gui.cliente;

import com.zlovo.dal.encomenda.enumerations.TipoHorario;
import com.zlovo.gui.ControladorGlobal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.util.ResourceBundle;

public class SelecionarHorarioController implements Initializable {
    @FXML
    private ChoiceBox<TipoHorario> tipoHorarioCB;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tipoHorarioCB.getItems().addAll(TipoHorario.values());
    }

    public void anterior(ActionEvent event) {
        SelecionarProdutosController.produtoselecionado = null;
        ControladorGlobal.chamaScene("cliente/SceneSelecionarCategoria.fxml", event);
    }
}

