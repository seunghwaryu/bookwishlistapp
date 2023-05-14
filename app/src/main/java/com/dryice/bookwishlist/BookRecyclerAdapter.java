package com.dryice.bookwishlist;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BookRecyclerAdapter extends RecyclerView.Adapter<BookRecyclerAdapter.ViewHolder> {
    private ArrayList<BookItem> bookItems;

    @NonNull
    @Override
    public BookRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_item, parent, false);
        BookRecyclerAdapter.ViewHolder viewHolder = new BookRecyclerAdapter.ViewHolder(view);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BookRecyclerAdapter.ViewHolder holder, int position) {
        holder.onBind(bookItems.get(position));
    }

    public void setFriendList(ArrayList<BookItem> list){
        this.bookItems = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return bookItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        CheckBox checkBox;
        Button button;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.book_name);
            checkBox = (CheckBox) itemView.findViewById(R.id.cheakBox);
            button = (Button) itemView.findViewById(R.id.buy_button);
        }

        void onBind(BookItem item){
            name.setText(item.getName());
        }
    }

}

