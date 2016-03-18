package com.adriencadet.downthere.models.dao.realm;

import android.content.Context;

import com.adriencadet.downthere.models.dao.realm.dto.TextFileDAODTO;

import java.util.List;

import io.realm.Realm;
import io.realm.Sort;

/**
 * TextFileDAO
 * <p>
 */
class TextFileDAO extends BaseDAO implements ITextFileDAO {
    TextFileDAO(Context context) {
        super(context);
    }

    @Override
    public List<TextFileDAODTO> listByDateDesc() {
        return getDAL().allObjectsSorted(TextFileDAODTO.class, "updatedAt", Sort.DESCENDING);
    }

    @Override
    public void saveList(List<TextFileDAODTO> list) {
        Realm dal = getDAL();

        dal.beginTransaction();
        dal.allObjects(TextFileDAODTO.class).clear();
        dal.copyToRealm(list);
        dal.commitTransaction();
    }
}
