package com.zlovo.bll.utilizador;

import com.zlovo.dal.Repositorio;
import com.zlovo.dal.empresa.Empresa;
import com.zlovo.dal.empresa.Produto;
import com.zlovo.dal.encomenda.Encomenda;
import com.zlovo.dal.encomenda.Trabalho;
import com.zlovo.dal.encomenda.enumerations.TipoEstadoEntrega;
import com.zlovo.dal.utilizador.Cliente;
import com.zlovo.dal.utilizador.Motard;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class MotardBLL {

    // Método que lista todos os Motards
    public static void listarMotards(){
        if(!Repositorio.getRepositorio().getUtilizadoresMap().isEmpty()) {
            System.out.println("Motards");
            for (int key : Repositorio.getRepositorio().getUtilizadoresMap().keySet())
                if (Repositorio.getRepositorio().getUtilizadoresMap().get(key) instanceof Motard)
                    System.out.println(Repositorio.getRepositorio().getUtilizadoresMap().get(key).getUsername());
        } else
            System.out.println("Sem motards registados!");
    }

    public static @NotNull ArrayList<Motard> getMotards(){
        ArrayList<Motard> motards = new ArrayList<>();
        for (int key: Repositorio.getRepositorio().getUtilizadoresMap().keySet())
            if (Repositorio.getRepositorio().getUtilizadoresMap().get(key) instanceof Motard)
                if (((Motard) Repositorio.getRepositorio().getUtilizadoresMap().get(key)).isDisponivel())
                    motards.add((Motard) Repositorio.getRepositorio().getUtilizadoresMap().get(key));
        return motards;
    }

    public static void changeCellValueMotardNome (@NotNull ListView<Motard> myListView){
        myListView.setCellFactory(new Callback<>() {
            public ListCell<Motard> call(ListView<Motard> param) {
                return new ListCell<>() {
                    @Override
                    public void updateItem(Motard item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) setText(item.getNome());
                    }
                };
            }
        });
    }

    public static void adicionaTrabalho(@NotNull Motard motard, Encomenda encomenda){
        Trabalho trabalho = new Trabalho(encomenda);
        motard.setDisponivel(false);
        motard.getHistorial().add(trabalho);
        encomenda.getDetalhes().setEstadoEntrega(TipoEstadoEntrega.A_CAMINHO);
    }

    public static @NotNull ArrayList<Encomenda> getEncomendas(@NotNull Motard motard){
        ArrayList<Encomenda> encomendas = new ArrayList<>();
        for (Trabalho trabalho : motard.getHistorial())
            if (trabalho.isEstado())
                encomendas.add(trabalho.getEncomenda());
        return encomendas;
    }

    public static @Nullable Trabalho getTrabalho(@NotNull Motard motard, Encomenda encomenda){
        for (Trabalho trabalho : motard.getHistorial())
            if (trabalho.getEncomenda().getId() == encomenda.getId())
                return trabalho;
        return null;
    }

    public static @Nullable Trabalho getCurrentTrabalho(@NotNull Motard motard){
        for (Trabalho trabalho : motard.getHistorial())
            if (!trabalho.isEstado())
                return trabalho;
        return null;
    }

    public static @NotNull ArrayList<Trabalho> getTrabalhosFinalizados(@NotNull Motard motard){
        ArrayList<Trabalho> trabalhos = new ArrayList<>();
        for (Trabalho trabalho : motard.getHistorial()) {
            if (trabalho.getEncomenda().getDetalhes().getEstadoEntrega().equals(TipoEstadoEntrega.ENTREGUE))
                trabalhos.add(trabalho);
            if (trabalho.getEncomenda().getDetalhes().getEstadoEntrega().equals(TipoEstadoEntrega.FALHA))
                trabalhos.add(trabalho);
        }
        return trabalhos;
    }

    public static void updateDestinoLabel(@NotNull ListView<Trabalho> myListView, Label myLabel){
        myListView.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            Cliente cliente = ClienteBLL.getCliente(myListView.getSelectionModel().getSelectedItem().getEncomenda().getDetalhes().getCliente());
            assert cliente != null;
            String dados = String.valueOf(cliente.getMorada().getLocalidade());
            myLabel.setText("Destino: " + dados);
        });
    }

    public static void updateOrigemLabel(@NotNull ListView<Trabalho> myListView, Label myLabel){
        myListView.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            Empresa empresa = EmpresaBLL.getEmpresa(myListView.getSelectionModel().getSelectedItem().getEncomenda().getIdEmpresa());
            assert empresa != null;
            String dados = String.valueOf(empresa.getMorada().getLocalidade());
            myLabel.setText("Origem: " + dados);
        });
    }

    public static void updateEmpresaLabel(@NotNull ListView<Trabalho> myListView, Label myLabel){
        myListView.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            Empresa empresa = EmpresaBLL.getEmpresa(myListView.getSelectionModel().getSelectedItem().getEncomenda().getIdEmpresa());
            assert empresa != null;
            String dados = String.valueOf(empresa.getNome());
            myLabel.setText("Empresa: " + dados);
        });
    }

    public static void updateDescricaoLabel(@NotNull ListView<Trabalho> myListView, Label myLabel){
        myListView.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            String dados = String.valueOf(myListView.getSelectionModel().getSelectedItem().getDescricao());
            myLabel.setText("Descrição: " + dados);
        });
    }

    public static void updateNPortaClienteLabel(@NotNull ListView<Trabalho> myListView, Label myLabel){
        myListView.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            Cliente cliente = ClienteBLL.getCliente(myListView.getSelectionModel().getSelectedItem().getEncomenda().getDetalhes().getCliente());
            assert cliente != null;
            String dados = String.valueOf(cliente.getMorada().getnPorta());
            myLabel.setText("(Destino) Número da Porta: " + dados);
        });
    }

    public static void updateRuaClienteLabel(@NotNull ListView<Trabalho> myListView, Label myLabel){
        myListView.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            Cliente cliente = ClienteBLL.getCliente(myListView.getSelectionModel().getSelectedItem().getEncomenda().getDetalhes().getCliente());
            assert cliente != null;
            String dados = String.valueOf(cliente.getMorada().getRua());
            myLabel.setText("(Destino) Rua : " + dados);
        });
    }

    public static void updateRuaEmpresaLabel(@NotNull ListView<Trabalho> myListView, Label myLabel){
        myListView.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            Empresa empresa = EmpresaBLL.getEmpresa(myListView.getSelectionModel().getSelectedItem().getEncomenda().getIdEmpresa());
            assert empresa != null;
            String dados = String.valueOf(empresa.getMorada().getRua());
            myLabel.setText("(Origem) Rua: " + dados);
        });
    }

    public static void updateNPortaEmpresaLabel(@NotNull ListView<Trabalho> myListView, Label myLabel){
        myListView.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            Empresa empresa = EmpresaBLL.getEmpresa(myListView.getSelectionModel().getSelectedItem().getEncomenda().getIdEmpresa());
            assert empresa != null;
            String dados = String.valueOf(empresa.getMorada().getnPorta());
            myLabel.setText("(Origem) Número da Porta: " + dados);
        });
    }

    public static void updateTipoEstadoLabel(@NotNull ListView<Trabalho> myListView, Label myLabel){
        myListView.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            String dados = String.valueOf(myListView.getSelectionModel().getSelectedItem().getEncomenda().getDetalhes().getEstadoEntrega());
            myLabel.setText("Estado Entrega: " + dados);
        });
    }

    public static void updateClienteLabel(@NotNull ListView<Trabalho> myListView, Label myLabel){
        myListView.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            String dados = myListView.getSelectionModel().getSelectedItem().getEncomenda().getDetalhes().getCliente();
            myLabel.setText("Cliente: " + dados);
        });
    }

    public static void updateDataLabel(@NotNull ListView<Trabalho> myListView, Label myLabel){
        myListView.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            String dados = String.valueOf(myListView.getSelectionModel().getSelectedItem().getEncomenda().getDataCliente());
            myLabel.setText("Data: " + dados);
        });
    }

    public static void changeCellValueTrabalhoNome (@NotNull ListView<Trabalho> myListView){
        myListView.setCellFactory(new Callback<>() {
            public ListCell<Trabalho> call(ListView<Trabalho> param) {
                return new ListCell<>() {
                    @Override
                    public void updateItem(Trabalho item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) setText("Trabalho #" + item.getEncomenda().getId());
                    }
                };
            }
        });
    }
}

