package com.wokun.tysl.serviceplace;

/**
 * Created by Administrator on 2018/8/22 0022.
 * F服务机构数据
 */

public class ServicePlaceDataBean {

    public String tilte;//标题
    public String content;//内容
    public String image;//图片

    public ServicePlaceDataBean(String tilte, String content, String image) {
        this.tilte=tilte;
        this.content=content;
        this.image=image;

    }
}
