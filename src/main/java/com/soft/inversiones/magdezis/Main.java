package com.soft.inversiones.magdezis;

import com.soft.inversiones.magdezis.view.ViewNavigator;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        ViewNavigator.cambiar(stage, ViewNavigator.LOGIN, "Inversiones Magdezis - Login");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
