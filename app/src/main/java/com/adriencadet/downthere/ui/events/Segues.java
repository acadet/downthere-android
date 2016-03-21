package com.adriencadet.downthere.ui.events;

import com.adriencadet.downthere.models.bll.dto.PictureBLLDTO;
import com.adriencadet.downthere.models.bll.dto.TextFileBLLDTO;

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

        public static class TextFileList {
        }

        public static class PictureInsight {
            public PictureBLLDTO picture;

            public PictureInsight(PictureBLLDTO picture) {
                this.picture = picture;
            }
        }

        public static class TextFileInsight {
            public TextFileBLLDTO textFile;

            public TextFileInsight(TextFileBLLDTO textFile) {
                this.textFile = textFile;
            }
        }
    }
}
