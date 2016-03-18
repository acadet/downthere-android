package com.adriencadet.downthere.models.dao.realm;

import com.adriencadet.downthere.models.dao.realm.dto.TextFileDAODTO;

import java.util.List;

/**
 * ITextFileDAO
 * <p>
 */
public interface ITextFileDAO {
    List<TextFileDAODTO> listByDateDesc();

    void saveList(List<TextFileDAODTO> list);
}
