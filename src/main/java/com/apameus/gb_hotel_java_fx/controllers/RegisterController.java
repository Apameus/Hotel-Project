package com.apameus.gb_hotel_java_fx.controllers;

import com.apameus.gb_hotel_java_fx.employees.Boss;
import com.apameus.gb_hotel_java_fx.employees.Employee;
import com.apameus.gb_hotel_java_fx.serializers.EmployeeSerializer;
import com.apameus.gb_hotel_java_fx.util.Initializer;
import com.apameus.gb_hotel_java_fx.util.Util;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.apameus.gb_hotel_java_fx.employees.EmployeeList.*;

public class RegisterController {

    @FXML
    private Button backButton;

    @FXML
    private Button confirmButton;

    @FXML
    private TextField keyField;

    @FXML
    private Label notificationLabel;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;

    @FXML
    void confirm(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String key = keyField.getText();
        if (Boss.key_employee_map.containsKey(key)){

            Employee employee = Boss.key_employee_map.get(key);

            employee.userName = username;
            employee.password = password;
            employee.registeredDate = String.valueOf(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

            Partition partition = getPartitionOf(employee);

            Boss.key_employee_map.remove(key);
            Boss.employee_Partition_Map.remove(employee);

            Initializer.employeeList.partitionName_Partition().get(partition.name).employees.add(employee);
            EmployeeSerializer.serialize();
            setNotification(true);
            clear();
        }
        else setNotification(false);
    }

    private void clear() {
        usernameField.setText("");
        passwordField.setText("");
        keyField.setText("");
    }

    private void setNotification(boolean successful) {
        if (successful) notificationLabel.setText("Register successfully !");
        else notificationLabel.setText("Wrong key !");
    }

    @FXML
    void goBack(ActionEvent event) {
        Util.changeScene("Login.fxml", backButton);
    }

    private Partition getPartitionOf(Employee employee) { // ToDO refactor !!!!
        return Boss.employee_Partition_Map.get(employee);
    }
}
