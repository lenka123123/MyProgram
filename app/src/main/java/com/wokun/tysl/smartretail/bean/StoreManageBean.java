package com.wokun.tysl.smartretail.bean;

public class StoreManageBean {

    private int storeNo;
    private String amountAvailable;
    private String cumulativeIncome;
    private int todayTurnover;
    private int todayProfit;
    private  String storeName;
    private   String  storePicture;
    private  int  role_type;

    public int getRole_type() {
        return role_type;
    }

    public void setRole_type(int role_type) {
        this.role_type = role_type;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStorePicture() {
        return storePicture;
    }

    public void setStorePicture(String storePicture) {
        this.storePicture = storePicture;
    }

    public int getStoreNo() {
        return storeNo;
    }

    public void setStoreNo(int storeNo) {
        this.storeNo = storeNo;
    }

    public String getAmountAvailable() {
        return amountAvailable;
    }

    public void setAmountAvailable(String amountAvailable) {
        this.amountAvailable = amountAvailable;
    }

    public String getCumulativeIncome() {
        return cumulativeIncome;
    }

    public void setCumulativeIncome(String cumulativeIncome) {
        this.cumulativeIncome = cumulativeIncome;
    }

    public int getTodayTurnover() {
        return todayTurnover;
    }

    public void setTodayTurnover(int todayTurnover) {
        this.todayTurnover = todayTurnover;
    }

    public int getTodayProfit() {
        return todayProfit;
    }

    public void setTodayProfit(int todayProfit) {
        this.todayProfit = todayProfit;
    }
}
