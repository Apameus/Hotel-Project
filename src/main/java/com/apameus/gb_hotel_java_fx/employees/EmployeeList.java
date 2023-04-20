package com.apameus.gb_hotel_java_fx.employees;

import com.apameus.gb_hotel_java_fx.util.Initializer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeList {
    private List<Partition> partitions = new ArrayList<>();
    private final Map<String,Partition> partitionName_Partition = new HashMap<>();

    public List<Partition> getPartitions() {return partitions;}
    public void setPartitions(List<Partition> partitions) {this.partitions = partitions;}

    public Map<String, Partition> partitionName_Partition() {return partitionName_Partition;}
    public void updateMap(){partitions.forEach(partition -> partitionName_Partition.put(partition.name, partition));}

    // Extra functionality
    public static List<Employee> getAllEmployees(){
        List<Employee> employeeList = new ArrayList<>();
        Initializer.employeeList.getPartitions().forEach(partition -> {
            employeeList.addAll(partition.employees);
        });
        return employeeList;
    }
    public static int countTotalEmployees(){
//        int totalEmployees = 0;
//        for (EmployeeList.Partition partition : Initializer.employeeList.getPartitions()) {
//            totalEmployees += partition.countEmployees();
//        }
//        return totalEmployees;

        return getAllEmployees().size();
    }

    public static int countTotalDailyIncome() {
        int totalIncome = 0;
        for (Employee employee : getAllEmployees()) {
            totalIncome += employee.dailyOrdersIncome;
        }
        return totalIncome;
    }

    public static int getHigherEmployeeId(){
        // or countEmployees() + 1;
        int highestId = 0;
        for (Employee employee : getAllEmployees()) {
            if (employee.id > highestId) highestId = employee.id;
        }
        return highestId;
    }

    public static Employee getEmployee(String fullName){
        for (Partition partition : Initializer.employeeList.getPartitions()) {
            if (partition.fullName_Employee_Map.containsKey(fullName))
                return partition.fullName_Employee_Map.get(fullName);
        }
        return null;
    }

    public static Partition getPartitionOfEmployee(String fullName){
        for (Partition partition : Initializer.employeeList.getPartitions()) {
            if (partition.fullName_Employee_Map.containsKey(fullName))
                return partition;
        }
        return null;
    }



    public static class Partition{
        public String name;
        public List<Employee> employees = new ArrayList<>();

        public Map<Integer, Employee> id_Employee_map = new HashMap<>(); // This map is used to retrieve the employee from an order
        public Map<String, Employee> username_Employee_map = new HashMap<>(); // This map is used to retrieve the employee from a successful login
        public Map<String, Employee> fullName_Employee_Map = new HashMap<>(); //

        public void set_Id_Employee_Map() {employees.forEach(employee -> id_Employee_map.put(employee.id, employee));}
        public void set_Username_Employee_Map() {employees.forEach(employee -> username_Employee_map.put(employee.userName, employee));}
        public void set_FullName_Employee_Map() {employees.forEach(employee -> fullName_Employee_Map.put(employee.firstName + " " + employee.lastName, employee));}

        // Extra functionality
        public int countEmployees(){return employees.size();}
        public Employee getEmployeeFromFullNameEmployee_map(String fullName){return fullName_Employee_Map.get(fullName);}


    }

}