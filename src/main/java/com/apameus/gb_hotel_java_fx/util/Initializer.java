package com.apameus.gb_hotel_java_fx.util;

import com.apameus.gb_hotel_java_fx.employees.Employees;
import com.apameus.gb_hotel_java_fx.menu.Menu;
import com.apameus.gb_hotel_java_fx.orders.Order;
import com.apameus.gb_hotel_java_fx.serializers.EmployeeSerializer;
import com.apameus.gb_hotel_java_fx.serializers.MenuSerializer;
import com.apameus.gb_hotel_java_fx.serializers.OrderSerializer;

import java.util.List;

public final class Initializer {

    public static final Menu menu = MenuSerializer.parse();
    public static final List<Order> orders = OrderSerializer.parse();

    public static final Employees employees = new Employees();


    public static void initialize() {
        employees.setPartitions(EmployeeSerializer.parse());
        employees.updateMap();

        // We might need to set the menu maps also
    }
}
