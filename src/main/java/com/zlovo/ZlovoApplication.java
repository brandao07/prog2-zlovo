package com.zlovo;

import com.zlovo.bll.utilizador.AdministradorBLL;
import com.zlovo.dal.Repositorio;
import com.zlovo.gui.ControladorGlobal;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class ZlovoApplication extends Application {
    public static void main(String[] args) throws IOException {
        Repositorio.desserializar(Repositorio.getRepositorio().getRepoPath());
        if (Repositorio.getRepositorio().getLocalidadeSet().isEmpty())
            Repositorio.getRepositorio().adicionaLocalidades();
        launch(args);
    }

    @Override
    public void start(@NotNull Stage stage) throws IOException {
        Parent root;
        if (AdministradorBLL.checkAdministrador())
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("gui/SceneLogin.fxml")));
        else
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("gui/administrador/ScenePrimeiraVez.fxml")));
        Scene scene = new Scene(root);
        Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("images/icon.png")));
        stage.getIcons().add(icon);
        stage.setTitle("Zlovo");
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(event -> {
            event.consume();
            ControladorGlobal.exit(stage);
        });
    }
}