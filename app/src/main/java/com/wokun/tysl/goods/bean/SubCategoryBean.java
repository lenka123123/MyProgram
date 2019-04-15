package com.wokun.tysl.goods.bean;

import com.chad.library.adapter.base.entity.SectionEntity;
import com.google.gson.annotations.SerializedName;
import com.wokun.tysl.model.bean.SubBean;

import java.io.Serializable;
import java.util.List;

public class SubCategoryBean extends SectionEntity<SubBean> implements Serializable {

    @SerializedName("gc_id")
    private String gcId;
    @SerializedName("gc_name")
    private String gcName;
    @SerializedName("gc_icon")
    private String gcIcon;
    private List<SubBean> sub;

    public SubCategoryBean(SubBean t) {
        super(t);
    }



    public SubCategoryBean(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public String getGcId() {
        return gcId;
    }

    public String getGcName() {
        return gcName;
    }

    public String getGcIcon() {
        return gcIcon;
    }

   public List<SubBean> getSub() {
        return sub;
    }

    public void setGcId(String gcId) {
        this.gcId = gcId;
    }

    public void setGcName(String gcName) {
        this.gcName = gcName;
    }

    public void setGcIcon(String gcIcon) {
        this.gcIcon = gcIcon;
    }

  public void setSub(List<SubBean> sub) {
        this.sub = sub;
    }
}