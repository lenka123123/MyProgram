package com.wokun.tysl.store.bean;

import com.google.gson.annotations.SerializedName;
import com.shantoo.widget.popupwindow.ActionItem;

import java.io.Serializable;

public class StoreIndexActionItemBean extends ActionItem implements Serializable {

    @SerializedName("sgc_id")
    private String sgcId;
    @SerializedName("sgc_name")
    private String sgcName;

    public StoreIndexActionItemBean(String title) {
        super(title);
    }

    public StoreIndexActionItemBean(String title, int mResourcesId) {
        super(title, mResourcesId);
    }

    public StoreIndexActionItemBean(String title, int childTitleColor, int childTitleSelectedColor) {
        super(title, childTitleColor, childTitleSelectedColor);
    }

    public StoreIndexActionItemBean(String title, int childTitleColor, int childTitleSelectedColor, boolean isSelected) {
        super(title, childTitleColor, childTitleSelectedColor, isSelected);
    }

    @Override
    public String getChildTitle() {
        return sgcName;
    }

    @Override
    public String getActionId() {
        return sgcId;
    }

    public String getSgcId() {
        return sgcId;
    }

    public String getSgcName() {
        return sgcName;
    }

    public void setSgcId(String sgcId) {
        this.sgcId = sgcId;
    }

    public void setSgcName(String sgcName) {
        this.sgcName = sgcName;
    }
}
