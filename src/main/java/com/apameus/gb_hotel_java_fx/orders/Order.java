package com.apameus.gb_hotel_java_fx.orders;

import java.time.LocalDate;

public record Order(int employeeId, int amount, LocalDate date) implements Comparable<Order>{

    @Override
    public int compareTo(Order o) {
        // sort by id
        if (this.employeeId > o.employeeId) return 1;
        else if (this.employeeId < o.employeeId) return -1;
        else return 0;
    }


}
