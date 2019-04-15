package com.wokun.tysl.myyijian;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018\10\17 0017.
 */

public class SendjianyiBean implements Serializable{


    private  String uploadFileName;
         private List<Object> uploadFileUrl;

    public String getUploadFileName() {
        return uploadFileName;
    }

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    public List<Object> getUploadFileUrl() {
        return uploadFileUrl;
    }

    public void setUploadFileUrl(List<Object> uploadFileUrl) {
        this.uploadFileUrl = uploadFileUrl;
    }
}
