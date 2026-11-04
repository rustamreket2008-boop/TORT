package com.tort.app;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.*;
import android.os.CountDownTimer;

public class CookingTimerActivity extends Activity {

    private TextView timerText;
    private EditText minutesInput;
    private CountDownTimer timer;
    private long remainingTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setGravity(Gravity.CENTER);
        layout.setPadding(25, 30, 25, 30);
        layout.setBackgroundColor(Color.rgb(255, 240, 246));

        TextView title = new TextView(this);
        title.setText("⏱ ТАЙМЕРИ ПУХТУПАЗӢ");
        title.setTextSize(25);
        title.setTextColor(Color.rgb(220, 70, 130));
        title.setGravity(Gravity.CENTER);

        timerText = new TextView(this);
        timerText.setText("00:00");
        timerText.setTextSize(55);
        timerText.setTextColor(Color.rgb(220, 70, 130));
        timerText.setGravity(Gravity.CENTER);
        timerText.setPadding(10, 35, 10, 35);

        minutesInput = new EditText(this);
        minutesInput.setHint("Вақт бо дақиқа");
        minutesInput.setInputType(2);

        Button startButton = new Button(this);
        startButton.setText("▶ Оғоз");

        Button resetButton = new Button(this);
        resetButton.setText("↻ Аз нав");

        layout.addView(title);
        layout.addView(timerText);
        layout.addView(minutesInput);
        layout.addView(startButton);
        layout.addView(resetButton);

        startButton.setOnClickListener(v -> startTimer());

        resetButton.setOnClickListener(v -> {
            if (timer != null) {
                timer.cancel();
            }
            remainingTime = 0;
            timerText.setText("00:00");
            minutesInput.setText("");
        });

        setContentView(layout);
    }

    private void startTimer() {
        if (timer != null) {
            timer.cancel();
        }

        String value = minutesInput.getText().toString().trim();

        if (value.isEmpty()) {
            Toast.makeText(this, "Вақтро ворид кун",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        int minutes;

        try {
            minutes = Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return;
        }

        if (minutes < 1 || minutes > 600) {
            Toast.makeText(this, "Аз 1 то 600 дақиқа ворид кун",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        remainingTime = minutes * 60L * 1000L;

        timer = new CountDownTimer(remainingTime, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                remainingTime = millisUntilFinished;
                long seconds = millisUntilFinished / 1000;
                long min = seconds / 60;
                long sec = seconds % 60;

                timerText.setText(String.format(
                        java.util.Locale.US, "%02d:%02d", min, sec));
            }

            @Override
            public void onFinish() {
                remainingTime = 0;
                timerText.setText("00:00");
                Toast.makeText(CookingTimerActivity.this,
                        "Вақти пухтупаз тамом шуд!",
                        Toast.LENGTH_LONG).show();
            }
        };

        timer.start();
    }

    @Override
    protected void onDestroy() {
        if (timer != null) {
            timer.cancel();
        }
        super.onDestroy();
    }
}
