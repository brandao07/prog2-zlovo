package com.zlovo.bll.utilizador;

import com.zlovo.dal.Repositorio;
import com.zlovo.dal.empresa.Empresa;
import com.zlovo.dal.utilizador.Administrador;
import com.zlovo.gui.administrador.EditarCategoriaController;
import com.zlovo.gui.administrador.MenuCategoriaController;

import java.util.ArrayList;

public class AdministradorBLL {

    // Método que lista todos os Administradores
    public static void listarAdministradores() {
        if (!Repositorio.getRepositorio().getUtilizadoresMap().isEmpty()) {
            System.out.println("Administradores");
            for (int key : Repositorio.getRepositorio().getUtilizadoresMap().keySet())
                if (Repositorio.getRepositorio().getUtilizadoresMap().get(key) instanceof Administrador)
                    System.out.println(Repositorio.getRepositorio().getUtilizadoresMap().get(key).getUsername());
        } else
            System.out.println("Sem administradores registados!");
    }

    public static boolean checkAdministrador() {
        for(int key : Repositorio.getRepositorio().getUtilizadoresMap().keySet())
            if(Repositorio.getRepositorio().getUtilizadoresMap().get(key) instanceof Administrador)
                return true;
        return false;
    }
}