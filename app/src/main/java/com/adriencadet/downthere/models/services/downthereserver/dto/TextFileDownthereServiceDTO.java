package com.adriencadet.downthere.models.services.downthereserver.dto;

/**
 * TextFileDownthereServiceDTO
 * <p>
 */
public class TextFileDownthereServiceDTO {
    public static class Attachment {
        public String url;
    }

    public int        id;
    public String     name;
    public String     created_at;
    public String     updated_at;
    public Attachment attachment;
}
