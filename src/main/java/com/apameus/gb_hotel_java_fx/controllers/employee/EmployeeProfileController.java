package com.apameus.gb_hotel_java_fx.controllers.employee;

import com.apameus.gb_hotel_java_fx.employees.Employee;
import com.apameus.gb_hotel_java_fx.util.Util;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;


public final class EmployeeProfileController implements Initializable {

    @FXML
    private Label totalOrdersNumber;

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

    public static void setEmployee(Employee employee1){employee = employee1;}


    public void newOrder(ActionEvent actionEvent) {
        Util.changeScene("employee/newOrder.fxml", newOrderButton);
    }

    public void logOut(ActionEvent actionEvent) {
        Util.changeScene("login.fxml", newOrderButton);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dailyOrdersNumber.setText(String.valueOf(employee.dailyOrdersDelivered));
        pointsNumber.setText(String.valueOf(employee.points));
        totalOrdersNumber.setText(String.valueOf(employee.totalOrdersDelivered));
    }
}
