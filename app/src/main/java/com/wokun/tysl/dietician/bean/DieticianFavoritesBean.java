package com.wokun.tysl.dietician.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

//营养师收藏
public class DieticianFavoritesBean implements Serializable {

    @SerializedName("dietitian_id")
    private String dietitianId;
    @SerializedName("dietitian_state")
    private String dietitianState;
    @SerializedName("truename")
    private String trueName;
    @SerializedName("jobname")
    private String jobName;
    @SerializedName("head_logo")
    private String headLogo;

    public String getDietitianId() {
        return dietitianId;
    }

    public String getDietitianState() {
        return dietitianState;
    }

    public String getTrueName() {
        return trueName;
    }

    public String getJobName() {
        return jobName;
    }

    public String getHeadLogo() {
        return headLogo;
    }

    public void setDietitianId(String dietitianId) {
        this.dietitianId = dietitianId;
    }

    public void setDietitianState(String dietitianState) {
        this.dietitianState = dietitianState;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public void setHeadLogo(String headLogo) {
        this.headLogo = headLogo;
    }
}
