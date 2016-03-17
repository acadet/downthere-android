package com.adriencadet.downthere.models.services.downthereserver.api;

import com.adriencadet.downthere.ApplicationConfiguration;
import com.google.gson.GsonBuilder;

import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * DownthereServerAPIFactory
 * <p>
 */
@Module
public class DownthereServerAPIFactory {
    @Provides
    public IDownthereServerAPI provideAPI(ApplicationConfiguration configuration) {
        return new RestAdapter.Builder()
            .setEndpoint(configuration.SERVER_ENDPOINT)
            .setConverter(new GsonConverter(new GsonBuilder().create()))
            .build()
            .create(IDownthereServerAPI.class);
    }
}
