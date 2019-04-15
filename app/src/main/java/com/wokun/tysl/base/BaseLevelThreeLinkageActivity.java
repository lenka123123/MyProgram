package com.wokun.tysl.base;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;

import com.bigkoo.pickerview.OptionsPickerView;
import com.google.gson.reflect.TypeToken;
import com.shantoo.common.utils.JSONUtil;
import com.wokun.tysl.address.bean.CityBean;
import com.wokun.tysl.address.bean.DistrictBean;
import com.wokun.tysl.address.bean.ProvinceBean;
import com.wokun.tysl.model.cache.AppCache;

import java.lang.reflect.Type;
import java.util.ArrayList;

//实现三级联动效果的Activity
public abstract class BaseLevelThreeLinkageActivity extends BaseBindingActivity {

    private Thread thread;
    private ArrayList<ProvinceBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<CityBean>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<DistrictBean>>> options3Items = new ArrayList<>();
    private static final int MSG_LOAD_DATA = 0x0001;
    private static final int MSG_LOAD_SUCCESS = 0x0002;
    private static final int MSG_LOAD_FAILED = 0x0003;
    private boolean isLoaded = false;

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_LOAD_DATA:
                    if (thread == null) {//如果已创建就不再重新创建子线程了
                        thread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                // 写子线程中的操作,解析省市区数据
                                initJsonData();
                            }
                        });
                        thread.start();
                    }
                    break;
                case MSG_LOAD_SUCCESS:
                    isLoaded = true;
                    break;
                case MSG_LOAD_FAILED:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHandler.sendEmptyMessage(MSG_LOAD_DATA);
    }

    private int provinceCode;
    private int cityCode;
    private int districtCode;
    private String address;

    // 弹出选择器
    protected void showPickerView() {
        if (isLoaded) {
            OptionsPickerView pvOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
                @Override
                public void onOptionsSelect(int options1, int options2, int options3, View v) {
                    //返回的分别是三个级别的选中位置
                    ProvinceBean var1 = options1Items.get(options1);
                    CityBean var2 = options2Items.get(options1).get(options2);
                    DistrictBean var3 = options3Items.get(options1).get(options2).get(options3);
                    address = var1.getAreaName() +""+ var2.getAreaName() +""+ var3.getAreaName();
                    provinceCode = Integer.valueOf(var1.getAreaId());
                    cityCode = Integer.valueOf(var2.getAreaId());
                    districtCode = Integer.valueOf(var3.getAreaId());
                    onAddressSelectedListener.onAddressSelected(address, provinceCode, cityCode, districtCode);
                    //Toast.makeText(getApplication(), address, Toast.LENGTH_SHORT).show();
                }
            })
            .setTitleText("城市选择")
            .setDividerColor(Color.BLACK)
            .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
            .setContentTextSize(18)
            .build();

            pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
            pvOptions.show();
        }
    }

    private OnAddressSelectedListener onAddressSelectedListener;

    public interface OnAddressSelectedListener {
        void onAddressSelected(String address, int provinceCode, int cityCode, int districtCode);
    }

    public void setOnAddressSelectedListener(OnAddressSelectedListener onAddressSelectedListener) {
        this.onAddressSelectedListener = onAddressSelectedListener;
    }

    //解析数据
    private void initJsonData() {
        //获取assets目录下的json文件数据
        String json = AppCache.getAddress();

        if (TextUtils.isEmpty(json)) {
            json = JSONUtil.getJsonFromAssets("address.json");
            AppCache.saveAddress(json);
        }

        Type type = new TypeToken<ArrayList<ProvinceBean>>() {}.getType();
        ArrayList<ProvinceBean> provinces = JSONUtil.fromJson(json, type);
        options1Items = provinces;
        for (int i = 0; i < provinces.size(); i++) {//遍历省份
            //该省的城市列表（第二级）
            ArrayList<CityBean> cityList = new ArrayList<>();
            //该省的所有地区列表（第三极）
            ArrayList<ArrayList<DistrictBean>> provinceList = new ArrayList<>();
            for (int c = 0; c < provinces.get(i).getCitySub().size(); c++) {//遍历该省份的所有城市
                CityBean city = provinces.get(i).getCitySub().get(c);
                cityList.add(city);//添加城市

                ArrayList<DistrictBean> City_AreaList = new ArrayList<>();//该城市的所有地区列表

                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                if (provinces.get(i).getCitySub().get(c).getDistrictSub() == null
                        || provinces.get(i).getCitySub().get(c).getDistrictSub().size() == 0) {
                    City_AreaList.add(new DistrictBean("", ""));
                } else {
                    for (int d = 0; d < provinces.get(i).getCitySub().get(c).getDistrictSub().size(); d++) {//该城市对应地区所有数据
                        DistrictBean district = provinces.get(i).getCitySub().get(c).getDistrictSub().get(d);
                        City_AreaList.add(district);//添加该城市所有地区数据
                    }
                }
                provinceList.add(City_AreaList);//添加该省所有地区数据
            }
            //添加城市数据
            options2Items.add(cityList);
            //添加地区数据
            options3Items.add(provinceList);
        }
        mHandler.sendEmptyMessage(MSG_LOAD_SUCCESS);
    }
}
