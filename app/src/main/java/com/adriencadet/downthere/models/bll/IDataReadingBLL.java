package com.adriencadet.downthere.models.bll;

import com.adriencadet.downthere.models.dao.dto.PictureDAODTO;

import java.util.List;

import rx.Observable;

/**
 * IDataReadingBLL
 * <p>
 */
public interface IDataReadingBLL {
    Observable<List<PictureDAODTO>> listPicturesByDateDesc();
}
