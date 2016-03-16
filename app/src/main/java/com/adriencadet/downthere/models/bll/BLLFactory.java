package com.adriencadet.downthere.models.bll;

import com.adriencadet.downthere.models.dao.IPictureDAO;
import com.adriencadet.downthere.models.downthereserver.IDownthereServer;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * BLLFactory
 * <p>
 */
@Module
public class BLLFactory {
    @Provides
    @Singleton
    public IDataReadingBLL provideDataReadingBLL(IDownthereServer server, IPictureDAO pictureDAO) {
        return new DataReadingBLL(server, pictureDAO);
    }
}
