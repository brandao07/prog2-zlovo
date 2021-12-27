package com.zlovo.gui.empresario;
import com.zlovo.bll.empresa.ProdutoBLL;
import com.zlovo.dal.empresa.Produto;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class AlteraBundleController implements Initializable {
    @FXML
    ListView<Produto> produtosList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        produtosList.getItems().addAll(MenuBundleController.bundleSelecionado.getProdutosBundle());
        ProdutoBLL.changeCellValueProdutoNome(produtosList);
    }
}
