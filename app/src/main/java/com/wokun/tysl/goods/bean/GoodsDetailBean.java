package com.wokun.tysl.goods.bean;

import com.google.gson.annotations.SerializedName;
import com.wokun.tysl.model.bean.Store;

import java.io.Serializable;
import java.util.List;

public class GoodsDetailBean implements Serializable {

    @SerializedName("share_url")
    private String shareUrl;

    @SerializedName("goods_id")
    private String goodsId;

    @SerializedName("goods_image")
    private String goodsImage;

    @SerializedName("goods_price")
    private String goodsPrice;

    @SerializedName("goods_name")
    private String goodsName;

    @SerializedName("goods_salesnum")
    private String goodsSalesNum;

    @SerializedName("month_salesnum")
    private String monthSalesNum;

    @SerializedName("goods_freight")
    private int goodsFreight;//商品运费计算模式 0 按重量计算 1 包邮

    @SerializedName("good_mobile_detail")
    private String goodMobileDetail;

    private int favorite;

    @SerializedName("store_favorite")
    private int storeFavorite;

    private Store store;

    private EvalutionsBean evalutions;

    @SerializedName("good_mobile_images")
    private List<String> goodMobileImages;

    public String getShareUrl() {
        return shareUrl;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public String getGoodsImage() {
        return goodsImage;
    }

    public String getGoodsPrice() {
        return goodsPrice;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public String getGoodsSalesNum() {
        return goodsSalesNum;
    }

    public String getMonthSalesNum() {
        return monthSalesNum;
    }

    public int getGoodsFreight() {
        return goodsFreight;
    }

    public String getGoodMobileDetail() {
        return goodMobileDetail;
    }

    public int getFavorite() {
        return favorite;
    }

    public int getStoreFavorite() {
        return storeFavorite;
    }

    public Store getStore() {
        return store;
    }

    public EvalutionsBean getEvalutions() {
        return evalutions;
    }

    public List<String> getGoodMobileImages() {
        return goodMobileImages;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public void setGoodsImage(String goodsImage) {
        this.goodsImage = goodsImage;
    }

    public void setGoodsPrice(String goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public void setGoodsSalesNum(String goodsSalesNum) {
        this.goodsSalesNum = goodsSalesNum;
    }

    public void setMonthSalesNum(String monthSalesNum) {
        this.monthSalesNum = monthSalesNum;
    }

    public void setGoodsFreight(int goodsFreight) {
        this.goodsFreight = goodsFreight;
    }

    public void setGoodMobileDetail(String goodMobileDetail) {
        this.goodMobileDetail = goodMobileDetail;
    }

    public void setFavorite(int favorite) {
        this.favorite = favorite;
    }

    public void setStoreFavorite(int storeFavorite) {
        this.storeFavorite = storeFavorite;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public void setEvalutions(EvalutionsBean evalutions) {
        this.evalutions = evalutions;
    }

    public void setGoodMobileImages(List<String> goodMobileImages) {
        this.goodMobileImages = goodMobileImages;
    }
}
