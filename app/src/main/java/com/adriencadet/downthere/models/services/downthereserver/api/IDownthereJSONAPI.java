package com.adriencadet.downthere.models.services.downthereserver.api;

import com.adriencadet.downthere.models.services.downthereserver.dto.PictureDownthereServiceDTO;
import com.adriencadet.downthere.models.services.downthereserver.dto.TextFileDownthereServiceDTO;

import java.util.List;

import retrofit.http.GET;

/**
 * IDownthereJSONAPI
 * <p>
 */
public interface IDownthereJSONAPI {
    String FORMAT             = ".json";
    String PICTURE_ENDPOINT   = "/pictures";
    String TEXT_FILE_ENDPOINT = "/text_files";

    @GET(PICTURE_ENDPOINT + FORMAT)
    List<PictureDownthereServiceDTO> listPicturesByDateDesc();

    @GET(TEXT_FILE_ENDPOINT + FORMAT)
    List<TextFileDownthereServiceDTO> listTextFilesByDateDesc();
}
