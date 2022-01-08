package com.zlovo.gui.motard;

import com.zlovo.bll.utilizador.MotardBLL;
import com.zlovo.bll.utilizador.UtilizadorBLL;
import com.zlovo.dal.empresa.Empresa;
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
import java.util.ResourceBundle;

public class ListarOrdensController implements Initializable {

    public static Cliente cliente;
    public static Empresa empresa;
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
    @FXML
    private Label clienteLabel;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        trabalhosList.getItems().addAll(MotardBLL.getTrabalhosFinalizados((Motard) UtilizadorBLL.getUserLog()));
        if (trabalhosList.getItems() == null) {
            checkLabel.setText("Histórico de trabalhos vazio!");
            return;
        }
        MotardBLL.changeCellValueTrabalhoNome(trabalhosList);
        try {
            MotardBLL.updateDescricaoLabel(trabalhosList, descricaoLabel);
            MotardBLL.updateDestinoLabel(trabalhosList, destinoLabel);
            MotardBLL.updateOrigemLabel(trabalhosList, origemLabel);
            MotardBLL.updateEmpresaLabel(trabalhosList, empresaLabel);
            MotardBLL.updateTipoEstadoLabel(trabalhosList, estadoLabel);
            MotardBLL.updateDataLabel(trabalhosList, dataLabel);
            MotardBLL.updateClienteLabel(trabalhosList, clienteLabel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void anterior(ActionEvent event) {
        ControladorGlobal.chamaScene("motard/SceneMenuMotard.fxml", event);
    }
}

