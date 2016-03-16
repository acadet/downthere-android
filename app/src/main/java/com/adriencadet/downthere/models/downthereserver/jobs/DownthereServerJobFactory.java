package com.adriencadet.downthere.models.downthereserver.jobs;

import com.adriencadet.downthere.models.downthereserver.IDownthereServerAPI;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * DownthereServerJobFactory
 * <p>
 */
@Module
public class DownthereServerJobFactory {
    @Provides
    @Singleton
    public ListPicturesByDateDescJob provideListPicturesByDateDescJob(IDownthereServerAPI api) {
        return new ListPicturesByDateDescJob(api);
    }
}
