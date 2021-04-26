package com.sss.blogapp.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.sss.blogapp.repositories.BlogRepo;
import com.sss.blogapp.responses.BlogResponse;
import com.sss.blogapp.roomdb.Blog;

import java.util.List;

public class BlogViewModel extends AndroidViewModel {

    private BlogRepo repo;
    private MutableLiveData<BlogResponse> responseMutableLiveData;

    public BlogViewModel(@NonNull Application application) {
        super(application);
        if (repo != null)
            return;
        repo = BlogRepo.getInstance(application);
        responseMutableLiveData = repo.getResponseMutableLiveData();
    }

    public MutableLiveData<BlogResponse> getResponseMutableLiveData() {
        return responseMutableLiveData;
    }

    public void getApiData() {
        repo.getBlogFromApi();
    }



    public LiveData<List<Blog>> getAllBlogData() {
        return repo.getAllData();
    }
}
