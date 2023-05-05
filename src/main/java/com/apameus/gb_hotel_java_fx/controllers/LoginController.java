package com.apameus.gb_hotel_java_fx.controllers;

import com.apameus.gb_hotel_java_fx.controllers.employee.EmployeeProfileController;
import com.apameus.gb_hotel_java_fx.employees.Employee;
import com.apameus.gb_hotel_java_fx.employees.EmployeeList;
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

        if (username.isEmpty() || password.isBlank())  notificationLabel.setText("Both fields must be fulfilled!");
        else if (username.equals("antonis") && password.equals("theboss"))  Util.changeScene("boss/boss.fxml", loginButton);
        else {

            EmployeeList.getAllEmployees().forEach(employee -> {
                if (username.equals(employee.userName) && password.equals(employee.password)) {
                    notificationLabel.setText("Log-in Successfully!");
                    setEmployee(username);
                    Util.changeScene("employee/employee_profile.fxml", loginButton);
                } else notificationLabel.setText("Wrong info!");
            });

        }


    }

    private void setEmployee(String username) {
        EmployeeList.getAllEmployees().forEach(employee ->{
            if (employee.userName.equals(username)) {
                EmployeeProfileController.setEmployee(employee);
                return;
            }
        });
    }

    @FXML
    public void register(ActionEvent event) {
        Util.changeScene("register.fxml", loginButton);
    }

}
