package com.zlovo.gui.empresario;

import com.zlovo.bll.empresa.EmpresaBLL;
import com.zlovo.gui.ControladorGlobal;
import javafx.event.ActionEvent;


public class MenuFuncoesEmpController {

    public void anterior(ActionEvent event) {
        EmpresaBLL.setEmpresaLog(null);
        ControladorGlobal.chamaScene("empresario/SceneMenuEmpresario.fxml", event);
    }

    public void ConsultarEncomendas(ActionEvent event) {
        ControladorGlobal.chamaScene("empresario/SceneConsultarEncomendas.fxml", event);
    }

    public void DadosEmpresa(ActionEvent event) {
        ControladorGlobal.chamaScene("empresario/SceneDadosEmpresa.fxml", event);
    }

    public void MenuProdutos(ActionEvent event) {
        ControladorGlobal.chamaScene("empresario/SceneMenuCategorias.fxml", event);
    }
}
