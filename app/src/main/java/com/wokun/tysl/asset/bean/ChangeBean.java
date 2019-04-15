package com.wokun.tysl.asset.bean;

/**
 * Created by Administrator on 2018/9/5 0005.
 */

public class ChangeBean {
    private   boolean state;

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    private  String stateCode;
            private  String msg;
            private  String data;



}
