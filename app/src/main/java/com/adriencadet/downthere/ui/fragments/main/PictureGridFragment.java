package com.adriencadet.downthere.ui.fragments.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.adriencadet.downthere.R;
import com.adriencadet.downthere.models.bll.BLLErrors;
import com.adriencadet.downthere.models.bll.dto.PictureBLLDTO;
import com.adriencadet.downthere.ui.UIMediator;
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
    View noMessageWrapper;

    @Bind(R.id.picture_grid_fragment_grid_wrapper)
    SwipeRefreshLayout gridViewWrapper;

    @Bind(R.id.picture_grid_fragment_grid)
    GridView gridView;

    private void refresh(boolean hasCurrentlyNoContent) {
        if (listPicturesByDateDescSubscription != null) {
            listPicturesByDateDescSubscription.unsubscribe();
        }

        showSpinner();

        listPicturesByDateDescSubscription = UIMediator
            .getDataReadingBLL()
            .refreshPicturesByDateDesc()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Subscriber<List<PictureBLLDTO>>() {
                @Override
                public void onNext(List<PictureBLLDTO> pictures) {
                    if (pictures.isEmpty()) {
                        gridViewWrapper.setVisibility(View.GONE);
                        noMessageWrapper.setVisibility(View.VISIBLE);
                    } else {
                        gridAdapter.setItems(pictures);
                        if (hasCurrentlyNoContent) {
                            gridViewWrapper.setVisibility(View.VISIBLE);
                            noMessageWrapper.setVisibility(View.GONE);
                        }
                    }
                }

                @Override
                public void onCompleted() {
                    super.onCompleted();
                    if (!hasCurrentlyNoContent) {
                        gridViewWrapper.setRefreshing(false);
                    }
                    hideSpinner();
                }

                @Override
                public void onError(Throwable e) {
                    super.onError(e);
                    if (e instanceof BLLErrors.NoConnection) {
                        inform(getString(R.string.no_connection_error));
                    } else {
                        alert(e.getMessage());
                    }
                    if (!hasCurrentlyNoContent) {
                        gridViewWrapper.setRefreshing(false);
                    }
                    hideSpinner();
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

        if (listPicturesByDateDescSubscription != null) {
            listPicturesByDateDescSubscription.unsubscribe();
        }

        showSpinner();
        listPicturesByDateDescSubscription =
            UIMediator
                .getDataReadingBLL()
                .listPicturesByDateDesc()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<PictureBLLDTO>>() {
                    @Override
                    public void onNext(List<PictureBLLDTO> pictures) {
                        if (pictures.isEmpty()) {
                            gridViewWrapper.setVisibility(View.GONE);
                            noMessageWrapper.setVisibility(View.VISIBLE);
                        } else {
                            gridAdapter.setItems(pictures);
                            gridViewWrapper.setVisibility(View.VISIBLE);
                            noMessageWrapper.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onCompleted() {
                        super.onCompleted();
                        hideSpinner();
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        if (e instanceof BLLErrors.NoConnection) {
                            inform(getString(R.string.no_connection_error));
                        } else {
                            alert(e.getMessage());
                        }
                        hideSpinner();
                    }
                });

        gridViewWrapper.setOnRefreshListener(() -> refresh(false));

        return view;
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
        refresh(true);
    }
}
