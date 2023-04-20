package com.apameus.gb_hotel_java_fx.controllers.boss;

import com.apameus.gb_hotel_java_fx.employees.Employee;
import com.apameus.gb_hotel_java_fx.employees.EmployeeList;
import com.apameus.gb_hotel_java_fx.util.Initializer;
import com.apameus.gb_hotel_java_fx.util.Util;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class BossController implements Initializable {

    @FXML
    public Button logOutButton;

    @FXML
    private Label gDailyIncome;

    @FXML
    private Label gTotalEmployees;
    @FXML
    public Label gReservationPercentage;

    @FXML
    private Label aPartition_dailyOrdersIncome;

    @FXML
    private Label aPartition_dailyOrdersNumber;

    @FXML
    private Label aPartition_monthlyOrdersNumber;

    @FXML
    private Label bPartition_dailyOrdersIncome;

    @FXML
    private Label bPartition_dailyOrdersNumber;

    @FXML
    private Label bPartition_monthlyOrdersNumber;

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
    private Button reservationButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setPartitionsStatus();
        setGeneralStatus();
    }

    @FXML
    void logOut(ActionEvent event){Util.changeScene("login.fxml", logOutButton);}

    @FXML
    void seeEmployees(ActionEvent event) {
        Util.changeScene("boss/employees_stats.fxml", employeesButton);
    }

    @FXML
    void seeMenu(ActionEvent event) {
        Util.changeScene("boss/menu.fxml", menuButton);
    }
    @FXML
    void seeReservations(ActionEvent event) {

    }

    private void setPartitionsStatus() {
        String partition1 = partitionA.getText().replaceAll(" ", "").toUpperCase();
        String partition2 = partitionB.getText().replaceAll(" ", "").toUpperCase();
        setPartitionStatus(partition1, 'A');
        setPartitionStatus(partition2, 'B');
    }

    private void setGeneralStatus() {
        setTotalDailyIncome();
        setTotalEmployees();
    }

    private void setTotalEmployees() {
        gTotalEmployees.setText(String.valueOf(EmployeeList.countTotalEmployees()));
    }

    private void setTotalDailyIncome() {
        gDailyIncome.setText(String.valueOf(EmployeeList.countTotalDailyIncome()));
    }

    private void setPartitionStatus(String partitionName, Character ab) {
        int dailyOrders = 0;
        int dailyIncome = 0;
        int monthlyOrders = 0;

        EmployeeList.Partition partition = Initializer.employeeList.partitionName_Partition().get(partitionName);
        for (Employee employee : partition.employees) {
            dailyOrders += employee.dailyOrdersDelivered;
            dailyIncome += employee.dailyOrdersIncome;
            monthlyOrders += employee.monthlyOrdersDelivered;
        }

        if (ab.equals('A')){
            aPartition_dailyOrdersNumber.setText(String.valueOf(dailyOrders));
            aPartition_dailyOrdersIncome.setText(String.valueOf(dailyIncome));
            aPartition_monthlyOrdersNumber.setText(String.valueOf(monthlyOrders));
        }
        else {
            bPartition_dailyOrdersNumber.setText(String.valueOf(dailyOrders));
            bPartition_dailyOrdersIncome.setText(String.valueOf(dailyIncome));
            bPartition_monthlyOrdersNumber.setText(String.valueOf(monthlyOrders));
        }

    }
}
