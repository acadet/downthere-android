package com.adriencadet.downthere.models.dao;

import android.content.Context;

import io.realm.Realm;

/**
 * BaseDAO
 * <p>
 */
abstract class BaseDAO {
    private Context context;

    BaseDAO(Context context) {
        this.context = context;
    }

    Realm getDAL() {
        Realm dal = Realm.getInstance(context);

        dal.refresh();

        return dal;
    }
}
