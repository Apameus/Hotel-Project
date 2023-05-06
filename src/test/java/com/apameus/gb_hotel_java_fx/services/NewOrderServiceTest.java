package com.apameus.gb_hotel_java_fx.services;

import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableView;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.apameus.gb_hotel_java_fx.controllers.employee.NewOrderController.Example;
import static org.assertj.core.api.Assertions.assertThat;

class NewOrderServiceTest {

    private NewOrderServiceImplementation service = new NewOrderServiceImplementation();

    @Test
    void getTotalCostTestOfOneChildren(){
        TreeItem<Example> root = new TreeItem(new Example("", ""));

        TreeItem<Example> item1 = new TreeItem(new Example("","25",1));
        root.getChildren().add(item1);

        int actual = service.getTotalCostNumber(root);
        Assertions.assertEquals(25, actual);
    }

    @Test
    void getTotalCostTestOfOneChildrenWithAmountOf3(){
        TreeItem<Example> root = new TreeItem(new Example("", ""));

        TreeItem<Example> item1 = new TreeItem(new Example("","25",3));
        root.getChildren().add(item1);

        int actual = service.getTotalCostNumber(root);
        Assertions.assertEquals(75, actual);
    }

    @Test
    void getTotalCostTestOfTwoChildren(){
        TreeItem<Example> root = new TreeItem(new Example("", ""));

        TreeItem<Example> item1 = new TreeItem(new Example("","21",1));
        TreeItem<Example> item2 = new TreeItem(new Example("","19",1));
        root.getChildren().addAll(item1,item2);

        int actual = service.getTotalCostNumber(root);
        assertThat(actual).isEqualTo(40);
    }

    @Test
    void getIndexOfExistedItemInListOf3(){
        List<String> itemNames = List.of("Banana", "Apple", "GreekSalad");
        TreeItem<Example> item1 = new TreeItem<>(new Example("GreekSalad","12"));

        int actualIndex = service.getIndexOfExistedItem(item1, itemNames);
        assertThat(actualIndex).isEqualTo(2);
    }

    @Test
    void getIndexOfNonExistedItem(){
        List<String> itemNames = List.of("Banana", "Apple");
        TreeItem<Example> item1 = new TreeItem<>(new Example("GreekSalad","12"));

        int actualIndex = service.getIndexOfExistedItem(item1, itemNames);
        assertThat(actualIndex).isEqualTo(-1);
    }


    @Disabled
    @Test
    void getAmountOfSelectedItemFromTable(){
        TreeTableView<Example> table = new TreeTableView<>();
        TreeItem<Example> item1 = new TreeItem<>(new Example("GreekSalad","12",3));

        table.setRoot(item1);

        List<String> itemNames = List.of("Banana", "Apple", "GreekSalad");
        int targetIndex = 0;

        int actualAmount = service.getAmountOfSelectedItemFromTable(item1,itemNames,table,targetIndex);
        assertThat(actualAmount).isEqualTo(3);

    }


}