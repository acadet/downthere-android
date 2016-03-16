package com.adriencadet.downthere.ui.fragments.fullscreen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.adriencadet.downthere.R;
import com.adriencadet.downthere.ui.events.ShowPictureInsightSegue;
import com.adriencadet.downthere.ui.fragments.BaseFragment;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.joda.time.DateTime;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * PictureInsightFragment
 * <p>
 */
public class PictureInsightFragment extends BaseFragment {

    @Bind(R.id.picture_insight_embedded_picture)
    ImageView embeddedPictureView;

    @Bind(R.id.picture_insight_fragment_name)
    TextView nameView;

    @Bind(R.id.picture_insight_fragment_latest_update)
    TextView latestUpdateView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view;

        view = inflater.inflate(R.layout.fragment_picture_insight, container, false);
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

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onInit(ShowPictureInsightSegue e) {
        Picasso
            .with(getActivity())
            .load(e.picture.getAttachmentURL())
            .placeholder(R.drawable.picture_placeholder)
            .error(R.drawable.picture_error)
            .into(embeddedPictureView);

        nameView.setText(e.picture.getName());
        latestUpdateView.setText(
            DateUtils
                .getRelativeTimeSpanString(
                    e.picture.getUpdatedAt().toDate().getTime(),
                    DateTime.now().toDate().getTime(),
                    DateUtils.MINUTE_IN_MILLIS
                )
        );
    }
}
