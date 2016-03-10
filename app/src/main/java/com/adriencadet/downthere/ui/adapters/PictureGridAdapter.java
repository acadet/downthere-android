package com.adriencadet.downthere.ui.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.adriencadet.downthere.R;
import com.adriencadet.downthere.models.bll.dto.PictureBLLDTO;
import com.adriencadet.downthere.ui.UIMediator;
import com.adriencadet.downthere.ui.events.ShowPictureInsightSegue;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * PictureGridAdapter
 * <p>
 */
public class PictureGridAdapter extends BaseAdapter<PictureBLLDTO> {

    public PictureGridAdapter(Context context) {
        this(context, new ArrayList<>());
    }

    public PictureGridAdapter(Context context, List<PictureBLLDTO> items) {
        super(context, items);
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
            UIMediator.getFragmentActivityBus().postSticky(new ShowPictureInsightSegue(itemAt(position)));
        });

        return view;
    }
}
