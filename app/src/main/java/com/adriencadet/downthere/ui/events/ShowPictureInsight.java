package com.adriencadet.downthere.ui.events;

import com.adriencadet.downthere.models.bll.dto.PictureBLLDTO;

/**
 * ShowPictureInsight
 * <p>
 */
public class ShowPictureInsight {
    public PictureBLLDTO picture;

    public ShowPictureInsight(PictureBLLDTO picture) {
        this.picture = picture;
    }
}
