package com.soft.inversiones.magdezis.controller;

import com.soft.inversiones.magdezis.dto.RegistroDTO;
import com.soft.inversiones.magdezis.exception.AuthException;
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

public class RegistroController {

    @FXML private TextField txtNombre;
    @FXML private TextField txtApellido;
    @FXML private TextField txtCorreo;
    @FXML private PasswordField txtContrasena;

    private final AuthService authService = new AuthServiceImpl();

    @FXML
    private void onRegistrar(ActionEvent event) {
        try {
            authService.registrar(new RegistroDTO(txtNombre.getText(), txtApellido.getText(),
                    txtCorreo.getText(), txtContrasena.getText()));
            ViewNavigator.alerta(Alert.AlertType.INFORMATION, "Registro exitoso",
                    "Cuenta creada correctamente. Ya puede iniciar sesión.");
            irALogin(event);
        } catch (AuthException e) {
            ViewNavigator.alerta(Alert.AlertType.ERROR, "Error", e.getMessage());
        }
    }

    @FXML
    private void onVolver(ActionEvent event) {
        irALogin(event);
    }

    private void irALogin(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        ViewNavigator.cambiar(stage, ViewNavigator.LOGIN, "Inversiones Magdezis - Login");
    }
}
