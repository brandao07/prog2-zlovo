package com.zlovo.gui.empresario;

import com.zlovo.gui.ControladorGlobal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class DadosEmpresaController {
    @FXML
    private ListView myListView;

    public void anterior(ActionEvent event){
        ControladorGlobal.chamaScene("empresario/SceneMenuFuncoesEmp.fxml", event);
    }

    public void confirmar(ActionEvent event){
        try {
            Empresa empresa = new Empresa();
            empresa.setNome(nomeTF.getText());
            empresa.setTelefone(telefoneTF.getText());
            Morada morada = new Morada();
            morada.setRua(ruaTF.getText());
            if (portaTF.getText().isEmpty())
                morada.setnPorta(EmpresaBLL.getEmpresaLog().getMorada().getnPorta());
            else
                morada.setnPorta(Integer.parseInt(portaTF.getText()));
            morada.setLocalidade(localidadeCB.getValue());
            empresa.setMorada(morada);
            EmpresaBLL.alteraEmpresa(empresa);
            ControladorGlobal.chamaScene("empresario/SceneMenuFuncoesEmp.fxml", event);
        } catch (Exception e) {
            checkDados.setText("Dados inválidos!");
            e.printStackTrace();
        }
    }
}
