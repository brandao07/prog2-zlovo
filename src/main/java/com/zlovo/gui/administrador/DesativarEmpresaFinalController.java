package com.zlovo.gui.administrador;

import com.zlovo.bll.empresa.EmpresaBLL;
import com.zlovo.bll.utilizador.EmpresarioBLL;
import com.zlovo.dal.empresa.Empresa;
import com.zlovo.gui.ControladorGlobal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class DesativarEmpresaFinalController implements Initializable {
    @FXML
    private ListView<Empresa> empresaList;
    @FXML
    private Label myLabel;
    @FXML
    private Label checkerror;


    public void remover(ActionEvent event) {
        Empresa empresaSelecionada;
        if (empresaList.getSelectionModel().getSelectedItem() == null) {
            checkerror.setText("Selecione uma empresa");
            return;
        }
        empresaSelecionada = empresaList.getSelectionModel().getSelectedItem();
        EmpresaBLL.removerEmpresa(empresaSelecionada, DesativarEmpresaController.empresarioSelecionado);
        ControladorGlobal.chamaScene("administrador/SceneMenuAdmin.fxml", event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        empresaList.getItems().addAll(DesativarEmpresaController.empresarioSelecionado.getEmpresasList());
        EmpresarioBLL.changeCellValueEmpresaNome(empresaList);
        EmpresarioBLL.updateNomeEmpresaLabel(empresaList, myLabel);
    }

    public void anterior(ActionEvent event) {
        ControladorGlobal.chamaScene("administrador/SceneMenuAdmin.fxml", event);
    }
}
