package com.adriencadet.downthere.models.bll;

import com.adriencadet.downthere.models.downthereserver.DownthereServerFactory;

/**
 * BLLFactory
 * <p>
 */
public class BLLFactory {
    private static IDataReadingBLL dataReadingBLL;
    private static final Object dataReadingBLLLock = new Object();

    public static IDataReadingBLL buildDataReadingBLL() {
        if (dataReadingBLL == null) {
            synchronized (dataReadingBLLLock) {
                if (dataReadingBLL == null) {
                    dataReadingBLL = new DataReadingBLL(DownthereServerFactory.build());
                }
            }
        }

        return dataReadingBLL;
    }
}
