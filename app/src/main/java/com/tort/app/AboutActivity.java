package com.tort.app;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.*;

public class AboutActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setGravity(Gravity.CENTER);
        layout.setPadding(25, 30, 25, 30);
        layout.setBackgroundColor(Color.rgb(255, 240, 246));

        TextView logo = new TextView(this);
        logo.setText("🎂");
        logo.setTextSize(75);
        logo.setGravity(Gravity.CENTER);

        TextView title = new TextView(this);
        title.setText("TORT");
        title.setTextSize(34);
        title.setTextColor(Color.rgb(220, 70, 130));
        title.setGravity(Gravity.CENTER);

        TextView description = new TextView(this);
        description.setText(
                "Хуш омадед ба TORT!\n\n" +
                "TORT барномаест барои дӯстдорони шириниҳо ва пухтупаз.\n\n" +
                "Имкониятҳои барнома:\n\n" +
                "🎂 Рецептҳои тортҳо\n" +
                "🧁 Кексҳо ва шириниҳо\n" +
                "🥛 Рӯйхати маҳсулот\n" +
                "❤️ Рецептҳои дӯстдошта\n" +
                "🛒 Рӯйхати харид\n" +
                "⏱ Таймери пухтупаз\n" +
                "⚖️ Табдилдиҳандаи воҳидҳо\n" +
                "📅 Нақшаи пухтупаз\n" +
                "📖 Маслиҳатҳои пухтупаз\n\n" +
                "Версия: 1.0\n" +
                "Бо TORT пухтупазро лаззатбахш гардон!"
        );
        description.setTextSize(17);
        description.setTextColor(Color.DKGRAY);
        description.setGravity(Gravity.CENTER);
        description.setPadding(5, 25, 5, 10);

        layout.addView(logo);
        layout.addView(title);
        layout.addView(description);

        setContentView(layout);
    }
}
