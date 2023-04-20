package com.apameus.gb_hotel_java_fx.controllers.boss;

import com.apameus.gb_hotel_java_fx.employees.Boss;
import com.apameus.gb_hotel_java_fx.employees.Employee;
import com.apameus.gb_hotel_java_fx.employees.EmployeeList;
import com.apameus.gb_hotel_java_fx.serializers.EmployeeSerializer;
import com.apameus.gb_hotel_java_fx.util.Initializer;
import com.apameus.gb_hotel_java_fx.util.Util;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import org.apache.commons.lang3.RandomStringUtils;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static com.apameus.gb_hotel_java_fx.employees.EmployeeList.*;

// ToDo you need to f**king refactor this class
public class EditEmployeesController implements Initializable {

    @FXML
    private TreeView<String> employeesTree;
    @FXML
    private Text text;

    @FXML
    private Button addButton;

    @FXML
    private Button saveButton;

    @FXML
    private Button backButton;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField firstNameTextField;

    @FXML
    private Label infoLabel;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private TextField phoneNumberTextField;

    @FXML
    private TextField salaryTextField;

    @FXML
    private ChoiceBox<String> partitionChoiceBox;

    @FXML
    private Text partitionText;

    @FXML
    private Text keyText;


    private TreeItem<String> root = new TreeItem<>();
    private TreeItem<String> selectedEmployee = new TreeItem<>();



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTreeView();
        setChoiceBox();
    }

    @FXML
    void selectEmployee(MouseEvent event) {
        selectedEmployee = employeesTree.getSelectionModel().getSelectedItem();
        if (selectedEmployee != null) showInfo();
        if (newEmployeeForm()) clearLastFields();

    }

    @FXML
    void goBack(ActionEvent event){
        Util.changeScene("boss/employees_stats.fxml", backButton);
    }

    @FXML
    void newEmployeeForm(ActionEvent event) {
        clearFields();
        text.setText("New Employee");
        partitionChoiceBox.setVisible(true);
        partitionText.setText("*Partition:");
    }

    @FXML
    void save(ActionEvent event){
        Employee employee = new Employee();
        if (!newEmployeeForm()) employee = getEmployee(selectedEmployee.getValue());

        overrideEmployeeInfo(employee);

        if (newEmployeeForm()){
            employee.id = getHigherEmployeeId() + 1;
            setUniqueKey(employee);
        }
    }

    @FXML
    public void removeEmployee(ActionEvent event) {
        if (selectedEmployee != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Remove Employee");
            alert.setHeaderText("You are about to remove the selected employee!");
            alert.setContentText("Are you sure?");
            if (alert.showAndWait().get() == ButtonType.OK){
                // get the specific employee from EmployeeList and delete him.

//                Partition partition = getPartitionOfEmployee(selectedEmployee.getValue());
//                partition.employees.remove(partition.fullName_Employee_Map.get(selectedEmployee.getValue()));

                Initializer.employeeList.getPartitions().forEach(partition -> {
                    if (partition.fullName_Employee_Map.containsKey(selectedEmployee.getValue())){
                        partition.employees.remove(partition.fullName_Employee_Map.get(selectedEmployee.getValue()));
                        EmployeeSerializer.serialize(); //
                    }
                });
                root.getChildren().remove(selectedEmployee);
            }
        }
    }

    @FXML
    void copyKey(MouseEvent event) {
        StringSelection selection = new StringSelection(keyText.getText().split(" ")[1]);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, selection);
    }

    private void setChoiceBox() {
        List<String> partitions = new ArrayList<>();
        for (Partition partition : Initializer.employeeList.getPartitions()) {
            partitions.add(partition.name);
        }

        partitionChoiceBox.getItems().addAll(partitions);
        partitionChoiceBox.setVisible(false);
    }

    private void setTreeView() {
        EmployeeList.getAllEmployees().forEach(employee -> root.getChildren().add(new TreeItem<>(employee.firstName + " " + employee.lastName)));
        employeesTree.setRoot(root);
        employeesTree.setShowRoot(false);
    }

    private void showInfo() {
        text.setText("Edit Employee");
        Employee employee = EmployeeList.getEmployee(selectedEmployee.getValue());
        firstNameTextField.setText(employee.firstName);
        lastNameTextField.setText(employee.lastName);
        emailTextField.setText(employee.email);
        phoneNumberTextField.setText(employee.phoneNumber);
        salaryTextField.setText(String.valueOf(employee.salary));
    }



    private void clearFields() {
        firstNameTextField.setText("");
        lastNameTextField.setText("");
        emailTextField.setText("");
        phoneNumberTextField.setText("");
        salaryTextField.setText("");

        clearLastFields();
    }

    private void clearLastFields() {
        partitionChoiceBox.setVisible(false);
        partitionText.setText("");
        keyText.setText("");
        infoLabel.setText("");
    }

    private boolean newEmployeeForm() {
        return partitionChoiceBox.isVisible();
    }

    private void setUniqueKey(Employee employee) {
        String key = RandomStringUtils.random(10,true,true);
        Boss.key_employee_map.put(key, employee);
        //
        Boss.employee_Partition_Map.put(employee, Initializer.employeeList.partitionName_Partition().get(partitionChoiceBox.getValue()));
        //
        infoLabel.setText("Register successfully");
        keyText.setText("Key: " + key);
    }

    private void overrideEmployeeInfo(Employee employee) {
        employee.firstName = firstNameTextField.getText();
        employee.lastName = lastNameTextField.getText();
        employee.email = emailTextField.getText();
        employee.phoneNumber = phoneNumberTextField.getText();
        employee.salary = Integer.parseInt(salaryTextField.getText());
    }
}
