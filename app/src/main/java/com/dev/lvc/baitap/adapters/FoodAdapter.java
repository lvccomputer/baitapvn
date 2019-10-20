package com.dev.lvc.baitap.adapters;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dev.lvc.baitap.R;
import com.dev.lvc.baitap.Utils;
import com.dev.lvc.baitap.activities.MainActivity;
import com.dev.lvc.baitap.models.Food;

import java.util.ArrayList;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ItemFoodViewHolder> {

    private MainActivity activity;

    private ArrayList<Food> foodArrayList;

    private OnClickItem onClickItem;

    public FoodAdapter(MainActivity activity, ArrayList<Food> foodArrayList) {
        this.activity = activity;
        this.foodArrayList = foodArrayList;
    }

    @NonNull
    @Override
    public ItemFoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(activity).inflate(R.layout.item_list_food, parent, false);
        return new ItemFoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemFoodViewHolder holder, final int position) {
        final Food food = foodArrayList.get(position);
//
        Glide.with(activity).load(Utils.uri + food.getIcon()).into(holder.imgIcon);
        holder.tvName.setText(food.getName());
        holder.tvMoney.setText(food.getMoney() + " VND");


        holder.imgUp.setOnClickListener(v -> {
            int count = food.getCount();
            count++;
            food.setCount(count);
            holder.tvCount.setText(String.valueOf(count));
        });
        holder.imgDown.setOnClickListener(v -> {

            if (Integer.valueOf(holder.tvCount.getText().toString()) > 0) {
                int count = Integer.valueOf(holder.tvCount.getText().toString());
                count--;
                food.setCount(count);
                holder.tvCount.setText(String.valueOf(count));
            }

        });
    }

    @Override
    public int getItemCount() {
        return foodArrayList.size();
    }

    public class ItemFoodViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName, tvMoney, tvCount;

        private ImageView imgIcon, imgUp, imgDown;

        public ItemFoodViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvMoney = itemView.findViewById(R.id.tvMoney);
            tvCount = itemView.findViewById(R.id.tvCount);
            imgIcon = itemView.findViewById(R.id.imgIcon);
            imgUp = itemView.findViewById(R.id.imgUp);
            imgDown = itemView.findViewById(R.id.imgDown);
            itemView.setOnClickListener(v -> {
                if (onClickItem != null)
                    onClickItem.setOnClickFoodItem(getAdapterPosition(), foodArrayList.get(getAdapterPosition()));
            });
        }
    }

    public void setOnClickItem(OnClickItem onClickItem) {
        this.onClickItem = onClickItem;
    }

    public interface OnClickItem {
        void setOnClickFoodItem(int position, Food food);
    }

}
