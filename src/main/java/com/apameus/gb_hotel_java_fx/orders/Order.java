package com.apameus.gb_hotel_java_fx.orders;

import java.time.LocalDate;

public record Order(int employeeId, int amount, LocalDate date) {
}
