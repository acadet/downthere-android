package com.adriencadet.downthere.models.bll;

import com.adriencadet.downthere.models.bll.dto.PictureBLLDTO;
import com.adriencadet.downthere.models.dao.dto.PictureDAODTO;
import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;

import org.joda.time.DateTime;

import java.util.List;

/**
 * PictureBLLDTOSerializer
 * <p>
 */
class PictureBLLDTOSerializer {
    static PictureBLLDTO fromDAO(PictureDAODTO dto) {
        return new PictureBLLDTO()
            .setId(dto.getId())
            .setName(dto.getName())
            .setCreatedAt(new DateTime(dto.getCreatedAt()))
            .setUpdatedAt(new DateTime(dto.getUpdatedAt()))
            .setAttachmentURL(dto.getAttachmentURL());
    }

    static PictureDAODTO toDAO(PictureBLLDTO dto) {
        PictureDAODTO outcome = new PictureDAODTO();

        outcome.setId(dto.getId());
        outcome.setName(dto.getName());
        outcome.setCreatedAt(dto.getCreatedAt().toDate());
        outcome.setUpdatedAt(dto.getUpdatedAt().toDate());
        outcome.setAttachmentURL(dto.getAttachmentURL());

        return outcome;
    }

    static List<PictureBLLDTO> fromDAO(List<PictureDAODTO> dtos) {
        return Stream
            .of(dtos)
            .map(PictureBLLDTOSerializer::fromDAO)
            .collect(Collectors.toList());
    }

    static List<PictureDAODTO> toDAO(List<PictureBLLDTO> dtos) {
        return Stream
            .of(dtos)
            .map(PictureBLLDTOSerializer::toDAO)
            .collect(Collectors.toList());
    }
}
