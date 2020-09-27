package com.dastan.beelinenewsapp.ui.home;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dastan.beelinenewsapp.data.RetrofitBuilder;
import com.dastan.beelinenewsapp.data.entities.Article;
import com.dastan.beelinenewsapp.data.entities.News;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends ViewModel {

    public MutableLiveData<List<Article>> mText = new MutableLiveData<>();
    public HomeViewModel() {

    }

//    public LiveData<String> getText() {
//        return mText;
//    }

    public void getNews(){
        RetrofitBuilder.getNewsService().getNews("ru", "69801f265a0b4588be1bdb2ef7cccfa7")
                .enqueue(new Callback<News>() {
                    @Override
                    public void onResponse(Call<News> call, Response<News> response) {
                        mText.setValue(response.body().getArticles());
                    }

                    @Override
                    public void onFailure(Call<News> call, Throwable t) {
                        Log.e("ron", "error");
                    }
                });

    }
}