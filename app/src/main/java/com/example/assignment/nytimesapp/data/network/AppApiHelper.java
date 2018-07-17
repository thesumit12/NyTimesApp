package com.example.assignment.nytimesapp.data.network;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.assignment.nytimesapp.BuildConfig;
import com.example.assignment.nytimesapp.data.network.model.Article;
import com.example.assignment.nytimesapp.data.network.model.ArticleResponse;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AppApiHelper implements ApiHelper {
    private static final String TAG = "AppApiHelper1";

    @Override
    public void getArticleList(final OnFinishedListener onFinishedListener) {
        Log.e(TAG,"Inside method");
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<ArticleResponse> call = apiService.getArticles(BuildConfig.ApiKey);

        call.enqueue(new Callback<ArticleResponse>() {
            @Override
            public void onResponse(@NonNull Call<ArticleResponse> call, @NonNull Response<ArticleResponse> response) {
                if (response.isSuccessful()){
                    ArticleResponse articleResponse = response.body();
                    if (articleResponse != null) {
                        List<Article> mList = articleResponse.getResults();
                        Log.e(TAG,"Size : "+mList.size());
                        onFinishedListener.onFinished(mList);
                    }
                }else
                    Log.e(TAG, String.valueOf(response.errorBody()));
            }

            @Override
            public void onFailure(@NonNull Call<ArticleResponse> call, @NonNull Throwable t) {
                Log.e(TAG, "Error Occured");
                onFinishedListener.onFailure(t);
            }
        });
    }

}
