package com.zlovo.gui.motard;

import com.zlovo.dal.encomenda.Trabalho;
import com.zlovo.dal.encomenda.enumerations.TipoEstadoEntrega;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

public class EncomendaPendenteController implements Initializable {
    @FXML
    private Label encomendaLabel;
    @FXML
    private Label empresaLabel;
    @FXML
    private Label origemLabel;
    @FXML
    private Label destinoLabel;
    @FXML
    private ChoiceBox<TipoEstadoEntrega> estadoBox;
    @FXML
    private TextArea textArea;
    @FXML
    private Label checkDados;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        estadoBox.getItems().add(TipoEstadoEntrega.ENTREGUE);
        estadoBox.getItems().add(TipoEstadoEntrega.FALHA);
    }

    public void confirmar(ActionEvent event){
        //TODO associar trabalho pendente ao motard.
        if (estadoBox.getValue() == null){
            checkDados.setText("Escolha estado da encomenda!");
            return;
        }

    }
}
