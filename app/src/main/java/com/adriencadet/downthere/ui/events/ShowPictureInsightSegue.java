package com.adriencadet.downthere.ui.events;

import com.adriencadet.downthere.models.bll.dto.PictureBLLDTO;

/**
 * ShowPictureInsightSegue
 * <p>
 */
public class ShowPictureInsightSegue {
    public PictureBLLDTO picture;

    public ShowPictureInsightSegue(PictureBLLDTO picture) {
        this.picture = picture;
    }
}
