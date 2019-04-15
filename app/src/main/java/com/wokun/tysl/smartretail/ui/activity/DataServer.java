package com.wokun.tysl.smartretail.ui.activity;

import com.wokun.tysl.widget.shopcartdialog.Product;

import java.util.ArrayList;

public class DataServer {

    public static ArrayList<Product> getBreakfastData(){
        ArrayList<Product> list = new ArrayList<>();
        list.add(new Product("","面包",1.0,10));
        list.add(new Product("","蛋挞",1.0,10));
        list.add(new Product("","牛奶",1.0,10));
        list.add(new Product("","肠粉",1.0,10));
        list.add(new Product("","绿茶饼",1.0,10));
        list.add(new Product("","花卷",1.0,10));
        list.add(new Product("","包子",1.0,10));
        return list;
    }

    public static ArrayList<Product> getLaunchData(){
        ArrayList<Product> list = new ArrayList<>();
        list.add(new Product("","粥",1.0,10));
        list.add(new Product("","炒饭",1.0,10));
        list.add(new Product("","炒米粉",1.0,10));
        list.add(new Product("","炒粿条",1.0,10));
        list.add(new Product("","炒牛河",1.0,10));
        list.add(new Product("","炒菜",1.0,10));
        return list;
    }

    public static ArrayList<Product> getEveningData(){
        ArrayList<Product> list = new ArrayList<>();
        list.add(new Product("","淋菜",1.0,10));
        list.add(new Product("","川菜",1.0,10));
        list.add(new Product("","湘菜",1.0,10));
        list.add(new Product("","粤菜",1.0,10));
        list.add(new Product("","赣菜",1.0,10));
        list.add(new Product("","东北菜",1.0,10));
        return list;
    }

    public static ArrayList<Product> getMenuData(){
        ArrayList<Product> list = new ArrayList<>();
        list.add(new Product("","淋菜",1.0,10));
        list.add(new Product("","川菜",1.0,10));
        list.add(new Product("","湘菜",1.0,10));
        list.add(new Product("","粤菜",1.0,10));
        list.add(new Product("","赣菜",1.0,10));
        list.add(new Product("","东北菜",1.0,10));
        return list;
    }
}