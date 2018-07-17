package com.example.assignment.nytimesapp.data.network;

import com.example.assignment.nytimesapp.data.network.model.ArticleResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("7.json")
    Call<ArticleResponse> getArticles(@Query("api_key") String apiKey);
}
