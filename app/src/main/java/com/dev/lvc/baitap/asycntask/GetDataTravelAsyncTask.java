package com.dev.lvc.baitap.asycntask;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.dev.lvc.baitap.Utils;
import com.dev.lvc.baitap.adapters.TravelAdapter;
import com.dev.lvc.baitap.models.Travel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class GetDataTravelAsyncTask extends AsyncTask<Void, Void, Void> {

    private ArrayList<Travel> travelArrayList = new ArrayList<>();

    private Context context;

    private OnLoadFinish onLoadFinish;

    public GetDataTravelAsyncTask(Context context, OnLoadFinish onLoadFinish) {
        this.context = context;
        this.onLoadFinish = onLoadFinish;
    }


    @Override
    protected Void doInBackground(Void... voids) {
        getData();
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        if (onLoadFinish != null) onLoadFinish.onLoadFinish(travelArrayList);
    }

    public interface OnLoadFinish {
        void onLoadFinish(ArrayList<Travel> travelArrayList);
    }

    private void getData() {
        try {
            JSONObject jsonObject = new JSONObject(Utils.loadJSONFromAssets(context, "travel.json"));
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
//
//        adapter = new TravelAdapter(travelArrayList, mainActivity);
//        rcvTravel.setLayoutManager(new LinearLayoutManager(mainActivity));
//        rcvTravel.setAdapter(adapter);
//
//        adapter.setOnClickTravelListenter((position, travel) -> {
//            mainActivity.showDetailFragment(travel);
//        });
    }
}
