package com.dryice.bookwishlist;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Book {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name="group_name")
    public String groupName;

    @ColumnInfo(name="book_cnt")
    public int bookCnt;


}