package com.wokun.tysl.model.response;

import java.io.Serializable;

public class SimpleResponse implements Serializable {

    private static final long serialVersionUID = -1477609349345966116L;

    private boolean state;
    private int stateCode;
    private String msg;

    public BaseResponse toBaseResponse() {
        BaseResponse response = new BaseResponse();
        response.setState(state);
        response.setStateCode(stateCode);
        response.setMsg(msg);
        return response;
    }
}
