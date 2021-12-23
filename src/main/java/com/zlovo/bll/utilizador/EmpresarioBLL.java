package com.zlovo.bll.utilizador;

import com.zlovo.dal.Repositorio;
import com.zlovo.dal.empresa.Empresa;
import com.zlovo.dal.utilizador.Empresario;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import org.jetbrains.annotations.NotNull;

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
    // Método que adiciona uma empresa
    public static boolean adicionarEmpresa(Empresa empresa){
        for (int key : Repositorio.getRepositorio().getUtilizadoresMap().keySet())
            if (Repositorio.getRepositorio().getUtilizadoresMap().get(key) instanceof Empresario)
                if (Repositorio.getRepositorio().getUtilizadoresMap().get(key).getUsername().equals(UtilizadorBLL.getUserLog().getUsername())){
                    ((Empresario) Repositorio.getRepositorio().getUtilizadoresMap().get(key)).getEmpresasList().add(empresa);
                    return true;
                }
        return false;
    }

    public static void changeCellValueEmpresaNome (@NotNull ListView<Empresa> myListView){
        myListView.setCellFactory(new Callback<>() {
            public ListCell<Empresa> call(ListView<Empresa> param) {
                return new ListCell<>() {
                    @Override
                    public void updateItem(Empresa item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            setText(item.getNome());
                        }
                    }
                };
            }
        });
    }

    public static void updateNomeEmpresaLabel(@NotNull ListView<Empresa> myListView, Label myLabel){
        myListView.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            String currentEmpresa = myListView.getSelectionModel().getSelectedItem().getNome();
            myLabel.setText(currentEmpresa);
        });
    }
}

