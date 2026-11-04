package com.tort.app;

public class Ingredients {

    public static String get(String cake) {
        if (cake.contains("Шоколад")) {
            return "Орд — 250 г\nКакао — 50 г\nШакар — 200 г\nТухм — 3 дона\nШир — 200 мл\nРавған — 100 мл";
        }

        if (cake.contains("Қулфинай")) {
            return "Орд — 250 г\nҚулфинай — 300 г\nШакар — 180 г\nТухм — 3 дона\nҚаймоқ — 200 мл";
        }

        if (cake.contains("Асалӣ")) {
            return "Орд — 400 г\nАсал — 150 г\nШакар — 150 г\nТухм — 3 дона\nРавған — 100 г\nСода — 1 қошуқча";
        }

        return "Орд — 300 г\nШакар — 200 г\nТухм — 3 дона\nШир — 200 мл\nРавған — 100 мл\nРазрыхлитель — 10 г";
    }
}
