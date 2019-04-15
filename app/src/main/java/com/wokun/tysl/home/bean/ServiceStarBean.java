package com.wokun.tysl.home.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/8/30 0030.
 */

public class ServiceStarBean implements Serializable {

    @SerializedName("dietitian_id")
   private  String dietitian_id;
    @SerializedName("truename")
    private String truename;
    @SerializedName("head_logo")
    private  String  head_logo;
    @SerializedName("jobtype")
    private  String  jobtype;


    public String getDietitian_id() {
        return dietitian_id;
    }

    public void setDietitian_id(String dietitian_id) {
        this.dietitian_id = dietitian_id;
    }

    public String getTruename() {
        return truename;
    }

    public void setTruename(String truename) {
        this.truename = truename;
    }

    public String getHead_logo() {
        return head_logo;
    }

    public void setHead_logo(String head_logo) {
        this.head_logo = head_logo;
    }

    public String getJobtype() {
        return jobtype;
    }

    public void setJobtype(String jobtype) {
        this.jobtype = jobtype;
    }



}
