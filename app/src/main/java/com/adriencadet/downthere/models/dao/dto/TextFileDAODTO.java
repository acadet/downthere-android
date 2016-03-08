package com.adriencadet.downthere.models.dao.dto;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * TextFileDAODTO
 * <p>
 */
public class TextFileDAODTO extends RealmObject {
    @PrimaryKey
    private int id;

    private String name;
    private String attachmentURL;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAttachmentURL() {
        return attachmentURL;
    }

    public void setAttachmentURL(String attachmentURL) {
        this.attachmentURL = attachmentURL;
    }
}
