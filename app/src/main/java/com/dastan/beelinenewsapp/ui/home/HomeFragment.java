package com.dastan.beelinenewsapp.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dastan.beelinenewsapp.R;
import com.dastan.beelinenewsapp.data.entities.Article;
import com.dastan.beelinenewsapp.data.entities.News;
import com.dastan.beelinenewsapp.ui.home.adapter.NewsAdapter;
import com.dastan.beelinenewsapp.ui.home.adapter.OnItemClick;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements OnItemClick {

    private TextView titleText, descText;
    private ImageView newsImg;
    private RecyclerView newsRecyclerView;
    private static List<News> newsList;
    private static NewsAdapter newsAdapter;
    private LinearLayout container;
    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        initViews(root);
        initListeners();
        homeViewModel.getNews();
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        homeViewModel.mText.observe(getViewLifecycleOwner(), new Observer<List<Article>>() {
            @Override
            public void onChanged(List<Article> articles) {
                newsAdapter.setNewsList(articles);
            }
        });
    }

    private void initViews(View view) {
        titleText = view.findViewById(R.id.txtTitle);
        descText = view.findViewById(R.id.txtDescription);
        newsRecyclerView = view.findViewById(R.id.rvNews);
        container = view.findViewById(R.id.textContainer);
    }

    private void initListeners() {
        newsList = new ArrayList<>();
        newsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        newsAdapter = new NewsAdapter(this);
        newsRecyclerView.setAdapter(newsAdapter);


    }

    @Override
    public void onItemClick(String url) {
        Bundle bundle = new Bundle();
        bundle.putString("key", url);
        Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
                .navigate(R.id.action_nav_home_to_newsFragment, bundle);

    }
}