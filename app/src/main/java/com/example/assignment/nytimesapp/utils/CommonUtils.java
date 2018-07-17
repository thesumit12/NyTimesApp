package com.example.assignment.nytimesapp.utils;

import android.content.Context;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;



public class CommonUtils {

    private CommonUtils() {
        // This utility class is not publicly instantiable
    }

    public static ProgressBar createProgressBar(Context context, RelativeLayout layout){

        ProgressBar progressBar = new ProgressBar(context, null, android.R.attr.progressBarStyleLarge);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(300, 300);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        layout.addView(progressBar, params);
        progressBar.setIndeterminate(true);
        return progressBar;
    }
}
