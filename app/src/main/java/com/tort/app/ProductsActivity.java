package com.tort.app;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.*;

public class ProductsActivity extends Activity {

    String[] products = {
        "Орд",
        "Шакар",
        "Тухм",
        "Шир",
        "Равған",
        "Қаймоқ",
        "Какао",
        "Шоколад",
        "Қулфинай",
        "Асал",
        "Разрыхлитель",
        "Ванилин",
        "Намак",
        "Сода"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(20, 25, 20, 20);
        layout.setBackgroundColor(Color.rgb(255, 240, 246));

        TextView title = new TextView(this);
        title.setText("🥛 МАҲСУЛОТИ ЛОЗИМА");
        title.setTextSize(25);
        title.setTextColor(Color.rgb(220, 70, 130));
        title.setGravity(Gravity.CENTER);
        title.setPadding(10, 20, 10, 20);

        layout.addView(title);

        ListView list = new ListView(this);
        list.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                products
        ));

        layout.addView(list, new LinearLayout.LayoutParams(
                -1, 0, 1
        ));

        setContentView(layout);
    }
}
