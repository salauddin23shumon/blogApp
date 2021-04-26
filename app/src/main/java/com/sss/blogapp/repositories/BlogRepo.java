package com.sss.blogapp.repositories;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.sss.blogapp.interfaces.RetrofitInterface;
import com.sss.blogapp.network.RetrofitClient;
import com.sss.blogapp.responses.BlogResponse;
import com.sss.blogapp.responses.BlogResponseLocal;
import com.sss.blogapp.roomdb.Blog;
import com.sss.blogapp.roomdb.BlogDb;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.sss.blogapp.roomdb.BlogDb.databaseWriteExecutor;

public class BlogRepo {

    private static BlogRepo instance;
    private static List<Blog> blogList;
    private Blog model;
    private static BlogDb blogDb;

    private MutableLiveData<BlogResponse> responseMutableLiveData;
    private MutableLiveData<BlogResponseLocal> localResMutableLiveData;
    private RetrofitInterface retrofitInterface;

    private static final String TAG = "SensorRepo";

    public static BlogRepo getInstance(Application application) {
        blogList = new ArrayList<>();
        blogDb = BlogDb.getInstance(application);
        if(instance == null) {
            synchronized (BlogRepo.class) {
                if(instance == null) {
                    instance = new BlogRepo();
                }
            }
        }
        return instance;
    }

    public BlogRepo() {
        responseMutableLiveData=new MutableLiveData<>();
        localResMutableLiveData=new MutableLiveData<>();
        retrofitInterface= RetrofitClient.getInstance().getRetrofitService();
    }


    public void getBlogFromApi(){

        retrofitInterface.getAllBlogPost().enqueue(new Callback<BlogResponse>() {
            @Override
            public void onResponse(Call<BlogResponse> call, Response<BlogResponse> response) {
                if (response.isSuccessful()){
                    responseMutableLiveData.postValue(response.body());
                    for (Blog b: response.body().getBlogs()){
                        model=new Blog();
//                        model.setId(b.getId());
//                        model.setCoverPhoto(b.getCoverPhoto());
//                        model.setDescription(b.getDescription());
//                        model.setFkAid(b.getAuthor().getId());
//                        model.setCategories(b.getCategories());
                        blogDb.dao().insert(b);
                    }
                }else {
                    Log.d(TAG, "onResponse: "+response.code());
                }

            }

            @Override
            public void onFailure(Call<BlogResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: "+t.getMessage() );
                responseMutableLiveData.postValue(null);
            }
        });
    }

    public MutableLiveData<BlogResponse> getResponseMutableLiveData() {
        return responseMutableLiveData;
    }

    public MutableLiveData<BlogResponseLocal> getLocalResMutableLiveData() {
        return localResMutableLiveData;
    }

    public LiveData<List<Blog>> getAllData() {

        return blogDb.dao().getAllBlog();
    }

    public void insertBlog(Blog blog){
        databaseWriteExecutor.execute(() -> {
           if (blogDb.dao().insert(blog)>0){
               BlogResponseLocal localRes= new BlogResponseLocal(true);
               localResMutableLiveData.postValue(localRes);
               Log.e(TAG, "insertBlog: inside if" );
           }

        });
    }

    public void updateBlog(Blog blog){
        databaseWriteExecutor.execute(() -> {
            if (blogDb.dao().updateBlog(blog)>0){
                BlogResponseLocal localRes= new BlogResponseLocal(true);
                localResMutableLiveData.postValue(localRes);
                Log.e(TAG, "updateBlog: inside if" );
            }

        });
    }
}
