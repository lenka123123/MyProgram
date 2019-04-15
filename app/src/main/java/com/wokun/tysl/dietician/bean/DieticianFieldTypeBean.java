package com.wokun.tysl.dietician.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DieticianFieldTypeBean implements Serializable {

    @SerializedName("field_id")
    private String fieldId;
    @SerializedName("field_name")
    private String fieldName;
    @SerializedName("field_icon")
    private String fieldIcon;

    public void setFieldId(String fieldId) {
        this.fieldId = fieldId;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public void setFieldIcon(String fieldIcon) {
        this.fieldIcon = fieldIcon;
    }

    public String getFieldId() {
        return fieldId;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getFieldIcon() {
        return fieldIcon;
    }
}
