package com.zlovo.gui.empresario;

import com.zlovo.bll.empresa.EmpresaBLL;
import com.zlovo.bll.utilizador.EmpresarioBLL;
import com.zlovo.bll.utilizador.UtilizadorBLL;
import com.zlovo.dal.empresa.Empresa;
import com.zlovo.dal.utilizador.Empresario;
import com.zlovo.gui.ControladorGlobal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

import java.net.URL;
import java.util.ResourceBundle;


public class MenuEmpresarioController implements Initializable {
    @FXML
    private ListView<Empresa> myListView;
    @FXML
    private Label myLabel;
    @FXML
    private Label empresarioLabel;
    @FXML
    private Label checkerror;

    public void logout(ActionEvent event){
        UtilizadorBLL.setUserLog(null);
        ControladorGlobal.chamaScene("SceneLogin.fxml", event);
    }

    public void seguinte(ActionEvent event){
        if(myListView.getSelectionModel().getSelectedItem() == null){
            checkerror.setText("Crie uma empresa");
            return;
        }
        EmpresaBLL.setEmpresaLog(myListView.getSelectionModel().getSelectedItem());
        ControladorGlobal.chamaScene("empresario/SceneMenuFuncoesEmp.fxml", event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        empresarioLabel.setText("Bem-vindo: " + UtilizadorBLL.getUserLog().getUsername());
        if (UtilizadorBLL.getUserLog() instanceof Empresario)
            myListView.getItems().addAll(((Empresario) UtilizadorBLL.getUserLog()).getEmpresasList());
        EmpresarioBLL.changeCellValueEmpresaNome(myListView);
        EmpresarioBLL.updateNomeEmpresaLabel(myListView, myLabel);
    }

    public void criaEmpresa(ActionEvent event){
        ControladorGlobal.chamaScene("empresario/SceneCriarNovaEmpresa.fxml", event);
    }
}
