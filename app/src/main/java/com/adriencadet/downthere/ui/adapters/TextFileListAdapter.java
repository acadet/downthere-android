package com.adriencadet.downthere.ui.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.adriencadet.downthere.DownthereApplication;
import com.adriencadet.downthere.R;
import com.adriencadet.downthere.models.bll.dto.TextFileBLLDTO;
import com.adriencadet.downthere.ui.events.Segues;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * TextFileListAdapter
 * <p>
 */
public class TextFileListAdapter extends BaseAdapter<TextFileBLLDTO> {
    @Inject
    @Named("fragmentActivity")
    EventBus fragmentActivityBus;

    public TextFileListAdapter(Context context) {
        super(context, new ArrayList<>());
        DownthereApplication.getApplicationComponent().inject(this);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        TextView label;
        TextFileBLLDTO file = itemAt(position);

        view = recycle(R.layout.adapter_text_file_list, convertView, parent);

        label = (TextView) view.findViewById(R.id.adapter_text_file_list_label);

        label.setText(file.getName());

        view.setOnClickListener((v) -> fragmentActivityBus.postSticky(new Segues.Show.TextFileInsight(file)));

        return view;
    }
}
