package com.wokun.tysl.utils;

import android.content.Context;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationClientOption.AMapLocationMode;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.model.LatLng;

//import com.amap.api.maps.model.LatLng;


/**
 * 高德定位工具类
 *
 * @author Sfan
 */
public final class LocationUtils {

    private static LocationUtils instance = null;
    private Context mContext = null;
    // 定位相关
    private AMapLocationClient mlocationClient = null;
    private AMapLocationClientOption mLocationOption = null;
    private MyLocationListener mListener = null;
    private static AMapLocation myLocation = null;

    private LocationUtils() {
    }

    public static LocationUtils getInstance() {
        if (instance == null) {
            synchronized (LocationUtils.class) {
                instance = new LocationUtils();
            }
        }
        return instance;
    }

    /**
     * @return 定位成功
     */
    public static boolean locationSuccess() {
        return myLocation != null;
    }

    /**
     * @return 获取定位城市
     */
    public static String getCity() {

        String cityName = "";
        if (locationSuccess()) {
            cityName = myLocation.getCity();
            if (cityName != null && cityName.endsWith("市")) {
                cityName = cityName.substring(0, (cityName.lastIndexOf("市")));
            }
        }
        return cityName;
    }

    /**
     * @return 获取定位坐标
     */
   public static LatLng getLatLng() {
        double lat = 32.018188;
        double lon = 118.871688;
        if (locationSuccess()) {
            lat = myLocation.getLatitude();
            lon = myLocation.getLongitude();
        }
        return new LatLng(lat, lon);
    }

    /**
     * @return 获取定位地址
     */
    public static String getAddress() {
        String address = "";
        if (locationSuccess()) {
            address = myLocation.getAddress();
        }
        return address;
    }

    /**
     * 初始化
     */
    public void init(Context context) {
        mContext = context;
        mlocationClient = new AMapLocationClient(mContext);
    }

    /**
     * 开启定位
     *
     * @param interval     定位时间间隔
     * @param onceLocation 是否只定位一次
     * @param listener
     */
    public void start(long interval, boolean onceLocation, MyLocationListener listener) {
        if (listener != null) {
            // 设置定位回调监听
            mListener = listener;
        }
        mLocationOption = new AMapLocationClientOption();
        // 设置定位监听
        mlocationClient.setLocationListener(aMapLocationListener);
        // 设置为高精度定位模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationMode.Hight_Accuracy);
        // 定位时间间隔
        mLocationOption.setInterval(interval);
        // 是否返回地址信息，默认false
        mLocationOption.setNeedAddress(true);
        // 是否只定位一次，设置为false实时定位，默认false
        mLocationOption.setOnceLocation(onceLocation);
        // 设置定位参数
        mlocationClient.setLocationOption(mLocationOption);
        // 开启定位
        mlocationClient.startLocation();
    }

    /**
     * 重启定位 为了保证正常回调，使用start
     */
    @Deprecated
    public void reStart() {
        if (mlocationClient != null) {
            // 开启定位
            mlocationClient.startLocation();
        } else {
           // start(3000, false, mListener);
            start(0, false, mListener);
        }
    }

    /**
     * 停止定位
     */
    public void stop() {
        if (mlocationClient != null) {
            // 停止定位
            mlocationClient.stopLocation();
        }
//        if (myLocation != null && myLocation.getCity() != null) {
//            String cityName = myLocation.getCity();
//            if (cityName != null && cityName.endsWith("市")) {
//                cityName = cityName.substring(0, (cityName.lastIndexOf("市")));
//                SharedPreferencesUtil.setCityName(mContext, cityName);
//            } else if (cityName != null) {
//                SharedPreferencesUtil.setCityName(mContext, cityName);
//            }
//        }
    }

    /**
     * 终止定位
     */
    public void destroy() {
        if (mlocationClient != null) {
            // 停止定位
            mlocationClient.stopLocation();
            mlocationClient.onDestroy();
        }
        mListener = null;
    }

    private AMapLocationListener aMapLocationListener = new AMapLocationListener() {

        @Override
        public void onLocationChanged(AMapLocation location) {
            if (location != null && location.getErrorCode() == 0) {
                myLocation = location;
                if (mListener != null) {
                    mListener.onLocationChanged(myLocation);
                }
            } else {// 定位失败

            }
        }
    };

    public interface MyLocationListener {
        void onLocationChanged(AMapLocation location);
    }

    /**
     * @param distance 单位KM
     * @param lat1
     * @param lng1
     * @param angle    角度
     * @return
     */
/*
    public LatLng convertDistanceToLogLat(float distance, double lat1, double lng1, double angle) {
        double lon = lng1 + (distance * Math.sin(angle * Math.PI / 180)) / (111 * Math.cos(lat1 * Math.PI / 180));//将距离转换成经度的计算公式
        double lat = lat1 + (distance * Math.cos(angle * Math.PI / 180)) / 111;//将距离转换成纬度的计算公式
        return new LatLng(lat, lon);
    }
*/


}

