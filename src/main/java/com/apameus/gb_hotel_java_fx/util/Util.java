package com.apameus.gb_hotel_java_fx.util;

import com.apameus.gb_hotel_java_fx.menu.Menu;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public final class Util {


    public static String getInput(String msg) {
        System.out.println(msg);
        return String.valueOf(new Scanner(System.in));

    }

    public static List<String> getAllLines(Path path) {
        try {
            return Files.readAllLines(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void saveToFile(Path path, List<String> lines) {
        try {
            Files.write(path,lines);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void setMenuTree(Menu menu, TreeView<String> menuTree) {
        TreeItem<String> rootItem = new TreeItem<>("Menu");
        for (Menu.Category category : menu.getCategories()) {
            TreeItem<String> item = new TreeItem<>(category.name());
            rootItem.getChildren().add(item);

            for (String sub_category : category.subCategories()) {
                TreeItem<String> sub_item = new TreeItem<>(sub_category);
                item.getChildren().add(sub_item);

                for (String option : category.subCategory_Options().get(sub_category)) {
                    TreeItem<String> sub_item_option = new TreeItem<>(option);
                    sub_item.getChildren().add(sub_item_option);
                }

            }
        }

        menuTree.setRoot(rootItem);
        menuTree.setShowRoot(false);
    }
}
