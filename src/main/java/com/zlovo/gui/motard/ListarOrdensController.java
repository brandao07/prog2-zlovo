package com.zlovo.gui.motard;

import com.zlovo.bll.utilizador.UtilizadorBLL;
import com.zlovo.gui.ControladorGlobal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;


public class ListarOrdensController {
    @FXML
    private ListView<String> myListView;

    public void logout(ActionEvent event){
        UtilizadorBLL.setUserLog(null);
        ControladorGlobal.chamaScene("SceneLogin.fxml", event);
    }
    //Criar a scene que o Motard Marca a entrega como efetuada ou regista um problema ou nao faz um crlh e sai
    public void seguinte(ActionEvent event){
        ControladorGlobal.chamaScene("", event);
    }
}
