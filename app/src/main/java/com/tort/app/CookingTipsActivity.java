package com.tort.app;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.view.Gravity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.*;

public class CookingTipsActivity extends Activity {

    private LinearLayout list;
    private EditText search;

    private String[] titles = {
        "Ҳарорати дурусти танӯр",
        "Омода кардани хамир",
        "Тухмро чӣ гуна истифода барем?",
        "Креми мулоим",
        "Тортро чӣ гуна нигоҳ дорем?",
        "Пухтани кекс",
        "Истифодаи шоколад",
        "Ордро чӣ гуна тайёр кунем?",
        "Ороиши торт",
        "Бисквити нарм",
        "Истифодаи равған",
        "Мева барои торт",
        "Хамиртуруш",
        "Намак дар шириниҳо",
        "Пухтани кулча"
    };

    private String[] descriptions = {
        "Пеш аз гузоштани торт танӯрро мувофиқи дастури дорухат гарм кун.",
        "Маҳсулотро тадриҷан омехта кун ва хамирро аз ҳад зиёд наҷунбон.",
        "Барои натиҷаи якхела тухмро пешакӣ аз яхдон барор.",
        "Қаймоқи хунукро истифода бар ва онро то пайдо шудани шакли устувор бизан.",
        "Тортро дар зарфи пӯшида ва мувофиқи таркибаш дар яхдон нигоҳ дор.",
        "Қолаби кексро аз ҳад зиёд пур накун.",
        "Шоколадро оҳиста об кун, то насӯзад.",
        "Ордро аз элак гузарон, то доначаҳо кам шаванд.",
        "Барои ороиш аз мева, крем ва шоколад истифода бар.",
        "Хамирро ором омехта кун, то бисквит нарм барояд.",
        "Миқдори равғанро мувофиқи дорухат истифода бар.",
        "Меваи тозаро бишӯй ва пеш аз ороиш хушк кун.",
        "Хамиртурушро мувофиқи дастур истифода бар.",
        "Каме намак метавонад таъми шириниро мувозинат кунад.",
        "Ҳарорати танӯр ва вақти пухтанро мувофиқи дорухат риоя кун."
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout main = new LinearLayout(this);
        main.setOrientation(LinearLayout.VERTICAL);
        main.setPadding(18, 20, 18, 15);
        main.setBackgroundColor(Color.rgb(255, 240, 246));

        TextView title = new TextView(this);
        title.setText("📖 Маслиҳатҳои пухтупаз");
        title.setTextSize(25);
        title.setTextColor(Color.rgb(220, 70, 130));
        title.setGravity(Gravity.CENTER);
        title.setPadding(5, 15, 5, 15);

        search = new EditText(this);
        search.setHint("🔍 Ҷустуҷӯи маслиҳат...");

        list = new LinearLayout(this);
        list.setOrientation(LinearLayout.VERTICAL);

        ScrollView scroll = new ScrollView(this);
        scroll.addView(list);

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                showTips(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        main.addView(title);
        main.addView(search);
        main.addView(scroll);

        setContentView(main);
        showTips("");
    }

    private void showTips(String query) {
        list.removeAllViews();

        boolean found = false;

        for (int i = 0; i < titles.length; i++) {
            if (titles[i].toLowerCase().contains(query.toLowerCase())
                || descriptions[i].toLowerCase().contains(query.toLowerCase())) {

                found = true;

                TextView heading = new TextView(this);
                heading.setText("💡 " + titles[i]);
                heading.setTextSize(19);
                heading.setTextColor(Color.rgb(220, 70, 130));
                heading.setPadding(12, 15, 12, 5);

                TextView description = new TextView(this);
                description.setText(descriptions[i]);
                description.setTextSize(16);
                description.setTextColor(Color.DKGRAY);
                description.setPadding(18, 5, 18, 15);

                list.addView(heading);
                list.addView(description);

                View divider = new View(this);
                divider.setBackgroundColor(Color.rgb(245, 190, 210));
                list.addView(divider,
                    new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT, 2
                    ));
            }
        }

        if (!found) {
            TextView empty = new TextView(this);
            empty.setText("Маслиҳат ёфт нашуд.");
            empty.setTextSize(17);
            empty.setGravity(Gravity.CENTER);
            empty.setPadding(10, 25, 10, 25);
            list.addView(empty);
        }
    }
}
