package com.zlovo.bll.utilizador;

import com.zlovo.dal.Repositorio;
import com.zlovo.dal.empresa.Empresa;
import com.zlovo.dal.utilizador.Administrador;
import com.zlovo.dal.utilizador.Empresario;
import com.zlovo.gui.administrador.MenuCategoriaController;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import org.jetbrains.annotations.NotNull;

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

    // Método que verifica se o utilizador é do tipo Addministrador
    public static boolean checkAdministrador() {
        for (int key : Repositorio.getRepositorio().getUtilizadoresMap().keySet())
            if (Repositorio.getRepositorio().getUtilizadoresMap().get(key) instanceof Administrador)
                return true;
        return false;
    }

    // Método que verifica se a categoria existe
    public static boolean checkCategorias(String categoria) {
        if (Repositorio.getRepositorio().getCategoriaSet().contains(categoria))
            if (!Repositorio.getRepositorio().getCategoriasEmpresasMap().isEmpty())
                return Repositorio.getRepositorio().getCategoriasEmpresasMap().containsKey(categoria);
        return false;
    }

    // Método que altera o nome de uma categoria
    public static void editarCategoria(String categoria) {
        for (String key : Repositorio.getRepositorio().getCategoriasEmpresasMap().keySet()) {
            if (key.equals(MenuCategoriaController.categoriaSelecionada)) {
                ArrayList<Empresa> empresas = Repositorio.getRepositorio().getCategoriasEmpresasMap().remove(key);
                Repositorio.getRepositorio().getCategoriasEmpresasMap().put(categoria, empresas);
            }
        }
    }

    // Método que remove uma categoria
    public static void removerCategoria(String categoria) {
        for (String key : Repositorio.getRepositorio().getCategoriasEmpresasMap().keySet())
            if (key.equals(categoria))
                Repositorio.getRepositorio().getCategoriasEmpresasMap().remove(key);
        Repositorio.getRepositorio().getCategoriaSet().remove(categoria);
    }

    // Método que devolve todos os empresários
    public static @NotNull ArrayList<Empresario> getEmpresarios() {
        ArrayList<Empresario> empresarios = new ArrayList<>();
        for (int key : Repositorio.getRepositorio().getUtilizadoresMap().keySet())
            if (Repositorio.getRepositorio().getUtilizadoresMap().get(key) instanceof Empresario)
                empresarios.add((Empresario) Repositorio.getRepositorio().getUtilizadoresMap().get(key));
        return empresarios;
    }

    public static void changeCellValueEmpresarioNome(@NotNull ListView<Empresario> listView) {
        listView.setCellFactory(new Callback<>() {
            public ListCell<Empresario> call(ListView<Empresario> param) {
                return new ListCell<>() {
                    @Override
                    public void updateItem(Empresario item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            setText(item.getUsername());
                        }
                    }
                };
            }
        });
    }

    public static void updateNomeEmpresarioLabel(@NotNull ListView<Empresario> listView, Label label) {
        listView.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            String empresario = listView.getSelectionModel().getSelectedItem().getNome();
            label.setText(empresario);
        });
    }
}