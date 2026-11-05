package com.tort.app;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.view.Gravity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.*;
import android.content.Intent;

public class ProductsActivity extends Activity {

    private LinearLayout list;
    private EditText search;

    private String[] names = {
        "Орд", "Шакар", "Тухм", "Шир", "Равған",
        "Равғани маска", "Какао", "Шоколад", "Ванилин",
        "Хамиртуруш", "Намак", "Асал", "Чормағз",
        "Мавиз", "Крахмал", "Қаймоқ", "Панир",
        "Кокос", "Клубника", "Банан", "Лимӯ",
        "Хокаи нонпазӣ", "Қаҳва", "Ҷелатин"
    };

    private String[] quantities = {
        "1 кг", "1 кг", "10 дона", "1 литр", "1 литр",
        "200 грамм", "100 грамм", "200 грамм", "1 баста",
        "1 баста", "1 баста", "500 грамм", "200 грамм",
        "200 грамм", "200 грамм", "500 грамм", "500 грамм",
        "100 грамм", "500 грамм", "1 кг", "500 грамм",
        "1 баста", "100 грамм", "1 баста"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout main = new LinearLayout(this);
        main.setOrientation(LinearLayout.VERTICAL);
        main.setPadding(18, 20, 18, 15);
        main.setBackgroundColor(Color.rgb(255, 240, 246));

        TextView title = new TextView(this);
        title.setText("🥛 Маҳсулоти қаннодӣ");
        title.setTextSize(26);
        title.setTextColor(Color.rgb(220, 70, 130));
        title.setGravity(Gravity.CENTER);
        title.setPadding(5, 15, 5, 15);

        search = new EditText(this);
        search.setHint("🔍 Ҷустуҷӯи маҳсулот...");

        list = new LinearLayout(this);
        list.setOrientation(LinearLayout.VERTICAL);

        ScrollView scroll = new ScrollView(this);
        scroll.addView(list);

        search.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                showProducts(s.toString());
            }

            public void afterTextChanged(Editable s) {}
        });

        main.addView(title);
        main.addView(search);
        main.addView(scroll);

        setContentView(main);
        showProducts("");
    }

    private void showProducts(String query) {
        list.removeAllViews();

        for (int i = 0; i < names.length; i++) {
            if (names[i].toLowerCase().contains(query.toLowerCase())) {

                TextView item = new TextView(this);
                item.setText("🥣 " + names[i] + "\nМиқдори намунавӣ: " + quantities[i]);
                item.setTextSize(17);
                item.setTextColor(Color.DKGRAY);
                item.setPadding(15, 18, 15, 18);

                Button add = new Button(this);
                add.setText("➕ Ба рӯйхати харид");

                final String product = names[i];

                add.setOnClickListener(v -> {
                    Toast.makeText(
                        this,
                        product + " — барои илова ба рӯйхати харид, онро дар бахши рӯйхати харид навис",
                        Toast.LENGTH_LONG
                    ).show();
                });

                list.addView(item);
                list.addView(add);
            }
        }
    }
            }
