package com.wokun.tysl.model.cache;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shantoo.common.utils.JSONUtil;
import com.shantoo.common.utils.SPUtil;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.dietician.bean.DieticianInfoBean;
import com.wokun.tysl.home.bean.BannerBean;
import com.wokun.tysl.home.bean.NoticeBean;
import com.wokun.tysl.model.bean.User;
import com.wokun.tysl.model.bean.UserInfo;

import java.util.ArrayList;
import java.util.List;

public class AppCache {

    //公告缓存
    public static void saveNotice(@NonNull List<NoticeBean> list) {
        SPUtil.getInstance().putString(Constants.Notice, JSONUtil.toJSON(list));
    }

    //Banner缓存
    public static void saveBanner(@NonNull List<BannerBean> list) {
        SPUtil.getInstance().putString(Constants.BANNER, JSONUtil.toJSON(list));
    }

    //从缓存获取notice
    public static List<String> getNotice() {
        String json = SPUtil.getInstance().getString(Constants.Notice);
        List<NoticeBean> data = new Gson().fromJson(json, new TypeToken<List<NoticeBean>>() {
        }.getType());
        List<String> notices = new ArrayList<>();
        if (data != null) {
            for (NoticeBean notice : data) {
                notices.add(notice.getTitle());
            }
        }
        return notices;
    }

    //从缓存获取banner
    public static List<String> getBanner() {
        String json = SPUtil.getInstance().getString(Constants.BANNER);
        List<BannerBean> data = new Gson().fromJson(json, new TypeToken<List<BannerBean>>() {
        }.getType());
        List<String> banners = new ArrayList<>();
        if (data != null) {
            for (BannerBean banner : data) {
                banners.add(banner.getPicture());
            }
        }
        return banners;
    }

    public static void saveAddress(String address) {
        SPUtil.getInstance().putString(Constants.ADDRESS_JSON, address);
    }

    public static String getAddress(){
       return SPUtil.getInstance().getString(Constants.ADDRESS_JSON);
    }

    //用户登录状态缓存
    public static void setUser(User user){
        SPUtil.getInstance().putString(Constants.USER,JSONUtil.toJSON(user));
    }

    public static User getUser(){
        String json = SPUtil.getInstance().getString(Constants.USER);
        return JSONUtil.fromJson(json,User.class);
    }

    //融云Token信息缓存
    public static void setIMToken(String imToken) {
        SPUtil.getInstance().putString("imToken",imToken);
    }

    public static String getIMToken(){
        return SPUtil.getInstance().getString("imToken");
    }

    //普通用户登录信息缓存
    public static void setUserInfo(UserInfo userInfo){
        SPUtil.getInstance().putString("userInfo",JSONUtil.toJSON(userInfo));
    }

    public static UserInfo getUserInfo(){
        String json = SPUtil.getInstance().getString("userInfo");
        return JSONUtil.fromJson(json,UserInfo.class);
    }

    //营养师登录信息缓存
    public static void setDieticianInfo(DieticianInfoBean dieticianInfo){
        SPUtil.getInstance().putString("dieticianInfo",JSONUtil.toJSON(dieticianInfo));
    }

    public static DieticianInfoBean getDieticianInfo(){
        String json = SPUtil.getInstance().getString("dieticianInfo");
        return JSONUtil.fromJson(json,DieticianInfoBean.class);
    }

    //缓存主界面当前Tab位置
    public static int getTabPosition(){
        return SPUtil.getInstance().getInt(Constants.TAB_POSITION,-1);
    }

    public static void setTabPosition(int tabPosition){
        SPUtil.getInstance().putInt(Constants.TAB_POSITION,tabPosition);
    }

    public static void clearUser(){
        SPUtil.getInstance().remove("imToken");
        SPUtil.getInstance().remove("isLogin");
        SPUtil.getInstance().remove("user");
        SPUtil.getInstance().remove("userInfo");
        SPUtil.getInstance().remove("dieticianInfo");
    }

    public static void setLogin(boolean isLogin){
        SPUtil.getInstance().putBoolean("isLogin",isLogin);
    }

    public static boolean getLogin(){
        return SPUtil.getInstance().getBoolean("isLogin");
    }
}
