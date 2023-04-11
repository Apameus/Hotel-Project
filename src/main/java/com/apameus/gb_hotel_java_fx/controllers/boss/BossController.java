package com.apameus.gb_hotel_java_fx.controllers.boss;

import com.apameus.gb_hotel_java_fx.employees.Employee;
import com.apameus.gb_hotel_java_fx.employees.Employees;
import com.apameus.gb_hotel_java_fx.util.Initializer;
import com.apameus.gb_hotel_java_fx.util.Util;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class BossController implements Initializable {

    @FXML
    private Label TotalOrdersNumber;

    @FXML
    private Label aPartition_dailyOrdersIncome;

    @FXML
    private Label aPartition_dailyOrdersNumber;

    @FXML
    private Label aPartition_mothlyOrdersNumber;

    @FXML
    private Label bPartition_dailyOrdersIncome;

    @FXML
    private Label bPartition_dailyOrdersNumber;

    @FXML
    private Label bPartition_mothlyOrdersNumber;

    @FXML
    private Label dailyOrdersNumber;

    @FXML
    private Button employeesButton;

    @FXML
    private Button menuButton;

    @FXML
    private Text partitionA;

    @FXML
    private Text partitionB;

    @FXML
    private ImageView personImage;

    @FXML
    private Label pointsNumber;

    @FXML
    private Button reservationButton;


    @FXML
    void seeEmployees(ActionEvent event) {
        Util.changeScene("boss/employees.fxml", employeesButton);
    }

    @FXML
    void seeMenu(ActionEvent event) {
        Util.changeScene("boss/menu.fxml", menuButton);
    }

    @FXML
    void seeReservations(ActionEvent event) {
        Util.changeScene("boss/menu.fxml", menuButton);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String partition1 = partitionA.getText();
        String partition2 = partitionB.getText();
        setPartitionStatus(partition1, 'A');
        setPartitionStatus(partition2, 'B');

    }

    private void setPartitionStatus(String name, Character ab) {
        int dailyOrders = 0;
        int dailyIncome = 0;
        int monthlyOrders = 0;

        Employees.Partition partition = Initializer.employees.getMap().get(name);
        for (Employee employee : partition.employees) {
            dailyOrders += employee.dailyOrdersDelivered;
            dailyIncome += employee.dailyOrdersIncome;
            monthlyOrders += employee.monthlyOrdersDelivered;
        }

        if (ab.equals('A')){
            aPartition_dailyOrdersNumber.setText(String.valueOf(dailyOrders));
            aPartition_dailyOrdersIncome.setText(String.valueOf(dailyIncome));
            aPartition_mothlyOrdersNumber.setText(String.valueOf(monthlyOrders));
        }
        else {
            bPartition_dailyOrdersNumber.setText(String.valueOf(dailyOrders));
            bPartition_dailyOrdersIncome.setText(String.valueOf(dailyIncome));
            bPartition_mothlyOrdersNumber.setText(String.valueOf(monthlyOrders));
        }

    }
}
