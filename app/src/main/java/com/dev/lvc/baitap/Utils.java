package com.dev.lvc.baitap;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;

public class Utils {
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
    public static String loadJSONFromAssets(Context context) {
        String json = null;
        try {
            InputStream inputStream = context.getAssets().open("food.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, StandardCharsets.UTF_8);


        } catch (IOException e) {
            Log.e("", "loadJSONFromAssets: "+e);
            return null;

        }
        return json;
    }
}
