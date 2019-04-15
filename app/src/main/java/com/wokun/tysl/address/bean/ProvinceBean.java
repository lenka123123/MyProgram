package com.wokun.tysl.address.bean;

import com.bigkoo.pickerview.model.IPickerViewData;
import com.google.gson.annotations.SerializedName;
import com.wokun.tysl.address.bean.CityBean;

import java.io.Serializable;
import java.util.List;

public class ProvinceBean implements IPickerViewData, Serializable {

    @SerializedName("area_id")
    private String areaId;
    @SerializedName("area_name")
    private String areaName;
    private List<CityBean> citySub;

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

    public List<CityBean> getCitySub() {
        return citySub;
    }

    public void setCitySub(List<CityBean> citySub) {
        this.citySub = citySub;
    }
}
