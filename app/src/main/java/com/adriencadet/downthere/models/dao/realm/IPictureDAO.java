package com.adriencadet.downthere.models.dao.realm;

import com.adriencadet.downthere.models.dao.realm.dto.PictureDAODTO;

import java.util.List;

/**
 * IPictureDAO
 * <p>
 */
public interface IPictureDAO {
    List<PictureDAODTO> listByDateDesc();

    void saveList(List<PictureDAODTO> list);
}
