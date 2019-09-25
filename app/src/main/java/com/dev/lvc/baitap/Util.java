package com.dev.lvc.baitap;

import java.text.DecimalFormat;

public class Util {
    private static DecimalFormat decimalFormat = new DecimalFormat("#.##");

    public static String convertTempF(int tempC){
        float F;
        F = (float) (tempC * 1.8 + 32.00);
        return decimalFormat.format(F) + " \u2109";
    }
    public static float BMI(float weightKG, int heightCm){
        float BMI;
        float height = (float) (heightCm/100.00);
        BMI = (float) (weightKG/(height*height));
        String convertDot = decimalFormat.format(BMI).replace(",",".");
        float f = Float.parseFloat(convertDot);
        return f;
    }
}
