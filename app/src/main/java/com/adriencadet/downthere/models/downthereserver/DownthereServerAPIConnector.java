package com.adriencadet.downthere.models.downthereserver;

import android.util.Log;

import com.adriencadet.downthere.ApplicationConfiguration;
import com.coshx.chocolatine.utils.actions.Action;
import com.coshx.chocolatine.utils.actions.Action0;
import com.google.gson.GsonBuilder;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * DownthereServerAPIConnector
 * <p>
 */
class DownthereServerAPIConnector {
    static void connect(Action<IDownthereServerAPI> onSuccess, Action0 onFailure) {
        try {
            IDownthereServerAPI outcome = new RestAdapter.Builder()
                .setEndpoint(ApplicationConfiguration.SERVER_ENDPOINT)
                .setConverter(new GsonConverter(new GsonBuilder().create()))
                .build()
                .create(IDownthereServerAPI.class);

            onSuccess.run(outcome);
        } catch (Exception e) {
            Log.e("DownthereServerAPIConne", "Failed to initialize a connection", e);
            onFailure.run();
        }
    }
}
