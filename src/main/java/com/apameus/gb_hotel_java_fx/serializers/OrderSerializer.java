package com.apameus.gb_hotel_java_fx.serializers;

import com.apameus.gb_hotel_java_fx.employees.Employee;
import com.apameus.gb_hotel_java_fx.employees.EmployeeList;
import com.apameus.gb_hotel_java_fx.orders.Order;
import com.apameus.gb_hotel_java_fx.util.BubbleSort;
import com.apameus.gb_hotel_java_fx.util.Initializer;
import com.apameus.gb_hotel_java_fx.util.Util;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class OrderSerializer{
    public static final Path path = Path.of("C:\\Users\\Ιωάννης Τζωρτζίνης\\IdeaProjects\\GB Hotel -JAVA FX\\src\\main\\resources\\files\\Orders.txt");
    private static List<String> lines = new ArrayList<>();

    public static List<Order> parse(){
        List<Order> orders = new ArrayList<>();
        lines = Util.getAllLines(path);
        lines.removeAll(Collections.singleton(""));

        int employeeId = 0;
        int amount = 0;
        LocalDate date = null;

        for (String line : lines) {
            String value = line.split(" ")[1];

            if (line.startsWith("Employee_ID")) employeeId = Integer.parseInt(value);
            else if (line.startsWith("Amount")) amount = Integer.parseInt(value);
            else if (line.startsWith("Date")) {
                date = LocalDate.parse(value);
                Order order = new Order(employeeId, amount, date);
                orders.add(order);
            }
        }
        return orders;
    }

    public static void serialize(List<Order> orders){
        lines = new ArrayList<>();
        //
//        BubbleSort<Order> bubbleSort = new BubbleSort<>(orders);
//        bubbleSort.sort();
        //
        orders.forEach(order -> {
            lines.add("Employee_ID: " + order.employeeId());
            lines.add("Amount: " + order.amount());
            lines.add("Date: " + order.date());
            lines.add("");
        });

        Util.saveToFile(path, lines);
        //
//        Util.setOrdersInEmployees(orders);
    }



    public static void serialize(Order order){
        lines = new ArrayList<>();
        Initializer.orders.add(order);
        serialize(Initializer.orders);
    }

}
