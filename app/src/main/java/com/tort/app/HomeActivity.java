package com.tort.app;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.*;
import android.text.TextWatcher;
import android.text.Editable;
import android.view.View;

public class HomeActivity extends Activity {

    LinearLayout main;
    ListView list;
    EditText search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        main = new LinearLayout(this);
        main.setOrientation(LinearLayout.VERTICAL);
        main.setPadding(18, 20, 18, 10);
        main.setBackgroundColor(Colors.LIGHT_PINK);

        TextView title = new TextView(this);
        title.setText("🎂 TORT");
        title.setTextSize(32);
        title.setTextColor(Colors.PINK);
        title.setGravity(Gravity.CENTER);
        main.addView(title);

        TextView subtitle = new TextView(this);
        subtitle.setText("Ҷаҳони шириниҳо");
        subtitle.setTextSize(16);
        subtitle.setGravity(Gravity.CENTER);
        subtitle.setTextColor(Colors.DARK);
        main.addView(subtitle);

        search = new EditText(this);
        search.setHint("Ҷустуҷӯи торт...");
        search.setSingleLine(true);
        main.addView(search);

        list = new ListView(this);
        main.addView(list, new LinearLayout.LayoutParams(
            -1, 0, 1
        ));

        showCakes();

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(
                CharSequence s, int start, int before, int count) {
                list.setAdapter(
                    new RecipeAdapter(HomeActivity.this,
                        Search.find(s.toString()))
                );
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        list.setOnItemClickListener((parent, view, position, id) -> {
            String cake = (String) parent.getItemAtPosition(position);
            android.content.Intent intent =
                new android.content.Intent(HomeActivity.this,
                    DetailActivity.class);
            intent.putExtra("cake", cake);
            startActivity(intent);
        });

        setContentView(main);
    }

    private void showCakes() {
        list.setAdapter(
            new RecipeAdapter(this, Cakes.getAll())
        );
    }
          }
