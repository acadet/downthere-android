package com.adriencadet.downthere.ui.fragments.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.adriencadet.downthere.R;
import com.adriencadet.downthere.models.bll.dto.TextFileBLLDTO;
import com.adriencadet.downthere.ui.adapters.TextFileListAdapter;
import com.adriencadet.downthere.ui.fragments.BaseFragment;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

/**
 * TextFileListFragment
 * <p>
 */
public class TextFileListFragment extends BaseFragment {
    private Subscription listTextFilesByDateDescSubscription;

    private TextFileListAdapter listAdapter;

    @Bind(R.id.text_file_list_fragment_no_content_wrapper)
    View noContentWrapper;

    @Bind(R.id.text_file_list_fragment_list_wrapper)
    SwipeRefreshLayout listViewWrapper;

    @Bind(R.id.text_file_list_fragment_list)
    ListView listView;

    private void refresh(boolean hasCurrentlyNoContent) {
        if (listTextFilesByDateDescSubscription != null) {
            listTextFilesByDateDescSubscription.unsubscribe();
        }

        dataReadingBLL
            .refreshTextFilesByDateDesc()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new BaseSubscriber<List<TextFileBLLDTO>>() {
                @Override
                public void onCompleted() {
                    listViewWrapper.setRefreshing(false);
                }

                @Override
                public void onError(Throwable e) {
                    super.onError(e);
                    listViewWrapper.setRefreshing(false);
                }

                @Override
                public void onNext(List<TextFileBLLDTO> files) {
                    if (files.isEmpty()) {
                        listViewWrapper.setVisibility(View.GONE);
                        noContentWrapper.setVisibility(View.VISIBLE);
                    } else {
                        listAdapter.setItems(files);
                        if (hasCurrentlyNoContent) {
                            listViewWrapper.setVisibility(View.VISIBLE);
                            noContentWrapper.setVisibility(View.GONE);
                        }
                    }
                }
            });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view;

        view = inflater.inflate(R.layout.fragment_text_file_list, container, false);
        ButterKnife.bind(this, view);

        listAdapter = new TextFileListAdapter(getActivity());
        listView.setAdapter(listAdapter);

        listViewWrapper.setOnRefreshListener(() -> refresh(false));

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        showSpinner();
        listTextFilesByDateDescSubscription =
            dataReadingBLL
                .listTextFilesByDateDesc()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<List<TextFileBLLDTO>>() {
                    @Override
                    public void onCompleted() {
                        hideSpinner();
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        hideSpinner();
                    }

                    @Override
                    public void onNext(List<TextFileBLLDTO> files) {
                        if (files.isEmpty()) {
                            listViewWrapper.setVisibility(View.GONE);
                            noContentWrapper.setVisibility(View.VISIBLE);
                        } else {
                            listAdapter.setItems(files);
                            listViewWrapper.setVisibility(View.VISIBLE);
                            noContentWrapper.setVisibility(View.GONE);
                        }
                    }
                });
    }

    @Override
    public void onPause() {
        super.onPause();
        if (listTextFilesByDateDescSubscription != null) {
            listTextFilesByDateDescSubscription.unsubscribe();
        }
    }

    @OnClick(R.id.text_file_fragment_no_content_refresher)
    public void onManualRefreshWhenNoContent() {
        listViewWrapper.setRefreshing(true);
        refresh(true);
    }
}
