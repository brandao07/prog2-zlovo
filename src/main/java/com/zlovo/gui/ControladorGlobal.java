package com.zlovo.gui;

import com.zlovo.dal.Repositorio;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

public class ControladorGlobal {
    public static void chamaScene(String nomeScene, @NotNull ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(ControladorGlobal.class.getResource(nomeScene));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Zlovo");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void exit(Stage stage) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText("Estás prestes a fechar a aplicação!");
        alert.setContentText("Tens a certeza?");

        if (alert.showAndWait().get() == ButtonType.OK) {
            Repositorio.getRepositorio().serializar(Repositorio.getRepositorio().getRepoPath());
            stage.close();
        }
    }
}
