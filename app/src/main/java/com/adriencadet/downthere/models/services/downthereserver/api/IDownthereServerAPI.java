package com.adriencadet.downthere.models.services.downthereserver.api;

import com.adriencadet.downthere.models.services.downthereserver.dto.PictureServerDTO;

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
