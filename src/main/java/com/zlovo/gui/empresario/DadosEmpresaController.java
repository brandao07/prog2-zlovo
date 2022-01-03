package com.zlovo.gui.empresario;

import com.zlovo.bll.empresa.EmpresaBLL;
import com.zlovo.dal.Morada;
import com.zlovo.dal.Repositorio;
import com.zlovo.dal.empresa.Empresa;
import com.zlovo.gui.ControladorGlobal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class DadosEmpresaController implements Initializable {
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
    private Label nomeLabel;
    @FXML
    private Label telefoneLabel;
    @FXML
    private Label ruaLabel;
    @FXML
    private Label portaLabel;
    @FXML
    private Label localidadeLabel;
    @FXML
    private Label checkDados;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nomeLabel.setText("Nome: " + EmpresaBLL.getEmpresaLog().getNome());
        telefoneLabel.setText("Telefone: " + EmpresaBLL.getEmpresaLog().getTelefone());
        ruaLabel.setText("Rua: " + EmpresaBLL.getEmpresaLog().getMorada().getRua());
        portaLabel.setText("Porta: " + EmpresaBLL.getEmpresaLog().getMorada().getnPorta());
        localidadeLabel.setText("Localidade: " + EmpresaBLL.getEmpresaLog().getMorada().getLocalidade());
        localidadeCB.getItems().addAll(Repositorio.getRepositorio().getLocalidadeSet());
    }

    public void anterior(ActionEvent event) {
        ControladorGlobal.chamaScene("empresario/SceneMenuFuncoesEmp.fxml", event);
    }

    public void confirmar(ActionEvent event) {
        try {
            Empresa empresa = new Empresa();
            empresa.setNome(nomeTF.getText());
            empresa.setTelefone(telefoneTF.getText());
            Morada morada = new Morada();
            morada.setRua(ruaTF.getText());
            if (portaTF.getText().isEmpty())
                morada.setnPorta(EmpresaBLL.getEmpresaLog().getMorada().getnPorta());
            else
                morada.setnPorta(Integer.parseInt(portaTF.getText()));
            morada.setLocalidade(localidadeCB.getValue());
            empresa.setMorada(morada);
            EmpresaBLL.alteraEmpresa(empresa);
            ControladorGlobal.chamaScene("empresario/SceneMenuFuncoesEmp.fxml", event);
        } catch (Exception e) {
            checkDados.setText("Dados inválidos!");
            e.printStackTrace();
        }
    }
}
