package com.tort.app;

import android.app.Activity;
import android.os.Bundle;
import android.widget.*;
import android.view.Gravity;
import android.content.Intent;
import java.util.ArrayList;

public class FavoritesActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(18, 20, 18, 20);
        layout.setBackgroundColor(Colors.LIGHT_PINK);

        TextView title = new TextView(this);
        title.setText("❤️ Дӯстдоштаҳои ман");
        title.setTextSize(25);
        title.setTextColor(Colors.PINK);
        title.setGravity(Gravity.CENTER);
        layout.addView(title);

        ListView list = new ListView(this);

        ArrayList<String> cakes =
            new ArrayList<>(Favorites.getAll(this));

        list.setAdapter(new RecipeAdapter(this, cakes));

        list.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra("cake", cakes.get(position));
            startActivity(intent);
        });

        layout.addView(list,
            new LinearLayout.LayoutParams(-1, 0, 1));

        setContentView(layout);
    }
}
