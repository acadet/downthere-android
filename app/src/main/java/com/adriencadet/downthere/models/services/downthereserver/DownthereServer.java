package com.adriencadet.downthere.models.services.downthereserver;

import com.adriencadet.downthere.models.bll.dto.PictureBLLDTO;
import com.adriencadet.downthere.models.bll.dto.TextFileBLLDTO;
import com.adriencadet.downthere.models.services.downthereserver.jobs.GetTextFileContentJob;
import com.adriencadet.downthere.models.services.downthereserver.jobs.ListPicturesByDateDescJob;
import com.adriencadet.downthere.models.services.downthereserver.jobs.ListTextFilesByDateDescJob;

import java.util.List;

import rx.Observable;

/**
 * DownthereServer
 * <p>
 */
class DownthereServer implements IDownthereServer {
    private ListPicturesByDateDescJob  listPicturesByDateDescJob;
    private ListTextFilesByDateDescJob listTextFilesByDateDescJob;
    private GetTextFileContentJob      getTextFileContentJob;

    DownthereServer(ListPicturesByDateDescJob listPicturesByDateDescJob, ListTextFilesByDateDescJob listTextFilesByDateDescJob, GetTextFileContentJob getTextFileContentJob) {
        this.listPicturesByDateDescJob = listPicturesByDateDescJob;
        this.listTextFilesByDateDescJob = listTextFilesByDateDescJob;
        this.getTextFileContentJob = getTextFileContentJob;
    }

    @Override
    public Observable<List<PictureBLLDTO>> listPicturesByDateDesc() {
        return listPicturesByDateDescJob.get();
    }

    @Override
    public Observable<List<TextFileBLLDTO>> listTextFilesByDateDesc() {
        return this.listTextFilesByDateDescJob.get();
    }

    @Override
    public Observable<String> getTextFileContent(String url) {
        return getTextFileContentJob.get(url);
    }
}
