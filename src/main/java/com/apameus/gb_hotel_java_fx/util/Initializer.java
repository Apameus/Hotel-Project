package com.apameus.gb_hotel_java_fx.util;

import com.apameus.gb_hotel_java_fx.employees.EmployeeList;
import com.apameus.gb_hotel_java_fx.menu.Menu;
import com.apameus.gb_hotel_java_fx.orders.Order;
import com.apameus.gb_hotel_java_fx.serializers.EmployeeSerializer;
import com.apameus.gb_hotel_java_fx.serializers.MenuSerializer;
import com.apameus.gb_hotel_java_fx.serializers.OrderSerializer;

import java.time.LocalDate;
import java.util.List;

public final class Initializer {

    public static final Menu menu = MenuSerializer.parse();
    public static final List<Order> orders = OrderSerializer.parse();

    public static final EmployeeList employeeList = new EmployeeList();

    public static LocalDate day = LocalDate.now();
    public static LocalDate month = LocalDate.now();

    public static void initialize() {
        employeeList.setPartitions(EmployeeSerializer.parse());
        employeeList.updateMap();

        // We might need to set the menu maps also
    }


//    public void ordersToEmployees(){
//        BubbleSort<Order> bubbleSort = new BubbleSort<>(orders);
//        bubbleSort.sort();
//    }


}
