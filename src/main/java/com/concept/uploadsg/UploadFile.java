package com.concept.uploadsg;

public class UploadFile {
    private String extension;

    private String realName;

    private String name;

    private String type;

    private long size;

    private String url;

    private String deleteUrl;

    private String deleteType;

    private String absolutePath;

    private String description;

    private String scope;

    private String id;

    public final String getAbsolutePath() {
        return absolutePath;
    }

    public final void setAbsolutePath(String absolutePath) {
        this.absolutePath = absolutePath;
    }

    public final String getDescription() {
        return description;
    }

    public final void setDescription(String description) {
        this.description = description;
    }

    public final String getName() {
        return name;
    }

    public final void setName(String name) {
        this.name = name;
    }

    public final long getSize() {
        return size;
    }

    public final void setSize(long size) {
        this.size = size;
    }

    public final String getUrl() {
        return url;
    }

    public final void setUrl(String url) {
        this.url = url;
    }

    public final String getDeleteUrl() {
        return deleteUrl;
    }

    public final void setDeleteUrl(String deleteUrl) {
        this.deleteUrl = deleteUrl;
    }

    public final String getDeleteType() {
        return deleteType;
    }

    public final void setDeleteType(String deleteType) {
        this.deleteType = deleteType;
    }

    public final String getType() {
        return type;
    }

    public final void setType(String type) {
        this.type = type;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getRealName() {
        return realName;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getExtension() {
        return extension;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
