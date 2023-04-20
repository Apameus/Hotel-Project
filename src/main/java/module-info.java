module com.apameus.gb_hotel_java_fx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires javafx.swing;
    requires java.sql;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.core;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires org.testng;
    requires org.junit.jupiter.api;
    requires org.mockito;
    requires org.apache.commons.lang3;


    opens com.apameus.gb_hotel_java_fx to javafx.fxml;
    exports com.apameus.gb_hotel_java_fx;
    exports com.apameus.gb_hotel_java_fx.controllers;
    opens com.apameus.gb_hotel_java_fx.controllers to javafx.fxml;
    exports com.apameus.gb_hotel_java_fx.controllers.employee;
    opens com.apameus.gb_hotel_java_fx.controllers.employee to javafx.fxml;

    exports com.apameus.gb_hotel_java_fx.controllers.boss;
    opens com.apameus.gb_hotel_java_fx.controllers.boss to javafx.fxml;
}