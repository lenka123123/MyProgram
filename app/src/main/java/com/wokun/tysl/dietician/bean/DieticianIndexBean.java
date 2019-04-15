package com.wokun.tysl.dietician.bean;

import java.io.Serializable;
import java.util.List;

public class DieticianIndexBean implements Serializable{

    private List<DietitianListBean> serviceStar;
    private List<DieticianFieldTypeBean> field;
    private List<DieticianJobTypeBean> job;

    public void setServiceStar(List<DietitianListBean> serviceStar) {
        this.serviceStar = serviceStar;
    }

    public void setField(List<DieticianFieldTypeBean> field) {
        this.field = field;
    }

    public void setJob(List<DieticianJobTypeBean> job) {
        this.job = job;
    }

    public List<DietitianListBean> getServiceStar() {
        return serviceStar;
    }

    public List<DieticianFieldTypeBean> getField() {
        return field;
    }

    public List<DieticianJobTypeBean> getJob() {
        return job;
    }

    /*public static class ServiceStarBean {
        *//**
         * dietitian_id : 62
         * truename : 沈夏冰
         * head_logo : http://img.tyitop.com/m_tyitop_com/avatar/user_1464569631755.jpg
         * jobtype :
         *//*

        private String dietitian_id;
        private String truename;
        private String head_logo;
        private String jobtype;

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

    public static class FieldBean {
        *//**
         * field_id : 15
         * field_name : 体重管理
         * field_icon : http://192.168.0.108/uploads/field/default.png
         *//*

        private String field_id;
        private String field_name;
        private String field_icon;

        public String getField_id() {
            return field_id;
        }

        public void setField_id(String field_id) {
            this.field_id = field_id;
        }

        public String getField_name() {
            return field_name;
        }

        public void setField_name(String field_name) {
            this.field_name = field_name;
        }

        public String getField_icon() {
            return field_icon;
        }

        public void setField_icon(String field_icon) {
            this.field_icon = field_icon;
        }
    }

    public static class JobBean {
        *//**
         * type_id : 1
         * type_name : 临床营养师
         * type_icon : http://192.168.0.108/uploads/jobtype/default.png
         *//*

        private String type_id;
        private String type_name;
        private String type_icon;

        public String getType_id() {
            return type_id;
        }

        public void setType_id(String type_id) {
            this.type_id = type_id;
        }

        public String getType_name() {
            return type_name;
        }

        public void setType_name(String type_name) {
            this.type_name = type_name;
        }

        public String getType_icon() {
            return type_icon;
        }

        public void setType_icon(String type_icon) {
            this.type_icon = type_icon;
        }
    }*/
}
