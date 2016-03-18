package com.adriencadet.downthere.models.services.downthereserver;

import com.adriencadet.downthere.models.bll.dto.PictureBLLDTO;
import com.adriencadet.downthere.models.bll.dto.TextFileBLLDTO;
import com.adriencadet.downthere.models.services.downthereserver.jobs.ListPicturesByDateDescJob;
import com.adriencadet.downthere.models.services.downthereserver.jobs.ListTextFilesByDateDescJob;

import java.util.List;

import rx.Observable;

/**
 * DownthereServer
 * <p>
 */
class DownthereServer implements IDownthereServer {
    ListPicturesByDateDescJob  listPicturesByDateDescJob;
    ListTextFilesByDateDescJob listTextFilesByDateDescJob;

    DownthereServer(ListPicturesByDateDescJob listPicturesByDateDescJob, ListTextFilesByDateDescJob listTextFilesByDateDescJob) {
        this.listPicturesByDateDescJob = listPicturesByDateDescJob;
        this.listTextFilesByDateDescJob = listTextFilesByDateDescJob;
    }

    @Override
    public Observable<List<PictureBLLDTO>> listPicturesByDateDesc() {
        return listPicturesByDateDescJob.get();
    }

    @Override
    public Observable<List<TextFileBLLDTO>> listTextFilesByDateDesc() {
        return this.listTextFilesByDateDescJob.get();
    }
}
