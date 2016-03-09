package com.adriencadet.downthere.models.dao;

import android.content.Context;

/**
 * DAOFactory
 * <p>
 */
public class DAOFactory {
    private static IPictureDAO pictureDAO;
    private static final Object pictureDAOLock = new Object();

    public static IPictureDAO buildPicture(Context context) {
        if (pictureDAO == null) {
            synchronized (pictureDAOLock) {
                if (pictureDAO == null) {
                    pictureDAO = new PictureDAO(context);
                }
            }
        }

        return pictureDAO;
    }
}
