package com.tort.app;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.*;
import android.view.ViewGroup;
import java.util.ArrayList;

public class ShoppingListActivity extends Activity {

    private ArrayList<String> items;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(20, 25, 20, 20);
        layout.setBackgroundColor(Color.rgb(255, 240, 246));

        TextView title = new TextView(this);
        title.setText("🛒 РӮЙХАТИ ХАРИД");
        title.setTextSize(27);
        title.setTextColor(Color.rgb(220, 70, 130));
        title.setGravity(Gravity.CENTER);
        title.setPadding(10, 20, 10, 25);
        layout.addView(title);

        EditText input = new EditText(this);
        input.setHint("Номи маҳсулотро навис...");
        input.setTextSize(16);
        layout.addView(input);

        Button addButton = new Button(this);
        addButton.setText("➕ Илова кардан");
        layout.addView(addButton);

        items = new ArrayList<>();

        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                items
        );

        ListView list = new ListView(this);
        list.setAdapter(adapter);

        layout.addView(list, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                0,
                1
        ));

        addButton.setOnClickListener(v -> {
            String product = input.getText().toString().trim();

            if (product.isEmpty()) {
                Toast.makeText(this,
                        "Аввал номи маҳсулотро навис!",
                        Toast.LENGTH_SHORT).show();
            } else {
                items.add("🛍 " + product);
                adapter.notifyDataSetChanged();
                input.setText("");
            }
        });

        list.setOnItemClickListener((parent, view, position, id) -> {
            items.remove(position);
            adapter.notifyDataSetChanged();
            Toast.makeText(this,
                    "Аз рӯйхат хориҷ шуд",
                    Toast.LENGTH_SHORT).show();
        });

        setContentView(layout);
    }
              }
