package com.adriencadet.downthere.models.dao;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * DAOFactory
 * <p>
 */
@Module
public class DAOFactory {
    @Provides
    @Singleton
    public IPictureDAO providePictureDAO(Context context) {
        return new PictureDAO(context);
    }

    @Provides
    @Singleton
    public ITextFileDAO provideTextFileDAO(Context context) {
        return new TextFileDAO(context);
    }
}
