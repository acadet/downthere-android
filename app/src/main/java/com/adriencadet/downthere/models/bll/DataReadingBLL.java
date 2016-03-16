package com.adriencadet.downthere.models.bll;

import com.adriencadet.downthere.models.bll.dto.PictureBLLDTO;
import com.adriencadet.downthere.models.bll.jobs.ListPicturesByDateDescJob;

import java.util.List;

import rx.Observable;

/**
 * DataReadingBLL
 * <p>
 */
class DataReadingBLL implements IDataReadingBLL {
    ListPicturesByDateDescJob listPicturesByDateDesc;

    DataReadingBLL(ListPicturesByDateDescJob listPicturesByDateDesc) {
        this.listPicturesByDateDesc = listPicturesByDateDesc;
    }

    @Override
    public Observable<List<PictureBLLDTO>> listPicturesByDateDesc() {
        return listPicturesByDateDesc.get(false);
    }

    @Override
    public Observable<List<PictureBLLDTO>> refreshPicturesByDateDesc() {
        return listPicturesByDateDesc.get(false);
    }
}
