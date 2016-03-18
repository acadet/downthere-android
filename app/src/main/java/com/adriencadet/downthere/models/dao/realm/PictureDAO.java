package com.adriencadet.downthere.models.dao.realm;

import android.content.Context;

import com.adriencadet.downthere.models.dao.realm.dto.PictureDAODTO;

import java.util.List;

import io.realm.Realm;
import io.realm.Sort;

/**
 * PictureDAO
 * <p>
 */
class PictureDAO extends BaseDAO implements IPictureDAO {

    PictureDAO(Context context) {
        super(context);
    }

    @Override
    public List<PictureDAODTO> listByDateDesc() {
        return getDAL().allObjectsSorted(PictureDAODTO.class, "updatedAt", Sort.DESCENDING);
    }

    @Override
    public void saveList(List<PictureDAODTO> list) {
        Realm dal = getDAL();

        dal.beginTransaction();
        dal.allObjects(PictureDAODTO.class).clear();
        dal.copyToRealm(list);
        dal.commitTransaction();
    }
}
