package com.adriencadet.downthere.models.services.downthereserver.jobs;

import com.adriencadet.downthere.ApplicationConfiguration;
import com.adriencadet.downthere.models.services.downthereserver.api.IDownthereJSONAPI;
import com.adriencadet.downthere.models.services.downthereserver.api.IDownthereServerPlainAPI;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * DownthereServiceJobFactory
 * <p>
 */
@Module
public class DownthereServiceJobFactory {
    @Provides
    @Singleton
    public ListPicturesByDateDescJob provideListPicturesByDateDescJob(ApplicationConfiguration configuration, IDownthereJSONAPI api) {
        return new ListPicturesByDateDescJob(configuration, api);
    }

    @Provides
    @Singleton
    public ListTextFilesByDateDescJob provideListTextFilesByDateDescJob(ApplicationConfiguration configuration, IDownthereJSONAPI api) {
        return new ListTextFilesByDateDescJob(configuration, api);
    }

    @Provides
    @Singleton
    public GetTextFileContentJob provideGetTextFileContentJob(ApplicationConfiguration configuration, IDownthereServerPlainAPI api) {
        return new GetTextFileContentJob(configuration, api);
    }
}
