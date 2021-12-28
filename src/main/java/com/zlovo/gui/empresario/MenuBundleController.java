package com.zlovo.gui.empresario;

import com.zlovo.bll.empresa.BundleBLL;
import com.zlovo.bll.empresa.EmpresaBLL;
import com.zlovo.bll.empresa.ProdutoBLL;
import com.zlovo.dal.empresa.Bundle;
import com.zlovo.dal.empresa.Produto;
import com.zlovo.gui.ControladorGlobal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuBundleController implements Initializable {
    @FXML
    private Label quantidadeLabel;
    @FXML
    private Label precoLabel;
    @FXML
    private ListView<Bundle> bundleList;
    @FXML
    private Label checkDados;

    public static Bundle bundleSelecionado;
//TODO: ver criar bundle melhor
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bundleList.getItems().addAll(BundleBLL.getBundles(EmpresaBLL.getEmpresaLog(),MenuCategoriasController.categoriaSelecionada));
        BundleBLL.changeCellValueBundleNome(bundleList);
        BundleBLL.updatePrecoLabel(bundleList,precoLabel);
        BundleBLL.updateQuantidadeLabel(bundleList,quantidadeLabel);
    }

    public void criarBundle(ActionEvent event){
        ControladorGlobal.chamaScene("empresario/SceneCriaBundle.fxml", event);
    }

    public void removerBundle(ActionEvent event){
        bundleSelecionado = bundleList.getSelectionModel().getSelectedItem();
        if (bundleSelecionado == null){
            checkDados.setText("Selecione um Bundle!");
            return;
        }
        EmpresaBLL.removeProduto(bundleSelecionado);
        bundleSelecionado = null;
        ControladorGlobal.chamaScene("empresario/SceneMenuBundle.fxml", event);
    }

    public void editarBundle(ActionEvent event){
        bundleSelecionado = bundleList.getSelectionModel().getSelectedItem();
        if (bundleSelecionado == null){
            checkDados.setText("Selecione um Bundle!");
            return;
        }
        ControladorGlobal.chamaScene("empresario/SceneAlteraBundle.fxml", event);
    }

    public void anterior(ActionEvent event){
        ControladorGlobal.chamaScene("empresario/SceneMenuProdutos.fxml", event);
    }
}
