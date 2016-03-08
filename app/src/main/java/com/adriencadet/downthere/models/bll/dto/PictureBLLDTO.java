package com.adriencadet.downthere.models.bll.dto;

import org.joda.time.DateTime;

/**
 * PictureBLLDTO
 * <p>
 */
public class PictureBLLDTO {
    private int      id;
    private String   name;
    private DateTime createdAt;
    private DateTime updatedAt;
    private String   attachmentURL;

    public int getId() {
        return id;
    }

    public PictureBLLDTO setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public PictureBLLDTO setName(String name) {
        this.name = name;
        return this;
    }

    public DateTime getCreatedAt() {
        return createdAt;
    }

    public PictureBLLDTO setCreatedAt(DateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public DateTime getUpdatedAt() {
        return updatedAt;
    }

    public PictureBLLDTO setUpdatedAt(DateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public String getAttachmentURL() {
        return attachmentURL;
    }

    public PictureBLLDTO setAttachmentURL(String attachmentURL) {
        this.attachmentURL = attachmentURL;
        return this;
    }
}
