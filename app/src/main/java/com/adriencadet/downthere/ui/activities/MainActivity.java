package com.adriencadet.downthere.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.adriencadet.downthere.R;
import com.adriencadet.downthere.ui.events.Segues;
import com.adriencadet.downthere.ui.fragments.main.FooterFragment;
import com.adriencadet.downthere.ui.fragments.main.PictureGridFragment;
import com.adriencadet.downthere.ui.fragments.main.TextFileListFragment;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Stack;

import butterknife.Bind;

public class MainActivity extends BaseActivity {
    private enum Screen {
        PICTURES, FILES
    }

    private Screen        currentScreen;
    private Stack<Screen> history;

    @Bind(R.id.main_activity_body)
    View body;

    @Bind(R.id.main_activity_footer)
    View footer;

    private void showScreen(Screen screen, boolean addToStack) {
        if (currentScreen == screen) {
            return;
        }

        switch (screen) {
            case PICTURES:
                setFragment(R.id.main_activity_body, new PictureGridFragment());
                break;
            case FILES:
                setFragment(R.id.main_activity_body, new TextFileListFragment());
                break;
        }

        if (currentScreen != null && addToStack) {
            history.add(currentScreen);
        }
        currentScreen = screen;
    }

    private void showScreen(Screen screen) {
        showScreen(screen, true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        history = new Stack<>();

        setContentView(R.layout.activity_main);
        showScreen(Screen.PICTURES);
        setFragment(R.id.main_activity_footer, new FooterFragment());
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Clear buses of any stick event to prevent any caching
        fragmentActivityBus.removeAllStickyEvents();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void showPictureGrid(Segues.Show.PictureGrid e) {
        showScreen(Screen.PICTURES);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void showTextFileList(Segues.Show.TextFileList e) {
        showScreen(Screen.FILES);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void showPictureInsight(Segues.Show.PictureInsight e) {
        Intent intent = new Intent(this, FullScreenActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        if (history.isEmpty()) {
            finish();
        } else {
            showScreen(history.pop(), false);
        }
    }
}
