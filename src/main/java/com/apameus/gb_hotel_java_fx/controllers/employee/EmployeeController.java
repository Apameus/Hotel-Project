package com.apameus.gb_hotel_java_fx.controllers.employee;

import com.apameus.gb_hotel_java_fx.controllers.LoginController;
import com.apameus.gb_hotel_java_fx.employees.Employee;
import com.apameus.gb_hotel_java_fx.util.Util;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public final class EmployeeController {

    @FXML
    private Label TotalOrdersNumber;

    @FXML
    private Label dailyOrdersNumber;

    @FXML
    private Button logOutButton;

    @FXML
    private Button newOrderButton;

    @FXML
    private ImageView personImage;

    @FXML
    private Label pointsNumber;

    public static Employee employee = null;

    public static void setEmployee(Employee employee1){
        employee = employee1;
    }


    public void newOrder(ActionEvent actionEvent) {
        Util.changeScene("employee/newOrder2.fxml", newOrderButton);
    }

    public void logOut(ActionEvent actionEvent) {
        Util.changeScene("login.fxml", newOrderButton);
    }


}
