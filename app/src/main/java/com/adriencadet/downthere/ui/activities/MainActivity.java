package com.adriencadet.downthere.ui.activities;

import android.app.Fragment;
import android.os.Bundle;
import android.view.View;

import com.adriencadet.downthere.R;
import com.adriencadet.downthere.ui.fragments.FooterFragment;
import com.adriencadet.downthere.ui.fragments.PictureGridFragment;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;

public class MainActivity extends BaseActivity {

    @Bind(R.id.main_activity_body)
    View body;

    @Bind(R.id.main_activity_footer)
    View footer;

    private void showPictureGrid(boolean forTheFirstTime) {
        Map<Integer, Fragment> m = new HashMap<>();

        m.put(R.id.main_activity_body, new PictureGridFragment());
        if (forTheFirstTime) {
            m.put(R.id.main_activity_footer, new FooterFragment());
        }

        setFragments(m);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showPictureGrid(true);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void show(Segues.Show.PictureGrid e) {
        showPictureGrid(false);
    }
}
