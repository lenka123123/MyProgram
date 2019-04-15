package com.wokun.tysl.mypersonmoney.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/9/6 0006.
 */

public class CashResponse {


  private List<CashBean> moneyList;

    public List<CashBean> getMoneyList() {
        return moneyList;
    }

    public void setMoneyList(List<CashBean> moneyList) {
        this.moneyList = moneyList;
    }
}
