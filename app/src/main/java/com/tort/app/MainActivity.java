package com.tort.app;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.*;
import android.content.Intent;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout main = new LinearLayout(this);
        main.setOrientation(LinearLayout.VERTICAL);
        main.setPadding(20, 30, 20, 20);
        main.setBackgroundColor(Color.rgb(255, 240, 246));

        TextView title = new TextView(this);
        title.setText("🎂 TORT");
        title.setTextSize(34);
        title.setTextColor(Color.rgb(220, 70, 130));
        title.setGravity(Gravity.CENTER);
        title.setPadding(10, 25, 10, 20);

        TextView subtitle = new TextView(this);
        subtitle.setText("Ҷаҳони шириниҳо ва пухтупаз");
        subtitle.setTextSize(17);
        subtitle.setTextColor(Color.DKGRAY);
        subtitle.setGravity(Gravity.CENTER);

        main.addView(title);
        main.addView(subtitle);

        Button cakes = createButton("🎂 Тортҳо");
        Button cupcakes = createButton("🧁 Кексҳо");
        Button desserts = createButton("🍰 Шириниҳо");
        Button pastries = createButton("🥐 Қаннодӣ");
        Button products = createButton("🥛 Маҳсулот");
        Button favorites = createButton("❤️ Дӯстдоштаҳо");
        Button search = createButton("🔍 Ҷустуҷӯи рецепт");

        main.addView(cakes);
        main.addView(cupcakes);
        main.addView(desserts);
        main.addView(pastries);
        main.addView(products);
        main.addView(favorites);
        main.addView(search);

        cakes.setOnClickListener(v -> openHome());
        search.setOnClickListener(v -> openHome());

        cupcakes.setOnClickListener(v ->
                openCategory("cupcakes"));

        desserts.setOnClickListener(v ->
                openCategory("desserts"));

        pastries.setOnClickListener(v ->
                openCategory("pastries"));

        favorites.setOnClickListener(v ->
                startActivity(new Intent(
                        MainActivity.this,
                        FavoritesActivity.class
                )));

        products.setOnClickListener(v ->
                Toast.makeText(this,
                        "Бахши маҳсулот баъд илова мешавад",
                        Toast.LENGTH_SHORT).show());

        setContentView(main);
    }

    private Button createButton(String text) {
        Button button = new Button(this);
        button.setText(text);
        button.setTextSize(17);
        return button;
    }

    private void openHome() {
        startActivity(new Intent(
                MainActivity.this,
                HomeActivity.class
        ));
    }

    private void openCategory(String category) {
        Intent intent = new Intent(
                MainActivity.this,
                CategoryActivity.class
        );

        intent.putExtra("category", category);
        startActivity(intent);
    }
}
