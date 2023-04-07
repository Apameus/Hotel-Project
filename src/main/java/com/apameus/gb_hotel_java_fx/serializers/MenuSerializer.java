package com.apameus.gb_hotel_java_fx.serializers;


import com.apameus.gb_hotel_java_fx.menu.Menu;
import com.apameus.gb_hotel_java_fx.util.Util;

import java.util.*;

public class MenuSerializer {


    public static Menu parse(){
        Menu menu = new Menu();

        List<String> lines = Util.getAllLines(Menu.PATH);
        lines.removeAll(Collections.singleton(""));

        Menu.Category category = null;
        String name = "";
        List<String> subCategories = new ArrayList<>();
        Map<String, List<Menu.Option>> subCategory_Options = new HashMap<>();

        String sub_Category = "";

        for (var line : lines){
            if (line.startsWith("#")){
                category = new Menu.Category(name, subCategories, subCategory_Options);
                addCategoryToTheList_IfNotEmptyOrNew(category, name);

                subCategories = new ArrayList<>();
                subCategory_Options = new HashMap<>();

                name = line.split("#")[1];
            }
            else if (line.trim().startsWith("/")) {
                sub_Category = line.split("/")[1];
                subCategories.add(sub_Category);
            }
            else if (line.trim().startsWith("-")) {
                Menu.Option option = getOption(line);

                category.addSubCategoryOption(subCategory_Options, sub_Category, option);
            }
        }
        category = new Menu.Category(name, subCategories, subCategory_Options);
        menu.addCategory(category);

        return menu;
    }

    public static void serialize(Menu menu){
        List<String> lines = new ArrayList<>();
        for (var mainCategory : menu.getCategories()){
            String line = "#" + mainCategory.name().toUpperCase();
            lines.add(line);
            for (String subCategory : mainCategory.subCategories()){
                line = "\t" + "/" + subCategory;
                lines.add(line);
                for (Menu.Option option : mainCategory.subCategory_Options().get(subCategory)){
                    line = "\t\t" + "-" + option.name() + " €" + option.price();
                    lines.add(line);
                }
                lines.add("");
            }
            lines.add("");
        }
        Util.saveToFile(Menu.PATH, lines);
    }


    private static void addCategoryToTheList_IfNotEmptyOrNew(Menu.Category category, String previousName) {
        if (!category.name().equals("") || !category.name().equals(previousName)){
            Menu.addCategory(category);
        }

    }


    private static Menu.Option getOption(String line) {
        line = line.trim().replaceAll("-", "");
        String[] lineSplit = line.split(" €");
        String optionName = lineSplit[0];
        Integer optionPrice = Integer.parseInt(lineSplit[1]);

        return new Menu.Option(optionName, optionPrice);

    }
}
