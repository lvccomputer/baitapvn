package com.dev.lvc.baitap.fragments.book_manager;

import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.dev.lvc.baitap.R;
import com.dev.lvc.baitap.fragments.BaseFragment;
import com.dev.lvc.baitap.listener.UpdateBookListener;
import com.dev.lvc.baitap.models.Book;

public class EditBookFragment extends BaseFragment {
    private ImageView imgBack, imgAccept;

    private EditText edtName, edtDescription, edtPrice, edtPage;

//    private UpdateBookListener updateBookListener;

    private Book book;

    public void setBook(Book book) {
        this.book = book;
    }

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
        edtName.setText(book.getName());
        edtPage.setText(book.getPage() + "");
        edtDescription.setText(book.getDescription());
        edtPrice.setText(book.getPrice() + "");

        imgAccept.setOnClickListener(v -> {
            if (!TextUtils.isEmpty(edtName.getText().toString()) && !edtName.getText().toString().equals(book.getName())) {
                SQLiteStatement updateName = mainActivity.sqlBook.compileStatement("update Books set name = '" + edtName.getText().toString() + "'where id =='" + book.getId() + "'");
                int i = updateName.executeUpdateDelete();
                Log.e("cuong", "update Name: " + i);
            }
            if (!TextUtils.isEmpty(edtDescription.getText().toString()) && !edtDescription.getText().toString().equals(book.getDescription())) {
                SQLiteStatement updateName = mainActivity.sqlBook.compileStatement("update Books set description = '" + edtName.getText().toString() + "'where id =='" + book.getId() + "'");
                int i = updateName.executeUpdateDelete();
                Log.e("cuong", "update Description: " + i);
            }
            if (!TextUtils.isEmpty(edtPrice.getText().toString()) && !edtPrice.getText().toString().equals(String.valueOf(book.getPrice()))) {
                SQLiteStatement updateName = mainActivity.sqlBook.compileStatement("update Books set price = '" + edtName.getText().toString() + "'where id =='" + book.getId() + "'");
                int i = updateName.executeUpdateDelete();
                Log.e("cuong", "update price: " + i);
            }
            if (!TextUtils.isEmpty(edtPage.getText().toString()) && !edtPage.getText().toString().equals(String.valueOf(book.getPage()))) {
                SQLiteStatement updateName = mainActivity.sqlBook.compileStatement("update Books set page = '" + edtName.getText().toString() + "'where id =='" + book.getId() + "'");
                int i = updateName.executeUpdateDelete();
                Log.e("cuong", "update page: " + i);
            }
            Toast.makeText(mainActivity,"Updated!",Toast.LENGTH_SHORT).show();
            imgBack.performClick();

        });
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_add_book;
    }
}
