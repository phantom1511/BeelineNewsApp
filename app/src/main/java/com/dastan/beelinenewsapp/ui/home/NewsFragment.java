package com.dastan.beelinenewsapp.ui.home;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.dastan.beelinenewsapp.MainActivity;
import com.dastan.beelinenewsapp.R;

public class NewsFragment extends Fragment {

    private String url;
    private WebView webView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        Bundle bundle = this.getArguments();
        url = bundle.getString("key");
        webView = view.findViewById(R.id.web_view);
        setContent();
        return view;
    }

    public void setContent(){
        String url = this.getArguments().getString("key");
        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient(){
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(request.getUrl().toString());
                return super.shouldOverrideUrlLoading(view, request);
            }
        });

    }

}