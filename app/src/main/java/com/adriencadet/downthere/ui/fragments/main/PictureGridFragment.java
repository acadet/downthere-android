package com.adriencadet.downthere.ui.fragments.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.adriencadet.downthere.R;
import com.adriencadet.downthere.models.bll.dto.PictureBLLDTO;
import com.adriencadet.downthere.ui.adapters.PictureGridAdapter;
import com.adriencadet.downthere.ui.fragments.BaseFragment;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

/**
 * PictureGridFragment
 * <p>
 */
public class PictureGridFragment extends BaseFragment {

    private Subscription listPicturesByDateDescSubscription;

    private PictureGridAdapter gridAdapter;

    @Bind(R.id.picture_grid_fragment_no_content_wrapper)
    View noContentWrapper;

    @Bind(R.id.picture_grid_fragment_grid_wrapper)
    SwipeRefreshLayout gridViewWrapper;

    @Bind(R.id.picture_grid_fragment_grid)
    GridView gridView;

    private void refresh(boolean hasCurrentlyNoContent) {
        if (listPicturesByDateDescSubscription != null) {
            listPicturesByDateDescSubscription.unsubscribe();
        }

        listPicturesByDateDescSubscription =
            dataReadingBLL
                .refreshPicturesByDateDesc()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<List<PictureBLLDTO>>() {
                    @Override
                    public void onNext(List<PictureBLLDTO> pictures) {
                        if (pictures.isEmpty()) {
                            gridViewWrapper.setVisibility(View.GONE);
                            noContentWrapper.setVisibility(View.VISIBLE);
                        } else {
                            gridAdapter.setItems(pictures);
                            if (hasCurrentlyNoContent) {
                                gridViewWrapper.setVisibility(View.VISIBLE);
                                noContentWrapper.setVisibility(View.GONE);
                            }
                        }
                    }

                    @Override
                    public void onCompleted() {
                        gridViewWrapper.setRefreshing(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        gridViewWrapper.setRefreshing(false);
                    }
                });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view;

        view = inflater.inflate(R.layout.fragment_picture_grid, container, false);
        ButterKnife.bind(this, view);

        gridAdapter = new PictureGridAdapter(getActivity());
        gridView.setAdapter(gridAdapter);

        gridViewWrapper.setOnRefreshListener(() -> refresh(false));

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        showSpinner();
        listPicturesByDateDescSubscription =
            dataReadingBLL
                .listPicturesByDateDesc()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<List<PictureBLLDTO>>() {
                    @Override
                    public void onNext(List<PictureBLLDTO> pictures) {
                        if (pictures.isEmpty()) {
                            gridViewWrapper.setVisibility(View.GONE);
                            noContentWrapper.setVisibility(View.VISIBLE);
                        } else {
                            gridAdapter.setItems(pictures);
                            gridViewWrapper.setVisibility(View.VISIBLE);
                            noContentWrapper.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onCompleted() {
                        hideSpinner();
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        hideSpinner();
                    }
                });
    }

    @Override
    public void onPause() {
        super.onPause();
        if (listPicturesByDateDescSubscription != null) {
            listPicturesByDateDescSubscription.unsubscribe();
        }
    }

    @OnClick(R.id.picture_grid_fragment_no_content_refresher)
    public void onManualRefreshWhenNoContent() {
        gridViewWrapper.setRefreshing(true);
        refresh(true);
    }
}
