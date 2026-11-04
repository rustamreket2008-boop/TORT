package com.tort.app;

public class RecipeDetails {

    public static String getIngredients(String cake) {
        return "Орд — 300 грамм\n" +
               "Шакар — 200 грамм\n" +
               "Тухм — 3 дона\n" +
               "Шир — 200 мл\n" +
               "Равған — 100 мл\n" +
               "Разрыхлитель — 10 грамм";
    }

    public static String getSteps(String cake) {
        return "1. Тухм ва шакарро омехта кун.\n\n" +
               "2. Шир ва равғанро илова кун.\n\n" +
               "3. Орд ва разрыхлительро илова карда омехта кун.\n\n" +
               "4. Хамирро ба қолаб рез.\n\n" +
               "5. Дар танӯри пешакӣ гармшуда паз, то тайёр шавад.";
    }

    public static String getTime(String cake) {
        return "45 дақиқа";
    }
}
