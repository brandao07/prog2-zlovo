package com.zlovo.bll.utilizador;

import com.zlovo.dal.Repositorio;
import com.zlovo.dal.encomenda.Encomenda;
import com.zlovo.dal.encomenda.enumerations.TipoEstado;
import com.zlovo.dal.utilizador.Cliente;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ClienteBLL {

    // Método que lista todos os Clientes
    public static void listarClientes(){
        if(!Repositorio.getRepositorio().getUtilizadoresMap().isEmpty()) {
            System.out.println("Clientes");
            for (int key : Repositorio.getRepositorio().getUtilizadoresMap().keySet())
                if (Repositorio.getRepositorio().getUtilizadoresMap().get(key) instanceof Cliente)
                    System.out.println(Repositorio.getRepositorio().getUtilizadoresMap().get(key).getUsername());
        } else
            System.out.println("Sem clientes registados!");
    }

    public static void updateCliente(@NotNull Cliente cliente){
        Repositorio.getRepositorio().getUtilizadoresMap().remove(cliente.getIdUtilizador());
        Repositorio.getRepositorio().getUtilizadoresMap().put(cliente.getIdUtilizador(), cliente);
    }

    public static @NotNull ArrayList<Encomenda> encomendasPorPagar(@NotNull Cliente cliente){
        ArrayList<Encomenda> encomendas = new ArrayList<>();
        for (Encomenda e : cliente.getHistorial())
            if (e.getDetalhes().getTipoEstado().equals(TipoEstado.CONFIRMADA_POR_PAGAR))
                encomendas.add(e);
        return encomendas;
    }

    public static @NotNull ArrayList<Encomenda> encomendasConfirmadas(@NotNull Cliente cliente){
        ArrayList<Encomenda> encomendas = new ArrayList<>();
        for (Encomenda e : cliente.getHistorial())
            if (e.getDetalhes().getTipoEstado().equals(TipoEstado.CONFIRMADA))
                encomendas.add(e);
        return encomendas;
    }
}
