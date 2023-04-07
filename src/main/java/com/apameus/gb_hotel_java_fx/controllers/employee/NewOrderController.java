package com.apameus.gb_hotel_java_fx.controllers.employee;

import com.apameus.gb_hotel_java_fx.menu.Menu;
import com.apameus.gb_hotel_java_fx.serializers.MenuSerializer;
import com.apameus.gb_hotel_java_fx.util.Util;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

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

        setOrderTree();
        setMenuTree(menu, menuTree);
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


    private void setOrderTree() {
        orderTree.setRoot(rootOrder);
        orderTree.setShowRoot(false);
    }

    public static void setMenuTree(Menu menu, TreeView<String> menuTree) {
        TreeItem<String> rootItem = new TreeItem<>("Menu");
        for (Menu.Category category : menu.getCategories()) {
            TreeItem<String> item = new TreeItem<>(category.name());
            rootItem.getChildren().add(item);

            for (String sub_category : category.subCategories()) {
                TreeItem<String> sub_item = new TreeItem<>(sub_category);
                item.getChildren().add(sub_item);

                for (Menu.Option option : category.subCategory_Options().get(sub_category)) {
                    TreeItem<String> sub_item_option = new TreeItem<>(option.name());
                    sub_item.getChildren().add(sub_item_option);
                }
            }
        }
        menuTree.setRoot(rootItem);
        menuTree.setShowRoot(false);
    }

    public static void setMenuTreeWithStreamAPI(Menu menu, TreeView<String> menuTree) {
        TreeItem<String> rootItem = new TreeItem<>("Menu");
        menu.getCategories().forEach(category -> {
            TreeItem<String> item = new TreeItem<>(category.name());
            rootItem.getChildren().add(item);

            category.subCategories().forEach(subCategory -> {
                TreeItem<String> sub_item = new TreeItem<>(subCategory);
                item.getChildren().add(sub_item);

                category.subCategory_Options().get(subCategory).forEach(option -> {
                    TreeItem<String> sub_item_option = new TreeItem<>(option.name());
                    sub_item.getChildren().add(sub_item_option);
                });
            });
        });
        menuTree.setRoot(rootItem);
        menuTree.setShowRoot(false);

    }
}
