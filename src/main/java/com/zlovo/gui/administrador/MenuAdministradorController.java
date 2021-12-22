package com.zlovo.gui.administrador;

import com.zlovo.bll.utilizador.UtilizadorBLL;
import com.zlovo.gui.ControladorGlobal;
import javafx.event.ActionEvent;

import java.util.ResourceBundle;

public class MenuAdministradorController {
    // TODO continuacao dos butoes

    public void criarUser(ActionEvent event){
        ControladorGlobal.chamaScene("administrador/SceneSelecionaTipoFuncionario.fxml", event);
    }

    public void menuCategoriaProdutos(ActionEvent event){
        ControladorGlobal.chamaScene("administrador/SceneMenuCategorias.fxml", event);
    }

    public void logout(ActionEvent event){
        UtilizadorBLL.setUserLog(null);
        ControladorGlobal.chamaScene("SceneLogin.fxml", event);
    }
}
