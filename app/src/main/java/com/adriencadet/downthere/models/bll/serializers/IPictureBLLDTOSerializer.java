package com.adriencadet.downthere.models.bll.serializers;

import com.adriencadet.downthere.models.bll.dto.PictureBLLDTO;
import com.adriencadet.downthere.models.dao.realm.dto.PictureDAODTO;

import java.util.List;

/**
 * IPictureBLLDTOSerializer
 * <p>
 */
public interface IPictureBLLDTOSerializer {
    PictureBLLDTO fromDAO(PictureDAODTO dto);

    PictureDAODTO toDAO(PictureBLLDTO dto);

    List<PictureBLLDTO> fromDAO(List<PictureDAODTO> dtos);

    List<PictureDAODTO> toDAO(List<PictureBLLDTO> dtos);
}
