package com.example.assignment.nytimesapp.ui.main;

import com.example.assignment.nytimesapp.ui.base.MvpPresenter;
import com.example.assignment.nytimesapp.ui.base.MvpView;

public interface MainMvpPresenter<V extends MvpView> extends MvpPresenter<V> {

    void loadArticles();

    void onBindRoomRowViewAtPosition(int position, ArticleRowView rowView);

    int getArticleRowCount();

    void onItemInteraction(int position);
}
