package com.zlovo.gui.empresario;

import com.zlovo.bll.empresa.ProdutoBLL;
import com.zlovo.dal.empresa.Produto;
import com.zlovo.dal.empresa.enumerations.TipoUnidade;
import com.zlovo.gui.ControladorGlobal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class CriaProdutoController implements Initializable {
    @FXML
    private TextField nomeTF;
    @FXML
    private TextField precoTF;
    @FXML
    private TextField dimensaoTF;
    @FXML
    private TextField pesoTF;
    @FXML
    private ChoiceBox<TipoUnidade> tipoUnidadeCB;
    @FXML
    private Label checkDados;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tipoUnidadeCB.getItems().setAll(TipoUnidade.values());
    }

    public void confirmar (ActionEvent event){
        if(!nomeTF.getText().isEmpty() & !precoTF.getText().isEmpty() & !dimensaoTF.getText().isEmpty() & !pesoTF.getText().isEmpty()){
            if(ProdutoBLL.checkProdutoNome(nomeTF.getText())){
                checkDados.setText("Username já registado!");
                return;
            }
            Produto produto = new Produto();
            produto.setNome(nomeTF.getText());
            produto.setPreco(Double.parseDouble(precoTF.getText()));
            produto.setDimensao(Double.parseDouble(dimensaoTF.getText()));
            produto.setPeso(Double.parseDouble(pesoTF.getText()));
            produto.setCategoria(MenuCategoriasController.categoriaSelecionada);
            produto.setUnidade(tipoUnidadeCB.getValue());
            ProdutoBLL.criarProduto(produto);
            ControladorGlobal.chamaScene("empresario/SceneMenuProdutos.fxml",event);
        }
        else checkDados.setText("Campos inválidos!");
    }

    public void cancelar(ActionEvent event){
        ControladorGlobal.chamaScene("empresario/SceneMenuProdutos.fxml", event);
    }
}
