package com.example.assignment.nytimesapp.ui.main.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.assignment.nytimesapp.R;
import com.example.assignment.nytimesapp.ui.main.ArticleRowView;
import com.example.assignment.nytimesapp.ui.main.MainMvpPresenter;
import com.example.assignment.nytimesapp.ui.main.MainMvpView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyArticleAdapter extends RecyclerView.Adapter<MyArticleAdapter.ArticleHolder> {

    private final MainMvpPresenter<MainMvpView> mPresenter;

    public MyArticleAdapter(MainMvpPresenter<MainMvpView> presenter){
        this.mPresenter = presenter;
    }
    @NonNull
    @Override
    public ArticleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ArticleHolder(LayoutInflater.from(parent.getContext())
        .inflate(R.layout.article_row_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleHolder holder, int position) {
        mPresenter.onBindRoomRowViewAtPosition(position,holder);
    }

    @Override
    public int getItemCount() {
        return mPresenter.getArticleRowCount();
    }

    public class ArticleHolder  extends RecyclerView.ViewHolder implements ArticleRowView, View.OnClickListener{
        @BindView(R.id.tv_article_title)
        TextView mTitle;
        @BindView(R.id.tv_article_byline)
        TextView mByline;
        @BindView(R.id.tv_article_date)
        TextView mDate;
        @BindView(R.id.article_image)
        ImageView articleImage;

        public ArticleHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void setTitle(String title) {
            mTitle.setText(title);
        }

        @Override
        public void setByline(String byline) {
            mByline.setText(byline);
        }

        @Override
        public void setDate(String date) {
            mDate.setText(date);
        }

        @Override
        public void setImage() {
            mPresenter.setImageUsingUrl(articleImage);
        }

        @Override
        public void onClick(View v) {
            if (mPresenter != null){
                mPresenter.onItemInteraction(getAdapterPosition());
            }
        }
    }
}
