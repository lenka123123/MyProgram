package com.wokun.tysl.dietician.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class DietitianDetailBean implements Serializable{

    private String uid;
    private String wechat;
    private String mobile;
    private String profile;
    private String praise;
    private  int pingfen;

    private  String  share_url;

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public int getPingfen() {
        return pingfen;
    }

    public void setPingfen(int pingfen) {
        this.pingfen = pingfen;
    }

    public ArticlepeopleBean getArticle() {
        return article;
    }

    public void setArticle(ArticlepeopleBean article) {
        this.article = article;
    }

    @SerializedName("dietitian_id")
    private int dietitianId;
    @SerializedName("dietitian_user_id")
    private int dietitianUserId;
    @SerializedName("service_fee")
    private String serviceFee;
    @SerializedName("service_nums")
    private String servicenNums;
    @SerializedName("area_info")
    private String areaInfo;
    @SerializedName("truename")
    private String trueName;
    @SerializedName("head_logo")
    private String headLogo;
    private String birthday;
    @SerializedName("type_name")
    private String typeName;
    @SerializedName("eval_nums")
    private String evalNums;
    @SerializedName("article_nums")
    private String articleNums;
    @SerializedName("rec_goods_total")
    private int recGoodsTotal;//推荐商品数量
    private int buyService; //1已购买0未购买
    private int favorite; //1已收藏 0未收藏;
    private List<String> field;
    @SerializedName("rec_goods_Data")
    private List<RecGoodsBean> recGoodsData;//推荐商品数据
    @SerializedName("eval_data")
    private List<EvalDataBean> evalData;






     private  ArticlepeopleBean article;
    public static class ArticlepeopleBean implements Serializable{
        private  String des;
        private  String click_num;
        private  String add_time;
        private  String article_image;

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }

        public String getClick_num() {
            return click_num;
        }

        public void setClick_num(String click_num) {
            this.click_num = click_num;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getArticle_image() {
            return article_image;
        }

        public void setArticle_image(String article_image) {
            this.article_image = article_image;
        }






    }
    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setDietitianId(int dietitianId) {
        this.dietitianId = dietitianId;
    }

    public void setDietitianUserId(int dietitianUserId) {
        this.dietitianUserId = dietitianUserId;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setServiceFee(String serviceFee) {
        this.serviceFee = serviceFee;
    }

    public void setServicenNums(String servicenNums) {
        this.servicenNums = servicenNums;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public void setAreaInfo(String areaInfo) {
        this.areaInfo = areaInfo;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public void setHeadLogo(String headLogo) {
        this.headLogo = headLogo;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public void setPraise(String praise) {
        this.praise = praise;
    }

    public void setEvalNums(String evalNums) {
        this.evalNums = evalNums;
    }

    public void setArticleNums(String articleNums) {
        this.articleNums = articleNums;
    }

    public void setRecGoodsTotal(int recGoodsTotal) {
        this.recGoodsTotal = recGoodsTotal;
    }

    public void setBuyService(int buyService) {
        this.buyService = buyService;
    }

    public void setFavorite(int favorite) {
        this.favorite = favorite;
    }

    public void setField(List<String> field) {
        this.field = field;
    }

    public void setRecGoodsData(List<RecGoodsBean> recGoodsData) {
        this.recGoodsData = recGoodsData;
    }

    public void setEvalData(List<EvalDataBean> evalData) {
        this.evalData = evalData;
    }

    public String getUid() {
        return uid;
    }

    public int getDietitianId() {
        return dietitianId;
    }

    public int getDietitianUserId() {
        return dietitianUserId;
    }

    public String getWechat() {
        return wechat;
    }

    public String getMobile() {
        return mobile;
    }

    public String getServiceFee() {
        return serviceFee;
    }

    public String getServicenNums() {
        return servicenNums;
    }

    public String getProfile() {
        return profile;
    }

    public String getAreaInfo() {
        return areaInfo;
    }

    public String getTrueName() {
        return trueName;
    }

    public String getHeadLogo() {
        return headLogo;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getTypeName() {
        return typeName;
    }

    public String getPraise() {
        return praise;
    }

    public String getEvalNums() {
        return evalNums;
    }

    public String getArticleNums() {
        return articleNums;
    }

    public int getRecGoodsTotal() {
        return recGoodsTotal;
    }

    public int getBuyService() {
        return buyService;
    }

    public int getFavorite() {
        return favorite;
    }

    public List<String> getField() {
        return field;
    }

    public List<RecGoodsBean> getRecGoodsData() {
        return recGoodsData;
    }

    public List<EvalDataBean> getEvalData() {
        return evalData;
    }



}
