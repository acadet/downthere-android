package com.adriencadet.downthere.ui.fragments.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.adriencadet.downthere.R;
import com.adriencadet.downthere.ui.events.Segues;
import com.adriencadet.downthere.ui.fragments.BaseFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * FooterFragment
 * <p>
 */
public class FooterFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view;

        view = inflater.inflate(R.layout.fragment_footer, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @OnClick(R.id.footer_fragment_picture_icon)
    public void onPictureIconClick() {
        fragmentActivityBus.post(new Segues.Show.PictureGrid());
    }

    @OnClick(R.id.footer_fragment_text_file_icon)
    public void onTextFileIconClick() {

    }
}
