package com.wokun.tysl.mypersonmoney.bean;

/**
 * Created by Administrator on 2018/9/6 0006.
 */

public class MylastMoneyBean {
    private   String  id;
    private   String  source_type;
    private   String  integral;
    private   String  create_time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSource_type() {
        return source_type;
    }

    public void setSource_type(String source_type) {
        this.source_type = source_type;
    }

    public String getIntegral() {
        return integral;
    }

    public void setIntegral(String integral) {
        this.integral = integral;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }
}
