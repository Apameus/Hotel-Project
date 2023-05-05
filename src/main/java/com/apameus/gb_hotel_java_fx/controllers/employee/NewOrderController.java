package com.apameus.gb_hotel_java_fx.controllers.employee;

import com.apameus.gb_hotel_java_fx.menu.Menu;
import com.apameus.gb_hotel_java_fx.orders.Order;
import com.apameus.gb_hotel_java_fx.serializers.EmployeeSerializer;
import com.apameus.gb_hotel_java_fx.serializers.OrderSerializer;
import com.apameus.gb_hotel_java_fx.services.NewOrderService;
import com.apameus.gb_hotel_java_fx.services.NewOrderServiceImplementation;
import com.apameus.gb_hotel_java_fx.util.Initializer;
import com.apameus.gb_hotel_java_fx.util.Util;
import javafx.beans.property.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public final class NewOrderController implements Initializable {


    public static class Example {

        private StringProperty name;
        private StringProperty price;
        private IntegerProperty amount;


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
    private Button cancelButton;
    @FXML
    private Button placeOrderButton;
    @FXML
    private Button addButton;
    @FXML
    private Button removeButton;
    @FXML
    private Text totalCostNumber;
    @FXML
    private Label notificationLabel ;


    Example rootExample = new Example("", "");
    TreeItem<Example> rootOrder = new TreeItem<>(rootExample);
    TreeItem<Example> rootMenu = new TreeItem<>(rootExample);
    TreeItem<Example> orderSelectedItem;
    TreeItem<Example> menuSelectedItem;

    List<String> orderItemNames = new ArrayList<>(); //ToDo maybe i can find a better solution for this

    NewOrderService service = new NewOrderServiceImplementation();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setOrderTree();
        setMenuTree();
    }

    @FXML
    void cancel(ActionEvent event) {
        Util.changeScene("employee/employee_profile.fxml", cancelButton);
    }

    @FXML
    void placeOrder(ActionEvent event) {
        if (orderTreeTable.getTreeItem(0) == null) return;

        int employeeID = EmployeeProfileController.employee.id;
        int amount = Integer.parseInt(totalCostNumber.getText().split(" €")[0]);
        LocalDate date = LocalDate.now();

        Order order = new Order(employeeID, amount, date);
        OrderSerializer.serialize(order);

        EmployeeProfileController.employee.addOrder(order);
        EmployeeSerializer.serialize(); // ToDo we serialize the whole list for just one added order..

        cleanTheOrderTable();
    }

    private void cleanTheOrderTable() {
        orderItemNames.clear();
        rootOrder.getChildren().clear();
        totalCostNumber.setText("0 €");
        notificationLabel.setText("Order placed successfully !!");
    }


    @FXML
    public void selectItem(MouseEvent event) {
        menuSelectedItem = menuTreeTable.getSelectionModel().getSelectedItem();
    }

    @FXML
    public void selectOrderItem(MouseEvent event) { orderSelectedItem = orderTreeTable.getSelectionModel().getSelectedItem(); }

    @FXML
    public void addToTheOrder(MouseEvent mouseEvent) {
        notificationLabel.setText(""); // ToDo every time the user use the ADD button, the notificationLabel is set.

        if (menuSelectedItem == null || !menuSelectedItem.getChildren().isEmpty()) {
            return;
        }
        else if (orderItemNames.contains(menuSelectedItem.getValue().getName())) {
            increaseAmountOfExistedItem();
        }
        else {
            addSelectedItem();
        }

        updateTotalCostNumber();
    }

    @FXML
    public void removeFromTheOrder(MouseEvent mouseEvent) {
        if (orderSelectedItem == null) return;

        for (String name : orderItemNames) {
            if (!name.equals(orderSelectedItem.getValue().getName())) continue;

            if (orderSelectedItem.getValue().getAmount() <= 1){
                rootOrder.getChildren().remove(orderSelectedItem);
                orderItemNames.remove(name);
//                updateTotalCostNumber();
            }
            else {
                int realIndex = orderItemNames.indexOf(name);
                int prevAmount = rootOrder.getChildren().get(realIndex).getValue().getAmount();

                rootOrder.getChildren().get(realIndex).getValue().setAmount(prevAmount -1);
                orderTreeTable.refresh();

            }
            updateTotalCostNumber();
            return;
        }
    }

    private void updateTotalCostNumber() {
        int totalCost = service.getTotalCostNumber(rootOrder);
        totalCostNumber.setText(totalCost + " €");
    }

    private void increaseAmountOfExistedItem() {
        int indexOfItem = service.getIndexOfExistedItem(menuSelectedItem, orderItemNames);
        int amount = service.getAmountOfSelectedItemFromTable(menuSelectedItem, orderItemNames, orderTreeTable, indexOfItem);
        orderTreeTable.getTreeItem(indexOfItem).getValue().setAmount(amount + 1);
        orderTreeTable.refresh();
    }

    private void addSelectedItem() {
        menuSelectedItem.getValue().setAmount(1);
        rootOrder.getChildren().add(menuSelectedItem);
        orderItemNames.add(menuSelectedItem.getValue().getName());
    }



    private void setOrderTree() {
        orderTreeTable.setRoot(rootOrder);
        orderTreeTable.setShowRoot(false);
        orderItemName.setCellValueFactory(new TreeItemPropertyValueFactory<>(rootExample.nameProperty().getName()));
        orderItemPrice.setCellValueFactory(new TreeItemPropertyValueFactory<>(rootExample.priceProperty().getName()));
        orderItemAmount.setCellValueFactory(new TreeItemPropertyValueFactory<>(rootExample.amountProperty().getName()));
    }

    private void setMenuTree() {

        List<Menu.Category> categories = Initializer.menu.getCategories();
        categories.forEach( category -> {
            TreeItem<Example> category_item = new TreeItem<>(new Example(category.name(), ""));
            rootMenu.getChildren().add(category_item);

            category.subCategories().forEach(subCategory -> {
                TreeItem<Example> subCategory_item = new TreeItem<>(new Example(subCategory, ""));
                category_item.getChildren().add(subCategory_item);


                category.subCategory_Options().get(subCategory).forEach(option -> {
                    TreeItem<Example> option_item = new TreeItem<>(new Example(option.name(),  option.price().toString() ));
                    subCategory_item.getChildren().add(option_item);
                });
            });
        });

        menuTreeTable.setRoot(rootMenu);
        menuTreeTable.setShowRoot(false);
        nameColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>(rootExample.nameProperty().getName()));
        priceColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>(rootExample.priceProperty().getName()));
    }



}

