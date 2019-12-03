package com.dev.lvc.baitap.fragments.book_manager;

import android.app.Dialog;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dev.lvc.baitap.R;
import com.dev.lvc.baitap.adapters.BookAdapter;
import com.dev.lvc.baitap.fragments.BaseFragment;
import com.dev.lvc.baitap.listener.UpdateBookListener;
import com.dev.lvc.baitap.models.Book;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;

public class BookFragment extends BaseFragment {

    private RecyclerView rcvBook;

    private BookAdapter bookAdapter;

    private ImageView imgBack;

    private RelativeLayout layoutAddBook, layoutSearchBook;

    private ArrayList<Book> bookArrayList;

    private LinearLayout name, price;
    private TextView titleDialog;
    private EditText edtSearchName;
    private TextView tvOK, tvCancel;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initID();
        initView();
    }

    private void initID() {
        rcvBook = view.findViewById(R.id.rcvBook);
        imgBack = view.findViewById(R.id.imgBack);
        layoutAddBook = view.findViewById(R.id.layoutAddBook);
        layoutSearchBook = view.findViewById(R.id.layoutSearchBook);
        bookArrayList = new ArrayList<>();
        tvCancel = view.findViewById(R.id.tvCancel);
        tvCancel.setVisibility(View.GONE);

    }

    private void initView() {
        imgBack.setOnClickListener(v -> mainActivity.onBackPressed());
        bookAdapter = new BookAdapter(mainActivity, bookArrayList);
        rcvBook.setLayoutManager(new LinearLayoutManager(mainActivity));
        rcvBook.setAdapter(bookAdapter);
        layoutAddBook.setOnClickListener(v -> mainActivity.showAddBookFragment(() -> getDataFromSQL()));
        layoutSearchBook.setOnClickListener(v -> {
            BottomSheetDialog dialog = new BottomSheetDialog(mainActivity);
            dialog.setContentView(R.layout.dialog_search_book);
            name = dialog.findViewById(R.id.searchName);
            price = dialog.findViewById(R.id.searchPrice);
            name.setOnClickListener(v1 -> {
                dialog.dismiss();
                Dialog dialog1 = new Dialog(mainActivity);
                if (dialog1.getWindow() != null)
                    dialog.getWindow().setBackgroundDrawableResource( android.R.color.transparent );
                dialog1.setContentView(R.layout.dialog_search_name);
                edtSearchName = dialog1.findViewById(R.id.edtSearchName);
                tvOK = dialog1.findViewById(R.id.tvOK);
                tvOK.setOnClickListener(v2 -> {
                    ArrayList<Book> booksName = new ArrayList<>();
                    for (int index = 0; index < bookArrayList.size(); index++) {
                        Log.e("cuong", "initView: "+bookArrayList.get(index).getName() );
                        if (bookArrayList.get(index).getName().toLowerCase().contains(edtSearchName.getText().toString())) {
                            Log.e("cuong", "initView: "+bookArrayList.get(index).getName() );
                            booksName.add(bookArrayList.get(index));
                        }
                    }
                    bookAdapter.updateList(booksName);
                    tvCancel.setVisibility(View.VISIBLE);
                    dialog1.dismiss();
                });
                dialog1.show();
            });
            price.setOnClickListener(v1 -> {
                dialog.dismiss();
                Dialog dialog1 = new Dialog(mainActivity);
                dialog1.setContentView(R.layout.dialog_search_name);
                if (dialog1.getWindow() != null)
                    dialog.getWindow().setBackgroundDrawableResource( android.R.color.transparent );
                edtSearchName = dialog1.findViewById(R.id.edtSearchName);
                titleDialog = dialog1.findViewById(R.id.tvTitleDialog);
                tvOK = dialog1.findViewById(R.id.tvOK);
                titleDialog.setText("Điền giá bán để tìm kiếm");
                tvOK.setOnClickListener(v2 -> {
                    ArrayList<Book> booksPrice = new ArrayList<>();
                    for (int index = 0; index < bookArrayList.size(); index++) {
                        if (edtSearchName.getText().toString().equals(String.valueOf(bookArrayList.get(index).getPrice()))) {
                            booksPrice.add(bookArrayList.get(index));
                        }
                    }
                    bookAdapter.updateList(booksPrice);
                    tvCancel.setVisibility(View.VISIBLE);
                    dialog1.dismiss();
                });
                dialog1.show();
            });
            dialog.show();
        });

        tvCancel.setOnClickListener(v -> {
            bookAdapter.updateList(bookArrayList);
            tvCancel.setVisibility(View.GONE);
        });

    }

    private void getDataFromSQL() {
        bookArrayList.clear();
        Cursor cursor = mainActivity.sqlBook.rawQuery("select * from Books", null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                Book book = new Book();
                book.setId(cursor.getInt(0));
                book.setName(cursor.getString(1));
                book.setPage(cursor.getString(2));
                book.setPrice(cursor.getInt(3));
                book.setDescription(cursor.getString(4));
                bookArrayList.add(book);

            } while (cursor.moveToNext());
            cursor.close();
        }
        bookAdapter.notifyDataSetChanged();
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_book;
    }

    @Override
    public void onResume() {
        super.onResume();
        getDataFromSQL();
    }
}
