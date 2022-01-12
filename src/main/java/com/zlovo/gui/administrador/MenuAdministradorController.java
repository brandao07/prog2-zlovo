package com.zlovo.gui.administrador;

import com.zlovo.bll.utilizador.UtilizadorBLL;
import com.zlovo.gui.ControladorGlobal;
import javafx.event.ActionEvent;

public class MenuAdministradorController {
    public void criarUser(ActionEvent event) {
        ControladorGlobal.chamaScene("administrador/SceneSelecionaTipoFuncionario.fxml", event);
    }

    public void menuCategoriaProdutos(ActionEvent event) {
        ControladorGlobal.chamaScene("administrador/SceneMenuCategorias.fxml", event);
    }

    public void desativarEmpresa(ActionEvent event) {
        ControladorGlobal.chamaScene("administrador/SceneDesativarEmpresa.fxml", event);
    }

    public void desativarDonoEmpresa(ActionEvent event) {
        ControladorGlobal.chamaScene("administrador/SceneDesativarDonoEmpresa.fxml", event);
    }

    public void enviarMotards(ActionEvent event) {
        ControladorGlobal.chamaScene("administrador/SceneSelecionarEncomenda.fxml", event);
    }

    public void logout(ActionEvent event) {
        UtilizadorBLL.setUserLog(null);
        ControladorGlobal.chamaScene("SceneLogin.fxml", event);
    }

    public void consultarSistema(ActionEvent event){
        ControladorGlobal.chamaScene("administrador/SceneConsultarSistema.fxml", event);
    }
}
