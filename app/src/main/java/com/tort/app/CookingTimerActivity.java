package com.tort.app;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.*;

public class CookingTimerActivity extends Activity {

    private EditText minutesInput;
    private TextView timerText;
    private Button startButton;

    private Handler handler = new Handler();
    private long endTime = 0;
    private boolean running = false;

    private Runnable timerRunnable = new Runnable() {
        @Override
        public void run() {
            updateTimer();
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

        minutesInput = new EditText(this);
        minutesInput.setHint("Вақт бо дақиқа");
        minutesInput.setInputType(2);

        timerText = new TextView(this);
        timerText.setText("00:00");
        timerText.setTextSize(48);
        timerText.setTextColor(Color.rgb(220, 70, 130));
        timerText.setGravity(Gravity.CENTER);
        timerText.setPadding(10, 30, 10, 30);

        startButton = new Button(this);
        startButton.setText("▶ Оғоз");

        Button resetButton = new Button(this);
        resetButton.setText("↻ Аз нав");

        startButton.setOnClickListener(v -> startTimer());

        resetButton.setOnClickListener(v -> resetTimer());

        main.addView(title);
        main.addView(minutesInput);
        main.addView(timerText);
        main.addView(startButton);
        main.addView(resetButton);

        setContentView(main);

        updateTimer();
    }

    private void startTimer() {
        if (running) return;

        String value = minutesInput.getText().toString().trim();

        if (value.isEmpty()) {
            minutesInput.setError("Дақиқаро навис");
            return;
        }

        try {
            int minutes = Integer.parseInt(value);

            if (minutes < 1 || minutes > 600) {
                minutesInput.setError("Аз 1 то 600 дақиқа");
                return;
            }

            endTime = System.currentTimeMillis() + minutes * 60000L;
            running = true;
            startButton.setEnabled(false);
            updateTimer();

        } catch (NumberFormatException e) {
            minutesInput.setError("Рақами дурустро навис");
        }
    }

    private void updateTimer() {
        if (!running) return;

        long remaining = endTime - System.currentTimeMillis();

        if (remaining <= 0) {
            running = false;
            timerText.setText("00:00");
            startButton.setEnabled(true);

            Toast.makeText(this, "⏰ Вақт тамом шуд!",
                    Toast.LENGTH_LONG).show();
            return;
        }

        long totalSeconds = (remaining + 999) / 1000;
        long minutes = totalSeconds / 60;
        long seconds = totalSeconds % 60;

        timerText.setText(String.format(
                java.util.Locale.getDefault(),
                "%02d:%02d", minutes, seconds
        ));

        handler.removeCallbacks(timerRunnable);
        handler.postDelayed(timerRunnable, 500);
    }

    private void resetTimer() {
        running = false;
        handler.removeCallbacks(timerRunnable);
        timerText.setText("00:00");
        startButton.setEnabled(true);
        minutesInput.setText("");
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateTimer();
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(timerRunnable);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(timerRunnable);
    }
}
