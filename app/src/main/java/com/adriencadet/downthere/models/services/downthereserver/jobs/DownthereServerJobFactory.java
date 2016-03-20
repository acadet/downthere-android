package com.adriencadet.downthere.models.services.downthereserver.jobs;

import com.adriencadet.downthere.ApplicationConfiguration;
import com.adriencadet.downthere.models.services.downthereserver.api.IDownthereServerJSONAPI;
import com.adriencadet.downthere.models.services.downthereserver.api.IDownthereServerPlainAPI;

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
    public ListPicturesByDateDescJob provideListPicturesByDateDescJob(ApplicationConfiguration configuration, IDownthereServerJSONAPI api) {
        return new ListPicturesByDateDescJob(configuration, api);
    }

    @Provides
    @Singleton
    public ListTextFilesByDateDescJob provideListTextFilesByDateDescJob(ApplicationConfiguration configuration, IDownthereServerJSONAPI api) {
        return new ListTextFilesByDateDescJob(configuration, api);
    }

    @Provides
    @Singleton
    public GetTextFileContentJob provideGetTextFileContentJob(ApplicationConfiguration configuration, IDownthereServerPlainAPI api) {
        return new GetTextFileContentJob(configuration, api);
    }
}
