package com.wokun.tysl.widget.shopcartdialog;

public class Product {

    private String productImage;//图片
    private String productName;//名称
    private double productPrice;//价格
    private int productAmount;//总计
    private int productRemain;//剩余

    public Product(String productImage,String productName, double productPrice, int productAmount){
        this.productImage = productImage;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productAmount = productAmount;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getDishName() {
        return productName;
    }

    public void setDishName(String dishName) {
        this.productName = dishName;
    }

    public double getDishPrice() {
        return productPrice;
    }

    public void setDishPrice(double dishPrice) {
        this.productPrice = dishPrice;
    }

    public int getDishAmount() {
        return productAmount;
    }

    public void setDishAmount(int dishAmount) {
        this.productAmount = dishAmount;
    }

    public int getDishRemain() {
        return productRemain;
    }

    public void setDishRemain(int dishRemain) {
        this.productRemain = dishRemain;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj==this)return true;

        return obj instanceof Product
                && this.productName.equals(((Product)obj).productName)
                && this.productPrice == ((Product)obj).productPrice
                && this.productAmount == ((Product)obj).productAmount
                && this.productRemain == ((Product)obj).productRemain;
    }
}