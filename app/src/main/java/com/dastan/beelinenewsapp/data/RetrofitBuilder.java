package com.dastan.beelinenewsapp.data;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {
    private static NewsService newsService;

    public static NewsService getNewsService(){
        if (newsService == null){
            newsService = builderRetrofit();
        }

        return newsService;
    }

    private static NewsService builderRetrofit(){
        return new Retrofit.Builder()
                .baseUrl("http://newsapi.org/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(NewsService.class);

    }
}
