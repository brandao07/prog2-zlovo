package com.zlovo.gui.empresario;

import com.zlovo.bll.empresa.EmpresaBLL;
import com.zlovo.bll.utilizador.UtilizadorBLL;
import com.zlovo.gui.ControladorGlobal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MenuFuncoesEmpController {
    public void logout(ActionEvent event){
        EmpresaBLL.setEmpresaLog(null);
        ControladorGlobal.chamaScene("empresario/SceneMenuEmpresario.fxml", event);
    }
    //Falta fazer as scenes da funçoes do empresario
}
