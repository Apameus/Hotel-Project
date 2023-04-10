package com.apameus.gb_hotel_java_fx;

import com.apameus.gb_hotel_java_fx.employees.Partitions;
import com.apameus.gb_hotel_java_fx.menu.Menu;
import com.apameus.gb_hotel_java_fx.orders.Order;
import com.apameus.gb_hotel_java_fx.serializers.EmployeeSerializer;
import com.apameus.gb_hotel_java_fx.serializers.MenuSerializer;
import com.apameus.gb_hotel_java_fx.serializers.OrderSerializer;
import com.apameus.gb_hotel_java_fx.server.DataBaseConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.List;
import java.util.Objects;

public class Launcher extends Application {
    public static Menu menu = MenuSerializer.parse(); //toDo that sh*t doesn't belong here
    public static List<Partitions.Partition> partitions = EmployeeSerializer.parse(); //toDo that sh*t doesn't belong here

    public static List<Order> orders = OrderSerializer.parse(); //toDo that sh*t doesn't belong here

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));

        stage.getIcons().add(new Image("C:\\Users\\Ιωάννης Τζωρτζίνης\\IdeaProjects\\GB Hotel -JAVA FX\\src\\main\\resources\\images\\hotelIcon.png"));
        stage.setTitle("GΒ HOTEL APPLICATION");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

