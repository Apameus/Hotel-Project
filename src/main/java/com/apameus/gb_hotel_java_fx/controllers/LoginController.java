package com.apameus.gb_hotel_java_fx.controllers;

import com.apameus.gb_hotel_java_fx.Launcher;
import com.apameus.gb_hotel_java_fx.controllers.employee.EmployeeController;
import com.apameus.gb_hotel_java_fx.employees.Employee;
import com.apameus.gb_hotel_java_fx.util.Util;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public final class LoginController {

    @FXML
    private Button loginButton;

    @FXML
    private Button registerButton;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label notificationLabel;

    public static Employee employee;


    @FXML
    public void login(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isBlank()){
            notificationLabel.setText("Both fields must be fulfilled!");
        }
       else {
            Launcher.partitions.get(0).employees.forEach(employee2 -> {
                if (username.equals(employee2.userName) && password.equals(employee2.password)) {
                    notificationLabel.setText("Log-in Successfully!");
                    setEmployee();
                    Util.changeScene("employee/employee.fxml", loginButton);
                } else notificationLabel.setText("Wrong info!");
            });
        }


    }

    private void setEmployee() {
//        employee = Partitions.Partition.username_Id_map.get(usernameField.getText());
        Employee employee1 = Launcher.partitions.get(0).username_Employee_map.get(usernameField.getText());
        EmployeeController.setEmployee(employee1);
    }

    @FXML
    public void register(ActionEvent event) {
        Util.changeScene("register.fxml", loginButton);
    }

}
