package com.apameus.gb_hotel_java_fx.controllers.employee;

import com.apameus.gb_hotel_java_fx.menu.Menu;
import com.apameus.gb_hotel_java_fx.serializers.MenuSerializer;
import javafx.beans.Observable;
import javafx.beans.property.*;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import com.apameus.gb_hotel_java_fx.menu.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public final class NewOrderController2 implements Initializable {


    public void selectItem(ContextMenuEvent event) {
    }

    public void addToTheOrder(MouseEvent mouseEvent) {
    }


    public static class File {
        private StringProperty name;
        private LongProperty lastModified;



        public File(String name, long size) {
            setName(name);
            setLastModified(size);
        }

        public void setName(String value) { nameProperty().set(value); }

        public StringProperty nameProperty() {
            if (name == null) name = new SimpleStringProperty(this, "name");
            return name;
        }

        public void setLastModified(long value) { lastModifiedProperty().set(value); }

        public LongProperty lastModifiedProperty() {
            if (lastModified == null) lastModified = new SimpleLongProperty(this, "lastModified");
            return lastModified;
        }


        public String getName() { return nameProperty().get(); }
        public long getLastModified() { return lastModifiedProperty().get(); }
    }


    @FXML
    private TreeTableView<File> orderTreeTable;

    @FXML
    private TreeTableView<File> menuTreeTable;

    @FXML
    private TreeTableColumn<File, String> nameColumn = new TreeTableColumn<>("Filename");

    @FXML
    private TreeTableColumn<File, Long> priceColumn = new TreeTableColumn<>("Size");

//    private final ObservableList<File> dataList = FXCollections.observableArrayList();



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Menu menu = MenuSerializer.parse();
        MenuSerializer.serialize(menu);

        File rootFile = new File("Images", 900);
        TreeItem<File> root = new TreeItem<>(rootFile);



        List<File> files = List.of(
                new File("Cat.png", 300),
                new File("Dog.png", 500),
                new File("Bird.png", 100));

        files.forEach(file -> root.getChildren().add(new TreeItem<>(file)));

        menuTreeTable.setRoot(root);

        nameColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>(rootFile.nameProperty().getName()));
        priceColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>(rootFile.lastModifiedProperty().getName()));

        menuTreeTable.getColumns().setAll(nameColumn, priceColumn);

    }



}

