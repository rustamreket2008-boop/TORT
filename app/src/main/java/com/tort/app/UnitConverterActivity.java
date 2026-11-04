package com.tort.app;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.*;

public class UnitConverterActivity extends Activity {

    private EditText input;
    private TextView result;
    private Spinner fromSpinner;
    private Spinner toSpinner;

    private String[] units = {
        "Грамм", "Килограмм", "Миллилитр", "Литр"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(22, 30, 22, 20);
        layout.setGravity(Gravity.CENTER);
        layout.setBackgroundColor(Color.rgb(255, 240, 246));

        TextView title = new TextView(this);
        title.setText("⚖️ ТАБДИЛДИҲАНДА");
        title.setTextSize(26);
        title.setTextColor(Color.rgb(220, 70, 130));
        title.setGravity(Gravity.CENTER);

        input = new EditText(this);
        input.setHint("Миқдорро ворид кун");
        input.setInputType(8194);

        fromSpinner = new Spinner(this);
        toSpinner = new Spinner(this);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                units
        );
        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );

        fromSpinner.setAdapter(adapter);
        toSpinner.setAdapter(adapter);
        toSpinner.setSelection(1);

        Button convert = new Button(this);
        convert.setText("Ҳисоб кардан");

        result = new TextView(this);
        result.setTextSize(22);
        result.setTextColor(Color.rgb(220, 70, 130));
        result.setGravity(Gravity.CENTER);
        result.setPadding(10, 25, 10, 25);

        layout.addView(title);
        layout.addView(input);
        layout.addView(fromSpinner);
        layout.addView(toSpinner);
        layout.addView(convert);
        layout.addView(result);

        convert.setOnClickListener(v -> calculate());

        setContentView(layout);
    }

    private void calculate() {
        String value = input.getText().toString().trim();

        if (value.isEmpty()) {
            input.setError("Миқдорро навис");
            return;
        }

        try {
            double number = Double.parseDouble(value);
            String from = units[fromSpinner.getSelectedItemPosition()];
            String to = units[toSpinner.getSelectedItemPosition()];

            double answer;

            if (from.equals(to)) {
                answer = number;
            } else if (from.equals("Грамм") && to.equals("Килограмм")) {
                answer = number / 1000;
            } else if (from.equals("Килограмм") && to.equals("Грамм")) {
                answer = number * 1000;
            } else if (from.equals("Миллилитр") && to.equals("Литр")) {
                answer = number / 1000;
            } else if (from.equals("Литр") && to.equals("Миллилитр")) {
                answer = number * 1000;
            } else {
                result.setText("Ин ду воҳид табдил намешаванд");
                return;
            }

            result.setText("Натиҷа: " + answer + " " + to);

        } catch (NumberFormatException e) {
            result.setText("Миқдори дурустро ворид кун");
        }
    }
}
