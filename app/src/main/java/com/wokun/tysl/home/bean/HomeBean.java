package com.wokun.tysl.home.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/8/30 0030.
 */

public class HomeBean  implements Serializable {

    private List< NaojiankangEntity> naojiankang;
    private List<NoticeBean> notice;
    private List<BannerBean> banner;
    private List<ServiceStarBean> serviceStar;
    private List<ArticleBean> article;
    private AdBean ad;
    private RetailBean retail;
    private String njk_ad;

    public String getNjk_ad() {
        return njk_ad;
    }

    public void setNjk_ad(String njk_ad) {
        this.njk_ad = njk_ad;
    }

    public List<NaojiankangEntity> getNaojiankang() {
        return naojiankang;
    }

    public void setNaojiankang(List<NaojiankangEntity> naojiankang) {
        this.naojiankang = naojiankang;
    }

    public class NaojiankangEntity {
        /**
         * goods_name : 脑康键
         * goods_pic : http://img.tyitop.com/m_tyitop_com/goods/goods_d71c503d5e15c243_360.jpeg
         * goods_id : 490
         * goods_desc : 守护脑健康
         */
        private String goods_name;
        private String goods_pic;
        private String goods_id;
        private String goods_desc;

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public void setGoods_pic(String goods_pic) {
            this.goods_pic = goods_pic;
        }

        public void setGoods_id(String goods_id) {
            this.goods_id = goods_id;
        }

        public void setGoods_desc(String goods_desc) {
            this.goods_desc = goods_desc;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public String getGoods_pic() {
            return goods_pic;
        }

        public String getGoods_id() {
            return goods_id;
        }

        public String getGoods_desc() {
            return goods_desc;
        }
    }
    public RetailBean getRetail() {
        return retail;
    }

    public void setRetail(RetailBean retail) {
        this.retail = retail;
    }

    private List<JigouBean>   jigou;


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

    public List<ServiceStarBean> getServiceStar() {
        return serviceStar;
    }

    public void setServiceStar(List<ServiceStarBean> serviceStar) {
        this.serviceStar = serviceStar;
    }

    public List<ArticleBean> getArticle() {
        return article;
    }

    public void setArticle(List<ArticleBean> article) {
        this.article = article;
    }

    public AdBean getAd() {
        return ad;
    }

    public void setAd(AdBean ad) {
        this.ad = ad;
    }



    public List<JigouBean> getJigou() {
        return jigou;
    }

    public void setJigou(List<JigouBean> jigou) {
        this.jigou = jigou;
    }

    public static class AdBean implements Serializable{

        private String image;
        private String url;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

}
