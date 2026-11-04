package com.tort.app;

import java.util.ArrayList;

public class Search {

    public static ArrayList<String> find(String query) {
        ArrayList<String> results = new ArrayList<>();

        if (query == null || query.trim().isEmpty()) {
            return results;
        }

        for (String cake : Recipes.cakeNames) {
            if (cake.toLowerCase().contains(query.toLowerCase().trim())) {
                results.add(cake);
            }
        }

        return results;
    }
}
