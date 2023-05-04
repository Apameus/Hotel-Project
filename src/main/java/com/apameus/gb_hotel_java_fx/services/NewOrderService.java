package com.apameus.gb_hotel_java_fx.services;

import com.apameus.gb_hotel_java_fx.controllers.employee.NewOrderController.Example;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableView;

import java.util.List;

public interface NewOrderService {
    void placeOrder();
    int getTotalCostNumber(TreeItem<Example> rootOrder);

    int getIndexOfExistedItem(TreeItem<Example> target, List<String> list);

    int getAmountOfSelectedItemFromTable(TreeItem<Example> target, List<String> list, TreeTableView<Example> table, int targetIndex);
}
