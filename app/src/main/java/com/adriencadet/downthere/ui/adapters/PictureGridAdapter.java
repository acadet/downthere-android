package com.adriencadet.downthere.ui.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.adriencadet.downthere.R;
import com.adriencadet.downthere.models.dao.dto.PictureDAODTO;
import com.coshx.chocolatine.widgets.SmartAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * PictureGridAdapter
 * <p>
 */
public class PictureGridAdapter extends SmartAdapter<PictureDAODTO> {
    public PictureGridAdapter(List<PictureDAODTO> items, Context context) {
        super(items, context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        ImageView embeddedPicture;

        view = recycle(convertView, R.layout.adapter_picture_grid, parent);
        embeddedPicture = (ImageView) view.findViewById(R.id.adapter_picture_grid_picture);

        Picasso
            .with(getContext())
            .load(itemAt(position).getAttachmentURL())
            .resize(80, 80)
            .centerCrop()
            .into(embeddedPicture);

        return view;
    }
}
