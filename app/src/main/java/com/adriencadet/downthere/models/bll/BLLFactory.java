package com.adriencadet.downthere.models.bll;

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
    public IDataReadingBLL provideDataReadingBLL() {
        return new DataReadingBLL();
    }
}
