package com.adriencadet.downthere.models.services.downthereserver.api;

import com.adriencadet.downthere.ApplicationConfiguration;
import com.google.gson.GsonBuilder;

import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * DownthereAPIFactory
 * <p>
 */
@Module
public class DownthereAPIFactory {
    @Provides
    public IDownthereJSONAPI provideJSONAPI(ApplicationConfiguration configuration) {
        return new RestAdapter.Builder()
            .setEndpoint(configuration.SERVER_ENDPOINT)
            .setConverter(new GsonConverter(new GsonBuilder().create()))
            .build()
            .create(IDownthereJSONAPI.class);
    }

    @Provides
    public IDownthereServerPlainAPI providePlainAPI(ApplicationConfiguration configuration) {
        return new RestAdapter.Builder()
            .setEndpoint(configuration.RAW_TEXT_FILE_SERVER_ENDPOINT)
            .setConverter(new StringConverter())
            .build()
            .create(IDownthereServerPlainAPI.class);
    }
}
