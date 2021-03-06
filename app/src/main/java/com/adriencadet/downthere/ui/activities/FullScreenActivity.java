package com.adriencadet.downthere.ui.activities;

import android.os.Bundle;

import com.adriencadet.downthere.R;
import com.adriencadet.downthere.ui.events.Segues;
import com.adriencadet.downthere.ui.fragments.fullscreen.PictureInsightFragment;
import com.adriencadet.downthere.ui.fragments.fullscreen.TextFileInsightFragment;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * FullScreenActivity
 * <p>
 */
public class FullScreenActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onShowPictureInsight(Segues.Show.PictureInsight e) {
        setFragment(R.id.fullscreen_activity_embedded_content, new PictureInsightFragment());
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onShowTextFileInsight(Segues.Show.TextFileInsight e) {
        setFragment(R.id.fullscreen_activity_embedded_content, new TextFileInsightFragment());
    }
}
