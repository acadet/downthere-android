package com.adriencadet.downthere.ui.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.adriencadet.downthere.DownthereApplication;
import com.adriencadet.downthere.R;
import com.adriencadet.downthere.models.bll.dto.PictureBLLDTO;
import com.adriencadet.downthere.ui.events.Segues;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * PictureGridAdapter
 * <p>
 */
public class PictureGridAdapter extends BaseAdapter<PictureBLLDTO> {
    @Inject
    @Named("fragmentActivity")
    EventBus fragmentActivityBus;

    public PictureGridAdapter(Context context) {
        super(context, new ArrayList<>());
        DownthereApplication.getApplicationComponent().inject(this);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        ImageView embeddedPicture;

        view = recycle(R.layout.adapter_picture_grid, convertView, parent);
        embeddedPicture = (ImageView) view.findViewById(R.id.adapter_picture_grid_picture);

        int width = getContext().getResources().getDisplayMetrics().widthPixels / 3;

        embeddedPicture.setLayoutParams(new FrameLayout.LayoutParams(width, width));

        Picasso
            .with(getContext())
            .load(itemAt(position).getAttachmentURL())
            .placeholder(R.drawable.picture_placeholder)
            .error(R.drawable.picture_error)
            .resize(width, width)
            .centerCrop()
            .into(embeddedPicture);

        embeddedPicture.setOnClickListener((v) -> {
            fragmentActivityBus.postSticky(new Segues.Show.PictureInsight(itemAt(position)));
        });

        return view;
    }
}
