package com.adriencadet.downthere.models.downthereserver;

import com.adriencadet.downthere.models.downthereserver.jobs.ListPicturesByDateDescJob;

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
    public IDownthereServer provideServer(ListPicturesByDateDescJob listPicturesByDateDescJob) {
        return new DownthereServer(listPicturesByDateDescJob);
    }
}
