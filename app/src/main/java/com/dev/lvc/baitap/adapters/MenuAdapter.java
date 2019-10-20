package com.dev.lvc.baitap.adapters;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dev.lvc.baitap.R;
import com.dev.lvc.baitap.models.Menu;

import java.util.ArrayList;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ItemMenuAdapter> {

    private ArrayList<Menu> menuArrayList;

    private Context context;

    private OnClickItemMenuListener onClickItemMenuListener;

    public MenuAdapter(ArrayList<Menu> menuArrayList, Context context) {
        this.menuArrayList = menuArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemMenuAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list_menu, parent, false);
        return new ItemMenuAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemMenuAdapter holder, int position) {
        Menu menu = menuArrayList.get(position);
        holder.tvTitle.setText(menu.getTitle());
        holder.imgIcon.setImageResource(menu.getIcon());


    }

    @Override
    public int getItemCount() {
        return menuArrayList.size();
    }

    public class ItemMenuAdapter extends RecyclerView.ViewHolder {
        private ImageView imgIcon;
        private TextView tvTitle;

        public ItemMenuAdapter(@NonNull View itemView) {
            super(itemView);
            imgIcon = itemView.findViewById(R.id.imgIcon);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            itemView.setOnClickListener(v1 -> {
                if (onClickItemMenuListener!=null)
                    onClickItemMenuListener.setOnClickItemMenuListenter(getAdapterPosition(),menuArrayList.get(getAdapterPosition()));
            });


        }
    }

    public void setOnClickItemMenuListener(OnClickItemMenuListener onClickItemMenuListener) {
        this.onClickItemMenuListener = onClickItemMenuListener;
    }

    public interface OnClickItemMenuListener {
        void setOnClickItemMenuListenter(int position, Menu menu);
    }
}
