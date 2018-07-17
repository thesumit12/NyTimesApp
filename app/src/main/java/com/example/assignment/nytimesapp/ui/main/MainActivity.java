package com.example.assignment.nytimesapp.ui.main;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.example.assignment.nytimesapp.R;
import com.example.assignment.nytimesapp.ui.base.BaseActivity;
import com.example.assignment.nytimesapp.ui.detail.ArticleDetailActivity;
import com.example.assignment.nytimesapp.ui.main.adapter.MyArticleAdapter;
import com.example.assignment.nytimesapp.utils.CommonUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements MainMvpView {

    private MyArticleAdapter mAdapter;

    @BindView(R.id.rv_article)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        MainMvpPresenter<MainMvpView> mPresenter = new MainPresenter<>(getAppDataManager());
        mPresenter.onAttach(this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new MyDividerItemDecoration(this,
                LinearLayoutManager.VERTICAL, 4));
        mAdapter = new MyArticleAdapter(mPresenter);
        mRecyclerView.setAdapter(mAdapter);

        mPresenter.loadArticles();
    }

    @Override
    public void refreshView() {
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void navigateToArticleDetail(String title, String detail) {
        Intent intent = new Intent(MainActivity.this, ArticleDetailActivity.class);
        intent.putExtra("HEADING",title);
        intent.putExtra("DETAIL",detail);
        startActivity(intent);
    }

    @Override
    public ProgressBar createProgressBar() {
        return CommonUtils.createProgressBar(this,
                (RelativeLayout) findViewById(R.id.progress_root_layout));
    }
}
