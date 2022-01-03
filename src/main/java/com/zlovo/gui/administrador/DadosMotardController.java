package com.zlovo.gui.administrador;

import com.zlovo.bll.utilizador.UtilizadorBLL;
import com.zlovo.dal.Morada;
import com.zlovo.dal.Repositorio;
import com.zlovo.gui.ControladorGlobal;
import com.zlovo.gui.SelecionaTipoUserController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class DadosMotardController implements Initializable {
    @FXML
    private TextField usernameTF;
    @FXML
    private PasswordField passwordF;
    @FXML
    private TextField nomeTF;
    @FXML
    private ChoiceBox<String> localidadeBox;
    @FXML
    private TextField ccTF;
    @FXML
    private TextField nifTF;
    @FXML
    private TextField telefoneTF;
    @FXML
    private TextField ruaTF;
    @FXML
    private TextField portaTF;
    @FXML
    private Label invalidDados;

    public void confirmar(ActionEvent event) {
        if (!nomeTF.getText().isEmpty() & !ccTF.getText().isEmpty() & !nifTF.getText().isEmpty() & !telefoneTF.getText().isEmpty() & !ruaTF.getText().isEmpty() & !portaTF.getText().isEmpty() & !usernameTF.getText().isEmpty() & !passwordF.getText().isEmpty()) {
            if (UtilizadorBLL.checkUsername(usernameTF.getText())) {
                invalidDados.setText("Username já registado!");
                return;
            }
            SelecionaTipoFuncionarioController.utilizador.setUsername(usernameTF.getText());
            SelecionaTipoFuncionarioController.utilizador.setPassword(passwordF.getText());
            SelecionaTipoFuncionarioController.utilizador.setNome(nomeTF.getText());
            SelecionaTipoFuncionarioController.utilizador.setNumCC(ccTF.getText());
            SelecionaTipoFuncionarioController.utilizador.setNif(nifTF.getText());
            SelecionaTipoFuncionarioController.utilizador.setTelefone(telefoneTF.getText());
            Morada morada = new Morada();
            morada.setLocalidade(localidadeBox.getValue());
            morada.setRua(ruaTF.getText());
            morada.setnPorta(Integer.parseInt(portaTF.getText()));
            SelecionaTipoFuncionarioController.utilizador.setMorada(morada);
            UtilizadorBLL.criarUtilizador(SelecionaTipoFuncionarioController.utilizador);
            SelecionaTipoFuncionarioController.utilizador = null;
            ControladorGlobal.chamaScene("administrador/SceneMenuAdmin.fxml", event);
        } else invalidDados.setText("Dados inválidos!");
    }

    public void cancelar(ActionEvent event) {
        SelecionaTipoUserController.utilizador = null;
        ControladorGlobal.chamaScene("administrador/SceneMenuAdmin.fxml", event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        localidadeBox.getItems().addAll(Repositorio.getRepositorio().getLocalidadeSet());
    }
}
