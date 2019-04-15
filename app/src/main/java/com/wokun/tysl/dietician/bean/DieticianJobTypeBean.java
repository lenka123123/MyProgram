package com.wokun.tysl.dietician.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DieticianJobTypeBean implements Serializable {

    @SerializedName("type_id")
    private String typeId;
    @SerializedName("type_name")
    private String typeName;
    @SerializedName("type_icon")
    private String typeIcon;

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public void setTypeIcon(String typeIcon) {
        this.typeIcon = typeIcon;
    }

    public String getTypeId() {
        return typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public String getTypeIcon() {
        return typeIcon;
    }
}
