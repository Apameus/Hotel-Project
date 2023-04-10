package com.apameus.gb_hotel_java_fx.employees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Employees {
    private List<Partition> partitions = new ArrayList<>();
    private final Map<String,Partition> partitionName_Partition = new HashMap<>();

    public List<Partition> getPartitions() {return partitions;}
    public void setPartitions(List<Partition> partitions) {this.partitions = partitions;}

    public Map<String, Partition> getMap() {return partitionName_Partition;}
    public void updateMap(){partitions.forEach(partition -> partitionName_Partition.put(partition.name, partition));}


    public static class Partition{
        public String name;
        public List<Employee> employees = new ArrayList<>();

        public Map<Integer, Employee> id_Employee_map = new HashMap<>(); // This map is used to retrieve the employee from an order
        public Map<String, Employee> username_Employee_map = new HashMap<>(); // This map is used to retrieve the employee from a successful login

        public void set_Id_Employee_Map() {employees.forEach(employee -> id_Employee_map.put(employee.ID, employee));}
        public void set_Username_Employee_Map() {employees.forEach(employee -> username_Employee_map.put(employee.userName, employee));}

        public int countEmployees(){return employees.size();}

    }

}