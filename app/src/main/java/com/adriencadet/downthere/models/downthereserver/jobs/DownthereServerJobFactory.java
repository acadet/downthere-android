package com.adriencadet.downthere.models.downthereserver.jobs;

import com.adriencadet.downthere.ApplicationConfiguration;
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
    public ListPicturesByDateDescJob provideListPicturesByDateDescJob(ApplicationConfiguration configuration, IDownthereServerAPI api) {
        return new ListPicturesByDateDescJob(configuration, api);
    }
}
