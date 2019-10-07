package com.dev.lvc.baitap;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class FragmentBMI extends BaseFragment {

    private EditText edtWeight, edtHeight;

    private TextView tvResult, tvReview;

    private LinearLayout btnCount;
    private ImageView imgBack;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initID();
        actionView();
    }
    private void initID() {
        edtWeight = view.findViewById(R.id.edtWeight);
        edtHeight =view.findViewById(R.id.edtHeight);
        tvResult = view.findViewById(R.id.tvResult);
        tvReview = view.findViewById(R.id.tvReview);
        btnCount = view.findViewById(R.id.btnCount);
        imgBack = view.findViewById(R.id.imgBack);
    }

    private void actionView() {
        btnCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.hideKeyBoard(mainActivity);
                if (!TextUtils.isEmpty(edtWeight.getText().toString()) || !TextUtils.isEmpty(edtHeight.getText().toString())) {
                    float result = Util.BMI(Float.valueOf(edtWeight.getText().toString()), Integer.valueOf(edtHeight.getText().toString()));
                    tvResult.setText(result+"");
                    if (result<18){
                        tvReview.setText(R.string.bmi18);
                    }else if (result>=18 &&result<24.9){
                        tvReview.setText(R.string.bmi24);
                    }else if (result>=25 && result<29.9){
                        tvReview.setText(R.string.bmi29);
                    }else if (result>=30 && result <34.9){
                        tvReview.setText(R.string.bmi34);
                    }else if (result>35){
                        tvReview.setText(R.string.bmi35);
                    }


                }else {
                    Toast.makeText(mainActivity,"Chieu cao hoac Can nang khong hop le!",Toast.LENGTH_SHORT).show();
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
        return R.layout.fragment_bmi;
    }

    @Override
    public void onResume() {
        super.onResume();
        mainActivity.setUserInterface(view.findViewById(R.id.fragmentbmi));
    }
}
