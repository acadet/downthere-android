package com.adriencadet.downthere.models.bll;

import com.adriencadet.downthere.models.bll.dto.PictureBLLDTO;
import com.adriencadet.downthere.models.bll.jobs.ListPicturesByDateDescJob;

import java.util.List;

import javax.inject.Inject;

import dagger.Lazy;
import rx.Observable;

/**
 * DataReadingBLL
 * <p>
 */
class DataReadingBLL implements IDataReadingBLL {
    @Inject
    Lazy<ListPicturesByDateDescJob> listPicturesByDateDesc;

    @Override
    public Observable<List<PictureBLLDTO>> listPicturesByDateDesc() {
        return listPicturesByDateDesc.get().get(false);
    }

    @Override
    public Observable<List<PictureBLLDTO>> refreshPicturesByDateDesc() {
        return listPicturesByDateDesc.get().get(true);
    }
}
