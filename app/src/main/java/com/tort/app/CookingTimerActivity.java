package com.tort.app;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.*;

public class CookingTimerActivity extends Activity {

    private TextView timerText;
    private EditText minutesInput;
    private Handler handler = new Handler();

    private long endTime = 0;
    private long remaining = 0;
    private boolean running = false;

    private Runnable ticker = new Runnable() {
        @Override
        public void run() {
            if (!running) return;

            remaining = endTime - System.currentTimeMillis();

            if (remaining <= 0) {
                remaining = 0;
                running = false;
                updateTimer();

                Toast.makeText(
                    CookingTimerActivity.this,
                    "⏰ Вақт тамом шуд!",
                    Toast.LENGTH_LONG
                ).show();

                return;
            }

            updateTimer();
            handler.postDelayed(this, 1000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout main = new LinearLayout(this);
        main.setOrientation(LinearLayout.VERTICAL);
        main.setGravity(Gravity.CENTER);
        main.setPadding(25, 30, 25, 30);
        main.setBackgroundColor(Color.rgb(255, 240, 246));

        TextView title = new TextView(this);
        title.setText("⏱ Таймери пухтупаз");
        title.setTextSize(27);
        title.setTextColor(Color.rgb(220, 70, 130));
        title.setGravity(Gravity.CENTER);

        minutesInput = new EditText(this);
        minutesInput.setHint("Вақт бо дақиқа");
        minutesInput.setInputType(2);

        timerText = new TextView(this);
        timerText.setTextSize(48);
        timerText.setTextColor(Color.rgb(220, 70, 130));
        timerText.setGravity(Gravity.CENTER);
        timerText.setText("00:00");
        timerText.setPadding(10, 30, 10, 30);

        Button start = new Button(this);
        start.setText("▶ Оғоз");

        Button pause = new Button(this);
        pause.setText("⏸ Таваққуф");

        Button reset = new Button(this);
        reset.setText("↻ Аз нав");

        start.setOnClickListener(v -> startTimer());

        pause.setOnClickListener(v -> pauseTimer());

        reset.setOnClickListener(v -> resetTimer());

        main.addView(title);
        main.addView(minutesInput);
        main.addView(timerText);
        main.addView(start);
        main.addView(pause);
        main.addView(reset);

        setContentView(main);
    }

    private void startTimer() {
        if (running) return;

        if (remaining <= 0) {
            String value = minutesInput.getText().toString().trim();

            if (value.isEmpty()) {
                minutesInput.setError("Дақиқаро навис");
                return;
            }

            try {
                int minutes = Integer.parseInt(value);

                if (minutes <= 0 || minutes > 1440) {
                    minutesInput.setError("Аз 1 то 1440 дақиқа навис");
                    return;
                }

                remaining = minutes * 60L * 1000L;

            } catch (NumberFormatException e) {
                minutesInput.setError("Рақами дуруст навис");
                return;
            }
        }

        running = true;
        endTime = System.currentTimeMillis() + remaining;

        handler.removeCallbacks(ticker);
        ticker.run();
    }

    private void pauseTimer() {
        if (!running) return;

        remaining = Math.max(0, endTime - System.currentTimeMillis());
        running = false;
        handler.removeCallbacks(ticker);
        updateTimer();
    }

    private void resetTimer() {
        running = false;
        handler.removeCallbacks(ticker);
        remaining = 0;
        timerText.setText("00:00");
        minutesInput.setText("");
    }

    private void updateTimer() {
        long seconds = remaining / 1000;

        long minutes = seconds / 60;
        long rest = seconds % 60;

        timerText.setText(
            String.format("%02d:%02d", minutes, rest)
        );
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (running) {
            remaining = Math.max(0, endTime - System.currentTimeMillis());
            handler.removeCallbacks(ticker);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (running) {
            endTime = System.currentTimeMillis() + remaining;
            handler.post(ticker);
        }
    }

    @Override
    protected void onDestroy() {
        handler.removeCallbacks(ticker);
        super.onDestroy();
    }
    }
