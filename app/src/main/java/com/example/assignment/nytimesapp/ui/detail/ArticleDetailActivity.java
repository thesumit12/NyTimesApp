package com.example.assignment.nytimesapp.ui.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.assignment.nytimesapp.R;
import com.example.assignment.nytimesapp.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArticleDetailActivity extends BaseActivity {

    @BindView(R.id.article_heading)
    TextView articleHeading;

    @BindView(R.id.article_detail)
    TextView articleDetail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.article_detail);
        ButterKnife.bind(this);

        if (getIntent().getExtras() != null){
            articleHeading.setText(getIntent().getExtras().getString("HEADING"));
            articleDetail.setText(getIntent().getExtras().getString("DETAIL"));
        }

    }

    @Override
    public ProgressBar createProgressBar() {
        return null;
    }
}
