package com.zlovo.gui.cliente;

import com.zlovo.bll.empresa.ProdutoBLL;
import com.zlovo.dal.Repositorio;
import com.zlovo.gui.ControladorGlobal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class SelecionarCategoriaController implements Initializable {
    @FXML
    private Label checkDados;
    @FXML
    private ListView<String> categoriasList;
    @FXML
    private Label categoriaLabel;

    public static String categoriaSelecionada;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        categoriasList.getItems().addAll(Repositorio.getRepositorio().getCategoriaSet());
        // se a categoria tiver sem produtos o programa cracha
        ProdutoBLL.updateCategoriaLabel(categoriasList,categoriaLabel);
    }

    public void seguinte(ActionEvent event){
        categoriaSelecionada = categoriasList.getSelectionModel().getSelectedItem();
        if (categoriaSelecionada == null) {
            checkDados.setText("Selecione uma categoria!");
            return;
        }
        ControladorGlobal.chamaScene("cliente/SceneSelecionarProdutos.fxml", event);
    }

    public void anterior(ActionEvent event){
        ControladorGlobal.chamaScene("cliente/SceneSelecionarEmpresa.fxml", event);
    }
}
