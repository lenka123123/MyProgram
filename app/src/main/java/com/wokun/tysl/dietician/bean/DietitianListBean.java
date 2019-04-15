package com.wokun.tysl.dietician.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DietitianListBean implements Serializable {

    @SerializedName("dietitian_id")
    private String dietitianId;
    @SerializedName("truename")
    private String trueName;
    @SerializedName("head_logo")
    private String headLogo;
    @SerializedName("jobtype")
    private String jobType;

    private String service_fee;
    private String field_name;

    public String getField_name() {
        return field_name;
    }

    public void setField_name(String field_name) {
        this.field_name = field_name;
    }

    public String getService_fee() {
        return service_fee;
    }

    public void setService_fee(String service_fee) {
        this.service_fee = service_fee;
    }

    public String getDietitianId() {
        return dietitianId;
    }

    public String getTrueName() {
        return trueName;
    }

    public String getHeadLogo() {
        return headLogo;
    }

    public String getJobType() {
        return jobType;
    }

    public void setDietitianId(String dietitianId) {
        this.dietitianId = dietitianId;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public void setHeadLogo(String headLogo) {
        this.headLogo = headLogo;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }
}
