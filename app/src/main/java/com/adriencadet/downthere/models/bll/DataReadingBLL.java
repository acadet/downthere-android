package com.adriencadet.downthere.models.bll;

import com.adriencadet.downthere.models.bll.dto.PictureBLLDTO;
import com.adriencadet.downthere.models.bll.dto.TextFileBLLDTO;
import com.adriencadet.downthere.models.bll.jobs.ListPicturesByDateDescJob;
import com.adriencadet.downthere.models.bll.jobs.ListTextFilesByDateDescJob;

import java.util.List;

import rx.Observable;

/**
 * DataReadingBLL
 * <p>
 */
class DataReadingBLL implements IDataReadingBLL {
    private ListPicturesByDateDescJob  listPicturesByDateDesc;
    private ListTextFilesByDateDescJob listTextFilesByDateDescJob;

    DataReadingBLL(
        ListPicturesByDateDescJob listPicturesByDateDesc,
        ListTextFilesByDateDescJob listTextFilesByDateDescJob
    ) {
        this.listPicturesByDateDesc = listPicturesByDateDesc;
        this.listTextFilesByDateDescJob = listTextFilesByDateDescJob;
    }

    @Override
    public Observable<List<PictureBLLDTO>> listPicturesByDateDesc() {
        return listPicturesByDateDesc.get(false);
    }

    @Override
    public Observable<List<PictureBLLDTO>> refreshPicturesByDateDesc() {
        return listPicturesByDateDesc.get(true);
    }

    @Override
    public Observable<List<TextFileBLLDTO>> listTextFilesByDateDesc() {
        return listTextFilesByDateDescJob.get(false);
    }

    @Override
    public Observable<List<TextFileBLLDTO>> refreshTextFilesByDateDesc() {
        return listTextFilesByDateDescJob.get(true);
    }

    @Override
    public Observable<String> getTextFileContent(TextFileBLLDTO dto) {
        return null;
    }
}
