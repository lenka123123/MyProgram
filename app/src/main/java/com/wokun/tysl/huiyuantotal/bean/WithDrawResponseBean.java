package com.wokun.tysl.huiyuantotal.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2018\10\23 0023.
 */

public class WithDrawResponseBean implements Serializable{
    private  String  keti;
    private  String  mobile;
    private  int  have;
    private  WithDrawBean  default_bank;

    public String getKeti() {
        return keti;
    }

    public void setKeti(String keti) {
        this.keti = keti;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getHave() {
        return have;
    }

    public void setHave(int have) {
        this.have = have;
    }

    public WithDrawBean getDefault_bank() {
        return default_bank;
    }

    public void setDefault_bank(WithDrawBean default_bank) {
        this.default_bank = default_bank;
    }
}
