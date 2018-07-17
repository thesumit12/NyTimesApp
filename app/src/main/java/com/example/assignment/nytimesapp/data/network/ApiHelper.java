package com.example.assignment.nytimesapp.data.network;

import com.example.assignment.nytimesapp.data.network.model.Article;

import java.util.List;

public interface ApiHelper {

    interface OnFinishedListener{
        void onFinished(List<Article> articleList);
        void onFailure(Throwable throwable);
    }

    void getArticleList(OnFinishedListener onFinishedListener);

}
