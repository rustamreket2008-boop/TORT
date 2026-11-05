package com.tort.app;

import android.app.Activity;
import android.os.Bundle;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.*;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;

public class CookingPlannerActivity extends Activity {

    private ArrayList<String> plans = new ArrayList<>();
    private LinearLayout list;
    private EditText input;
    private Spinner daySpinner;
    private SharedPreferences preferences;

    private String[] days = {
        "Душанбе", "Сешанбе", "Чоршанбе",
        "Панҷшанбе", "Ҷумъа", "Шанбе", "Якшанбе"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        preferences = getSharedPreferences("TORT_PLANNER", MODE_PRIVATE);
        loadPlans();

        LinearLayout main = new LinearLayout(this);
        main.setOrientation(LinearLayout.VERTICAL);
        main.setPadding(20, 25, 20, 20);
        main.setBackgroundColor(Color.rgb(255, 240, 246));

        TextView title = new TextView(this);
        title.setText("📅 Нақшаи пухтупаз");
        title.setTextSize(26);
        title.setTextColor(Color.rgb(220, 70, 130));
        title.setGravity(Gravity.CENTER);
        title.setPadding(5, 15, 5, 20);

        daySpinner = new Spinner(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
            this,
            android.R.layout.simple_spinner_item,
            days
        );
        adapter.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        );
        daySpinner.setAdapter(adapter);

        input = new EditText(this);
        input.setHint("Номи таом ё тортро навис");

        Button add = new Button(this);
        add.setText("➕ Илова кардани нақша");

        list = new LinearLayout(this);
        list.setOrientation(LinearLayout.VERTICAL);

        ScrollView scroll = new ScrollView(this);
        scroll.addView(list);

        add.setOnClickListener(v -> addPlan());

        main.addView(title);
        main.addView(daySpinner);
        main.addView(input);
        main.addView(add);
        main.addView(scroll);

        setContentView(main);
        showPlans();
    }

    private void addPlan() {
        String name = input.getText().toString().trim();

        if (name.isEmpty()) {
            input.setError("Номи таомро навис");
            return;
        }

        String day = daySpinner.getSelectedItem().toString();
        plans.add(day + " — " + name);

        savePlans();
        input.setText("");
        showPlans();

        Toast.makeText(this, "Нақша илова шуд", Toast.LENGTH_SHORT).show();
    }

    private void savePlans() {
        JSONArray array = new JSONArray();

        for (String plan : plans) {
            array.put(plan);
        }

        preferences.edit()
            .putString("plans", array.toString())
            .apply();
    }

    private void loadPlans() {
        String saved = preferences.getString("plans", "[]");

        try {
            JSONArray array = new JSONArray(saved);

            for (int i = 0; i < array.length(); i++) {
                plans.add(array.getString(i));
            }
        } catch (Exception e) {
            plans.clear();
        }
    }

    private void showPlans() {
        list.removeAllViews();

        for (String plan : plans) {
            TextView item = new TextView(this);
            item.setText("🍰 " + plan + "     ❌");
            item.setTextSize(17);
            item.setTextColor(Color.DKGRAY);
            item.setPadding(12, 18, 12, 18);

            item.setOnClickListener(v -> {
                plans.remove(plan);
                savePlans();
                showPlans();
            });

            list.addView(item);
        }
    }
}
