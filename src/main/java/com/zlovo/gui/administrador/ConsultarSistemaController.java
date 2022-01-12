package com.zlovo.gui.administrador;

import com.zlovo.dal.Repositorio;
import com.zlovo.gui.ControladorGlobal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

import java.net.URL;
import java.util.ResourceBundle;

public class ConsultarSistemaController implements Initializable {
    @FXML
    private BarChart<String, Integer> barras;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Dados");
        series1.getData().add(new XYChart.Data("Numero de utilizadores", Repositorio.getRepositorio().getNumUtilizadoresChart()));
        series1.getData().add(new XYChart.Data("Numero de encomendas", Repositorio.getRepositorio().getNumEncomendasChart()));
        series1.getData().add(new XYChart.Data("Numero de empresas", Repositorio.getRepositorio().getNumEmpresasChart()));
        series1.getData().add(new XYChart.Data("Numero de produtos", Repositorio.getRepositorio().getNumProdutosChart()));
        barras.getData().addAll(series1);
    }

    public void anterior(ActionEvent event){
        ControladorGlobal.chamaScene("administrador/SceneMenuAdmin.fxml", event);
    }
}
