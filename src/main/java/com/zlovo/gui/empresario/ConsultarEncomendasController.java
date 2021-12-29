package com.zlovo.gui.empresario;

import com.zlovo.bll.EncomendaBLL;
import com.zlovo.dal.Repositorio;
import com.zlovo.dal.encomenda.Encomenda;
import com.zlovo.gui.ControladorGlobal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class ConsultarEncomendasController implements Initializable {
    @FXML
    private ListView<Encomenda> encomendasList;
//TODO: falta confirmar encomenda
    public void anterior(ActionEvent event) {
        ControladorGlobal.chamaScene("empresario/SceneMenuFuncoesEmp.fxml", event);
    }

    public void confirmar(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        encomendasList.getItems().addAll(EncomendaBLL.getPorConfirmarEncomendas());
    }
}
