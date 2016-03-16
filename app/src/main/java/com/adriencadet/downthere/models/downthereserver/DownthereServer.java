package com.adriencadet.downthere.models.downthereserver;

import com.adriencadet.downthere.models.bll.dto.PictureBLLDTO;

import java.util.List;

import javax.inject.Inject;

import dagger.Lazy;
import rx.Observable;

/**
 * DownthereServer
 * <p>
 */
class DownthereServer implements IDownthereServer {
    @Inject
    Lazy<Observable<List<PictureBLLDTO>>> listPicturesByDateDescObservable;

    @Override
    public Observable<List<PictureBLLDTO>> listPicturesByDateDesc() {
        return listPicturesByDateDescObservable.get();
    }
}
