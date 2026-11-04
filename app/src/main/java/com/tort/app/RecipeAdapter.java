package com.tort.app;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.Gravity;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.graphics.Color;
import android.graphics.Typeface;
import java.util.List;

public class RecipeAdapter extends BaseAdapter {

    private Context context;
    private List<String> cakes;

    public RecipeAdapter(Context context, List<String> cakes) {
        this.context = context;
        this.cakes = cakes;
    }

    @Override
    public int getCount() {
        return cakes.size();
    }

    @Override
    public Object getItem(int position) {
        return cakes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LinearLayout card = new LinearLayout(context);
        card.setOrientation(LinearLayout.VERTICAL);
        card.setGravity(Gravity.CENTER_VERTICAL);
        card.setPadding(22, 18, 22, 18);
        card.setBackgroundColor(Color.rgb(255, 230, 240));

        TextView name = new TextView(context);
        name.setText("🎂 " + cakes.get(position));
        name.setTextSize(18);
        name.setTypeface(null, Typeface.BOLD);
        name.setTextColor(Color.rgb(190, 55, 110));

        TextView description = new TextView(context);
        description.setText("Барои дидани рецепт пахш кунед");
        description.setTextSize(13);
        description.setTextColor(Color.DKGRAY);

        card.addView(name);
        card.addView(description);

        return card;
    }
}
