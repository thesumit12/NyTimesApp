package com.example.assignment.nytimesapp.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Article {

    @SerializedName("url")
    @Expose
    private String url;

    @SerializedName("byline")
    @Expose
    private String byline;

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("abstract")
    @Expose
    private String articleAbstract;
    @SerializedName("published_date")
    @Expose
    private String publishedDate;

    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("asset_id")
    @Expose
    private Long assetId;
    @SerializedName("views")
    @Expose
    private Integer views;

    @SerializedName("media")
    @Expose
    private List<Media> media = null;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getByline() {
        return byline;
    }

    public void setByline(String byline) {
        this.byline = byline;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAbstract() {
        return articleAbstract;
    }

    public void setAbstract(String articleAbstract) {
        this.articleAbstract = articleAbstract;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAssetId() {
        return assetId;
    }

    public void setAssetId(Long assetId) {
        this.assetId = assetId;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public List<Media> getMedia() {
        return media;
    }

    public void setMedia(List<Media> media) {
        this.media = media;
    }

}
