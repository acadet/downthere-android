package com.adriencadet.downthere.ui.fragments.fullscreen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.adriencadet.downthere.R;
import com.adriencadet.downthere.ui.events.Segues;
import com.adriencadet.downthere.ui.fragments.BaseFragment;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.joda.time.DateTime;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

/**
 * TextFileInsightFragment
 * <p>
 */
public class TextFileInsightFragment extends BaseFragment {

    private Subscription fetchContentSubscription;

    @Bind(R.id.text_file_insight_filename)
    TextView filenameView;

    @Bind(R.id.text_file_insight_date)
    TextView dateView;

    @Bind(R.id.text_file_insight_textarea)
    TextView textareaView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view;

        view = inflater.inflate(R.layout.fragment_text_file_insight, container, false);
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

        if (fetchContentSubscription != null) {
            fetchContentSubscription.unsubscribe();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onInit(Segues.Show.TextFileInsight e) {
        filenameView.setText(e.textFile.getName());

        dateView.setText(
            getResources()
                .getString(
                    R.string.latest_update_prefix,
                    DateUtils
                        .getRelativeTimeSpanString(
                            e.textFile.getUpdatedAt().toDate().getTime(),
                            DateTime.now().toDate().getTime(),
                            DateUtils.MINUTE_IN_MILLIS
                        )
                )
        );

        inform(getString(R.string.info_loading_content));

        fetchContentSubscription =
            dataReadingBLL
                .getTextFileContent(e.textFile)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<String>() {
                    @Override
                    public void onCompleted() {
                        YoYo
                            .with(Techniques.FadeIn)
                            .duration(500)
                            .playOn(textareaView);

                    }

                    @Override
                    public void onNext(String s) {
                        textareaView.setText(s);
                    }
                });
    }
}
