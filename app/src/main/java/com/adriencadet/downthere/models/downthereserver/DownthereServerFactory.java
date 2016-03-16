package com.adriencadet.downthere.models.downthereserver;

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
    public IDownthereServer provideServer() {
        return new DownthereServer();
    }
}
