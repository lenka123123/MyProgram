package com.wokun.tysl.smartretail.bean;

import java.util.List;

public class RetailShop {

    private String storeCode;
    private String buyStoreCode;
    private List<GoodsDataBean> goodsData;
    private StoreInfoBean storeInfo;

    public StoreInfoBean getStoreInfo() {
        return storeInfo;
    }

    public void setStoreInfo(StoreInfoBean storeInfo) {
        this.storeInfo = storeInfo;
    }

    public String getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
    }

    public String getBuyStoreCode() {
        return buyStoreCode;
    }

    public void setBuyStoreCode(String buyStoreCode) {
        this.buyStoreCode = buyStoreCode;
    }

    public List<GoodsDataBean> getGoodsData() {
        return goodsData;
    }

    public void setGoodsData(List<GoodsDataBean> goodsData) {
        this.goodsData = goodsData;
    }
}