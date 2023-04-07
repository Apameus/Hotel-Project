package com.apameus.gb_hotel_java_fx.controllers.employee;

import com.apameus.gb_hotel_java_fx.menu.Menu;
import com.apameus.gb_hotel_java_fx.serializers.MenuSerializer;
import javafx.beans.property.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public final class NewOrderController2 implements Initializable {


    public static class Example {

        private StringProperty name;

        private StringProperty price;
//        private ListProperty<String> subCategories;
//        private MapProperty<String, Menu.Option> subCategory_Options;

        public Example(String name, String price) {
            setName(name);
            setPrice(price);
        }
        private void setPrice(String price) {priceProperty().set(price);}

        private StringProperty priceProperty() {
            if (price == null) price = new SimpleStringProperty(this, "price");
            return price;
        }

        public void setName(String value) { nameProperty().set(value); }

        public StringProperty nameProperty() {
            if (name == null) name = new SimpleStringProperty(this, "name");
            return name;
        }

        public String getName() { return nameProperty().get(); }

        public String getPrice() { return priceProperty().get(); }

    }
    @FXML
    private TreeTableView<Example> orderTreeTable;

    @FXML
    private TreeTableView<Example> menuTreeTable;
    @FXML
    private TreeTableColumn<Example, String> nameColumn = new TreeTableColumn<>("Name");

    @FXML
    private TreeTableColumn<Example, String> priceColumn = new TreeTableColumn<>("Price");
    @FXML
    private TreeTableColumn<Example, String> orderItemName = new TreeTableColumn<>("Name");

    @FXML
    private TreeTableColumn<Example, String> orderItemPrice = new TreeTableColumn<>("Price");
    @FXML
    private TreeTableColumn<Example, String> orderItemAmount = new TreeTableColumn<>("Name");

    @FXML
    private Button addButton = new Button();
    @FXML
    private Button removeButton = new Button();

    TreeItem<Example> rootOrder = new TreeItem<>(new Example("MOLON LAVE", ""));
    private TreeItem<Example> selectedItem;



//    private final ObservableList<File> dataList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Menu menu = MenuSerializer.parse();

        setOrderTree();
        setMenuTree(menu);
    }

    @FXML
    public void selectItem(ContextMenuEvent event) {
        selectedItem = menuTreeTable.getSelectionModel().getSelectedItem();
    }

    @FXML
    public void addToTheOrder(MouseEvent mouseEvent) {
        if (selectedItem != null){
            rootOrder.getChildren().add(selectedItem);
        }
    }

    @FXML
    public void removeFromTheOrder(MouseEvent mouseEvent) {
        if (selectedItem != null){
            rootOrder.getChildren().remove(selectedItem);
        }
    }



    private void setOrderTree() {
        orderTreeTable.setRoot(rootOrder);
        orderTreeTable.setShowRoot(false);
    }

    private void setMenuTree(Menu menu) {
        Example rootExample = new Example("MOLON LAVE", "");
        TreeItem<Example> root = new TreeItem<>(rootExample);

        List<Menu.Category> categories = menu.getCategories();
        categories.forEach( category -> {
            TreeItem<Example> category_item = new TreeItem<>(new Example(category.name(), ""));
            root.getChildren().add(category_item);

            category.subCategories().forEach(subCategory -> {
                TreeItem<Example> subCategory_item = new TreeItem<>(new Example(subCategory, ""));
                category_item.getChildren().add(subCategory_item);


                category.subCategory_Options().get(subCategory).forEach(option -> {
                    TreeItem<Example> option_item = new TreeItem<>(new Example(option.name(),  option.price().toString() ));
                    subCategory_item.getChildren().add(option_item);
                });
            });
        });

        menuTreeTable.setRoot(root);
        menuTreeTable.setShowRoot(false);
        nameColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>(rootExample.nameProperty().getName()));
        priceColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>(rootExample.priceProperty().getName()));

//        menuTreeTable.getColumns().setAll(nameColumn, priceColumn);
//        menuTreeTable.getColumns().addAll(nameColumn,priceColumn);
    }



}

