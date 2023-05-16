package com.dryice.bookwishlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class AddGroupActivity extends AppCompatActivity {

    private BookDB bookDB = null;
    private Context mContext;
    private EditText mEditTextGroupName;
    private Button mAddButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_add_group);

        mAddButton = (Button) findViewById(R.id.add_group_button);
        mEditTextGroupName = (EditText) findViewById(R.id.input_gruop_name);

        bookDB = BookDB.getInstance(this);
        mContext = getApplicationContext();

        class InsertRunnable implements Runnable {

            @Override
            public void run() {
                Book book = new Book();
                book.groupName = mEditTextGroupName.getText().toString();
                book.bookCnt = 0;
                BookDB.getInstance(mContext).bookDao().insertAll(book);
            }
        }

        mAddButton.setOnClickListener(v -> {

            InsertRunnable insertRunnable = new InsertRunnable();
            Thread addThread = new Thread(insertRunnable);
            addThread.start();

            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//            intent.putExtra("그룹이름",mEditTextGroupName.getText().toString());
//            intent.putExtra("북카운트",0);
            startActivity(intent);
            finish();

        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BookDB.destroyInstance();
    }

    //팝업 밖 터치시 팝업 종료 방지
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_OUTSIDE) {
            return false;
        }
        return true;
    }
}