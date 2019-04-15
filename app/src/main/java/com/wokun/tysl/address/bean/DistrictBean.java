package com.wokun.tysl.address.bean;

import com.bigkoo.pickerview.model.IPickerViewData;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DistrictBean implements IPickerViewData, Serializable {

    @SerializedName("area_id")
    private String areaId;
    @SerializedName("area_name")
    private String areaName;

    public DistrictBean(String areaId, String areaName) {
        this.areaId = areaId;
        this.areaName = areaName;
    }

    @Override
    public String getPickerViewText() {
        return areaName;
    }

    public String getAreaId() {
        return areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }
}
