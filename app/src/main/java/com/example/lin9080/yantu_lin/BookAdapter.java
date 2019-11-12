package com.example.lin9080.yantu_lin;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {
    private List<Book> mList;
    static class ViewHolder extends RecyclerView.ViewHolder{
        View BookView;
        TextView bookModeText;
        TextView bookNameText;

        public ViewHolder(View view){
            super(view);
            BookView = view;
            bookModeText = (TextView) view.findViewById(R.id.bookMode);
            bookNameText = (TextView) view.findViewById(R.id.bookName);
        }
    }

    public BookAdapter(List<Book> list){
        mList = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_show_item,parent,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.BookView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = holder.getAdapterPosition();
                Book book = mList.get(pos);
                Intent intent = new Intent(view.getContext(), WebViewActivity.class);//TODO 跳转到新页面
                intent.putExtra("url",book.getUrl());
                view.getContext().startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Book book = mList.get(i);
        viewHolder.bookNameText.setText(book.getBookname());
        viewHolder.bookModeText.setText(book.getBookMode());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}