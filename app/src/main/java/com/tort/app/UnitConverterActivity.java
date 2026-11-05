package com.tort.app;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.*;

public class UnitConverterActivity extends Activity {

    private EditText amount;
    private Spinner from, to;
    private TextView result;

    private String[] units = {
        "Грамм", "Килограмм", "Миллилитр", "Литр"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout main = new LinearLayout(this);
        main.setOrientation(LinearLayout.VERTICAL);
        main.setPadding(22, 30, 22, 20);
        main.setGravity(Gravity.CENTER_HORIZONTAL);
        main.setBackgroundColor(Color.rgb(255, 240, 246));

        TextView title = new TextView(this);
        title.setText("⚖️ Табдилдиҳандаи воҳидҳо");
        title.setTextSize(25);
        title.setTextColor(Color.rgb(220, 70, 130));
        title.setGravity(Gravity.CENTER);
        title.setPadding(5, 15, 5, 25);

        amount = new EditText(this);
        amount.setHint("Миқдорро навис");
        amount.setInputType(8194);

        from = new Spinner(this);
        to = new Spinner(this);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
            this,
            android.R.layout.simple_spinner_item,
            units
        );

        adapter.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        );

        from.setAdapter(adapter);
        to.setAdapter(adapter);

        Button convert = new Button(this);
        convert.setText("🔄 Ҳисоб кардан");

        result = new TextView(this);
        result.setTextSize(23);
        result.setTextColor(Color.rgb(220, 70, 130));
        result.setGravity(Gravity.CENTER);
        result.setPadding(10, 30, 10, 30);

        convert.setOnClickListener(v -> calculate());

        main.addView(title);
        main.addView(amount);
        main.addView(from);
        main.addView(to);
        main.addView(convert);
        main.addView(result);

        setContentView(main);
    }

    private void calculate() {
        String value = amount.getText().toString().trim();

        if (value.isEmpty()) {
            amount.setError("Миқдорро навис");
            return;
        }

        try {
            double number = Double.parseDouble(value);

            if (number < 0) {
                amount.setError("Рақами мусбат навис");
                return;
            }

            int a = from.getSelectedItemPosition();
            int b = to.getSelectedItemPosition();

            double answer;

            if (a == b) {
                answer = number;
            } else if (a == 0 && b == 1) {
                answer = number / 1000;
            } else if (a == 1 && b == 0) {
                answer = number * 1000;
            } else if (a == 2 && b == 3) {
                answer = number / 1000;
            } else if (a == 3 && b == 2) {
                answer = number * 1000;
            } else {
                result.setText(
                    "Ин ду воҳид мустақим табдил намешаванд."
                );
                return;
            }

            result.setText(
                "Натиҷа: " + String.format("%.3f", answer)
                + " " + units[b]
            );

        } catch (NumberFormatException e) {
            amount.setError("Рақами дуруст навис");
        }
    }
}
