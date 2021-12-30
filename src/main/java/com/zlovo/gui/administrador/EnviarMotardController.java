package com.zlovo.gui.administrador;

import com.zlovo.bll.empresa.EmpresaBLL;
import com.zlovo.bll.utilizador.ClienteBLL;
import com.zlovo.bll.utilizador.MotardBLL;
import com.zlovo.dal.empresa.Empresa;
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

public class EnviarMotardController implements Initializable {
    @FXML
    private Label encomendaLabel;
    @FXML
    private Label empresaLabel;
    @FXML
    private Label origemLabel;
    @FXML
    private Label destinoLabel;
    @FXML
    private Label checkLabel;
    @FXML
    private ListView<Motard> motardsList;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        motardsList.getItems().addAll(MotardBLL.getMotards());
        if (motardsList.getItems().isEmpty()){
            checkLabel.setText("Sem encomendas pendentes!");
            return;
        }
        try {
            Empresa empresa = EmpresaBLL.getEmpresa(SelecionarEncomendaController.encomendaSelecionada.getIdEmpresa());
            Cliente cliente = ClienteBLL.getCliente(SelecionarEncomendaController.encomendaSelecionada.getDetalhes().getCliente());
            MotardBLL.changeCellValueMotardNome(motardsList);
            encomendaLabel.setText("Encomenda #" + SelecionarEncomendaController.encomendaSelecionada.getId());
            assert empresa != null;
            empresaLabel.setText("Empresa: " + empresa.getNome());
            origemLabel.setText("Origem: " + empresa.getMorada().getLocalidade());
            assert cliente != null;
            destinoLabel.setText("Destino: " + cliente.getMorada().getLocalidade());
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void anterior(ActionEvent event){
        ControladorGlobal.chamaScene("administrador/SceneSelecionarEncomenda.fxml", event);
    }

    public void confirmar(ActionEvent event){
        if (motardsList.getSelectionModel().getSelectedItem() == null){
            checkLabel.setText("Selecione um motard!");
            return;
        }
        Motard motard = motardsList.getSelectionModel().getSelectedItem();
        MotardBLL.adicionaTrabalho(motard,SelecionarEncomendaController.encomendaSelecionada);
        ControladorGlobal.chamaScene("administrador/SceneMenuAdmin.fxml", event);
    }
}
