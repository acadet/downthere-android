package com.adriencadet.downthere.models.services.downthereserver.dto;

/**
 * PictureServerDTO
 * <p>
 */
public class PictureServerDTO {
    public static class Attachment {
        public String url;
    }

    public int        id;
    public String     name;
    public String     created_at;
    public String     updated_at;
    public Attachment attachment;
}