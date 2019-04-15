package com.wokun.tysl.model.bean;

import java.io.Serializable;
import java.util.List;

public class SubBean implements Serializable {

    private String gc_id;
    private String gc_name;
    private String gc_icon;
    private List<?> sub;

    public String getGc_id() {
        return gc_id;
    }

    public void setGc_id(String gc_id) {
        this.gc_id = gc_id;
    }

    public String getGc_name() {
        return gc_name;
    }

    public void setGc_name(String gc_name) {
        this.gc_name = gc_name;
    }

    public String getGc_icon() {
        return gc_icon;
    }

    public void setGc_icon(String gc_icon) {
        this.gc_icon = gc_icon;
    }

    public List<?> getSub() {
        return sub;
    }

    public void setSub(List<?> sub) {
        this.sub = sub;
    }
}