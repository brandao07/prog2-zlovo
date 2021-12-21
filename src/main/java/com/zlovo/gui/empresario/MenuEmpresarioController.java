package com.zlovo.gui.empresario;

import com.zlovo.bll.utilizador.UtilizadorBLL;
import com.zlovo.gui.ControladorGlobal;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;


public class MenuEmpresarioController implements Initializable {
    @FXML
    private ListView<String> myListView;

    @FXML
    private Label myLabel;
    //teste para ver se funfa
    String[] food = {"pizza", "pizza1","pizza2"};
    String currentFood;

    public void logout(ActionEvent event){
        UtilizadorBLL.setUserLog(null);
        ControladorGlobal.chamaScene("SceneLogin.fxml", event);
    }

    public void seguinte(ActionEvent event){
        ControladorGlobal.chamaScene("empresario/SceneMenuFuncoesEmp.fxml", event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        myListView.getItems().addAll(food);
        //mudar o conteudo da label
        myListView.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            currentFood = myListView.getSelectionModel().getSelectedItem();

            myLabel.setText(currentFood);
        });
    }

    public void criaEmpresa(ActionEvent event){
        ControladorGlobal.chamaScene("empresario/SceneCriarNovaEmpresa.fxml", event);
    }
}
