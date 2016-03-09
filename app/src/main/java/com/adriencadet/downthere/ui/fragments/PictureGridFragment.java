package com.adriencadet.downthere.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.adriencadet.downthere.R;
import com.adriencadet.downthere.models.bll.dto.PictureBLLDTO;
import com.adriencadet.downthere.ui.UIMediator;
import com.adriencadet.downthere.ui.adapters.PictureGridAdapter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

/**
 * PictureGridFragment
 * <p>
 */
public class PictureGridFragment extends BaseFragment {

    private Subscription listPicturesByDateDescSubscription;

    @Bind(R.id.picture_grid_fragment_no_content)
    TextView noMessageView;

    @Bind(R.id.picture_grid_fragment_grid_wrapper)
    SwipeRefreshLayout gridViewWrapper;

    @Bind(R.id.picture_grid_fragment_grid)
    GridView gridView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view;

        view = inflater.inflate(R.layout.fragment_picture_grid, container, false);
        ButterKnife.bind(this, view);

        if (listPicturesByDateDescSubscription != null) {
            listPicturesByDateDescSubscription.unsubscribe();
        }
        listPicturesByDateDescSubscription =
            UIMediator
                .getDataReadingBLL()
                .listPicturesByDateDesc()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<PictureBLLDTO>>() {
                    @Override
                    public void onNext(List<PictureBLLDTO> pictureDAODTOs) {
                        if (pictureDAODTOs.isEmpty()) {
                            gridViewWrapper.setVisibility(View.GONE);
                            noMessageView.setVisibility(View.VISIBLE);
                        } else {
                            gridView.setAdapter(new PictureGridAdapter(getActivity(), pictureDAODTOs));
                            gridViewWrapper.setVisibility(View.VISIBLE);
                            noMessageView.setVisibility(View.GONE);
                        }
                    }
                });

        gridViewWrapper.setOnRefreshListener(() -> {
            if (listPicturesByDateDescSubscription != null) {
                listPicturesByDateDescSubscription.unsubscribe();
            }

            listPicturesByDateDescSubscription = UIMediator
                .getDataReadingBLL()
                .listPicturesByDateDesc()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<PictureBLLDTO>>() {
                    @Override
                    public void onNext(List<PictureBLLDTO> pictureDAODTOs) {
                        if (pictureDAODTOs.isEmpty()) {
                            gridViewWrapper.setVisibility(View.GONE);
                            noMessageView.setVisibility(View.VISIBLE);
                        } else {
                            gridView.setAdapter(new PictureGridAdapter(getActivity(), pictureDAODTOs));
                        }
                        gridViewWrapper.setRefreshing(false);
                    }
                });
        });

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        if (listPicturesByDateDescSubscription != null) {
            listPicturesByDateDescSubscription.unsubscribe();
        }
    }
}
