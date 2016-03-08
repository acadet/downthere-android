package com.adriencadet.downthere.models.downthereserver;

/**
 * PictureServerDTO
 * <p>
 */
class PictureServerDTO {
    public static class Attachment {
        public String url;
    }

    public int        id;
    public String     name;
    public String     createdAt;
    public String     updatedAt;
    public Attachment attachment;
}
