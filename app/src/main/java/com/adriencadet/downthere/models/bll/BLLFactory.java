package com.adriencadet.downthere.models.bll;

import android.content.Context;

import com.adriencadet.downthere.models.dao.DAOFactory;
import com.adriencadet.downthere.models.downthereserver.DownthereServerFactory;

/**
 * BLLFactory
 * <p>
 */
public class BLLFactory {
    private static IDataReadingBLL dataReadingBLL;
    private static final Object dataReadingBLLLock = new Object();

    public static IDataReadingBLL buildDataReadingBLL(Context context) {
        if (dataReadingBLL == null) {
            synchronized (dataReadingBLLLock) {
                if (dataReadingBLL == null) {
                    dataReadingBLL = new DataReadingBLL(DownthereServerFactory.build(), DAOFactory.buildPicture(context));
                }
            }
        }

        return dataReadingBLL;
    }
}
