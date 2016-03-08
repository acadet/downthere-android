package com.adriencadet.downthere.models.downthereserver;

import java.util.List;

import retrofit.http.GET;

/**
 * IDownthereServerAPI
 * <p>
 */
public interface IDownthereServerAPI {
    String FORMAT           = ".json";
    String PICTURE_ENDPOINT = "/pictures";

    @GET(PICTURE_ENDPOINT + FORMAT)
    List<PictureServerDTO> listPicturesByDateDesc();
}
