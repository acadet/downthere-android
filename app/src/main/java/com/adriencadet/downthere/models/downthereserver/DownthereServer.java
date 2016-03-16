package com.adriencadet.downthere.models.downthereserver;

import com.adriencadet.downthere.models.bll.dto.PictureBLLDTO;
import com.adriencadet.downthere.models.downthereserver.jobs.ListPicturesByDateDescJob;

import java.util.List;

import rx.Observable;

/**
 * DownthereServer
 * <p>
 */
class DownthereServer implements IDownthereServer {
    ListPicturesByDateDescJob listPicturesByDateDescJob;

    DownthereServer(ListPicturesByDateDescJob listPicturesByDateDescJob) {
        this.listPicturesByDateDescJob = listPicturesByDateDescJob;
    }

    @Override
    public Observable<List<PictureBLLDTO>> listPicturesByDateDesc() {
        return listPicturesByDateDescJob.get();
    }
}
