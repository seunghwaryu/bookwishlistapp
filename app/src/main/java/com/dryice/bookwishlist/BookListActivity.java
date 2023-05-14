package com.dryice.bookwishlist;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BookListActivity extends AppCompatActivity {

    private RecyclerView bRecyclerView;
    private BookRecyclerAdapter bRecyclerAdapter;
    private ArrayList<BookItem> bookItems;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.booklist);

        bRecyclerView = (RecyclerView) findViewById(R.id.book_recycler_view);

        /* initiate adapter */
        bRecyclerAdapter = new BookRecyclerAdapter();

        /* initiate recyclerview */
        bRecyclerView.setAdapter(bRecyclerAdapter);
        bRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        bRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL,false));

        /* adapt data */
        bookItems = new ArrayList<>();
        bookItems.add(new BookItem("아몬드"));
        bookItems.add(new BookItem("해리포터"));

        bRecyclerAdapter.setFriendList(bookItems);
    }
}
