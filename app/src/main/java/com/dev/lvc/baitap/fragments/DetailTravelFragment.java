package com.dev.lvc.baitap.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.dev.lvc.baitap.R;
import com.dev.lvc.baitap.Utils;
import com.dev.lvc.baitap.models.Travel;

public class DetailTravelFragment extends BaseFragment {

    private TextView tvTitle, tvContent, tvContent1, tvDes1, tvDes2, tvDes3;

    private ImageView imgPicture, imgPicture1, imgPicture2;

    private Travel travel;

    private ImageView imgBack;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        setData();
    }

    public void setTravel(Travel travel) {
        this.travel = travel;
    }

    private void init(){
        tvTitle = view.findViewById(R.id.tvTitle);
        tvContent= view.findViewById(R.id.tvContent);
        tvContent1 = view.findViewById(R.id.tvContent1);
        tvDes1 = view.findViewById(R.id.tvDes);
        tvDes2 = view.findViewById(R.id.tvDes1);
        tvDes3 = view.findViewById(R.id.tvDes2);
        imgPicture = view.findViewById(R.id.imgPicture);
        imgPicture1 = view.findViewById(R.id.imgPicture1);
        imgPicture2 = view.findViewById(R.id.imgPicture2);
        imgBack = view.findViewById(R.id.imgBack);
        imgBack.setOnClickListener(v -> mainActivity.onBackPressed());
    }
    private void setData(){
        tvTitle.setText(travel.getTitle());
        tvContent.setText(travel.getDescription());
        tvContent1.setText(travel.getContent());
        tvDes1.setText(travel.getDescription1());
        tvDes2.setText(travel.getDescription2());
        tvDes3.setText(travel.getDescription3());

        Glide.with(mainActivity).load(Utils.uri + travel.getPicture()).into(imgPicture);
        Glide.with(mainActivity).load(Utils.uri + travel.getPicture1()).into(imgPicture1);
        Glide.with(mainActivity).load(Utils.uri + travel.getPicture2()).into(imgPicture2);

    }
    @Override
    protected int getLayoutID() {
        return R.layout.fragment_detail_travel;
    }
}
