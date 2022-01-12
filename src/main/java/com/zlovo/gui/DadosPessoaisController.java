package com.zlovo.gui;

import com.zlovo.bll.utilizador.UtilizadorBLL;
import com.zlovo.dal.Morada;
import com.zlovo.dal.Repositorio;
import com.zlovo.dal.utilizador.Cliente;
import com.zlovo.dal.utilizador.Empresario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class DadosPessoaisController implements Initializable {
    @FXML
    private ChoiceBox<String> localidadeBox;
    @FXML
    private TextField nomeTextfield;
    @FXML
    private TextField ccTextfield;
    @FXML
    private TextField nifTextfield;
    @FXML
    private TextField telefoneTextfield;
    @FXML
    private TextField ruaTextfield;
    @FXML
    private TextField portaTextfield;
    @FXML
    private Label invalidDados;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        localidadeBox.getItems().addAll(Repositorio.getRepositorio().getLocalidadeSet());
    }

    public void seguinte(ActionEvent event) {
        if (!nomeTextfield.getText().isEmpty() & !ccTextfield.getText().isEmpty() & !nifTextfield.getText().isEmpty() & !telefoneTextfield.getText().isEmpty() & !ruaTextfield.getText().isEmpty() & !portaTextfield.getText().isEmpty()) {
            SelecionaTipoUserController.utilizador.setNome(nomeTextfield.getText());
            SelecionaTipoUserController.utilizador.setNumCC(ccTextfield.getText());
            SelecionaTipoUserController.utilizador.setNif(nifTextfield.getText());
            SelecionaTipoUserController.utilizador.setTelefone(telefoneTextfield.getText());
            Morada morada = new Morada();
            morada.setLocalidade(localidadeBox.getValue());
            morada.setRua(ruaTextfield.getText());
            morada.setnPorta(Integer.parseInt(portaTextfield.getText()));
            SelecionaTipoUserController.utilizador.setMorada(morada);
            if (SelecionaTipoUserController.utilizador instanceof Cliente) {
                UtilizadorBLL.criarUtilizador(SelecionaTipoUserController.utilizador);
                ControladorGlobal.chamaScene("SceneLogin.fxml", event);
            }
            else if(SelecionaTipoUserController.utilizador instanceof Empresario)
                ControladorGlobal.chamaScene("SceneCriarNovaEmpresaFirst.fxml", event);
        } else
            invalidDados.setText("Campos por preencher!");
    }

    public void anterior(ActionEvent event) {
        ControladorGlobal.chamaScene("SceneLogin.fxml", event);
    }
}
