package com.example.assignment.nytimesapp.ui.base;

import com.example.assignment.nytimesapp.data.DataManager;

public class BasePresenter<V extends MvpView> implements MvpPresenter<V> {

    private final DataManager mDataManager;

    private V mMvpView;

    public BasePresenter(DataManager dataManager){
        this.mDataManager = dataManager;
    }

    @Override
    public void onAttach(V mvpView) {
        mMvpView = mvpView;
    }

    @Override
    public void onDetach() {
        mMvpView = null;
    }

    protected boolean isViewAttached() {
        return mMvpView != null;
    }

    protected V getMvpView() {
        return mMvpView;
    }

    protected DataManager getDataManager() {
        return mDataManager;
    }
}
