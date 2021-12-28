package com.zlovo.bll.utilizador;

import com.zlovo.dal.Repositorio;
import com.zlovo.dal.utilizador.Cliente;
import org.jetbrains.annotations.NotNull;

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
}
