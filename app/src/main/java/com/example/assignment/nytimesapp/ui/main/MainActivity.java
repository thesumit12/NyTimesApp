package com.example.assignment.nytimesapp.ui.main;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
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
    public void setArticleImage(String url, final ImageView articleImage) {
        Glide.with(this).asBitmap().load(url).into(new BitmapImageViewTarget(articleImage){
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularDrawable =
                        RoundedBitmapDrawableFactory.create(MainActivity.this.getResources(), resource);
                circularDrawable.setCircular(true);
                articleImage.setImageDrawable(circularDrawable);
            }
        });
    }

    @Override
    public void navigateToArticleDetail(String title, String detail, String url) {
        Intent intent = new Intent(MainActivity.this, ArticleDetailActivity.class);
        intent.putExtra("HEADING",title);
        intent.putExtra("DETAIL",detail);
        intent.putExtra("IMAGE_URL", url);
        startActivity(intent);
    }

    @Override
    public ProgressBar createProgressBar() {
        return CommonUtils.createProgressBar(this,
                (RelativeLayout) findViewById(R.id.progress_root_layout));
    }
}
