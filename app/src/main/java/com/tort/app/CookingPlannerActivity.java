package com.tort.app;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.*;

public class CookingPlannerActivity extends Activity {

    private String[] days = {
        "Душанбе", "Сешанбе", "Чоршанбе",
        "Панҷшанбе", "Ҷумъа", "Шанбе", "Якшанбе"
    };

    private Spinner daySpinner;
    private EditText foodInput;
    private LinearLayout plans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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

        foodInput = new EditText(this);
        foodInput.setHint("Номи ширинӣ ё тортро навис");

        Button addButton = new Button(this);
        addButton.setText("➕ Илова кардан");

        TextView listTitle = new TextView(this);
        listTitle.setText("Нақшаҳои ман:");
        listTitle.setTextSize(20);
        listTitle.setTextColor(Color.rgb(220, 70, 130));
        listTitle.setPadding(5, 20, 5, 10);

        plans = new LinearLayout(this);
        plans.setOrientation(LinearLayout.VERTICAL);

        ScrollView scroll = new ScrollView(this);
        scroll.addView(plans);

        addButton.setOnClickListener(v -> addPlan());

        main.addView(title);
        main.addView(daySpinner);
        main.addView(foodInput);
        main.addView(addButton);
        main.addView(listTitle);
        main.addView(scroll);

        setContentView(main);
    }

    private void addPlan() {
        String food = foodInput.getText().toString().trim();

        if (food.isEmpty()) {
            foodInput.setError("Номи шириниро навис");
            return;
        }

        TextView item = new TextView(this);
        item.setText("📅 " +
                daySpinner.getSelectedItem().toString() +
                " — " + food + "     ❌");

        item.setTextSize(17);
        item.setTextColor(Color.DKGRAY);
        item.setPadding(12, 18, 12, 18);

        item.setOnClickListener(v -> {
            plans.removeView(item);
            Toast.makeText(this, "Нақша нест карда шуд",
                    Toast.LENGTH_SHORT).show();
        });

        plans.addView(item);
        foodInput.setText("");

        Toast.makeText(this, "Нақша илова шуд",
                Toast.LENGTH_SHORT).show();
    }
}
