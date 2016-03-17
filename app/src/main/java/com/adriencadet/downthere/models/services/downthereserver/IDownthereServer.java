package com.adriencadet.downthere.models.services.downthereserver;

import com.adriencadet.downthere.models.bll.dto.PictureBLLDTO;

import java.util.List;

import rx.Observable;

/**
 * IDownthereServer
 * <p>
 */
public interface IDownthereServer {
    Observable<List<PictureBLLDTO>> listPicturesByDateDesc();
}
