package com.adriencadet.downthere.models.services.downthereserver.jobs;

import com.adriencadet.downthere.ApplicationConfiguration;
import com.adriencadet.downthere.models.services.downthereserver.api.IDownthereServerAPI;

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

    @Provides
    @Singleton
    public ListTextFilesByDateDescJob provideListTextFilesByDateDescJob(ApplicationConfiguration configuration, IDownthereServerAPI api) {
        return new ListTextFilesByDateDescJob(configuration, api);
    }

    @Provides
    @Singleton
    public GetTextFileContentJob provideGetTextFileContentJob(IDownthereServerAPI api) {
        return new GetTextFileContentJob(api);
    }
}
