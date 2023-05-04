package com.apameus.gb_hotel_java_fx;

import com.apameus.gb_hotel_java_fx.util.Initializer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class Launcher extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));

        stage.getIcons().add(new Image("C:\\Users\\Ιωάννης Τζωρτζίνης\\IdeaProjects\\GB Hotel -JAVA FX\\src\\main\\resources\\images\\hotelIcon.png"));
        stage.setTitle("GΒ HOTEL APPLICATION");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void main(String[] args) {
        Initializer.initialize();
        launch(args);
    }

}