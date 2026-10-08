package com.soft.inversiones.magdezis.view;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

/** Navegación entre pantallas FXML y utilidades de alertas. */
public final class ViewNavigator {

    public static final String LOGIN = "/view/login.fxml";
    public static final String REGISTRO = "/view/registro.fxml";

    private ViewNavigator() { }

    public static void cambiar(Stage stage, String fxml, String titulo) {
        try {
            Parent root = FXMLLoader.load(ViewNavigator.class.getResource(fxml));
            stage.setScene(new Scene(root));
            stage.setTitle(titulo);
            stage.setResizable(false);
            stage.sizeToScene();
            stage.centerOnScreen();
            stage.show();
        } catch (IOException e) {
            throw new IllegalStateException("No se pudo cargar la vista " + fxml, e);
        }
    }

    public static void alerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
