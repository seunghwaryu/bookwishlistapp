package com.dryice.bookwishlist;

public class GroupItem {
    String name;
    String cnt;


    public GroupItem(String name, String cnt) {
        this.name = name;
        this.cnt= cnt;
    }

    public String getCnt() {
        return cnt;
    }

    public String getName() {
        return name;
    }

    public void setMessage(String cnt) {
        this.cnt = cnt;
    }

    public void setName(String name) {
        this.name = name;
    }

}
