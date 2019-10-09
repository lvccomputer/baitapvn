package com.dev.lvc.baitap.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.dev.lvc.baitap.R;

public class FoodFragment extends BaseFragment implements View.OnClickListener {


    private LinearLayout mon1,mon2,mon3,mon4,mon5,mon6,mon7,mon8;

    private CheckBox check1,check2,check3,check4,check5,check6,check7,check8;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initID();
        mon1.setOnClickListener(this);
        mon2.setOnClickListener(this);
        mon3.setOnClickListener(this);
        mon4.setOnClickListener(this);
        mon5.setOnClickListener(this);
        mon6.setOnClickListener(this);
        mon7.setOnClickListener(this);
        mon8.setOnClickListener(this);

    }

    private void initID(){
        mon1 = view.findViewById(R.id.mon1);
        mon2 = view.findViewById(R.id.mon2);
        mon3 = view.findViewById(R.id.mon3);
        mon4 = view.findViewById(R.id.mon4);
        mon5 = view.findViewById(R.id.mon5);
        mon6 = view.findViewById(R.id.mon6);
        mon7 = view.findViewById(R.id.mon7);
        mon8 = view.findViewById(R.id.mon8);

        check1 = view.findViewById(R.id.check1);
        check2 = view.findViewById(R.id.check2);
        check3 = view.findViewById(R.id.check3);
        check4 = view.findViewById(R.id.check4);
        check5 = view.findViewById(R.id.check5);
        check6 = view.findViewById(R.id.check6);
        check7 = view.findViewById(R.id.check7);
        check8 = view.findViewById(R.id.check8);
    }
    @Override
    protected int getLayoutID() {
        return R.layout.fragment_list_food;
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();

        if (id ==R.id.mon1){
            check1.setChecked(true);
        }else if (id == R.id.mon2){
            check2.setChecked(true);
        }else if (id == R.id.mon3){
            check3.setChecked(true);
        }else if (id == R.id.mon4){
            check4.setChecked(true);
        }else if(id ==R.id.mon5){
            check5.setChecked(true);
        }else if (id == R.id.check6){
            check6.setChecked(true);
        }else if (id ==R.id.check7) {
            check7.setChecked(true);
        }else if(id ==R.id.check8){
            check8.setChecked(true);
        }
    }
}
