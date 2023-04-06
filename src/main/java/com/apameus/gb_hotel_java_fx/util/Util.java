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


}
