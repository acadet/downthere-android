package com.adriencadet.downthere.models.services.downthereserver.api;

import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Path;

/**
 * IDownthereServerPlainAPI
 * <p>
 * For fetching text files only
 */
public interface IDownthereServerPlainAPI {
    @Headers({
        "Content-Type: text/plain"
    })
    @GET("/{url}")
    String getTextFileContent(@Path("url") String url);
}
