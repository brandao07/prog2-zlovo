package com.zlovo.bll.utilizador;

import com.zlovo.dal.Repositorio;
import com.zlovo.dal.utilizador.Empresario;

public class EmpresarioBLL {

    // Método que lista todos os Empresarios
    public static void listarEmpresarios(){
        if(!Repositorio.getRepositorio().getUtilizadoresMap().isEmpty()) {
            System.out.println("Empresários");
            for (int key : Repositorio.getRepositorio().getUtilizadoresMap().keySet())
                if (Repositorio.getRepositorio().getUtilizadoresMap().get(key) instanceof Empresario)
                    System.out.println(Repositorio.getRepositorio().getUtilizadoresMap().get(key).getUsername());
        } else
            System.out.println("Sem donos de empresas registados!");
    }
}

