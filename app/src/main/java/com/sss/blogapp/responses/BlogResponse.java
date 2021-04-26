
package com.sss.blogapp.responses;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sss.blogapp.roomdb.Blog;


public class BlogResponse {

    @SerializedName("blogs")
    @Expose
    private List<Blog> blogs = null;

    private boolean isInsert;

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }

    public boolean isInsert() {
        return isInsert;
    }

    public void setInsert(boolean insert) {
        isInsert = insert;
    }
}
