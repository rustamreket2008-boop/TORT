
package com.tort.app;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.*;

public class CookingTipsActivity extends Activity {

    private String[] tips = {
        "🎂 Пеш аз пухтан танӯрро гарм кун.",
        "🥚 Тухмҳоро пеш аз истифода каме дар ҳарорати хона нигоҳ дор.",
        "🥛 Маҳсулотро бо миқдори дар рецепт навишташуда истифода бар.",
        "🍰 Хамирро аз ҳад зиёд омехта накун.",
        "⏱ Ҳангоми пухтан дари танӯрро зуд-зуд накушо.",
        "🍫 Шоколадро бо ҳарорати паст об кун.",
        "🧁 Қолабро пеш аз рехтани хамир омода кун.",
        "🍓 Меваҳоро пеш аз ороиш шуста, хушк намо.",
        "❄️ Пеш аз крем молидан тортро пурра хунук кун.",
        "❤️ Ҳангоми пухтупаз тозагӣ ва эҳтиётро риоя кун."
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(20, 25, 20, 20);
        layout.setBackgroundColor(Color.rgb(255, 240, 246));

        TextView title = new TextView(this);
        title.setText("📖 Маслиҳатҳои пухтупаз");
        title.setTextSize(25);
        title.setTextColor(Color.rgb(220, 70, 130));
        title.setGravity(Gravity.CENTER);
        title.setPadding(5, 15, 5, 20);

        ScrollView scroll = new ScrollView(this);
        LinearLayout list = new LinearLayout(this);
        list.setOrientation(LinearLayout.VERTICAL);

        for (String tip : tips) {
            TextView item = new TextView(this);
            item.setText(tip);
            item.setTextSize(17);
            item.setTextColor(Color.DKGRAY);
            item.setPadding(15, 18, 15, 18);
            list.addView(item);
        }

        scroll.addView(list);
        layout.addView(title);
        layout.addView(scroll);

        setContentView(layout);
    }
}
