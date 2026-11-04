package com.tort.app;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.*;

public class DetailActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String cake = getIntent().getStringExtra("cake");

        if (cake == null) cake = "Торти шоколадӣ";

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(22, 25, 22, 25);
        layout.setBackgroundColor(Colors.LIGHT_PINK);

        TextView title = new TextView(this);
        title.setText("🎂 " + cake);
        title.setTextSize(26);
        title.setTextColor(Colors.PINK);
        title.setGravity(Gravity.CENTER);
        layout.addView(title);

        TextView ingredientsTitle = new TextView(this);
        ingredientsTitle.setText("\n🥛 Компонентҳо:");
        ingredientsTitle.setTextSize(21);
        ingredientsTitle.setTextColor(Colors.DARK);
        layout.addView(ingredientsTitle);

        TextView ingredients = new TextView(this);
        ingredients.setText(Ingredients.get(cake));
        ingredients.setTextSize(17);
        layout.addView(ingredients);

        TextView stepsTitle = new TextView(this);
        stepsTitle.setText("\n👩‍🍳 Тарзи пухтан:");
        stepsTitle.setTextSize(21);
        stepsTitle.setTextColor(Colors.DARK);
        layout.addView(stepsTitle);

        TextView steps = new TextView(this);
        steps.setText(CookingSteps.get(cake));
        steps.setTextSize(16);
        layout.addView(steps);

        Button favorite = new Button(this);
        favorite.setText("❤️ Илова ба дӯстдоштаҳо");
        layout.addView(favorite);

        favorite.setOnClickListener(v -> {
            Favorites.add(this, cake);
            Toast.makeText(this,
                "Ба дӯстдоштаҳо илова шуд!",
                Toast.LENGTH_SHORT).show();
        });

        ScrollView scroll = new ScrollView(this);
        scroll.addView(layout);
        setContentView(scroll);
    }
}
