package com.apameus.gb_hotel_java_fx.serializers;

import com.apameus.gb_hotel_java_fx.employees.Employee;
import com.apameus.gb_hotel_java_fx.util.Initializer;
import com.apameus.gb_hotel_java_fx.util.Util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.apameus.gb_hotel_java_fx.employees.EmployeeList.*;


public final class EmployeeSerializer {

    public static void serialize(){
        List<String> lines = new ArrayList<>();
        for (Partition partition : Initializer.employeeList.getPartitions()){
            lines.add("#" + partition.name);
            for (Employee employee : partition.employees) { employeeFieldsAsLines(lines, employee); }
        }
        Util.saveToFile(Employee.PATH, lines);
    }


    private static void employeeFieldsAsLines(List<String> lines, Employee employee) {
        lines.add("\t" + "/");
        lines.add("\t\t" + "-" + "ID: " + employee.id);
        lines.add("\t\t" + "-" + "User_Name: " + employee.userName);
        lines.add("\t\t" + "-" + "Password: " + employee.password);
        lines.add("");
        lines.add("\t\t" + "-" + "First_Name: " + employee.firstName);
        lines.add("\t\t" + "-" + "Last_Name: " + employee.lastName);
        lines.add("\t\t" + "-" + "Email: " + employee.email);
        lines.add("\t\t" + "-" + "Phone_Number: " + employee.phoneNumber);
        lines.add("");
        lines.add("\t\t" + "-" + "Registered_To_App: " + employee.registeredDate);
        lines.add("");
        lines.add("\t\t" + "-" + "Daily_Orders_Delivered: " + employee.dailyOrdersDelivered);
        lines.add("\t\t" + "-" + "Monthly_Orders_Delivered: " + employee.monthlyOrdersDelivered);
        lines.add("\t\t" + "-" + "Total_Orders_Delivered: " + employee.totalOrdersDelivered);
        lines.add("");
        lines.add("\t\t" + "-" + "Daily_Orders_Income: " + employee.dailyOrdersIncome);
        lines.add("\t\t" + "-" + "Monthly_Orders_Income: " + employee.monthlyOrdersIncome);
        lines.add("\t\t" + "-" + "Total_Orders_Income: " + employee.totalOrdersIncome);
        lines.add("");
        lines.add("\t\t" + "-" + "Points: " + employee.points);
        lines.add("");
        lines.add("\t\t" + "-" + "Salary: " + employee.salary);
        lines.add("\t\t" + "-" + "Bonus: " + employee.bonus);
    }

    public static List<Partition> parse(){
        List<String> lines = Util.getAllLines(Employee.PATH);
        lines.removeAll(Collections.singleton(""));

        List<Partition> partitions = new ArrayList<>();
        Partition partition = new Partition();
        Employee employee = new Employee();

        for (String line : lines) {
            line = line.trim();
            if (line.startsWith("#"))        partition = getPartition(partitions, partition, line);
            else if (line.startsWith("/"))   employee = new Employee();
            else if (line.startsWith("-"))   setEmployeeInfo(partition, employee, line);
        }

        updatePartitionMaps(partition);
        partitions.add(partition);
        return partitions;
    }

    private static Partition getPartition(List<Partition> partitions, Partition partition, String line) {
        if (partition.name != null) {
            partitions.add(partition);
            updatePartitionMaps(partition); //TODO Refactor pls
        }
        partition = new Partition();
        partition.name = line.split("#")[1];
        return partition;
    }

    private static void setEmployeeInfo(Partition partition, Employee employee, String line) {
        line = line.replaceAll("-", "");
        String value = line.split(" ")[1];

        if (line.startsWith("ID: ")) employee.id = Integer.parseInt(value);

        else if (line.startsWith("User_Name: ")) employee.userName = value;

        else if (line.startsWith("Password: ")) employee.password = value;

        else if (line.startsWith("First_Name: ")) employee.firstName = value;

        else if (line.startsWith("Last_Name: ")) employee.lastName = value;

        else if (line.startsWith("Email: ")) employee.email = value;

        else if (line.startsWith("Phone_Number: ")) employee.phoneNumber = value;

        else if (line.startsWith("Registered_To_App: ")) employee.registeredDate = value;

        else if (line.startsWith("Daily_Orders_Delivered: ")) employee.dailyOrdersDelivered = Integer.parseInt(value);

        else if (line.startsWith("Monthly_Orders_Delivered: ")) employee.monthlyOrdersDelivered = Integer.parseInt(value);

        else if (line.startsWith("Total_Orders_Delivered: ")) employee.totalOrdersDelivered = Integer.parseInt(value);

        else if (line.startsWith("Daily_Orders_Income: ")) employee.dailyOrdersIncome = Integer.parseInt(value);

        else if (line.startsWith("Monthly_Orders_Income: ")) employee.monthlyOrdersIncome = Integer.parseInt(value);

        else if (line.startsWith("Total_Orders_Income: ")) employee.totalOrdersIncome = Integer.parseInt(value);

        else if (line.startsWith("Points: ")) employee.points = Integer.parseInt(value);

        else if (line.startsWith("Salary: ")) employee.salary = Integer.parseInt(value);

        else if (line.startsWith("Bonus: ")) {
            employee.bonus = Integer.parseInt(value);
            partition.employees.add(employee);
        }
    }

    private static void updatePartitionMaps(Partition partition) {
        partition.update_id_Employee_Map();
        partition.update_Username_Employee_Map();
        partition.update_FullName_Employee_Map();
    }
}
