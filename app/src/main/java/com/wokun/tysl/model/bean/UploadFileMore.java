package com.wokun.tysl.model.bean;

import java.util.List;


public class UploadFileMore {

    private String uploadFileName;
    private List<String> uploadFileUrl;

    public String getUploadFileName() {
        return uploadFileName;
    }

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    public List<String> getUploadFileUrl() {
        return uploadFileUrl;
    }

    public void setUploadFileUrl(List<String> uploadFileUrl) {
        this.uploadFileUrl = uploadFileUrl;
    }
}
