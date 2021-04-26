package com.sss.blogapp.responses;

public class BlogResponseLocal {
    private boolean isInsert;

    public BlogResponseLocal(boolean isInsert) {
        this.isInsert = isInsert;
    }

    public boolean isInsert() {
        return isInsert;
    }

    public void setInsert(boolean insert) {
        isInsert = insert;
    }
}
