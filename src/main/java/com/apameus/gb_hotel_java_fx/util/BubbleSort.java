package com.apameus.gb_hotel_java_fx.util;

import java.util.List;

public final class BubbleSort <T extends Comparable<T>>{
    List<T> list;

    public BubbleSort(List<T> list) {
        this.list = list;
    }

    public void sort(){
        for (int i = 0; i < list.size() - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < list.size() -1 -i; j++) {
                if (list.get(j).compareTo(list.get(j + 1)) > 0) {
                    T temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                    swapped = true;
                }
            }
            if (!swapped){
                break;
            }
        }
    }
}
