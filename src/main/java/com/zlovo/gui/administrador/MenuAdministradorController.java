package com.zlovo.gui.administrador;

import com.zlovo.bll.utilizador.UtilizadorBLL;
import com.zlovo.gui.ControladorGlobal;
import javafx.event.ActionEvent;

public class MenuAdministradorController {
    // TODO continuacao dos butoes

    public void logout(ActionEvent event){
        UtilizadorBLL.setUserLog(null);
        ControladorGlobal.chamaScene("SceneLogin.fxml", event);
    }
}
