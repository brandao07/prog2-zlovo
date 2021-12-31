package com.zlovo.gui.motard;

import com.zlovo.bll.EncomendaBLL;
import com.zlovo.bll.empresa.EmpresaBLL;
import com.zlovo.bll.utilizador.ClienteBLL;
import com.zlovo.bll.utilizador.MotardBLL;
import com.zlovo.bll.utilizador.UtilizadorBLL;
import com.zlovo.dal.empresa.Empresa;
import com.zlovo.dal.encomenda.Encomenda;
import com.zlovo.dal.encomenda.Trabalho;
import com.zlovo.dal.utilizador.Cliente;
import com.zlovo.dal.utilizador.Motard;
import com.zlovo.gui.ControladorGlobal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ListarOrdensController implements Initializable {

    @FXML
    private ListView<Trabalho> trabalhosList;
    @FXML
    private Label origemLabel;
    @FXML
    private Label destinoLabel;
    @FXML
    private Label empresaLabel;
    @FXML
    private Label descricaoLabel;
    @FXML
    private Label checkLabel;
    @FXML
    private Label dataLabel;
    @FXML
    private Label estadoLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        trabalhosList.getItems().addAll(MotardBLL.getTrabalhosFinalizados((Motard) UtilizadorBLL.getUserLog()));
        if (trabalhosList.getItems() == null){
            checkLabel.setText("Histórico de trabalhos vazio!");
            return;
        }
        MotardBLL.changeCellValueTrabalhoNome(trabalhosList);
        try{
            EncomendaBLL.changeCellValueEncomendaNome(trabalhosList);
            EncomendaBLL.updateDataLabel(trabalhosList,dataLabel);
            Trabalho trabalho = MotardBLL.getTrabalho((Motard) UtilizadorBLL.getUserLog(), trabalhosList.getSelectionModel().getSelectedItem());
            Empresa empresa = EmpresaBLL.getEmpresa(trabalhosList.getSelectionModel().getSelectedItem().getIdEmpresa());
            Cliente cliente = ClienteBLL.getCliente(trabalhosList.getSelectionModel().getSelectedItem().getDetalhes().getCliente());
            EncomendaBLL.updateOrigemLabel(trabalhosList,origemLabel,empresa);
            EncomendaBLL.updateDestinoLabel(trabalhosList,destinoLabel,cliente);
            EncomendaBLL.updateEmpresaLabel(trabalhosList,empresaLabel,empresa);
            EncomendaBLL.updateEstadoLabel(trabalhosList,estadoLabel,trabalho);
            EncomendaBLL.updateDescricaoLabel(trabalhosList,descricaoLabel,trabalho);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void anterior(ActionEvent event){
        ControladorGlobal.chamaScene("motard/SceneMenuMotard.fxml", event);
    }
}

