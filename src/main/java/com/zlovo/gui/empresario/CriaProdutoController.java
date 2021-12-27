package com.zlovo.gui.empresario;

import com.zlovo.bll.empresa.EmpresaBLL;
import com.zlovo.bll.empresa.ProdutoBLL;
import com.zlovo.dal.Morada;
import com.zlovo.dal.Repositorio;
import com.zlovo.dal.empresa.Empresa;
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
    private ChoiceBox<String> categoriaCB;
    @FXML
    private TextField precoTF;
    @FXML
    private TextField dimensaoTF;
    @FXML
    private TextField pesoTF;
    @FXML
    private ChoiceBox<String> tipoUnidadeCB;
    @FXML
    private Label checkDados;


    public void confirmar (ActionEvent event){
        if(!nomeTF.getText().isEmpty() & !precoTF.getText().isEmpty() & !dimensaoTF.getText().isEmpty() & !pesoTF.getText().isEmpty()){
            if(ProdutoBLL.checkProdutoNome(nomeTF.getText())){
                checkDados.setText("Username já registado!");
                return;
            }
            Produto produto = new Produto();
            produto.setNome(nomeTF.getText());
            produto.setPreco(precoTF.getPrefColumnCount());
            produto.setDimensao(dimensaoTF.getPrefColumnCount());
            produto.setPeso(pesoTF.getPrefColumnCount());
            //Falta acabar as choiceBox´s
            EmpresaBLL.adicionaProduto(produto);
            ControladorGlobal.chamaScene("empresario/SceneMenuProdutos.fxml",event);
        }
        else checkDados.setText("Campos inválidos!");
    }

    public void cancelar(ActionEvent event){
        ControladorGlobal.chamaScene("empresario/SceneMenuProdutos.fxml", event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        categoriaCB.getItems().addAll(Repositorio.getRepositorio().getCategoriaSet());
        //tipoUnidadeCB.getItems().addAll(TipoUnidade);
        //Falta saber fazer
    }
}
