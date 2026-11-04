package com.tort.app;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.*;

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
        title.setTextSize(32);
        title.setTextColor(Color.rgb(220, 70, 130));
        title.setGravity(Gravity.CENTER);
        title.setPadding(10, 20, 10, 20);

        TextView subtitle = new TextView(this);
        subtitle.setText("Ҷаҳони шириниҳо ва пухтупаз");
        subtitle.setTextSize(17);
        subtitle.setTextColor(Color.DKGRAY);
        subtitle.setGravity(Gravity.CENTER);

        main.addView(title);
        main.addView(subtitle);

        String[] menu = {
            "🎂 Тортҳо",
            "🧁 Кексҳо",
            "🍰 Шириниҳо",
            "🥛 Маҳсулот",
            "❤️ Дӯстдоштаҳо",
            "🔍 Ҷустуҷӯи рецепт"
        };

        for (String item : menu) {
            Button button = new Button(this);
            button.setText(item);
            button.setTextSize(17);
            main.addView(button);
        }

        setContentView(main);
    }
}
