package com.wokun.tysl.widget.shopcartdialog;

import android.util.Log;

import com.wokun.tysl.smartretail.bean.GoodsDataBean;
import com.wokun.tysl.smartretail.bean.GoodsSetteleBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ShopCart {

    private int shoppingAccount;//商品总数
    private double shoppingTotalPrice;//商品总价钱
    private Map<GoodsDataBean,Integer> shoppingSingle;//单个物品的总价价钱

    public ShopCart(){
        this.shoppingAccount = 0;
        this.shoppingTotalPrice = 0;
        this.shoppingSingle = new HashMap<>();
    }

    public int getShoppingAccount() {
        return shoppingAccount;
    }

    public double getShoppingTotalPrice() {
        return shoppingTotalPrice;
    }

    public Map<GoodsDataBean, Integer> getShoppingSingleMap() {
        return shoppingSingle;
    }

    public void addGoodsSettle(GoodsSetteleBean bean){
        shoppingSingle.put(bean.getBean(),bean.getGoodsNumber());
    }

    public boolean addShoppingSingle(GoodsDataBean dish){
        int remain = dish.getProductRemain();
        if(remain<=0)
            return false;
        dish.setProductRemain(--remain);
        int num = 0;
        if(shoppingSingle.containsKey(dish)){
            num = shoppingSingle.get(dish);
        }
        num+=1;
        shoppingSingle.put(dish,num);
        Log.e("TAG", "addShoppingSingle: "+shoppingSingle.get(dish));

        shoppingTotalPrice += Double.valueOf(dish.getGoodsPrice());
        shoppingAccount++;
        return true;
    }

    public boolean subShoppingSingle(GoodsDataBean dish){
        int num = 0;
        if(shoppingSingle.containsKey(dish)){
            num = shoppingSingle.get(dish);
        }
        if(num<=0) return false;
        num--;
        int remain = dish.getProductRemain();
        dish.setProductRemain(++remain);
        shoppingSingle.put(dish,num);
        if (num ==0) shoppingSingle.remove(dish);

        shoppingTotalPrice -= Double.valueOf(dish.getGoodsPrice());
        shoppingAccount--;
        return true;
    }

    public int getDishAccount() {
        return shoppingSingle.size();
    }

    public void clear(){
        this.shoppingAccount = 0;
        this.shoppingTotalPrice = 0;
        this.shoppingSingle.clear();
    }
}