package com.adriencadet.downthere.ui.activities;

import android.app.Activity;
import android.os.Bundle;

import com.adriencadet.downthere.ui.UIMediator;

/**
 * BaseActivity
 * <p>
 */
public abstract class BaseActivity extends Activity {
    public SegueManager getSegueManager() {
        return UIMediator.getSegueManager();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UIMediator.setCurrentActivity(this);
    }
}
