package com.adriencadet.downthere.models.bll;

import com.adriencadet.downthere.models.bll.jobs.GetTextFileContentJob;
import com.adriencadet.downthere.models.bll.jobs.ListPicturesByDateDescJob;
import com.adriencadet.downthere.models.bll.jobs.ListTextFilesByDateDescJob;

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
    public IDataReadingBLL provideDataReadingBLL(
        ListPicturesByDateDescJob listPicturesByDateDescJob,
        ListTextFilesByDateDescJob listTextFilesByDateDescJob,
        GetTextFileContentJob getTextFileContentJob) {
        return new DataReadingBLL(listPicturesByDateDescJob, listTextFilesByDateDescJob, getTextFileContentJob);
    }
}
