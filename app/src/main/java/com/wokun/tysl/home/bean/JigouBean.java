package com.wokun.tysl.home.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/8/30 0030.
 */

public class JigouBean  implements Serializable {

    private  String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    private  String desc;
    private  String imgurl;
    private  String  url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
