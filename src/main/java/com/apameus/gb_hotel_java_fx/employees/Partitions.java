package com.apameus.gb_hotel_java_fx.employees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Partitions { //toDo remove this sh*t
    public static List<Partition> partitions = new ArrayList<>(); //toDo remove this sh*t

    Map<String,Partition> partitionName_Partition = new HashMap<>();
    public void setMap(){partitions.forEach(partition -> partitionName_Partition.put(partition.name, partition));}


    public static class Partition{
        public String name;
        public List<Employee> employees = new ArrayList<>();

        public static Map<Integer, Employee> id_Employee_map = new HashMap<>();
        public static Map<String, Employee> username_Employee_map = new HashMap<>();
        public int countEmployees(){return employees.size();}

        public void set_Id_Employee_Map() {employees.forEach(employee -> id_Employee_map.put(employee.ID, employee));}
        public void set_Username_Employee_Map() {employees.forEach(employee -> username_Employee_map.put(employee.userName, employee));}
    }


}