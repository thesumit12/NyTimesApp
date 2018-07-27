package com.example.assignment.nytimesapp.ui.main;

import android.widget.ImageView;

import com.example.assignment.nytimesapp.data.DataManager;
import com.example.assignment.nytimesapp.data.network.ApiHelper;
import com.example.assignment.nytimesapp.data.network.model.Article;
import com.example.assignment.nytimesapp.data.network.model.Media;
import com.example.assignment.nytimesapp.ui.base.BasePresenter;

import java.util.List;

public class MainPresenter<V extends MainMvpView> extends BasePresenter<V> implements
        MainMvpPresenter<V>, ApiHelper.OnFinishedListener {
    private List<Article> mList;
    private String url;

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
        List<Media> mediaList = article.getMedia();
        List<Media.MediaMetadatum> mediaMetadata = mediaList.get(0).getMediaMetadata();
        url = mediaMetadata.get(0).getUrl();
        rowView.setTitle(article.getTitle());
        rowView.setByline(article.getByline());
        rowView.setDate(article.getPublishedDate());
        rowView.setImage();
    }

    @Override
    public int getArticleRowCount() {
        if (mList != null)
            return mList.size();
        else
            return 0;
    }

    @Override
    public void setImageUsingUrl(ImageView articleImage) {
        getMvpView().setArticleImage(url, articleImage);
    }

    @Override
    public void onItemInteraction(int position) {
        Article article = mList.get(position);
        List<Media> mediaList = article.getMedia();
        List<Media.MediaMetadatum> mediaMetadata = mediaList.get(0).getMediaMetadata();
        url = mediaMetadata.get(0).getUrl();
        getMvpView().navigateToArticleDetail(article.getTitle(),article.getAbstract(), url);
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
