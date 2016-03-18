package com.adriencadet.downthere.models.bll.serializers;

import com.adriencadet.downthere.models.bll.dto.TextFileBLLDTO;
import com.adriencadet.downthere.models.dao.dto.TextFileDAODTO;
import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;

import org.joda.time.DateTime;

import java.util.List;

/**
 * TextFileBLLDTOSerializer
 * <p>
 */
class TextFileBLLDTOSerializer implements ITextFileBLLDTOSerializer {
    @Override
    public TextFileBLLDTO fromDAO(TextFileDAODTO dto) {
        return new TextFileBLLDTO()
            .setId(dto.getId())
            .setName(dto.getName())
            .setCreatedAt(new DateTime(dto.getCreatedAt()))
            .setUpdatedAt(new DateTime(dto.getUpdatedAt()))
            .setAttachmentURL(dto.getAttachmentURL());
    }

    @Override
    public TextFileDAODTO toDAO(TextFileBLLDTO dto) {
        TextFileDAODTO outcome = new TextFileDAODTO();

        outcome.setId(dto.getId());
        outcome.setName(dto.getName());
        outcome.setCreatedAt(dto.getCreatedAt().toDate());
        outcome.setUpdatedAt(dto.getUpdatedAt().toDate());
        outcome.setAttachmentURL(dto.getAttachmentURL());

        return outcome;
    }

    @Override
    public List<TextFileBLLDTO> fromDAO(List<TextFileDAODTO> dtos) {
        return Stream
            .of(dtos)
            .map(this::fromDAO)
            .collect(Collectors.toList());
    }

    @Override
    public List<TextFileDAODTO> toDAO(List<TextFileBLLDTO> dtos) {
        return Stream
            .of(dtos)
            .map(this::toDAO)
            .collect(Collectors.toList());
    }
}
