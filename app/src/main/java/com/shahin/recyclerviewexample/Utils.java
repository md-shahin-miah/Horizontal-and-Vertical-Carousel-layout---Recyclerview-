package com.shahin.recyclerviewexample;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static List<String> getList() {
        List<String> list = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            list.add("Item " + i);
        }

        return list;
    }
    public static List<String> getListOfRecyclerviewType() {
        List<String> list = new ArrayList<>();

            list.add(AppConstants.CAROUSEL_RECYCLERVIEW);
            list.add(AppConstants.ANIMATED_ON_ADAPTER_RECYCLERVIEW);
            list.add(AppConstants.CUSTOM_ANIMATED_RECYCLERVIEW);


        return list;
    }

}
