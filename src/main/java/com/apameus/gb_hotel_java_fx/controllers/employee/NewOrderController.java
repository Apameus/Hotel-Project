package com.apameus.gb_hotel_java_fx.controllers.employee;

import com.apameus.gb_hotel_java_fx.menu.Menu;
import com.apameus.gb_hotel_java_fx.serializers.MenuSerializer;
import com.apameus.gb_hotel_java_fx.util.Util;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

import java.net.URL;
import java.util.ResourceBundle;

public final class NewOrderController implements Initializable {

    @FXML
    private TreeView<String> menuTree = new TreeView<>();
    @FXML
    private TreeView<String> orderTree = new TreeView<>();

    @FXML
    private Button addButton;

    private TreeItem<String> selectedItem;
    TreeItem<String> rootOrder = new TreeItem<>("Order");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Menu menu = new Menu();
        MenuSerializer.parse();
        orderTree.setRoot(rootOrder);
        orderTree.setShowRoot(false);


        Util.setMenuTree(menu, menuTree);
    }



    public void removeItem(){
        TreeItem<String> stringTreeItem = orderTree.getSelectionModel().getSelectedItem();
        rootOrder.getChildren().remove(stringTreeItem);
    }

    public void addToTheOrder(){
        if (selectedItem != null){
            rootOrder.getChildren().add(selectedItem);
        }
    }

    public void selectItem(){
        selectedItem = menuTree.getSelectionModel().getSelectedItem();

    }

}
