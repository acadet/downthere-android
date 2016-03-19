package com.adriencadet.downthere.ui.fragments.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.adriencadet.downthere.R;
import com.adriencadet.downthere.ui.events.Segues;
import com.adriencadet.downthere.ui.events.ShowingMainScreen;
import com.adriencadet.downthere.ui.fragments.BaseFragment;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.Bind;
import butterknife.BindColor;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * FooterFragment
 * <p>
 */
public class FooterFragment extends BaseFragment {

    @Bind(R.id.footer_fragment_picture_trigger)
    View pictureTrigger;

    @Bind(R.id.footer_fragment_text_file_trigger)
    View fileTrigger;

    @BindColor(R.color.blue)
    int activeTabColor;

    @BindColor(android.R.color.transparent)
    int inactiveTabColor;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view;

        view = inflater.inflate(R.layout.fragment_footer, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        fragmentActivityBus.register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        fragmentActivityBus.unregister(this);
    }

    @OnClick(R.id.footer_fragment_picture_trigger)
    public void onPictureIconClick() {
        fragmentActivityBus.post(new Segues.Show.PictureGrid());
    }

    @OnClick(R.id.footer_fragment_text_file_trigger)
    public void onTextFileIconClick() {
        fragmentActivityBus.post(new Segues.Show.TextFileList());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onShowingMainScreen(ShowingMainScreen e) {
        if (e.isPictureGrid) {
            pictureTrigger.setBackgroundColor(activeTabColor);
            fileTrigger.setBackgroundColor(inactiveTabColor);
        } else {
            fileTrigger.setBackgroundColor(activeTabColor);
            pictureTrigger.setBackgroundColor(inactiveTabColor);
        }
    }
}
