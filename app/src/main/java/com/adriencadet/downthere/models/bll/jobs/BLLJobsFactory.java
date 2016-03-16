package com.adriencadet.downthere.models.bll.jobs;

import com.adriencadet.downthere.ApplicationConfiguration;
import com.adriencadet.downthere.models.bll.serializers.IPictureBLLDTOSerializer;
import com.adriencadet.downthere.models.dao.IPictureDAO;
import com.adriencadet.downthere.models.downthereserver.IDownthereServer;

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
}
