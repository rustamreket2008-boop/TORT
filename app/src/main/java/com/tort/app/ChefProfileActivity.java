package com.tort.app;

import android.app.Activity;
import android.os.Bundle;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.*;

public class ChefProfileActivity extends Activity {

    private EditText nameInput;
    private TextView savedName;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        preferences = getSharedPreferences("TORT_CHEF", MODE_PRIVATE);

        LinearLayout main = new LinearLayout(this);
        main.setOrientation(LinearLayout.VERTICAL);
        main.setPadding(25, 30, 25, 20);
        main.setGravity(Gravity.CENTER_HORIZONTAL);
        main.setBackgroundColor(Color.rgb(255, 240, 246));

        TextView title = new TextView(this);
        title.setText("👨‍🍳 Профили ошпаз");
        title.setTextSize(27);
        title.setTextColor(Color.rgb(220, 70, 130));
        title.setGravity(Gravity.CENTER);
        title.setPadding(5, 20, 5, 25);

        TextView avatar = new TextView(this);
        avatar.setText("👨‍🍳");
        avatar.setTextSize(75);
        avatar.setGravity(Gravity.CENTER);

        TextView label = new TextView(this);
        label.setText("Номи ошпаз:");
        label.setTextSize(18);
        label.setTextColor(Color.DKGRAY);

        nameInput = new EditText(this);
        nameInput.setHint("Номи худро навис");
        nameInput.setText(
            preferences.getString("name", "")
        );

        Button save = new Button(this);
        save.setText("💾 Нигоҳ доштани ном");

        savedName = new TextView(this);
        savedName.setTextSize(20);
        savedName.setTextColor(Color.rgb(220, 70, 130));
        savedName.setGravity(Gravity.CENTER);
        savedName.setPadding(10, 20, 10, 20);

        TextView info = new TextView(this);
        info.setText(
            "TORT — ёрдамчии пухтупаз\n\n" +
            "Дар ин барнома шумо метавонед:\n" +
            "🎂 Тортҳои гуногунро омӯзед\n" +
            "🧁 Шириниҳо тайёр кунед\n" +
            "📖 Маслиҳатҳои пухтупазро хонед\n" +
            "❤️ Дӯстдоштаҳоро нигоҳ доред\n" +
            "📅 Нақшаи пухтупаз созед"
        );
        info.setTextSize(17);
        info.setTextColor(Color.DKGRAY);
        info.setPadding(10, 20, 10, 20);

        save.setOnClickListener(v -> {
            String name = nameInput.getText().toString().trim();

            if (name.isEmpty()) {
                nameInput.setError("Номро навис");
                return;
            }

            preferences.edit()
                .putString("name", name)
                .apply();

            showName(name);

            Toast.makeText(
                this,
                "Профил нигоҳ дошта шуд!",
                Toast.LENGTH_SHORT
            ).show();
        });

        main.addView(title);
        main.addView(avatar);
        main.addView(label);
        main.addView(nameInput);
        main.addView(save);
        main.addView(savedName);
        main.addView(info);

        setContentView(main);

        showName(preferences.getString("name", ""));
    }

    private void showName(String name) {
        if (name.isEmpty()) {
            savedName.setText("Хуш омадед ба TORT!");
        } else {
            savedName.setText("👋 Салом, " + name + "!");
        }
    }
  }
