package com.dev.lvc.baitap;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;

public class Utils {

    public static String uri = "file:///android_asset/image/";

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
    public static String loadJSONFromAssets(Context context,String nameJson) {
        String json = null;
        try {
            InputStream inputStream = context.getAssets().open(nameJson);
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
    public static SQLiteDatabase readSQLBookFromAssets(String filename, Context context) {
        File file = context.getDatabasePath(filename);
        if (!file.exists()) {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdir();
            }

            InputStream inputStream = null;
            try {
                inputStream = context.getAssets().open(filename);
            } catch (IOException e) {
                e.printStackTrace();
            }
            OutputStream outputStream = null;
            try {
                outputStream = new FileOutputStream(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            byte[] buffer = new byte[1024 * 8];
            int numOfBytesToRead;
            try {
                while ((numOfBytesToRead = inputStream.read(buffer)) > 0)
                    outputStream.write(buffer, 0, numOfBytesToRead);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return SQLiteDatabase.openOrCreateDatabase(file, null);
    }
}
