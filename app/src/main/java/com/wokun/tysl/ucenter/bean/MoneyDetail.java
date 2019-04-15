package com.wokun.tysl.ucenter.bean;

import com.google.gson.annotations.SerializedName;

public class MoneyDetail {

    private int type;
    private String money;
    @SerializedName("create_time")
    private String createTime;
    @SerializedName("type_name")
    private String typeName;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getCreate_time() {
        return createTime;
    }

    public void setCreate_time(String createTime) {
        this.createTime = createTime;
    }

    public String getType_name() {
        return typeName;
    }

    public void setType_name(String typeName) {
        this.typeName = typeName;
    }
}
