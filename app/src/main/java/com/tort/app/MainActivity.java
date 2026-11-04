package com.tort.app;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.*;
import android.content.Intent;

public class MainActivity extends Activity {

    private LinearLayout main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        main = new LinearLayout(this);
        main.setOrientation(LinearLayout.VERTICAL);
        main.setPadding(20, 25, 20, 25);
        main.setBackgroundColor(Color.rgb(255, 240, 246));

        TextView logo = new TextView(this);
        logo.setText("🎂");
        logo.setTextSize(55);
        logo.setGravity(Gravity.CENTER);

        TextView title = new TextView(this);
        title.setText("TORT");
        title.setTextSize(34);
        title.setTextColor(Color.rgb(220, 70, 130));
        title.setGravity(Gravity.CENTER);

        TextView subtitle = new TextView(this);
        subtitle.setText("Ҷаҳони шириниҳо ва пухтупаз");
        subtitle.setTextSize(17);
        subtitle.setTextColor(Color.DKGRAY);
        subtitle.setGravity(Gravity.CENTER);
        subtitle.setPadding(5, 5, 5, 20);

        main.addView(logo);
        main.addView(title);
        main.addView(subtitle);

        addButton("🎂 Тортҳо", HomeActivity.class);
        addButton("🧁 Кексҳо", CategoryActivity.class, "cupcakes");
        addButton("🍰 Шириниҳо", CategoryActivity.class, "desserts");
        addButton("🥐 Қаннодӣ", CategoryActivity.class, "pastries");
        addButton("🥛 Маҳсулот", ProductsActivity.class);
        addButton("❤️ Дӯстдоштаҳо", FavoritesActivity.class);
        addButton("🛒 Рӯйхати харид", ShoppingListActivity.class);
        addButton("⏱ Таймери пухтупаз", CookingTimerActivity.class);
        addButton("⚖️ Табдилдиҳандаи воҳидҳо", UnitConverterActivity.class);
        addButton("📖 Маслиҳатҳои пухтупаз", CookingTipsActivity.class);
        addButton("📅 Нақшаи пухтупаз", CookingPlannerActivity.class);
        addButton("ℹ️ Дар бораи барнома", AboutActivity.class);

        ScrollView scroll = new ScrollView(this);
        scroll.addView(main);

        setContentView(scroll);
    }

    private void addButton(String text, Class<?> activity) {
        Button button = createButton(text);

        button.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, activity)));

        main.addView(button);
    }

    private void addButton(
            String text,
            Class<?> activity,
            String category
    ) {
        Button button = createButton(text);

        button.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, activity);
            intent.putExtra("category", category);
            startActivity(intent);
        });

        main.addView(button);
    }

    private Button createButton(String text) {
        Button button = new Button(this);
        button.setText(text);
        button.setTextSize(16);
        button.setAllCaps(false);

        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );

        params.setMargins(0, 5, 0, 5);
        button.setLayoutParams(params);

        return button;
    }
                                  }
