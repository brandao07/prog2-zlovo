package com.zlovo.gui.motard;

import com.zlovo.bll.empresa.EmpresaBLL;
import com.zlovo.bll.utilizador.ClienteBLL;
import com.zlovo.bll.utilizador.MotardBLL;
import com.zlovo.bll.utilizador.UtilizadorBLL;
import com.zlovo.dal.empresa.Empresa;
import com.zlovo.dal.encomenda.Trabalho;
import com.zlovo.dal.encomenda.enumerations.TipoEstadoEntrega;
import com.zlovo.dal.utilizador.Cliente;
import com.zlovo.dal.utilizador.Motard;
import com.zlovo.gui.ControladorGlobal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

public class EncomendaPendenteController implements Initializable {
    public static Trabalho trabalho;
    @FXML
    private Label encomendaLabel;
    @FXML
    private Label empresaLabel;
    @FXML
    private Label origemLabel;
    @FXML
    private Label destinoLabel;
    @FXML
    private Label clienteLabel;
    @FXML
    private ChoiceBox<TipoEstadoEntrega> estadoBox;
    @FXML
    private TextArea textArea;
    @FXML
    private Label checkDados;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        estadoBox.getItems().add(TipoEstadoEntrega.ENTREGUE);
        estadoBox.getItems().add(TipoEstadoEntrega.FALHA);
        trabalho = MotardBLL.getCurrentTrabalho((Motard) UtilizadorBLL.getUserLog());
        assert trabalho != null;
        Empresa empresa = EmpresaBLL.getEmpresa(trabalho.getEncomenda().getIdEmpresa());
        Cliente cliente = ClienteBLL.getCliente(trabalho.getEncomenda().getDetalhes().getCliente());
        assert empresa != null;
        assert cliente != null;
        encomendaLabel.setText("Encomenda #" + trabalho.getEncomenda().getId());
        clienteLabel.setText("Cliente: " + cliente.getNome());
        empresaLabel.setText("Empresa: " + empresa.getNome());
        origemLabel.setText("Origem: " + empresa.getMorada().getRua() + ", " + empresa.getMorada().getnPorta() + ", " + empresa.getMorada().getLocalidade());
        destinoLabel.setText("Destino: " + cliente.getMorada().getRua() + ", " + cliente.getMorada().getnPorta() + ", " + cliente.getMorada().getLocalidade());
    }

    public void confirmar(ActionEvent event) {
        if (estadoBox.getValue() == null) {
            checkDados.setText("Escolha estado da encomenda!");
            return;
        }
        trabalho.getEncomenda().getDetalhes().setEstadoEntrega(estadoBox.getValue());
        if (trabalho.getEncomenda().getDetalhes().getEstadoEntrega().equals(TipoEstadoEntrega.FALHA))
            trabalho.setDescricao(textArea.getText());
        trabalho.setEstado(true);
        ((Motard) UtilizadorBLL.getUserLog()).setDisponivel(true);
        ControladorGlobal.chamaScene("motard/SceneMenuMotard.fxml", event);
    }

    public void anterior(ActionEvent event) {
        ControladorGlobal.chamaScene("motard/SceneMenuMotard.fxml", event);
    }
}
