package com.zlovo.bll.utilizador;

import com.zlovo.dal.Repositorio;
import com.zlovo.dal.utilizador.Motard;

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
}
