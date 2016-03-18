package com.adriencadet.downthere.models.bll;

import com.adriencadet.downthere.models.bll.dto.PictureBLLDTO;
import com.adriencadet.downthere.models.bll.dto.TextFileBLLDTO;

import java.util.List;

import rx.Observable;

/**
 * IDataReadingBLL
 * <p>
 */
public interface IDataReadingBLL {
    Observable<List<PictureBLLDTO>> listPicturesByDateDesc();

    Observable<List<PictureBLLDTO>> refreshPicturesByDateDesc();

    Observable<List<TextFileBLLDTO>> listTextFilesByDateDesc();

    Observable<List<TextFileBLLDTO>> refreshTextFilesByDateDesc();

    Observable<String> getTextFileContent(TextFileBLLDTO dto);
}
