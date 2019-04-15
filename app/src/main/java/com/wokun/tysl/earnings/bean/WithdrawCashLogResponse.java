package com.wokun.tysl.earnings.bean;


import com.wokun.tysl.model.bean.WithdrawCashLog;

import java.util.List;

public class WithdrawCashLogResponse {

    private List<WithdrawCashLog> withdrawList;

    public List<WithdrawCashLog> getWithdrawList() {
        return withdrawList;
    }

    public void setWithdrawList(List<WithdrawCashLog> withdrawList) {
        this.withdrawList = withdrawList;
    }
}
