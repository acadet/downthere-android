package com.adriencadet.downthere.ui.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.adriencadet.downthere.DownthereApplication;
import com.adriencadet.downthere.models.bll.dto.TextFileBLLDTO;

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
        return null;
    }
}
