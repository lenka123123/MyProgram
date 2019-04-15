package com.wokun.tysl.home.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class HeadDataBean implements Serializable{

    @SerializedName("service_state")
    private int serviceState;

    @SerializedName("lvju")
    private LvJu lvJu;
    private List<NoticeBean> notice;
    private List<BannerBean> banner;

    public int getServiceState() {
        return serviceState;
    }

    public LvJu getLvJu() {
        return lvJu;
    }

    public void setServiceState(int serviceState) {
        this.serviceState = serviceState;
    }

    public void setLvJu(LvJu lvJu) {
        this.lvJu = lvJu;
    }

    public List<NoticeBean> getNotice() {
        return notice;
    }

    public void setNotice(List<NoticeBean> notice) {
        this.notice = notice;
    }

    public List<BannerBean> getBanner() {
        return banner;
    }

    public void setBanner(List<BannerBean> banner) {
        this.banner = banner;
    }

    public static class LvJu {

        private String name;
        private String url;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
