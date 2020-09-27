package com.dastan.beelinenewsapp.data;

import com.dastan.beelinenewsapp.data.entities.News;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsService {

    @GET("top-headlines")
    Call<News> getNews(@Query("country") String country, @Query("apiKey") String apiKey);
}
