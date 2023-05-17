package com.dryice.bookwishlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private MainRecyclerAdapter mainRecyclerAdapter;
    private List<Book> groupList;
    private BookDB bookDB = null;
    private Context mContext = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.main_recycler_view);

        mContext = getApplicationContext();
        /* initiate adapter */
        mainRecyclerAdapter = new MainRecyclerAdapter(groupList);
        // DB 생성
        bookDB = BookDB.getInstance(this);

        //Intent addIntent = getIntent();

//        /* initiate recyclerview */
//        mRecyclerView.setAdapter(mainRecyclerAdapter);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL,false));

        // main thread에서 DB 접근 불가 => data 읽고 쓸 때 thread 사용하기
        class InsertRunnable implements Runnable {

            @Override
            public void run() {
//                Book book = new Book();
//                book.groupName = addIntent.getStringExtra("그룹이름");
//                book.bookCnt =  addIntent.getIntExtra("북카운트",0);
//                BookDB.getInstance(mContext).bookDao().insertAll(book);

                try {
                    groupList = BookDB.getInstance(mContext).bookDao().getAll();
                    mainRecyclerAdapter = new MainRecyclerAdapter(groupList);
                    mainRecyclerAdapter.notifyDataSetChanged();

                    mRecyclerView.setAdapter(mainRecyclerAdapter);
                    LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(mContext, RecyclerView.VERTICAL, false);
                    mRecyclerView.setLayoutManager(mLinearLayoutManager);
                }
                catch (Exception e) {

                }
            }
        }
        InsertRunnable insertRunnable = new InsertRunnable();
        Thread t = new Thread(insertRunnable);
        t.start();

        // 플로팅 버튼 이벤트처리
        FloatingActionButton fab = findViewById(R.id.fab_btn);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddGroupActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BookDB.destroyInstance();
        bookDB = null;
    }
}