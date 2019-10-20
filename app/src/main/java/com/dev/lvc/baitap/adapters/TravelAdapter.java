package com.dev.lvc.baitap.adapters;

import android.content.Context;
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
import com.dev.lvc.baitap.models.Travel;

import java.util.ArrayList;

public class TravelAdapter extends RecyclerView.Adapter<TravelAdapter.ItemTravelViewHolder> {

    private ArrayList<Travel> travelArrayList;

    private Context context;

    private OnClickTravelListenter onClickTravelListenter;

    public TravelAdapter(ArrayList<Travel> travelArrayList, Context context) {
        this.travelArrayList = travelArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemTravelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(context).inflate(R.layout.item_list_travel,parent,false);

        return new ItemTravelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemTravelViewHolder holder, int position) {
        Travel travel = travelArrayList.get(position);
        Glide.with(context).load(Utils.uri+travel.getPicture()).into(holder.imgPicture);
        holder.tvTitle.setText(travel.getTitle());
        holder.tvDescription.setText(travel.getDescription());
    }

    @Override
    public int getItemCount() {
        return travelArrayList.size();
    }

    public class ItemTravelViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgPicture;

        private TextView tvTitle;

        private TextView tvDescription;
        public ItemTravelViewHolder(@NonNull View itemView) {
            super(itemView);

            imgPicture = itemView.findViewById(R.id.imgPicture);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            itemView.setOnClickListener(v -> {
                if (onClickTravelListenter!=null){
                    onClickTravelListenter.setOnClickTravelLisnter(getAdapterPosition(),travelArrayList.get(getAdapterPosition()));
                }
            });

        }
    }

    public void updateListTravel(ArrayList<Travel> travelArrayList){
        this.travelArrayList = travelArrayList;

    }
    public void setOnClickTravelListenter(OnClickTravelListenter onClickTravelListenter) {
        this.onClickTravelListenter = onClickTravelListenter;
    }

    public interface OnClickTravelListenter{
        void setOnClickTravelLisnter(int position, Travel travel);
    }
}
