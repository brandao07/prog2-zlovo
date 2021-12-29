package com.zlovo.gui;

import com.zlovo.bll.utilizador.UtilizadorBLL;
import com.zlovo.dal.utilizador.Cliente;
import com.zlovo.dal.utilizador.Empresario;
import com.zlovo.dal.utilizador.Utilizador;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import java.net.URL;
import java.util.ResourceBundle;

public class SelecionaTipoUserController implements Initializable {
    @FXML
    private ChoiceBox<String> myChoiceBox;
    @FXML
    private Label invalidOption;

    // Criação do utilizador para armazenar no mapa no fim de tudo
    public static Utilizador utilizador;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        myChoiceBox.getItems().add("Cliente");
        myChoiceBox.getItems().add("Empresário");
    }

    public void submit(ActionEvent event) {
        try {
            if (myChoiceBox.getValue().equals("Cliente")) utilizador = new Cliente();
             else if (myChoiceBox.getValue().equals("Empresário")) utilizador = new Empresario();
            ControladorGlobal.chamaScene("SceneCriaConta.fxml", event);
        } catch (Exception e){
          invalidOption.setText("Selecione um tipo de utilizador!");
        }
    }

    public void anterior(ActionEvent event){
        utilizador = null;
        ControladorGlobal.chamaScene("SceneLogin.fxml", event);
    }
}