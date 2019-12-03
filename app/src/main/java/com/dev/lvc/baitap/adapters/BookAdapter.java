package com.dev.lvc.baitap.adapters;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dev.lvc.baitap.R;
import com.dev.lvc.baitap.activities.MainActivity;
import com.dev.lvc.baitap.models.Book;

import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ItemBookViewHolder> {

    private MainActivity context;
    private ArrayList<Book> bookArrayList;
    private OnClickItemBookListener onClickItemBookListener;

    public BookAdapter(MainActivity context, ArrayList<Book> bookArrayList) {
        this.context = context;
        this.bookArrayList = bookArrayList;
    }

    @NonNull
    @Override
    public ItemBookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_book, parent, false);

        return new ItemBookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemBookViewHolder holder, int position) {

        Book book = bookArrayList.get(position);
        holder.imgBook.setImageResource(R.drawable.ic_book);
        holder.tvName.setText(book.getName() + "\n(" + book.getPage() + " trang) ");
        holder.tvDescription.setText(book.getDescription());
        holder.tvPrice.setText(book.getPrice() + " vnd");
        holder.imgMore.setOnClickListener(v -> {
            PopupMenu popupMenu = new PopupMenu(context, v);
            MenuInflater menuInflater = popupMenu.getMenuInflater();
            menuInflater.inflate(R.menu.menu_more_book, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(menuItem -> {
                switch (menuItem.getItemId()) {
                    case R.id.edit:
                        context.showEditBookFragment(bookArrayList.get(position));
                        return true;

                    case R.id.delete:
                        context.sqlBook.delete("Books", "id = ?", new String[]{String.valueOf(book.getId())});
                        bookArrayList.remove(position);
                        notifyItemRemoved(position);
                        notifyDataSetChanged();
                        return true;
                    default:
                        return false;

                }
            });
            popupMenu.show();
        });
//        holder.view.setOnClickListener(v -> {
//            if (onClickItemBookListener != null)
//                onClickItemBookListener.setOnClickItemBookListener(position, bookArrayList.get(position));
//        });


    }

    public void updateList(ArrayList<Book> books){
        this.bookArrayList = books;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return bookArrayList.size();
    }

    public class ItemBookViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgBook, imgMore;
        private TextView tvName, tvDescription, tvPrice;
        private View view;

        public ItemBookViewHolder(@NonNull View itemView) {
            super(itemView);
            imgBook = itemView.findViewById(R.id.imgBook);
            imgMore = itemView.findViewById(R.id.imgMore);
            tvName = itemView.findViewById(R.id.tvNameBook);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvPrice = itemView.findViewById(R.id.tvPrice);

        }
    }

    public void setOnClickItemBookListener(OnClickItemBookListener onClickItemBookListener) {
        this.onClickItemBookListener = onClickItemBookListener;
    }

    public interface OnClickItemBookListener {
        void setOnClickItemBookListener(int position, Book book);
    }
}
