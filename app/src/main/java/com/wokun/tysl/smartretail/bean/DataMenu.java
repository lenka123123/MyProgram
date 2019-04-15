package com.wokun.tysl.smartretail.bean;

import com.wokun.tysl.widget.shopcartdialog.Product;

import java.util.ArrayList;

public class DataMenu {

    private String menuName;
    private ArrayList<Product> dishList;

    public DataMenu(){
    }

    public DataMenu(String menuName, ArrayList dishList){
        this.menuName = menuName;
        this.dishList = dishList;
    }

    public ArrayList<Product> getDishList() {
        return dishList;
    }

    public void setDishList(ArrayList<Product> dishList) {
        this.dishList = dishList;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }
}