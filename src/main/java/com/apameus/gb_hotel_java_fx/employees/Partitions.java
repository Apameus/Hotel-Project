package com.apameus.gb_hotel_java_fx.employees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Partitions {
    public static List<Partition> partitions = new ArrayList<>();

    Map<String,Partition> partitionName_Partition = new HashMap<>();
    public void setMap(){partitions.forEach(e -> partitionName_Partition.put(e.name, e));}


    public static class Partition{
        public String name;
        public List<Employee> employees = new ArrayList<>();

        public int countEmployees(){return employees.size();}
    }


}