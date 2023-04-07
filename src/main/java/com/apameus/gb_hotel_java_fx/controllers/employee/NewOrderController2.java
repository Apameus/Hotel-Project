package com.apameus.gb_hotel_java_fx.controllers.employee;

import com.apameus.gb_hotel_java_fx.menu.Menu;
import com.apameus.gb_hotel_java_fx.serializers.MenuSerializer;
import javafx.beans.property.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public final class NewOrderController2 implements Initializable {


    public static class Example {

        private StringProperty name;
        private StringProperty price;
        private IntegerProperty amount;

//        private ListProperty<String> subCategories;
//        private MapProperty<String, Menu.Option> subCategory_Options;

        public Example(String name, String price) {
            setName(name);
            setPrice(price);
        }

        public Example(String name, String price, Integer amount) {
            setName(name);
            setPrice(price);
            setAmount(amount);
        }

        public void setName(String value) { nameProperty().set(value); }
        private void setPrice(String price) {priceProperty().set(price);}
        private void setAmount(Integer amount) {amountProperty().set(amount);}


        public StringProperty nameProperty() {
            if (name == null) name = new SimpleStringProperty(this, "name");
            return name;
        }

        private StringProperty priceProperty() {
            if (price == null) price = new SimpleStringProperty(this, "price");
            return price;
        }

        private IntegerProperty amountProperty(){
            if (amount == null) amount = new SimpleIntegerProperty(this, "amount");
            return amount;
        }

        public String getName() { return nameProperty().get(); }

        public String getPrice() { return priceProperty().get(); }

        public Integer getAmount() { return amountProperty().get(); }

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
    private TreeTableColumn<Example, Integer> orderItemAmount = new TreeTableColumn<>("Amount");

    @FXML
    private Button addButton = new Button();
    @FXML
    private Button removeButton = new Button();
    @FXML
    private Text totalCostNumber;


    Example rootExample = new Example("MOLON LAVE", "");
    TreeItem<Example> rootOrder = new TreeItem<>(rootExample);
    TreeItem<Example> menuSelectedItem;
    TreeItem<Example> orderSelectedItem;

    List<String> names = new ArrayList<>();



//    private final ObservableList<Example> dataList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Menu menu = MenuSerializer.parse();

        setOrderTree();
        setMenuTree(menu);
    }

    private void updateTotalCostNumber() {
        int totalCost = 0;
        for (TreeItem<Example> item : rootOrder.getChildren()) {
            totalCost += Integer.parseInt(item.getValue().getPrice()) * item.getValue().getAmount();
        }
        totalCostNumber.setText(Integer.toString(totalCost));
    }

    @FXML
    public void selectItem(MouseEvent event) {
        menuSelectedItem = menuTreeTable.getSelectionModel().getSelectedItem();
    }
    @FXML
    public void selectOrderItem(MouseEvent event) {
        orderSelectedItem = orderTreeTable.getSelectionModel().getSelectedItem();
    }

    @FXML
    public void addToTheOrder(MouseEvent mouseEvent) {
        if (menuSelectedItem == null || !menuSelectedItem.getChildren().isEmpty()) {
            return;
        }
        else if (!names.contains(menuSelectedItem.getValue().getName())) {
            menuSelectedItem.getValue().setAmount(1);

            rootOrder.getChildren().add(menuSelectedItem);

            names.add(menuSelectedItem.getValue().getName());

            updateTotalCostNumber();
            return;
        }

        increaseAmountOfExistedItem();

        updateTotalCostNumber();
        //
//        rootOrder.getChildren().forEach(item -> {
//            if (item.getValue().getName().equals(menuSelectedItem.getValue().getName())) {
//                int rowIndex = orderTreeTable.getRow(item);
//                increaseAmountOfExistedItem(rowIndex);
//            } else {
//                rootOrder.getChildren().add(menuSelectedItem);
//            }
//        });
        //

    }

    private void increaseAmountOfExistedItem() {
        String name = menuSelectedItem.getValue().getName();
        String price = menuSelectedItem.getValue().getPrice();
        int amount = menuSelectedItem.getValue().getAmount() + 1;


        int realIndex = 0;
        for (String nameII : names) {
            if (nameII.equals(menuSelectedItem.getValue().getName())){
                realIndex = names.indexOf(nameII);

                amount = orderTreeTable.getTreeItem(realIndex).getValue().getAmount() + 1;
            }
        }

        TreeItem<Example> trial = new TreeItem<>(new Example(name,price,amount));
        rootOrder.getChildren().set(realIndex, trial);
        //        int rowIndex = orderTreeTable.getRow(menuSelectedItem);
//        TreeItem<Example> target = orderTreeTable.getTreeItem(rowIndex);
//        target.getValue().setAmount(target.getValue().getAmount() + 1);
//
//        orderTreeTable.getTreeItem(rowIndex).getValue().setAmount(orderTreeTable.getTreeItem(rowIndex).getValue().getAmount());

//        rootOrder.getChildren().get(rowIndex).getValue().setAmount(rootOrder.getChildren().get(rowIndex).getValue().getAmount());
    }

    @FXML
    public void removeFromTheOrder(MouseEvent mouseEvent) {
        if (orderSelectedItem != null){
            for (String name : names) {
                if (name.equals(orderSelectedItem.getValue().getName())) {
                    if (orderSelectedItem.getValue().getAmount() <= 1){
                        rootOrder.getChildren().remove(orderSelectedItem);
                        names.remove(name);

                        updateTotalCostNumber();
                        return;
                    }
                    else {
                        int realIndex = names.indexOf(name);
                        String price = orderSelectedItem.getValue().getPrice();
                        int amount = orderSelectedItem.getValue().getAmount() - 1;

                        TreeItem<Example> trial = new TreeItem<>(new Example(name,price,amount));
                        rootOrder.getChildren().set(realIndex, trial);

//                        orderSelectedItem.getValue().setAmount(orderSelectedItem.getValue().getAmount() - 1);
                    }
                }
            }
        }
        updateTotalCostNumber();
    }



    private void setOrderTree() {
        orderTreeTable.setRoot(rootOrder);
        orderTreeTable.setShowRoot(false);
        orderItemName.setCellValueFactory(new TreeItemPropertyValueFactory<>(rootExample.nameProperty().getName()));
        orderItemPrice.setCellValueFactory(new TreeItemPropertyValueFactory<>(rootExample.priceProperty().getName()));
        orderItemAmount.setCellValueFactory(new TreeItemPropertyValueFactory<>(rootExample.amountProperty().getName()));
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

