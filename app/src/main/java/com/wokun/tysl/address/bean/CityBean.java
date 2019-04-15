package com.wokun.tysl.address.bean;

import com.bigkoo.pickerview.model.IPickerViewData;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class CityBean implements IPickerViewData, Serializable {

    @SerializedName("area_id")
    private String areaId;
    @SerializedName("area_name")
    private String areaName;
    private List<DistrictBean> districtSub;

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

    public List<DistrictBean> getDistrictSub() {
        return districtSub;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public void setDistrictSub(List<DistrictBean> districtSub) {
        this.districtSub = districtSub;
    }
}
