package com.tort.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.*;
import android.content.Intent;
import java.util.ArrayList;
import java.util.Arrays;

public class CategoryActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String category = getIntent().getStringExtra("category");

        ArrayList<String> items = new ArrayList<>();
        String titleText = "🎂 TORT";

        if ("cupcakes".equals(category)) {
            items.addAll(Arrays.asList(Cupcakes.names));
            titleText = "🧁 Кексҳо";
        } else if ("desserts".equals(category)) {
            items.addAll(Arrays.asList(Desserts.names));
            titleText = "🍰 Шириниҳо";
        } else if ("pastries".equals(category)) {
            items.addAll(Arrays.asList(Pastries.names));
            titleText = "🥐 Қаннодӣ";
        }

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(18, 20, 18, 10);
        layout.setBackgroundColor(Colors.LIGHT_PINK);

        TextView title = new TextView(this);
        title.setText(titleText);
        title.setTextSize(28);
        title.setTextColor(Colors.PINK);
        title.setGravity(Gravity.CENTER);
        layout.addView(title);

        ListView list = new ListView(this);
        list.setAdapter(new RecipeAdapter(this, items));

        list.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(
                    CategoryActivity.this,
                    DetailActivity.class
            );

            intent.putExtra("cake", items.get(position));
            startActivity(intent);
        });

        layout.addView(list, new LinearLayout.LayoutParams(
                -1, 0, 1
        ));

        setContentView(layout);
    }
  }
