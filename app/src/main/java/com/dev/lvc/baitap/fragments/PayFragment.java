package com.dev.lvc.baitap.fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dev.lvc.baitap.R;
import com.dev.lvc.baitap.Utils;
import com.dev.lvc.baitap.adapters.FoodAdapter;
import com.dev.lvc.baitap.models.Food;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PayFragment extends BaseFragment {
    private static final String TAG = "cuong";

    private FoodAdapter drinkAdapter, foodAdapter;

    private ArrayList<Food> foodArrayList, drinkArrayList;

    private RecyclerView rcvFood, rcvDrinks;


    private RelativeLayout payMoney;

    private int money, count;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initID();
        getData();
    }


    private void initID() {
        foodArrayList = new ArrayList<>();
        drinkArrayList = new ArrayList<>();

        rcvDrinks = view.findViewById(R.id.rcvDrinks);
        rcvFood = view.findViewById(R.id.rcvFood);
        payMoney = view.findViewById(R.id.payMoney);
    }

    private void getData() {
        try {
            JSONObject jsonObject = new JSONObject(Utils.loadJSONFromAssets(mainActivity));
            JSONArray foodArrays = jsonObject.getJSONArray("Foods");
            JSONArray drinkArrays = jsonObject.getJSONArray("Drinks");

            for (int index = 0; index < foodArrays.length(); index++) {
                Food food = new Food();
                JSONObject foodObject = foodArrays.getJSONObject(index);
                food.setName(foodObject.getString("name"));
                food.setIcon(foodObject.getString("icon"));
                Log.d(TAG, "getData: " + foodObject.getString("icon"));
                food.setMoney(foodObject.getInt("price"));
                food.setCount(0);
                foodArrayList.add(food);

            }
            for (int index = 0; index < drinkArrays.length(); index++) {
                Food drink = new Food();
                JSONObject drinkObject = drinkArrays.getJSONObject(index);
                drink.setName(drinkObject.getString("name"));
                drink.setIcon(drinkObject.getString("icon"));
                drink.setMoney(drinkObject.getInt("price"));
                drink.setCount(0);
                drinkArrayList.add(drink);
            }


        } catch (JSONException e) {
            Log.e(TAG, "getData: " + e);
        }

        rcvFood.setLayoutManager(new LinearLayoutManager(mainActivity));
        rcvDrinks.setLayoutManager(new LinearLayoutManager(mainActivity));
        drinkAdapter = new FoodAdapter(mainActivity, drinkArrayList);
        foodAdapter = new FoodAdapter(mainActivity, foodArrayList);
        rcvFood.setAdapter(foodAdapter);
        rcvDrinks.setAdapter(drinkAdapter);

        payMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int moneyFood = 0;
                int moneyDrink = 0;
                for (int index = 0; index < foodArrayList.size(); index++) {
                    moneyFood += foodArrayList.get(index).getCount() * foodArrayList.get(index).getMoney();
                }
                for (int index = 0; index < drinkArrayList.size(); index++) {
                    moneyDrink += drinkArrayList.get(index).getCount() * foodArrayList.get(index).getMoney();
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity);

                builder.setTitle("Tổng tiền cần thanh toán");
                builder.setMessage(moneyDrink+moneyFood +" VND");
                builder.setCancelable(false);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
//                Log.e(TAG, "onClick: "+(sum+sum2) );
            }
        });
    }


    @Override
    protected int getLayoutID() {
        return R.layout.fragment_pay;
    }
}
