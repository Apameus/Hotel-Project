package com.apameus.gb_hotel_java_fx.services;

import javafx.scene.control.TreeItem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.apameus.gb_hotel_java_fx.controllers.employee.NewOrderController.Example;

class NewOrderServiceTest {


    @Test
    void getTotalCostTest(){
        NewOrderServiceImplementation service = new NewOrderServiceImplementation();
        Example example = new Example("","25");
        TreeItem<Example> item = new TreeItem(example);

        int actual = service.getTotalCostNumber(item);

        Assertions.assertEquals(25, actual);
    }

}