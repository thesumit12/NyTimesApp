package com.example.assignment.nytimesapp.ui.main;

import android.app.ProgressDialog;
import android.widget.ProgressBar;

import com.example.assignment.nytimesapp.data.AppDataManager;
import com.example.assignment.nytimesapp.data.DataManager;
import com.example.assignment.nytimesapp.data.network.ApiHelper;
import com.example.assignment.nytimesapp.data.network.model.Article;
import com.example.assignment.nytimesapp.ui.base.MvpView;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class MainPresenterTest {

    private MainPresenter<MainMvpView> mPresenter;

    @Mock
    private MainMvpView mainMvpView;
    @Mock
    private AppDataManager dataManager;

    private List<Article> articleList;

    @Before
    public void setUp() throws Exception {

        mPresenter = new MainPresenter<>(dataManager);
        mPresenter.onAttach(mainMvpView);

        articleList = Arrays.asList(new Article(), new Article());

    }

    @Test
    public void testShowLoading() {
        mPresenter.loadArticles();
        Mockito.verify(mainMvpView).showLoading();
    }

    @Test
    public void testGetArticles() {
        mPresenter.loadArticles();

        Mockito.verify(dataManager, Mockito.times(1)).getArticleList(
                Mockito.any(ApiHelper.OnFinishedListener.class)
        );
    }

    @Test
    public void testGetArticleListCount() {
        assertEquals(articleList.size(), 2);
    }
}