package com.sss.blogapp.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.sss.blogapp.repositories.BlogRepo;
import com.sss.blogapp.responses.BlogResponse;
import com.sss.blogapp.responses.BlogResponseLocal;
import com.sss.blogapp.roomdb.Blog;

import java.util.List;

public class BlogAddViewModel extends AndroidViewModel {

    private BlogRepo repo;
    private MutableLiveData<BlogResponseLocal> responseMutableLiveData;

    public BlogAddViewModel(@NonNull Application application) {
        super(application);
        if (repo != null)
            return;
        repo = BlogRepo.getInstance(application);
        responseMutableLiveData = repo.getLocalResMutableLiveData();
    }

    public MutableLiveData<BlogResponseLocal> getLocalResLiveData() {
        return responseMutableLiveData;
    }

    public void insertToDb(Blog blog) {
        repo.insertBlog(blog);
    }

    public void updateToDb(Blog blog) {
        repo.updateBlog(blog);
    }

}
