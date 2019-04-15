package com.wokun.tysl.goods.bean;

import com.google.gson.annotations.SerializedName;
import com.wokun.tysl.model.bean.Store;
import com.wokun.tysl.store.bean.StoreIndexActionItemBean;

import java.io.Serializable;
import java.util.List;

public class StoreIndex implements Serializable {

    @SerializedName("fav_state")
    private int favState;
    @SerializedName("share_url")
    private String shareUrl;
    @SerializedName("store_baseinfo")
    private Store storeBaseInfo;
    @SerializedName("store_class")
    private List<StoreIndexActionItemBean> storeClass;

    public int getFavState() {
        return favState;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public Store getStoreBaseInfo() {
        return storeBaseInfo;
    }

    public List<StoreIndexActionItemBean> getStoreClass() {
        return storeClass;
    }

    public void setFavState(int favState) {
        this.favState = favState;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public void setStoreBaseInfo(Store storeBaseInfo) {
        this.storeBaseInfo = storeBaseInfo;
    }

    public void setStoreClass(List<StoreIndexActionItemBean> storeClass) {
        this.storeClass = storeClass;
    }
}
