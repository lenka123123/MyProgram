package com.wokun.tysl.model.bean;

public class RefundDetail {

    private String refund_amount;
    private String account;
    private String reason;
    private String create_time;
    private String cancle_time;
    private int state;
    private String tips;

    public String getRefund_amount() {
        return refund_amount;
    }

    public void setRefund_amount(String refund_amount) {
        this.refund_amount = refund_amount;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getCancle_time() {
        return cancle_time;
    }

    public void setCancle_time(String cancle_time) {
        this.cancle_time = cancle_time;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }
}
