package com.wokun.tysl.myyijian;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018\10\17 0017.
 */

public class SendjianyiBean2 implements Serializable{


         private  String url;
         private String filename;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
