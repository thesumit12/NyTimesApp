package com.example.assignment.nytimesapp.ui.main;

import com.example.assignment.nytimesapp.data.DataManager;
import com.example.assignment.nytimesapp.data.network.ApiHelper;
import com.example.assignment.nytimesapp.data.network.model.Article;
import com.example.assignment.nytimesapp.ui.base.BasePresenter;

import java.util.List;

public class MainPresenter<V extends MainMvpView> extends BasePresenter<V> implements
        MainMvpPresenter<V>, ApiHelper.OnFinishedListener {
    private List<Article> mList;

    public MainPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void loadArticles() {
        getMvpView().showLoading();
        getDataManager().getArticleList(this);
    }

    @Override
    public void onBindRoomRowViewAtPosition(int position, ArticleRowView rowView) {
        Article article = mList.get(position);
        rowView.setTitle(article.getTitle());
        rowView.setByline(article.getByline());
        rowView.setDate(article.getPublishedDate());
    }

    @Override
    public int getArticleRowCount() {
        if (mList != null)
            return mList.size();
        else
            return 0;
    }

    @Override
    public void onItemInteraction(int position) {
        Article article = mList.get(position);
        getMvpView().navigateToArticleDetail(article.getTitle(),article.getAbstract());
    }

    @Override
    public void onFinished(List<Article> articleList) {
        getMvpView().hideLoading();
        mList = articleList;
        getMvpView().refreshView();
        if (mList != null && mList.isEmpty())
            getMvpView().showMessage("No data to show");
    }

    @Override
    public void onFailure(Throwable throwable) {
        getMvpView().hideLoading();
        getMvpView().showMessage("Some Error occured!!");

    }
}
