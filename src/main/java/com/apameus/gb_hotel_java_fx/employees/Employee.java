package com.apameus.gb_hotel_java_fx.employees;

import java.nio.file.Path;

public final class Employee {
    public static final Path PATH = Path.of("C:\\Users\\Ιωάννης Τζωρτζίνης\\IdeaProjects\\Georgioupolis Hotel Project\\src\\main\\resources\\Employees.txt");
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

//    public void newOrder(){
//        int atTable;
//
//        boolean next = true;
//        while (next) {
//            String categoryName;
//            Category selectedCategory = categoryName_Category.get(categoryName);
//            selectedCategory.subCategories();
//        }
//        // add the status
//        {
//            dailyOrdersDelivered++;
//        }
//    }


}
