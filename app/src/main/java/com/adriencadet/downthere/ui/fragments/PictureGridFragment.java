package com.adriencadet.downthere.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.adriencadet.downthere.R;
import com.adriencadet.downthere.models.dao.dto.PictureDAODTO;
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
                .subscribe(new Subscriber<List<PictureDAODTO>>() {
                    @Override
                    public void onNext(List<PictureDAODTO> pictureDAODTOs) {
                        gridView.setAdapter(new PictureGridAdapter(pictureDAODTOs, getActivity()));
                    }
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
