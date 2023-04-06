package com.apameus.gb_hotel_java_fx.serializers;


import com.apameus.gb_hotel_java_fx.menu.Menu;
import com.apameus.gb_hotel_java_fx.util.Util;

import java.util.*;

public class MenuSerializer {
    static Menu menu = new Menu();

    public Menu getMenu(){
        return menu;
    }

    public static void parse(){
        List<String> lines = Util.getAllLines(Menu.PATH);
        lines.removeAll(Collections.singleton(""));

        Menu.Category category = null;
        String name = "";
        List<String> sub_Categories = new ArrayList<>();
        Map<String, List<String>> sub_CategoryOptions = new HashMap<>();

        String sub_Category = "";

        for (var line : lines){
            if (line.startsWith("#")){
                category = new Menu.Category(name, sub_Categories, sub_CategoryOptions);
                category.addCategoryToTheList_IfNotEmptyOrNew(category, name);
                sub_Categories = new ArrayList<>();
                sub_CategoryOptions = new HashMap<>();

                name = line.split("#")[1];
            }
            else if (line.trim().startsWith("/")) {
                sub_Category = line.split("/")[1];
                sub_Categories.add(sub_Category);
            }
            else if (line.trim().startsWith("-")) {
                String option = line.split("-")[1];
                category.addSubCategoryOption(sub_CategoryOptions, sub_Category, option);
            }
        }
        category = new Menu.Category(name, sub_Categories, sub_CategoryOptions);
        menu.addCategory(category);
    }

    public void serialize(Menu menu){
        List<String> lines = new ArrayList<>();
        for (var mainCategory : menu.getCategories()){
            String line = "#" + mainCategory.name().toUpperCase();
            lines.add(line);
            for (String subCategory : mainCategory.subCategories()){
                line = "\t" + "/" + subCategory;
                lines.add(line);
                for (String option : mainCategory.subCategory_Options().get(subCategory)){
                    line = "\t\t" + "-" + option;
                    lines.add(line);
                }
                lines.add("");
            }
            lines.add("");
        }
        Util.saveToFile(Menu.PATH, lines);
    }

}
