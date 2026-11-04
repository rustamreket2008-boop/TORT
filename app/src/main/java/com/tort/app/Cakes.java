package com.tort.app;

import java.util.ArrayList;

public class Cakes {

    public static ArrayList<String> getAll() {
        ArrayList<String> cakes = new ArrayList<>();

        for (String name : Recipes.cakeNames) {
            cakes.add(name);
        }

        return cakes;
    }

    public static int getCount() {
        return Recipes.cakeNames.length;
    }
}
