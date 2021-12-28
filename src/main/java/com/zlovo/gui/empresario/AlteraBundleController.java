package com.zlovo.gui.empresario;
import com.zlovo.bll.empresa.EmpresaBLL;
import com.zlovo.dal.empresa.Bundle;
import com.zlovo.gui.ControladorGlobal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AlteraBundleController implements Initializable {
    @FXML
    private TextField nomeTF;
    @FXML
    private TextField precoTF;
    @FXML
    private Label checkDados;
    @FXML
    private Label nomeLabel;
    @FXML
    private Label precoLabel;

    public void confirmar (ActionEvent event){
        // Todo talvez fazer uma função que check nome
        Bundle bundle = MenuBundleController.bundleSelecionado;
        if(!nomeTF.getText().isEmpty()) bundle.setNome(nomeTF.getText());
        if(!precoTF.getText().isEmpty()) bundle.setPreco(Double.parseDouble(precoTF.getText()));
        EmpresaBLL.removeProduto(MenuBundleController.bundleSelecionado);
        EmpresaBLL.adicionaProduto(bundle);
        ControladorGlobal.chamaScene("empresario/SceneMenuBundle.fxml", event);
    }

    public void cancelar (ActionEvent event){
        ControladorGlobal.chamaScene("empresario/SceneMenuBundle.fxml", event);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nomeLabel.setText("Nome: " + MenuBundleController.bundleSelecionado.getNome());
        precoLabel.setText("Preço: " + MenuBundleController.bundleSelecionado.getPreco());
    }
}
