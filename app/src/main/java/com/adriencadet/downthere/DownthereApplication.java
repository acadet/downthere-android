package com.adriencadet.downthere;

import android.app.Application;

import com.adriencadet.downthere.ui.UIMediator;

/**
 * DownthereApplication
 * <p>
 */
public class DownthereApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        UIMediator.setContext(this);
    }
}
