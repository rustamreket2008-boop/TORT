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

public class ShoppingListActivity extends Activity {

    private ArrayList<String> items;
    private LinearLayout list;
    private EditText input;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        preferences = getSharedPreferences("TORT_SHOPPING", MODE_PRIVATE);

        items = new ArrayList<>(
            preferences.getStringSet("items", new HashSet<>())
        );

        LinearLayout main = new LinearLayout(this);
        main.setOrientation(LinearLayout.VERTICAL);
        main.setPadding(20, 25, 20, 20);
        main.setBackgroundColor(Color.rgb(255, 240, 246));

        TextView title = new TextView(this);
        title.setText("🛒 Рӯйхати харид");
        title.setTextSize(27);
        title.setTextColor(Color.rgb(220, 70, 130));
        title.setGravity(Gravity.CENTER);
        title.setPadding(5, 15, 5, 20);

        input = new EditText(this);
        input.setHint("Номи маҳсулотро навис");

        Button add = new Button(this);
        add.setText("➕ Илова кардан");

        Button clear = new Button(this);
        clear.setText("🗑 Пок кардани рӯйхат");

        list = new LinearLayout(this);
        list.setOrientation(LinearLayout.VERTICAL);

        ScrollView scroll = new ScrollView(this);
        scroll.addView(list);

        add.setOnClickListener(v -> addItem());

        clear.setOnClickListener(v -> {
            items.clear();
            saveItems();
            showItems();
            Toast.makeText(this, "Рӯйхат пок шуд",
                    Toast.LENGTH_SHORT).show();
        });

        main.addView(title);
        main.addView(input);
        main.addView(add);
        main.addView(clear);
        main.addView(scroll);

        setContentView(main);
        showItems();
    }

    private void addItem() {
        String value = input.getText().toString().trim();

        if (value.isEmpty()) {
            input.setError("Номи маҳсулотро навис");
            return;
        }

        items.add(value);
        saveItems();
        input.setText("");
        showItems();
    }

    private void saveItems() {
        Set<String> saved = new HashSet<>(items);
        preferences.edit().putStringSet("items", saved).apply();
    }

    private void showItems() {
        list.removeAllViews();

        for (String value : items) {
            TextView item = new TextView(this);
            item.setText("🛒 " + value + "     ❌");
            item.setTextSize(18);
            item.setTextColor(Color.DKGRAY);
            item.setPadding(12, 18, 12, 18);

            item.setOnClickListener(v -> {
                items.remove(value);
                saveItems();
                showItems();
            });

            list.addView(item);
        }
    }
}
