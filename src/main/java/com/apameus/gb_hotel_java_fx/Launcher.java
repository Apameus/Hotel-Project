package com.apameus.gb_hotel_java_fx;

import com.apameus.gb_hotel_java_fx.menu.Menu;
import com.apameus.gb_hotel_java_fx.serializers.MenuSerializer;
import com.apameus.gb_hotel_java_fx.server.DataBaseConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

public class Launcher extends Application {


    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("employee/newOrder2.fxml"));

        stage.getIcons().add(new Image("C:\\Users\\Ιωάννης Τζωρτζίνης\\IdeaProjects\\GB Hotel -JAVA FX\\src\\main\\resources\\images\\hotelIcon.png"));
        stage.setTitle("GΒ HOTEL APPLICATION");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void main(String[] args) {
//        Menu menu = new Menu();
//        MenuSerializer.parse();

        launch(args);
    }
}



// C:\Users\Ιωάννης Τζωρτζίνης\IdeaProjects\GB Hotel -JAVA FX\target\classes\module-info.class
//~\IdeaProjects\GB Hotel -JAVA FX\out