package com.adriencadet.downthere.models.services.downthereserver;

import com.adriencadet.downthere.models.services.downthereserver.jobs.ListPicturesByDateDescJob;
import com.adriencadet.downthere.models.services.downthereserver.jobs.ListTextFilesByDateDescJob;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * DownthereServerFactory
 * <p>
 */
@Module
public class DownthereServerFactory {
    @Provides
    @Singleton
    public IDownthereServer provideServer(ListPicturesByDateDescJob listPicturesByDateDescJob, ListTextFilesByDateDescJob listTextFilesByDateDescJob) {
        return new DownthereServer(listPicturesByDateDescJob, listTextFilesByDateDescJob);
    }
}
