package com.zlovo.gui.administrador;

import com.zlovo.bll.utilizador.AdministradorBLL;
import com.zlovo.dal.utilizador.Empresario;
import com.zlovo.gui.ControladorGlobal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;


public class DesativarEmpresaController implements Initializable {

    @FXML
    private ListView<Empresario> empresarioList;
    @FXML
    private Label myLabel;
    @FXML
    private Label checkerror;

    public static Empresario empresarioSelecionado;

    public void anterior(ActionEvent event){
        ControladorGlobal.chamaScene("administrador/SceneMenuAdmin.fxml", event);
    }

    public void seguinte(ActionEvent event){
        if(empresarioList.getSelectionModel().getSelectedItem() == null){
            checkerror.setText("Selecione um empresário");
            return;
        }
        empresarioSelecionado = empresarioList.getSelectionModel().getSelectedItem();
        ControladorGlobal.chamaScene("administrador/SceneDesativarEmpresaFinal.fxml", event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        empresarioList.getItems().addAll(AdministradorBLL.getEmpresarios());
        AdministradorBLL.changeCellValueEmpresarioNome(empresarioList);
        AdministradorBLL.updateNomeEmpresarioLabel(empresarioList,myLabel);
    }
}
