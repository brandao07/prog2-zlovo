package com.zlovo.gui;

import com.zlovo.bll.empresa.EmpresaBLL;
import com.zlovo.bll.utilizador.UtilizadorBLL;
import com.zlovo.dal.Morada;
import com.zlovo.dal.Repositorio;
import com.zlovo.dal.empresa.Empresa;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class CriarNovaEmpresaFirstController implements Initializable {
    @FXML
    private TextField nomeTF;
    @FXML
    private TextField telefoneTF;
    @FXML
    private TextField ruaTF;
    @FXML
    private TextField portaTF;
    @FXML
    private ChoiceBox<String> localidadeCB;
    @FXML
    private Label checkDados;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        localidadeCB.getItems().addAll(Repositorio.getRepositorio().getLocalidadeSet());
    }

    public void confirmar(ActionEvent event) {
        if (!nomeTF.getText().isEmpty() & !telefoneTF.getText().isEmpty() & !ruaTF.getText().isEmpty() & !portaTF.getText().isEmpty() & !localidadeCB.getValue().isEmpty()) {
            if (EmpresaBLL.checkEmpresaNome(nomeTF.getText())) {
                checkDados.setText("Username já registado!");
                return;
            }
            Empresa empresa = new Empresa();
            empresa.setNome(nomeTF.getText());
            empresa.setTelefone(telefoneTF.getText());
            Morada morada = new Morada();
            morada.setLocalidade(localidadeCB.getValue());
            morada.setRua(ruaTF.getText());
            morada.setnPorta(Integer.parseInt(portaTF.getText()));
            empresa.setMorada(morada);
            UtilizadorBLL.criarUtilizador(SelecionaTipoUserController.utilizador);
            EmpresaBLL.criarEmpresa(empresa, SelecionaTipoUserController.utilizador.getUsername());
            ControladorGlobal.chamaScene("SceneLogin.fxml", event);
        } else checkDados.setText("Campos inválidos!");
    }

    public void cancelar(ActionEvent event) {
        ControladorGlobal.chamaScene("SceneDadosPessoais.fxml", event);
    }
}
