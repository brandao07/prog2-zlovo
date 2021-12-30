package com.zlovo.bll.utilizador;

import com.zlovo.dal.Repositorio;
import com.zlovo.dal.empresa.Empresa;
import com.zlovo.dal.empresa.Produto;
import com.zlovo.dal.encomenda.Encomenda;
import com.zlovo.dal.encomenda.Trabalho;
import com.zlovo.dal.encomenda.enumerations.TipoEstadoEntrega;
import com.zlovo.dal.utilizador.Motard;
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
}

