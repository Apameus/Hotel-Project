package com.apameus.gb_hotel_java_fx.menu;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Menu{
    public static final Path PATH = Path.of("C:\\Users\\Ιωάννης Τζωρτζίνης\\IdeaProjects\\GB Hotel -JAVA FX\\src\\main\\resources\\files\\Menu.txt");
    private static List<Category> categories = new ArrayList<>();

    public List<Category> getCategories(){return categories;}

    public static void addCategory(Category category){categories.add(category);}
    //
//    public static Map<String,Category> categoryName_Category = new HashMap<>();

//    public void setMap(){categories.forEach(e -> categoryName_Category.put(e.name, e));}

    //
//    public void setCategories(List<Category> categories) {Menu.categories = categories;}


    public record Category(String name, List<String> subCategories, Map<String, List<Option>> subCategory_Options) {

        public void addSubCategory(String subCategory){
            List<Option> options = new ArrayList<>();
            subCategories.add(subCategory);
            subCategory_Options.put(subCategory, options);
        }

        public void addSubCategoryOption(Map<String, List<Option>> subCategory_Options, String subCategory, Option option){
            if (subCategory_Options.size() == 0 || subCategory_Options.get(subCategory) == null){
                List<Option> options = new ArrayList<>();
                options.add(option);
                subCategory_Options.put(subCategory, options);
            }
            else {
                subCategory_Options.get(subCategory).add(option);
            }
        }
    }

    public record Option(String name, Integer price){
    }
}
