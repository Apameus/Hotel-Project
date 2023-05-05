package com.apameus.gb_hotel_java_fx.services;

import com.apameus.gb_hotel_java_fx.controllers.employee.NewOrderController.Example;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableView;

import java.util.List;

public final class NewOrderServiceImplementation implements NewOrderService{

    @Override
    public void placeOrder() {

    }

    @Override
    public int getTotalCostNumber(TreeItem<Example> rootOrder) {
        int totalCost = 0;
        for (TreeItem<Example> item : rootOrder.getChildren()) {
            totalCost += Integer.parseInt(item.getValue().getPrice()) * item.getValue().getAmount();
        }
        return totalCost;
    }


    @Override
    public int getAmountOfSelectedItemFromTable(TreeItem<Example> target, List<String> list, TreeTableView<Example> table, int targetIndex) {
        int amount = 0; // ?
        for (String name : list) {
            if (name.equals(target.getValue().getName())){
                amount = table.getTreeItem(targetIndex).getValue().getAmount();
            }
        }

        return amount;
    }


    @Override
    public int getIndexOfExistedItem(TreeItem<Example> target, List<String> list) {
        int realIndex = 0;
        for (String name : list) {
            if (name.equals(target.getValue().getName())){
                realIndex = list.indexOf(name);
            }
        }
        return realIndex;
    }

}
