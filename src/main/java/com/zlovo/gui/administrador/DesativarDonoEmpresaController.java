package com.zlovo.gui.administrador;

import com.zlovo.bll.empresa.EmpresaBLL;
import com.zlovo.bll.utilizador.AdministradorBLL;
import com.zlovo.bll.utilizador.UtilizadorBLL;
import com.zlovo.dal.utilizador.Empresario;
import com.zlovo.gui.ControladorGlobal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class DesativarDonoEmpresaController implements Initializable {
    @FXML
    private ListView<Empresario> myListView;
    @FXML
    private Label myLabel;
    @FXML
    private Label checkError;


    public void anterior(ActionEvent event){
        ControladorGlobal.chamaScene("administrador/SceneMenuAdmin.fxml", event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        myListView.getItems().addAll(AdministradorBLL.getEmpresarios());
        AdministradorBLL.changeCellValueEmpresarioNome(myListView);
        AdministradorBLL.updateNomeEmpresarioLabel(myListView,myLabel);
    }

    public void desativar(ActionEvent event){
        if(myListView.getSelectionModel().getSelectedItem() == null){
            checkError.setText("Selecione um empresário!");
            return;
        }
        Empresario currentEmpresario = myListView.getSelectionModel().getSelectedItem();
        UtilizadorBLL.removeUtilizador(currentEmpresario.getUsername());
        EmpresaBLL.removerEmpresaALL(currentEmpresario.getUsername());
        ControladorGlobal.chamaScene("administrador/SceneMenuAdmin.fxml", event);
    }
}
