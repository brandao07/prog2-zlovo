package com.zlovo.gui.administrador;

import com.zlovo.dal.utilizador.Administrador;
import com.zlovo.dal.utilizador.Motard;
import com.zlovo.dal.utilizador.Utilizador;
import com.zlovo.gui.ControladorGlobal;
import com.zlovo.gui.SelecionaTipoUserController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class SelecionaTipoFuncionarioController implements Initializable {
    // Criação do utilizador para armazenar no mapa no fim de tudo
    public static Utilizador utilizador;
    @FXML
    private ChoiceBox<String> myChoiceBox;
    @FXML
    private Label invalidOption;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        myChoiceBox.getItems().add("Administrador");
        myChoiceBox.getItems().add("Motard");
    }

    public void submit(ActionEvent event) {
        try {
            if (myChoiceBox.getValue().equals("Administrador")) {
                utilizador = new Administrador();
                ControladorGlobal.chamaScene("administrador/SceneDadosAdmin.fxml", event);
            } else if (myChoiceBox.getValue().equals("Motard")) {
                utilizador = new Motard();
                ControladorGlobal.chamaScene("administrador/SceneDadosMotard.fxml", event);
            }
        } catch (Exception e) {
            invalidOption.setText("Selecione um tipo de utilizador!");
        }
    }

    public void anterior(ActionEvent event) {
        SelecionaTipoUserController.utilizador = null;
        ControladorGlobal.chamaScene("administrador/SceneMenuAdmin.fxml", event);
    }
}
