package com.dev.lvc.baitap.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.dev.lvc.baitap.activities.MainActivity;
import com.dev.lvc.baitap.R;
import com.dev.lvc.baitap.Utils;

public class ConvertFragment extends BaseFragment {

    private LinearLayout btnConvert;
    private EditText editText;
    private TextView tvResult;
    private ImageView imgBack;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        setAction();
    }

    private void init(){
        btnConvert = view.findViewById(R.id.btnConvert);
        editText = view.findViewById(R.id.edtC);
        tvResult =view.findViewById(R.id.tvResult);
        imgBack = view.findViewById(R.id.imgBack);

    }
    private void setAction(){
        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.hideKeyBoard(mainActivity);
                if (!TextUtils.isEmpty(editText.getText().toString())){
                    String result = Utils.convertTempF(Integer.valueOf(editText.getText().toString()));
                    tvResult.setText(result);
                }
            }
        });
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.onBackPressed();
            }
        });
    }
    @Override
    protected int getLayoutID() {
        return R.layout.fragment_convert;
    }

    @Override
    public void onResume() {
        super.onResume();
        mainActivity.setUserInterface(view.findViewById(R.id.fragmentconvert));
    }
}
