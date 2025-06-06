package com.zlovo.gui.cliente;

import com.zlovo.bll.empresa.EmpresaBLL;
import com.zlovo.bll.utilizador.EmpresarioBLL;
import com.zlovo.bll.utilizador.UtilizadorBLL;
import com.zlovo.dal.empresa.Empresa;
import com.zlovo.dal.encomenda.Encomenda;
import com.zlovo.gui.ControladorGlobal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class SelecionarEmpresaController implements Initializable {
    public static Empresa empresaSelecionada;
    public static Encomenda encomenda;
    @FXML
    private ListView<Empresa> empresaList;
    @FXML
    private Label myLabel;
    @FXML
    private Label checkerror;

    public void seguinte(ActionEvent event) {
        if (empresaList.getSelectionModel().getSelectedItem() == null) {
            checkerror.setText("Selecione uma empresa");
            return;
        }
        empresaSelecionada = empresaList.getSelectionModel().getSelectedItem();
        encomenda = new Encomenda(empresaSelecionada.getId(), UtilizadorBLL.getUserLog().getUsername());
        ControladorGlobal.chamaScene("cliente/SceneSelecionarCategoria.fxml", event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        empresaList.getItems().addAll(EmpresaBLL.getEmpresas());
        EmpresarioBLL.changeCellValueEmpresaNome(empresaList);
        EmpresarioBLL.updateNomeEmpresaLabel(empresaList, myLabel);
    }

    public void anterior(ActionEvent event) {
        empresaSelecionada = null;
        ControladorGlobal.chamaScene("cliente/SceneMenuCliente.fxml", event);
    }
}
