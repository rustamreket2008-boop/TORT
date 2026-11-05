package com.tort.app;

import android.app.Activity;
import android.os.Bundle;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class FavoritesActivity extends Activity {

    private ArrayList<String> favorites;
    private LinearLayout list;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        preferences = getSharedPreferences("TORT_FAVORITES", MODE_PRIVATE);

        Set<String> saved = preferences.getStringSet(
            "favorites", new HashSet<String>()
        );

        favorites = new ArrayList<>(saved);

        LinearLayout main = new LinearLayout(this);
        main.setOrientation(LinearLayout.VERTICAL);
        main.setPadding(20, 25, 20, 20);
        main.setBackgroundColor(Color.rgb(255, 240, 246));

        TextView title = new TextView(this);
        title.setText("❤️ Дӯстдоштаҳо");
        title.setTextSize(27);
        title.setTextColor(Color.rgb(220, 70, 130));
        title.setGravity(Gravity.CENTER);
        title.setPadding(5, 20, 5, 20);

        list = new LinearLayout(this);
        list.setOrientation(LinearLayout.VERTICAL);

        ScrollView scroll = new ScrollView(this);
        scroll.addView(list);

        Button clear = new Button(this);
        clear.setText("🗑 Пок кардани ҳама");

        clear.setOnClickListener(v -> {
            favorites.clear();
            saveFavorites();
            showFavorites();
        });

        main.addView(title);
        main.addView(clear);
        main.addView(scroll);

        setContentView(main);

        showFavorites();
    }

    private void saveFavorites() {
        Set<String> saved = new HashSet<>(favorites);

        preferences.edit()
            .putStringSet("favorites", saved)
            .apply();
    }

    private void showFavorites() {
        list.removeAllViews();

        if (favorites.isEmpty()) {
            TextView empty = new TextView(this);
            empty.setText("Ҳоло ягон торт илова нашудааст ❤️");
            empty.setTextSize(17);
            empty.setGravity(Gravity.CENTER);
            empty.setPadding(10, 30, 10, 30);
            list.addView(empty);
            return;
        }

        for (String name : favorites) {
            TextView item = new TextView(this);
            item.setText("❤️ " + name + "     ❌");
            item.setTextSize(18);
            item.setTextColor(Color.DKGRAY);
            item.setPadding(12, 18, 12, 18);

            item.setOnClickListener(v -> {
                favorites.remove(name);
                saveFavorites();
                showFavorites();
            });

            list.addView(item);
        }
    }
}
