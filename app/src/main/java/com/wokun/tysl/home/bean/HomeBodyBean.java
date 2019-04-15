package com.wokun.tysl.home.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class HomeBodyBean implements Serializable {

    private AdBean ad;
    private PictureBean dietitian;
    private PictureBean healthy365;
    private FaHeiHeiBean faheihei;
    private List<ArticleTypeBean> xuetang;

    @SerializedName("tuijian_new")
    private List<StoreTypeBean> tuijian;//营养师推荐
    @SerializedName("tiantuan")
    private TianTuan tianTuan; //健康服务天团
    @SerializedName("pintuan")
    private PinTuan pinTuan; //健康拼团

    public FaHeiHeiBean getFaheihei() {
        return faheihei;
    }

    public void setFaheihei(FaHeiHeiBean faheihei) {
        this.faheihei = faheihei;
    }

    public AdBean getAd() {
        return ad;
    }

    public void setAd(AdBean ad) {
        this.ad = ad;
    }

    public PictureBean getDietitian() {
        return dietitian;
    }

    public void setDietitian(PictureBean dietitian) {
        this.dietitian = dietitian;
    }

    public PictureBean getHealthy365() {
        return healthy365;
    }

    public void setHealthy365(PictureBean healthy365) {
        this.healthy365 = healthy365;
    }

    public List<StoreTypeBean> getTuijian() {
        return tuijian;
    }

    public void setTuijian(List<StoreTypeBean> tuijian) {
        this.tuijian = tuijian;
    }

    public List<ArticleTypeBean> getXuetang() {
        return xuetang;
    }

    public void setXuetang(List<ArticleTypeBean> xuetang) {
        this.xuetang = xuetang;
    }

    public TianTuan getTianTuan() {
        return tianTuan;
    }

    public PinTuan getPinTuan() {
        return pinTuan;
    }

    public void setTianTuan(TianTuan tiantuan) {
        this.tianTuan = tiantuan;
    }

    public void setPinTuan(PinTuan pintuan) {
        this.pinTuan = pintuan;
    }

    public static class AdBean implements Serializable{

        private String picture;
        private String url;

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public static class TianTuan implements Serializable{

        private String picture;
        private String url;

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUrl() {
            return url;
        }
    }

    public static class PinTuan implements Serializable{
        private String picture;

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }
    }

    public static class FaHeiHeiBean implements Serializable{
        private String picture;
        private int goods_id;

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public void setGoods_id(int goods_id) {
            this.goods_id = goods_id;
        }

        public int getGoods_id() {
            return goods_id;
        }
    }
}
