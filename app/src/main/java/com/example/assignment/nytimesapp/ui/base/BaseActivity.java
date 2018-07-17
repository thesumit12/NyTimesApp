package com.example.assignment.nytimesapp.ui.base;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignment.nytimesapp.R;
import com.example.assignment.nytimesapp.data.AppDataManager;
import com.example.assignment.nytimesapp.data.DataManager;
import com.example.assignment.nytimesapp.data.network.AppApiHelper;

public abstract class BaseActivity extends AppCompatActivity implements MvpView {

    private ProgressBar mProgressDialog;

    @Override
    public void showLoading() {
        hideLoading();
        mProgressDialog = createProgressBar();
        mProgressDialog.setVisibility(View.VISIBLE);
    }

    public abstract ProgressBar createProgressBar();

    @Override
    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.getVisibility() == View.VISIBLE) {
            mProgressDialog.setVisibility(View.GONE);
        }
    }

    public DataManager getAppDataManager(){
        return new AppDataManager(new AppApiHelper());
    }

    private void showSnackBar(String message){
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),
                message, Snackbar.LENGTH_SHORT);
        View sbView = snackbar.getView();
        TextView textView = sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(ContextCompat.getColor(this, R.color.error));
        snackbar.show();
    }

    @Override
    public void onError(int resId) {
        onError(getString(resId));
    }

    @Override
    public void onError(String message) {
        if (message != null){
            showSnackBar(message);
        }else{
            showSnackBar(getString(R.string.some_error));
        }
    }

    @Override
    public void showMessage(String message) {
        if (message != null) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, getString(R.string.some_error), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showMessage(int resId) {
        showMessage(getString(resId));
    }
}
