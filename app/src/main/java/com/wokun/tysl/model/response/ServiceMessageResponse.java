package com.wokun.tysl.model.response;

import com.wokun.tysl.dietician.bean.ServiceNoticeBean;

import java.util.List;

public class ServiceMessageResponse {

    private List<ServiceNoticeBean> serviceList;

    public List<ServiceNoticeBean> getServiceList() {
        return serviceList;
    }

    public void setServiceList(List<ServiceNoticeBean> serviceList) {
        this.serviceList = serviceList;
    }
}
