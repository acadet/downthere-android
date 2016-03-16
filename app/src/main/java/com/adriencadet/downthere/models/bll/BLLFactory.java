package com.adriencadet.downthere.models.bll;

import com.adriencadet.downthere.models.bll.jobs.ListPicturesByDateDescJob;

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
    public IDataReadingBLL provideDataReadingBLL(ListPicturesByDateDescJob listPicturesByDateDescJob) {
        return new DataReadingBLL(listPicturesByDateDescJob);
    }
}
