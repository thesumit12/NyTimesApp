package com.example.assignment.nytimesapp.data;

import com.example.assignment.nytimesapp.data.network.ApiHelper;

public class AppDataManager implements DataManager {

    private final ApiHelper mApiHelper;

    public AppDataManager(ApiHelper apiHelper){
        this.mApiHelper = apiHelper;
    }


    @Override
    public void getArticleList(OnFinishedListener onFinishedListener) {
        mApiHelper.getArticleList(onFinishedListener);
    }
}
