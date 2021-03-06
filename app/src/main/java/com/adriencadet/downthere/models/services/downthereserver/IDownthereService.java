package com.adriencadet.downthere.models.services.downthereserver;

import com.adriencadet.downthere.models.bll.dto.PictureBLLDTO;
import com.adriencadet.downthere.models.bll.dto.TextFileBLLDTO;

import java.util.List;

import rx.Observable;

/**
 * IDownthereService
 * <p>
 */
public interface IDownthereService {
    Observable<List<PictureBLLDTO>> listPicturesByDateDesc();

    Observable<List<TextFileBLLDTO>> listTextFilesByDateDesc();

    Observable<String> getTextFileContent(String url);
}
