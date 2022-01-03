package com.zlovo.gui.administrador;

import com.zlovo.bll.utilizador.AdministradorBLL;
import com.zlovo.dal.Repositorio;
import com.zlovo.gui.ControladorGlobal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class EditarCategoriaController implements Initializable {

    @FXML
    private TextField nomeTF;
    @FXML
    private TextField confirmarTF;
    @FXML
    private Label invalidDados;
    @FXML
    private Label currentCategoria;

    public void confirmar(ActionEvent event) {
        if (nomeTF.getText().isEmpty() & confirmarTF.getText().isEmpty()) {
            invalidDados.setText("Campos inválidos!");
            return;
        }
        if (!nomeTF.getText().equals(confirmarTF.getText())) {
            invalidDados.setText("Nomes não coincidem!");
            return;
        }
        if (AdministradorBLL.checkCategorias(MenuCategoriaController.categoriaSelecionada))
            AdministradorBLL.editarCategoria(nomeTF.getText());
        Repositorio.getRepositorio().getCategoriaSet().remove(MenuCategoriaController.categoriaSelecionada);
        Repositorio.getRepositorio().getCategoriaSet().add(nomeTF.getText());
        MenuCategoriaController.categoriaSelecionada = null;
        ControladorGlobal.chamaScene("administrador/SceneMenuAdmin.fxml", event);
    }

    public void anterior(ActionEvent event) {
        ControladorGlobal.chamaScene("administrador/SceneMenuAdmin.fxml", event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        currentCategoria.setText("Categoria: " + MenuCategoriaController.categoriaSelecionada);
    }
}
