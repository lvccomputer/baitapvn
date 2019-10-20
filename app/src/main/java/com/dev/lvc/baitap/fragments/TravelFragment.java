package com.dev.lvc.baitap.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dev.lvc.baitap.R;
import com.dev.lvc.baitap.Utils;
import com.dev.lvc.baitap.adapters.TravelAdapter;
import com.dev.lvc.baitap.models.Food;
import com.dev.lvc.baitap.models.Travel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TravelFragment extends BaseFragment {


    private RecyclerView rcvTravel;

    private TravelAdapter adapter;

    private ArrayList<Travel> travelArrayList;
    private ImageView imgBack;
    private ProgressBar progressBar;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        new Handler().postDelayed(() -> {
            progressBar.setVisibility(View.GONE);
            getData();

        }, 1500);
    }

    private void init() {
        rcvTravel = view.findViewById(R.id.rcvDuLich);
        travelArrayList = new ArrayList<>();
        imgBack = view.findViewById(R.id.imgBack);
        progressBar = view.findViewById(R.id.progressBar);
        imgBack.setOnClickListener(v -> mainActivity.onBackPressed());

    }

    private void getData() {
        try {
            JSONObject jsonObject = new JSONObject(Utils.loadJSONFromAssets(mainActivity, "travel.json"));
            JSONArray travelArrays = jsonObject.getJSONArray("Travel");

            for (int index = 0; index < travelArrays.length(); index++) {
                Travel travel = new Travel();
                JSONObject travelObject = travelArrays.getJSONObject(index);
                travel.setTitle(travelObject.getString("name"));
                travel.setPicture(travelObject.getString("icon"));
                travel.setDescription(travelObject.getString("content"));
                travel.setContent(travelObject.getString("content2"));
                travel.setDescription1(travelObject.getString("des1"));
                travel.setDescription2(travelObject.getString("des2"));
                travel.setDescription3(travelObject.getString("des3"));
                travel.setPicture1(travelObject.getString("hinh1"));
                travel.setPicture2(travelObject.getString("hinh2"));
                travelArrayList.add(travel);
            }
        } catch (JSONException e) {
            Log.e("cuong", "getData: " + e);
        }

        adapter = new TravelAdapter(travelArrayList, mainActivity);
        rcvTravel.setLayoutManager(new LinearLayoutManager(mainActivity));
        rcvTravel.setAdapter(adapter);

        adapter.setOnClickTravelListenter((position, travel) -> {
            mainActivity.showDetailFragment(travel);
        });
    }


    @Override
    protected int getLayoutID() {
        return R.layout.fragment_travel;
    }
}
