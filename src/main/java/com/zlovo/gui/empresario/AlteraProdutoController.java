package com.zlovo.gui.empresario;

import com.zlovo.bll.empresa.EmpresaBLL;
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

public class AlteraProdutoController implements Initializable {
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
    @FXML
    private Label precoLabel;
    @FXML
    private Label dimensaoLabel;
    @FXML
    private Label nomeLabel;
    @FXML
    private Label pesoLabel;
    @FXML
    private Label unidadeLabel;

    public void confirmar (ActionEvent event){
            if(ProdutoBLL.checkProdutoNome(nomeTF.getText(), MenuCategoriasController.categoriaSelecionada)){
                checkDados.setText("Nome já registado!");
                return;
            }
            Produto produto = MenuProdutosController.produtoSelecionado;
            if (!nomeTF.getText().isEmpty()) produto.setNome(nomeTF.getText());
            if (!precoTF.getText().isEmpty()) produto.setPreco(Double.parseDouble(precoTF.getText()));
            if (!dimensaoTF.getText().isEmpty()) produto.setDimensao(Double.parseDouble(dimensaoTF.getText()));
            if (!pesoTF.getText().isEmpty()) produto.setPeso(Double.parseDouble(pesoTF.getText()));
            if (tipoUnidadeCB.getValue() != null) produto.setUnidade(tipoUnidadeCB.getValue());
            EmpresaBLL.removeProduto(MenuProdutosController.produtoSelecionado);
            EmpresaBLL.adicionaProduto(produto);
            MenuProdutosController.produtoSelecionado = null;
            ControladorGlobal.chamaScene("empresario/SceneMenuProdutos.fxml",event);
    }

    public void cancelar(ActionEvent event){
        ControladorGlobal.chamaScene("empresario/SceneMenuProdutos.fxml", event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tipoUnidadeCB.getItems().setAll(TipoUnidade.values());
        nomeLabel.setText("Nome: " + MenuProdutosController.produtoSelecionado.getNome());
        precoLabel.setText("Preço: " + MenuProdutosController.produtoSelecionado.getPreco());
        dimensaoLabel.setText("Dimensao: " + MenuProdutosController.produtoSelecionado.getDimensao());
        pesoLabel.setText("Peso: " + MenuProdutosController.produtoSelecionado.getPeso());
        unidadeLabel.setText("Unidade: " + MenuProdutosController.produtoSelecionado.getUnidade());
    }
}

