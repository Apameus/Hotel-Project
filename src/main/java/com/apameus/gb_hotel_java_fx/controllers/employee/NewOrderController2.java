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


    public void selectItem(ContextMenuEvent event) {
    }

    public void addToTheOrder(MouseEvent mouseEvent) {
    }


    public static class Example {
        private StringProperty name;
        private LongProperty price;
//        private LongProperty lastModified;

//        private ListProperty<String> subCategories;
//        private MapProperty<String, Menu.Option> subCategory_Options;

        public Example(String name, long price) {
            setName(name);
            setPrice(price);
//            setLastModified(size);

        }

        private void setPrice(long price) {
            priceProperty().set(price);
        }

        private LongProperty priceProperty() {
            if (price == null) price = new SimpleLongProperty(this, "price");
            return price;
        }


        public void setName(String value) { nameProperty().set(value); }

        public StringProperty nameProperty() {
            if (name == null) name = new SimpleStringProperty(this, "name");
            return name;
        }

//        public void setLastModified(long value) { lastModifiedProperty().set(value); }

//        public LongProperty lastModifiedProperty() {
//            if (lastModified == null) lastModified = new SimpleLongProperty(this, "lastModified");
//            return lastModified;
//        }

//
        public String getName() { return nameProperty().get(); }
        public long getPrice() { return priceProperty().get(); }
    }


    @FXML
    private TreeTableView<Example> orderTreeTable;

    @FXML
    private TreeTableView<Example> menuTreeTable;

    @FXML
    private TreeTableColumn<Example, String> nameColumn = new TreeTableColumn<>("Name");

    @FXML
    private TreeTableColumn<Example, Long> priceColumn = new TreeTableColumn<>("Price");

//    private final ObservableList<File> dataList = FXCollections.observableArrayList();



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Menu menu = MenuSerializer.parse();

        Example rootExample = new Example("MOLON LAVE", 900);
        TreeItem<Example> root = new TreeItem<>(rootExample);

        List<Menu.Category> categories = menu.getCategories();
        categories.forEach( category -> {
            TreeItem<Example> category_item = new TreeItem<>(new Example(category.name(), 0));
            root.getChildren().add(category_item);

            category.subCategories().forEach(subCategory -> {
                TreeItem<Example> subCategory_item = new TreeItem<>(new Example(subCategory, 0));
                category_item.getChildren().add(subCategory_item);


                category.subCategory_Options().get(subCategory).forEach(option -> {
                    TreeItem<Example> option_item = new TreeItem<>(new Example(option.name(),  option.price().longValue() ));
                    subCategory_item.getChildren().add(option_item);
                });
            });
        });

//
//        List<Files> files = List.of(
//                new Category("Cat.png", 300),
//                new Category("Dog.png", 500),
//                new Category("Bird.png", 100));
//
//        files.forEach(file -> root.getChildren().add(new TreeItem<>(file)));

        menuTreeTable.setRoot(root);

        nameColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>(rootExample.nameProperty().getName()));
        priceColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>(rootExample.priceProperty().getName()));

//        menuTreeTable.getColumns().setAll(nameColumn, priceColumn);

//        menuTreeTable.getColumns().addAll(nameColumn,priceColumn);
    }



}

