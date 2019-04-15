package com.wokun.tysl.model.response;

import com.wokun.tysl.store.bean.BusinessShowBean;

import java.io.Serializable;
import java.util.List;

public class BusinessShowResponse implements Serializable{

    private List<BusinessShowBean> storeList;

    public List<BusinessShowBean> getStoreList() {
        return storeList;
    }

    public void setStoreList(List<BusinessShowBean> storeList) {
        this.storeList = storeList;
    }
}
