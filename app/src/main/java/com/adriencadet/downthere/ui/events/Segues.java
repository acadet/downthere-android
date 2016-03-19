package com.adriencadet.downthere.ui.events;

import com.adriencadet.downthere.models.bll.dto.PictureBLLDTO;

/**
 * Segues
 * <p>
 */
public class Segues {
    private Segues() {
    }

    public static class Show {
        private Show() {
        }

        public static class PictureGrid {
        }

        public static class PictureInsight {
            public PictureBLLDTO picture;

            public PictureInsight(PictureBLLDTO picture) {
                this.picture = picture;
            }
        }
    }
}
