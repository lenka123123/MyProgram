package com.wokun.tysl.model.response;


import com.wokun.tysl.earnings.bean.Bank;

import java.util.List;

public class GetBankResponse {

    private List<Bank> bankList;

    public List<Bank> getBankList() {
        return bankList;
    }

    public void setBankList(List<Bank> bankList) {
        this.bankList = bankList;
    }
}
