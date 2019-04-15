package com.wokun.tysl.model.response;

import java.io.Serializable;

public class BaseResponse<T> implements Serializable {

    private boolean state;
    private int stateCode;
    private String msg;
    private T data;

    public T getData() {
        return data;
    }

    public boolean isState() {
        return state;
    }

    public int getStateCode() {
        return stateCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public void setStateCode(int stateCode) {
        this.stateCode = stateCode;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(T data) {
        this.data = data;
    }
}
