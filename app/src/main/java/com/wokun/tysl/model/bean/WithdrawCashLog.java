package com.wokun.tysl.model.bean;

import com.google.gson.annotations.SerializedName;

public class WithdrawCashLog {

    @SerializedName("account_type_name")
    private String accountTypeName;

    @SerializedName("account_code")
    private String accountCode;

    @SerializedName("apply_time")
    private String applyTime;

    @SerializedName("withdraw_money")
    private String withdrawMoney;//提现金额

    @SerializedName("withdraw_state")
    private String withdrawState;

    @SerializedName("account_type")
    private int accountType;

    public String getAccountTypeName() {
        return accountTypeName;
    }

    public void setAccountTypeName(String accountTypeName) {
        this.accountTypeName = accountTypeName;
    }

    public String getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

    public String getWithdrawMoney() {
        return withdrawMoney;
    }

    public void setWithdrawMoney(String withdrawMoney) {
        this.withdrawMoney = withdrawMoney;
    }

    public String getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(String applyTime) {
        this.applyTime = applyTime;
    }

    public String getWithdrawState() {
        return withdrawState;
    }

    public void setWithdrawState(String withdrawState) {
        this.withdrawState = withdrawState;
    }

    public int getAccountType() {
        return accountType;
    }

    public void setAccountType(int accountType) {
        this.accountType = accountType;
    }
}
