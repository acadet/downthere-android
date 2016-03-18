package com.adriencadet.downthere.models.bll.serializers;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * BLLSerializerFactory
 * <p>
 */
@Module
public class BLLSerializerFactory {
    @Provides
    @Singleton
    public IPictureBLLDTOSerializer providePictureBLLDTOSerializer() {
        return new PictureBLLDTOSerializer();
    }

    @Provides
    @Singleton
    public ITextFileBLLDTOSerializer provideTextFileBLLDTOSerializer() {
        return new TextFileBLLDTOSerializer();
    }
}
