package com.wokun.tysl.model.response;

import java.util.List;

public class BaseListResponse<T> {

    private boolean state;
    private String stateCode;
    private String msg;
    private List<T> data;

    public void setState(boolean state) {
        this.state = state;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public boolean isState() {
        return state;
    }

    public String getStateCode() {
        return stateCode;
    }

    public String getMsg() {
        return msg;
    }

    public List<T> getData() {
        return data;
    }
}
