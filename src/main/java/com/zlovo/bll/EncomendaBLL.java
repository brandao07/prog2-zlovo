package com.zlovo.bll;

import com.zlovo.bll.utilizador.UtilizadorBLL;
import com.zlovo.dal.Repositorio;
import com.zlovo.dal.empresa.Empresa;
import com.zlovo.dal.empresa.Produto;
import com.zlovo.dal.encomenda.Encomenda;
import com.zlovo.dal.encomenda.Trabalho;
import com.zlovo.dal.encomenda.enumerations.TipoEstado;
import com.zlovo.dal.encomenda.enumerations.TipoEstadoEntrega;
import com.zlovo.dal.utilizador.Cliente;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class EncomendaBLL {

    public static boolean checkProduto(@NotNull Encomenda encomenda, Produto produto) {
        for (Produto p : encomenda.getProdutosList())
            if (p.getId() == produto.getId())
                return true;
        return false;
    }

    public static void updatePrecoLabel(@NotNull ListView<Produto> myListView, Label myLabel) {
        myListView.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            double dados = myListView.getSelectionModel().getSelectedItem().getPreco();
            myLabel.setText("Preço: " + dados * myListView.getSelectionModel().getSelectedItem().getQuantidade());
        });
    }

    public static void updateQuantidadeLabel(@NotNull ListView<Produto> myListView, Label myLabel) {
        myListView.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            int dados = myListView.getSelectionModel().getSelectedItem().getQuantidade();
            myLabel.setText("Quantidade: " + dados);
        });
    }

    public static void updateCategoriaLabel(@NotNull ListView<Produto> myListView, Label myLabel) {
        myListView.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            String dados = myListView.getSelectionModel().getSelectedItem().getCategoria();
            myLabel.setText("Categoria: " + dados);
        });
    }

    public static void updateClienteLabel(@NotNull ListView<Encomenda> myListView, Label myLabel) {
        myListView.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            String dados = myListView.getSelectionModel().getSelectedItem().getDetalhes().getCliente();
            myLabel.setText("Cliente: " + dados);
        });
    }

    public static void updateHorarioLabel(@NotNull ListView<Encomenda> myListView, Label myLabel) {
        myListView.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            String dados = String.valueOf(myListView.getSelectionModel().getSelectedItem().getHorario());
            myLabel.setText("Horário: " + dados);
        });
    }

    public static void updateDataLabel(@NotNull ListView<Encomenda> myListView, Label myLabel) {
        myListView.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            String dados = String.valueOf(myListView.getSelectionModel().getSelectedItem().getDataCliente());
            myLabel.setText("Data: " + dados);
        });
    }

    public static void updatePrecoTotalLabel(@NotNull ListView<Encomenda> myListView, Label myLabel) {
        myListView.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            double dados = somaPrecoProdutosCarrinho(myListView.getSelectionModel().getSelectedItem().getProdutosList());
            myLabel.setText("Preço Total: " + dados);
        });
    }

    public static void updateMetodoPagamentoLabel(@NotNull ListView<Encomenda> myListView, Label myLabel) {
        myListView.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            String dados = String.valueOf(myListView.getSelectionModel().getSelectedItem().getPagamento().getTipo());
            myLabel.setText("Método Pagamento: " + dados);
        });
    }

    public static void updateTipoEstadoLabel(@NotNull ListView<Encomenda> myListView, Label myLabel) {
        myListView.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            String dados = String.valueOf(myListView.getSelectionModel().getSelectedItem().getDetalhes().getEstadoEntrega());
            myLabel.setText("Estado Entrega: " + dados);
        });
    }

    public static double somaPrecoProdutosCarrinho(@NotNull ArrayList<Produto> carrinho) {
        double result = 0;
        for (Produto p : carrinho)
            result += (p.getPreco() * p.getQuantidade());
        return result;
    }

    public static void criarEncomenda(@NotNull Encomenda encomenda) {
        encomenda.setId(Repositorio.getRepositorio().getNumEncomendas() + 1);
        Repositorio.getRepositorio().getEncomendasMap().put(encomenda.getId(), encomenda);
        Repositorio.getRepositorio().setNumEncomendas(encomenda.getId());
        ((Cliente) UtilizadorBLL.getUserLog()).getHistorial().add(encomenda);
        Repositorio.getRepositorio().setNumEncomendasChart(Repositorio.getRepositorio().getNumEncomendasChart() + 1);
    }

    public static @NotNull ArrayList<Encomenda> getPorConfirmarEncomendas() {
        ArrayList<Encomenda> encomendas = new ArrayList<>();
        for (int key : Repositorio.getRepositorio().getEncomendasMap().keySet())
            if (Repositorio.getRepositorio().getEncomendasMap().get(key).getDetalhes().getTipoEstado().equals(TipoEstado.AGUARDAR))
                encomendas.add(Repositorio.getRepositorio().getEncomendasMap().get(key));
        return encomendas;
    }

    public static @NotNull ArrayList<Encomenda> getConfirmarEncomendas() {
        ArrayList<Encomenda> encomendas = new ArrayList<>();
        for (int key : Repositorio.getRepositorio().getEncomendasMap().keySet())
            if (Repositorio.getRepositorio().getEncomendasMap().get(key).getDetalhes().getTipoEstado().equals(TipoEstado.CONFIRMADA) & Repositorio.getRepositorio().getEncomendasMap().get(key).getDetalhes().getEstadoEntrega().equals(TipoEstadoEntrega.A_PERPARAR))
                encomendas.add(Repositorio.getRepositorio().getEncomendasMap().get(key));
        return encomendas;
    }

    public static void changeCellValueEncomendaNome(@NotNull ListView<Encomenda> myListView) {
        myListView.setCellFactory(new Callback<>() {
            public ListCell<Encomenda> call(ListView<Encomenda> param) {
                return new ListCell<>() {
                    @Override
                    public void updateItem(Encomenda item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) setText("Encomenda #" + item.getId());
                    }
                };
            }
        });
    }

    public static void updateDestinoLabel(@NotNull ListView<Encomenda> myListView, Label myLabel, Cliente cliente) {
        myListView.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            String dados = String.valueOf(cliente.getMorada().getLocalidade());
            myLabel.setText("Destino: " + dados);
        });
    }

    public static void updateOrigemLabel(@NotNull ListView<Encomenda> myListView, Label myLabel, Empresa empresa) {
        myListView.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            String dados = String.valueOf(empresa.getMorada().getLocalidade());
            myLabel.setText("Origem: " + dados);
        });
    }

    public static void updateEmpresaLabel(@NotNull ListView<Encomenda> myListView, Label myLabel, Empresa empresa) {
        myListView.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            String dados = String.valueOf(empresa.getNome());
            myLabel.setText("Empresa: " + dados);
        });
    }

    public static void updateEstadoLabel(@NotNull ListView<Encomenda> myListView, Label myLabel, Trabalho trabalho) {
        myListView.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            String dados = String.valueOf(trabalho.isEstado());
            myLabel.setText("Estado: " + dados);
        });
    }

    public static void updateDescricaoLabel(@NotNull ListView<Encomenda> myListView, Label myLabel, Trabalho trabalho) {
        myListView.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            String dados = String.valueOf(trabalho.getDescricao());
            myLabel.setText("Descrição: " + dados);
        });
    }

    public static void updateNPortaClienteLabel(@NotNull ListView<Encomenda> myListView, Label myLabel, Cliente cliente) {
        myListView.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            String dados = String.valueOf(cliente.getMorada().getnPorta());
            myLabel.setText("(Destino) Número da Porta: " + dados);
        });
    }

    public static void updateRuaClienteLabel(@NotNull ListView<Encomenda> myListView, Label myLabel, Cliente cliente) {
        myListView.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            String dados = String.valueOf(cliente.getMorada().getRua());
            myLabel.setText("(Destino) Rua : " + dados);
        });
    }

    public static void updateRuaEmpresaLabel(@NotNull ListView<Encomenda> myListView, Label myLabel, Empresa empresa) {
        myListView.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            String dados = String.valueOf(empresa.getMorada().getRua());
            myLabel.setText("(Origem) Rua: " + dados);
        });
    }

    public static void updateNPortaEmpresaLabel(@NotNull ListView<Encomenda> myListView, Label myLabel, Empresa empresa) {
        myListView.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            String dados = String.valueOf(empresa.getMorada().getnPorta());
            myLabel.setText("(Origem) Número da Porta: " + dados);
        });
    }
}
