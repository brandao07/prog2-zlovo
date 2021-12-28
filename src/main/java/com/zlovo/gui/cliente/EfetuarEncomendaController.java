package com.zlovo.gui.cliente;


import com.zlovo.dal.empresa.Empresa;
import com.zlovo.gui.ControladorGlobal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class EfetuarEncomendaController implements Initializable {
    @FXML
    private ListView<Empresa> empresaList;
    @FXML
    private Label myLabel;
    @FXML
    private Label checkerror;

    Empresa empresaSelecionada;

    public void seguinte (ActionEvent event){
        if(empresaList.getSelectionModel().getSelectedItem() == null){
            checkerror.setText("Selecione uma empresa");
            return;
        }
        empresaSelecionada = empresaList.getSelectionModel().getSelectedItem();
        ControladorGlobal.chamaScene("cliente/.fxml", event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void anterior(ActionEvent event){
        ControladorGlobal.chamaScene("cliente/SceneMenuCliente.fxml", event);
    }
}
