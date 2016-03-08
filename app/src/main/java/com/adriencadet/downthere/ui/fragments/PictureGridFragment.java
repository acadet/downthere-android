package com.adriencadet.downthere.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.adriencadet.downthere.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * PictureGridFragment
 * <p>
 */
public class PictureGridFragment extends BaseFragment {

    @Bind(R.id.picture_grid_fragment_grid)
    GridView gridView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view;

        view = inflater.inflate(R.layout.fragment_picture_grid, container, false);
        ButterKnife.bind(this, view);

        return view;
    }
}
