package com.adriencadet.downthere.models.services.downthereserver.api;

import com.adriencadet.downthere.models.services.downthereserver.dto.PictureServerDTO;
import com.adriencadet.downthere.models.services.downthereserver.dto.TextFileServerDTO;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Path;

/**
 * IDownthereServerAPI
 * <p>
 */
public interface IDownthereServerAPI {
    String FORMAT             = ".json";
    String PICTURE_ENDPOINT   = "/pictures";
    String TEXT_FILE_ENDPOINT = "/text_files";

    @GET(PICTURE_ENDPOINT + FORMAT)
    List<PictureServerDTO> listPicturesByDateDesc();

    @GET(TEXT_FILE_ENDPOINT + FORMAT)
    List<TextFileServerDTO> listTextFilesByDateDesc();

    @Headers({
        "Content-Type: application/text"
    })
    @GET("{url}")
    String getTextFileContent(@Path("url") String url);
}
