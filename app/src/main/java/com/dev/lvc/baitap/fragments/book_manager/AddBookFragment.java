package com.dev.lvc.baitap.fragments.book_manager;

import android.content.ContentValues;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.dev.lvc.baitap.R;
import com.dev.lvc.baitap.activities.MainActivity;
import com.dev.lvc.baitap.fragments.BaseFragment;
import com.dev.lvc.baitap.listener.UpdateBookListener;

public class AddBookFragment extends BaseFragment {

    private ImageView imgBack, imgAccept;

    private EditText edtName, edtDescription, edtPrice, edtPage;

    private UpdateBookListener updateBookListener;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initID();
        initView();
    }

    private void initID() {
        imgBack = view.findViewById(R.id.imgBack);
        imgAccept = view.findViewById(R.id.imgAccept);
        edtName = view.findViewById(R.id.edtName);
        edtDescription = view.findViewById(R.id.edtDescription);
        edtPage = view.findViewById(R.id.edtPage);
        edtPrice = view.findViewById(R.id.edtPrice);
    }

    private void initView() {
        imgBack.setOnClickListener(v -> mainActivity.onBackPressed());
        imgAccept.setOnClickListener(v -> {
            mainActivity.onBackPressed();
            MainActivity.hideKeyBoard(mainActivity);
            if (!TextUtils.isEmpty(edtName.getText().toString()) && !TextUtils.isEmpty(edtDescription.getText().toString())
                    && !TextUtils.isEmpty(edtPage.getText().toString()) && !TextUtils.isEmpty(edtPrice.getText().toString())) {
                ContentValues values = new ContentValues();
                values.put("name", edtName.getText().toString());
                values.put("page", edtPage.getText().toString());
                values.put("price", edtPrice.getText().toString());
                values.put("description", edtDescription.getText().toString());
                mainActivity.sqlBook.insert("Books", null, values);
                Toast.makeText(mainActivity, "Thêm sách thành công!", Toast.LENGTH_SHORT).show();
                if (updateBookListener != null) updateBookListener.onUpdateListener();

            } else
                Toast.makeText(mainActivity, "Vui lòng điền đầy đủ các thông tin!", Toast.LENGTH_SHORT).show();
        });
    }

    public void setUpdateBookListener(UpdateBookListener updateBookListener) {
        this.updateBookListener = updateBookListener;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_add_book;
    }

    @Override
    public void onResume() {
        super.onResume();
        mainActivity.setUserInterface(view.findViewById(R.id.fragmentAdd));
    }
}
