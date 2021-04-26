package com.sss.blogapp.interfaces;

import com.sss.blogapp.responses.BlogResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetrofitInterface {

    @GET("sohel-cse/simple-blog-api/db")
    Call<BlogResponse> getAllBlogPost();

}
