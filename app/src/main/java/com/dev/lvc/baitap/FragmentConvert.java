package com.dev.lvc.baitap;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class FragmentConvert extends BaseFragment {

    private LinearLayout btnConvert;
    private EditText editText;
    private TextView tvResult;

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

    }
    private void setAction(){
        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.hideKeyBoard(mainActivity);
                if (!TextUtils.isEmpty(editText.getText().toString())){
                    String result = Util.convertTempF(Integer.valueOf(editText.getText().toString()));
                    tvResult.setText(result);
                }
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
