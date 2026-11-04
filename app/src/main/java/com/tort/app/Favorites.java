package com.tort.app;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.HashSet;
import java.util.Set;

public class Favorites {

    private static final String PREFS = "TORT_FAVORITES";

    public static void add(Context context, String cake) {
        SharedPreferences prefs =
            context.getSharedPreferences(PREFS, Context.MODE_PRIVATE);

        Set<String> items = new HashSet<>(
            prefs.getStringSet("cakes", new HashSet<>())
        );

        items.add(cake);
        prefs.edit().putStringSet("cakes", items).apply();
    }

    public static void remove(Context context, String cake) {
        SharedPreferences prefs =
            context.getSharedPreferences(PREFS, Context.MODE_PRIVATE);

        Set<String> items = new HashSet<>(
            prefs.getStringSet("cakes", new HashSet<>())
        );

        items.remove(cake);
        prefs.edit().putStringSet("cakes", items).apply();
    }

    public static Set<String> getAll(Context context) {
        SharedPreferences prefs =
            context.getSharedPreferences(PREFS, Context.MODE_PRIVATE);

        return new HashSet<>(
            prefs.getStringSet("cakes", new HashSet<>())
        );
    }
}
