package com.apameus.gb_hotel_java_fx.employees;

import com.apameus.gb_hotel_java_fx.orders.Order;

import java.nio.file.Path;
import java.time.LocalDate;

public final class Employee {
    public static final Path PATH = Path.of("C:\\Users\\Ιωάννης Τζωρτζίνης\\IdeaProjects\\GB Hotel -JAVA FX\\src\\main\\resources\\files\\Employees.txt");
    //
    public int ID;
    public String userName;
    public String password;
    //
    public String firstName;
    public String lastName;
    public String email;
    public String phoneNumber;
    //
    public String registeredDate;
    public int dailyOrdersDelivered;
    public int monthlyOrdersDelivered;
    public int totalOrdersDelivered;
    //
    public int dailyOrdersIncome;
    public int monthlyOrdersIncome;
    public int totalOrdersIncome;
    //
    public int points;
    //
    public int salary;
    public int bonus;

    public void addOrder(Order order) {
        this.dailyOrdersDelivered ++;
        this.monthlyOrdersDelivered ++;
        this.totalOrdersDelivered ++;

        this.dailyOrdersIncome += order.amount();
        this.monthlyOrdersIncome += order.amount();
        this.totalOrdersIncome += order.amount();
    }

    public void refresh(LocalDate date){ // ToDo refresh();
        // if a day passed -> refreshDaily;
        // if a month passed -> refreshMonthly;
        // if the season ended -> refreshTotal;
    }

    private void refreshDaily(){
        this.dailyOrdersDelivered = 0;
        this.dailyOrdersIncome = 0;
    }

    private void refreshMonthly(){
        this.monthlyOrdersDelivered = 0;
        this.monthlyOrdersIncome = 0;
    }

    private void refreshTotal(){
        this.totalOrdersDelivered = 0;
        this.totalOrdersIncome = 0;
    }

}
