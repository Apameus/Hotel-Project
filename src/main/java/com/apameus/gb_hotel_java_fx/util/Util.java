package com.apameus.gb_hotel_java_fx.util;

import com.apameus.gb_hotel_java_fx.employees.EmployeeList;
import com.apameus.gb_hotel_java_fx.orders.Order;
import com.apameus.gb_hotel_java_fx.serializers.EmployeeSerializer;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public final class Util {

    public static List<String> getAllLines(Path path) {
        try {
            return Files.readAllLines(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void saveToFile(Path path, List<String> lines) {
        try {
            Files.write(path,lines);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void changeScene(String name, Button button)  {
        try {
            URL url = new File("src/main/resources/com/apameus/gb_hotel_java_fx/" + name).toURI().toURL();
            Parent root = FXMLLoader.load(url);
            Stage stage = (Stage) button.getScene().getWindow();
            stage.setScene(new Scene(root));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Sets for every employee, the order status (that is parsed from the order.txt).
     * Also serialize the Employees.txt with the new status & updates the active employeeList.
     * @param orders the orderList parsed from the txt file.
     */
    public static void setOrdersInEmployees(List<Order> orders) {
        EmployeeList.getAllEmployees().forEach(employee ->
                                orders.forEach(order -> {
            if (employee.id == order.employeeId()) employee.addOrder(order);
        }));

        EmployeeSerializer.serialize();
        Initializer.employeeList.setPartitions(EmployeeSerializer.parse());
    }
}
