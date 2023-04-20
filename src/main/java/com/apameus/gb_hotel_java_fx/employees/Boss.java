package com.apameus.gb_hotel_java_fx.employees;

import java.util.HashMap;
import java.util.Map;

import static com.apameus.gb_hotel_java_fx.employees.EmployeeList.*;

public final class Boss {

    public static Map<String, Employee> key_employee_map = new HashMap<>();
    public static Map<Employee, Partition> employee_Partition_Map = new HashMap<>();
}
