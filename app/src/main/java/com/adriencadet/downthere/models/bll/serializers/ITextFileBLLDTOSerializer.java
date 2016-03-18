package com.adriencadet.downthere.models.bll.serializers;

import com.adriencadet.downthere.models.bll.dto.TextFileBLLDTO;
import com.adriencadet.downthere.models.dao.dto.TextFileDAODTO;

import java.util.List;

/**
 * ITextFileBLLDTOSerializer
 * <p>
 */
public interface ITextFileBLLDTOSerializer {
    TextFileBLLDTO fromDAO(TextFileDAODTO dto);

    TextFileDAODTO toDAO(TextFileBLLDTO dto);

    List<TextFileBLLDTO> fromDAO(List<TextFileDAODTO> dtos);

    List<TextFileDAODTO> toDAO(List<TextFileBLLDTO> dtos);
}
