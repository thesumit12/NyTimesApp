package com.example.assignment.nytimesapp.ui.main;

import android.widget.ImageView;

import com.example.assignment.nytimesapp.ui.base.MvpView;

public interface MainMvpView extends MvpView {

    void refreshView();

    void navigateToArticleDetail(String title, String detail, String url);

    void setArticleImage(String url, ImageView articleImage);
}
