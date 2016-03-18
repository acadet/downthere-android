package com.adriencadet.downthere.models.bll.jobs;

import com.adriencadet.downthere.ApplicationConfiguration;
import com.adriencadet.downthere.models.bll.serializers.IPictureBLLDTOSerializer;
import com.adriencadet.downthere.models.bll.serializers.ITextFileBLLDTOSerializer;
import com.adriencadet.downthere.models.dao.IPictureDAO;
import com.adriencadet.downthere.models.dao.ITextFileDAO;
import com.adriencadet.downthere.models.services.downthereserver.IDownthereServer;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * BLLJobsFactory
 * <p>
 */
@Module
public class BLLJobsFactory {
    @Provides
    @Singleton
    public ListPicturesByDateDescJob provideListPicturesByDateDescJob(
        ApplicationConfiguration configuration,
        IDownthereServer server,
        IPictureDAO pictureDAO,
        IPictureBLLDTOSerializer serializer) {
        return new ListPicturesByDateDescJob(configuration, server, pictureDAO, serializer);
    }

    @Provides
    @Singleton
    public ListTextFilesByDateDescJob provideListTextFilesByDateDescJob(
        ApplicationConfiguration configuration,
        IDownthereServer server,
        ITextFileDAO textFileDAO,
        ITextFileBLLDTOSerializer serializer) {
        return new ListTextFilesByDateDescJob(configuration, server, textFileDAO, serializer);
    }
}
