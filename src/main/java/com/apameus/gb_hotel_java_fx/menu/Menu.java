package com.apameus.gb_hotel_java_fx.menu;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Menu{
    public static final Path PATH = Path.of("C:\\Users\\Ιωάννης Τζωρτζίνης\\IdeaProjects\\Georgioupolis Hotel Project\\src\\main\\resources\\Menu.txt");
    private static List<Category> categories = new ArrayList<>();


    //
    public static Map<String,Category> categoryName_Category = new HashMap<>();
    public void setMap(){categories.forEach(e -> categoryName_Category.put(e.name, e));}
    //

    public void setCategories(List<Category> categories) {Menu.categories = categories;}
    public static void addCategory(Category category){categories.add(category);}
    public List<Category> getCategories(){return categories;}


    public record Category(String name, List<String> subCategories, Map<String, List<String>> subCategory_Options) {

        public void addSubCategory(String subCategory){
            List<String> options = new ArrayList<>();
            subCategories.add(subCategory);
            subCategory_Options.put(subCategory, options);
        }


        public void addCategoryToTheList_IfNotEmptyOrNew(Category category, String previousName) {
            if (!category.name().equals("") || !category.name().equals(previousName)){
                addCategory(category);
            }
        }

        public void addSubCategoryOption(Map<String, List<String>> sub_CategoryOptions, String sub_Category, String option){
            if (sub_CategoryOptions.size() == 0 || sub_CategoryOptions.get(sub_Category) == null){
                List<String> options = new ArrayList<>();
                options.add(option);
                sub_CategoryOptions.put(sub_Category, options);
            }
            else {
                sub_CategoryOptions.get(sub_Category).add(option);
            }
        }
    }
}
