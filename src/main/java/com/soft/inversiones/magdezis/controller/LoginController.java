package com.soft.inversiones.magdezis.controller;

import com.soft.inversiones.magdezis.dto.LoginDTO;
import com.soft.inversiones.magdezis.exception.AuthException;
import com.soft.inversiones.magdezis.model.Usuario;
import com.soft.inversiones.magdezis.service.AuthService;
import com.soft.inversiones.magdezis.service.AuthServiceImpl;
import com.soft.inversiones.magdezis.view.ViewNavigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    @FXML private TextField txtCorreo;
    @FXML private PasswordField txtContrasena;

    private final AuthService authService = new AuthServiceImpl();

    @FXML
    private void onIniciarSesion(ActionEvent event) {
        try {
            Usuario usuario = authService.iniciarSesion(
                    new LoginDTO(txtCorreo.getText(), txtContrasena.getText()));
            ViewNavigator.alerta(Alert.AlertType.INFORMATION, "Inicio de sesión exitoso",
                    "Bienvenido, " + usuario.getNombreCompleto()
                            + "\nHa iniciado sesión con el rol: " + usuario.getRol().getNombre());
            txtContrasena.clear();
        } catch (AuthException e) {
            ViewNavigator.alerta(Alert.AlertType.ERROR, "Error", e.getMessage());
        }
    }

    @FXML
    private void onIrRegistro(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        ViewNavigator.cambiar(stage, ViewNavigator.REGISTRO, "Inversiones Magdezis - Registro");
    }
}
