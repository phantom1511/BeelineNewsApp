package com.dastan.beelinenewsapp.ui.home.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dastan.beelinenewsapp.data.entities.Article;
import com.dastan.beelinenewsapp.data.entities.News;
import com.dastan.beelinenewsapp.R;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private List<Article> newsList;
    private OnItemClick onItemClick;

    public NewsAdapter(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    public void setNewsList(List<Article> newsList){
        this.newsList = newsList;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.new_list, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        holder.bind(newsList.get(position));
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder{

        private TextView titleText, descText;
        private ImageView newsImg;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            initViews(itemView);
        }

        private void initViews(View view){
            titleText = view.findViewById(R.id.txtTitle);
            descText = view.findViewById(R.id.txtDescription);
            newsImg = view.findViewById(R.id.imgNews);
        }

        private void bind(final Article article){
            titleText.setText(article.getTitle());
            descText.setText(article.getDescription());
            Glide.with(itemView).load(article.getUrlToImage()).into(newsImg);

            titleText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClick.onItemClick(article.getUrl());
                }
            });
        }
    }
}