package com.zlovo.gui.cliente;

import com.zlovo.bll.utilizador.UtilizadorBLL;
import com.zlovo.dal.encomenda.enumerations.TipoEstado;
import com.zlovo.dal.encomenda.enumerations.TipoEstadoEntrega;
import com.zlovo.dal.encomenda.enumerations.TipoPagamento;
import com.zlovo.dal.utilizador.Cliente;
import com.zlovo.gui.ControladorGlobal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class SelecionarMetodoPagamentoController implements Initializable {

    @FXML
    private ChoiceBox<TipoPagamento> tipoPagamentoCB;
    @FXML
    private Label checkDados;
    @FXML
    private Label checkPagamento;

    public void confirmar(ActionEvent event) {
        if (tipoPagamentoCB.getSelectionModel().getSelectedItem() == null) {
            checkDados.setText("Selecione uma encomenda!");
            return;
        }

        if (tipoPagamentoCB.getSelectionModel().getSelectedItem().equals(TipoPagamento.APLICACAO)) {
            if ((((Cliente) UtilizadorBLL.getUserLog()).getSaldo()) < EncomendasPorPagarController.encomendaSelecionada.getPreco()) {
                checkPagamento.setText("Saldo insuficiente!");
                return;
            }
            ((Cliente) UtilizadorBLL.getUserLog()).setSaldo(((Cliente) UtilizadorBLL.getUserLog()).getSaldo() - EncomendasPorPagarController.encomendaSelecionada.getPreco());
            EncomendasPorPagarController.encomendaSelecionada.getPagamento().setEstado(true);
            EncomendasPorPagarController.encomendaSelecionada.getPagamento().setTipo(TipoPagamento.APLICACAO);
        } else if (tipoPagamentoCB.getSelectionModel().getSelectedItem().equals(TipoPagamento.DINHEIRO)) {
            EncomendasPorPagarController.encomendaSelecionada.getPagamento().setEstado(false);
            EncomendasPorPagarController.encomendaSelecionada.getPagamento().setTipo(TipoPagamento.DINHEIRO);
        } else if (tipoPagamentoCB.getSelectionModel().getSelectedItem().equals(TipoPagamento.MULTIBANCO)) {
            EncomendasPorPagarController.encomendaSelecionada.getPagamento().setEstado(true);
            EncomendasPorPagarController.encomendaSelecionada.getPagamento().setTipo(TipoPagamento.MULTIBANCO);
        } else if (tipoPagamentoCB.getSelectionModel().getSelectedItem().equals(TipoPagamento.CARTAO_CREDITO)) {
            EncomendasPorPagarController.encomendaSelecionada.getPagamento().setEstado(true);
            EncomendasPorPagarController.encomendaSelecionada.getPagamento().setTipo(TipoPagamento.CARTAO_CREDITO);
        }
        EncomendasPorPagarController.encomendaSelecionada.getDetalhes().setEstadoEntrega(TipoEstadoEntrega.A_PERPARAR);
        EncomendasPorPagarController.encomendaSelecionada.getDetalhes().setTipoEstado(TipoEstado.CONFIRMADA);
        ControladorGlobal.chamaScene("cliente/SceneEncomendasPorPagar.fxml", event);
    }

    public void anterior(ActionEvent event) {
        ControladorGlobal.chamaScene("cliente/SceneEncomendasPorPagar.fxml", event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tipoPagamentoCB.getItems().addAll(TipoPagamento.values());
    }
}
