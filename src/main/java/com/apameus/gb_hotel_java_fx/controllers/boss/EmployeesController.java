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
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public final class EmployeesController implements Initializable {

    @FXML
    private TreeView<String> employeesTree;

    @FXML
    private Button removeButton;

    @FXML
    private Button editButton;

    @FXML
    private Button backButton;

    @FXML
    private Label bonusLabel;

    @FXML
    private Label dailyOrdersDelivered;

    @FXML
    private Label dailyOrdersIncome;

    @FXML
    private Label emailLabel;

    @FXML
    private Label firstNameLabel;

    @FXML
    private Label idLabel;

    @FXML
    private Label lastNameLabel;

    @FXML
    private Label monthlyOrdersDelivered;

    @FXML
    private Label monthlyOrdersIncome;

    @FXML
    private Label phoneNumberLabel;

    @FXML
    private Label pointsLabel;


    @FXML
    private Label salaryLabel;

    @FXML
    private Label totalOrdersDelivered;
    @FXML
    private Label totalOrdersIncome;

    private TreeItem<String> root = new TreeItem<>();
    private TreeItem<String> selectedEmployee = new TreeItem<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTreeView();
    }

    @FXML
    void selectEmployee(MouseEvent event) {
        selectedEmployee = employeesTree.getSelectionModel().getSelectedItem();
        if (selectedEmployee != null) showInfo();
    }

    private void showInfo() {
//        String[] fullName = selectedEmployee.getValue().split(" ");
//        firstNameLabel.setText(fullName[0]);
//        lastNameLabel.setText(fullName[1]);

        Employee employee = null;
        for (EmployeeList.Partition partition : Initializer.employeeList.getPartitions()) {
            if (partition.fullname_Employee_Map.get(selectedEmployee.getValue()) != null) {
                employee = partition.fullname_Employee_Map.get(selectedEmployee.getValue());
                break;
            }
        }
        firstNameLabel.setText(employee.firstName);
        lastNameLabel.setText(employee.lastName);
        emailLabel.setText(employee.email);
        phoneNumberLabel.setText(employee.phoneNumber);

        dailyOrdersDelivered.setText(String.valueOf(employee.dailyOrdersDelivered));
        monthlyOrdersDelivered.setText(String.valueOf(employee.monthlyOrdersDelivered));
        totalOrdersDelivered.setText(String.valueOf(employee.totalOrdersDelivered));

        dailyOrdersIncome.setText(String.valueOf(employee.dailyOrdersIncome));
        monthlyOrdersIncome.setText(String.valueOf(employee.monthlyOrdersIncome));
        totalOrdersIncome.setText(String.valueOf(employee.totalOrdersIncome));

        pointsLabel.setText(String.valueOf(employee.points));
        salaryLabel.setText(String.valueOf(employee.salary));
        bonusLabel.setText(String.valueOf(employee.bonus));

        idLabel.setText(String.valueOf(employee.ID));
    }

    @FXML
    void editEmployee(ActionEvent event) {

    }

    @FXML
    void removeEmployee(ActionEvent event) {

    }

    @FXML
    void goBack(ActionEvent event){
        Util.changeScene("boss/boss.fxml", backButton);
    }


    private void setTreeView() {
        Initializer.employeeList.getPartitions().forEach(partition -> {
            partition.employees.forEach(employee -> {
                root.getChildren().add(new TreeItem<>(employee.firstName + " " + employee.lastName));
            });
        });
        employeesTree.setRoot(root);
        employeesTree.setShowRoot(false);
    }
}
