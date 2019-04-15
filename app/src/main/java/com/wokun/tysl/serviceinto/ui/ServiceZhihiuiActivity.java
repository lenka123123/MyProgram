package com.wokun.tysl.serviceinto.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shantoo.widget.multiplephotoselector.OnMultiplePhotoUpLoadListener;
import com.shantoo.widget.toast.RxToast;
import com.shantoo.widget.toolbar.WidgetBar;
import com.shantoo.widget.view.OnPhotoUpLoadListener;
import com.wokun.tysl.R;
import com.wokun.tysl.address.bean.CityBean;
import com.wokun.tysl.address.bean.DistrictBean;
import com.wokun.tysl.address.bean.ProvinceBean;
import com.wokun.tysl.base.BaseBindingActivity;
import com.wokun.tysl.base.BaseLevelThreeLinkageActivity;
import com.wokun.tysl.callback.JsonCallback;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.model.response.BaseResponse;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;

import static com.wokun.tysl.TyslApp.getContext;
import static io.rong.imlib.statistics.UserData.name;

/**
 * Created by Administrator on 2018/7/10/010.
 */

public class ServiceZhihiuiActivity   extends BaseLevelThreeLinkageActivity
        implements BaseLevelThreeLinkageActivity.OnAddressSelectedListener,
        OnPhotoUpLoadListener, OnMultiplePhotoUpLoadListener {

    @BindString(R.string.tysl_edit_zhihui)String title;
    @BindView(R.id.action_submit)
    Button mSubmit; //提交
    private Dialog mDialog;
    private String area_info;
    private int province_id,city_id,district_id = 0;
      @BindView(R.id.tv_area) TextView tvArea;//所在区域

      @BindView(R.id.ruzhu_placename)
      EditText ruzhu_placename;//店铺
      @BindView(R.id.ruzhu_detailsplace)
      EditText ruzhu_detailsplace;//
      @BindView(R.id.ruzhu_name)
      EditText ruzhu_name;//
      @BindView(R.id.ruzhu_phone)
      EditText ruzhu_phone;//

      @BindView(R.id.ruzhu_pp)
      TextView ruzhu_pp;//




    @Override
    public int createView() {
        return R.layout.activity_service_zhihui;
    }

    @Override
    public WidgetBar createToolBar() {
        return mWidgetBar.setWidgetBarTitle(title);
    }

    @Override
    public void init() {
        setOnAddressSelectedListener(this);
    }

    /**服务入住*/
    @OnClick(R.id.action_submit)
    public void actionsubmit(View v) {
        comitMsg();
    }

    /**
     * 选择所在地区
     * */
    @OnClick(R.id.action_select_area)
    public void actionSelectArea(View v) {
        showPickerView();
    }





    private void comitMsg() {

        String mplacename = ruzhu_placename.getText().toString().trim();
        String mruzhu_detailsplace = ruzhu_detailsplace.getText().toString().trim();
        String mruzhu_name = ruzhu_name.getText().toString().trim();
        String mruzhu_phone = ruzhu_phone.getText().toString().trim();


        actionJoinService( mplacename, mruzhu_detailsplace, mruzhu_name,mruzhu_phone , province_id, city_id, district_id,area_info);




    }

    private void actionJoinService(String mplacename,String mruzhu_detailsplace,String mruzhu_name,String mruzhu_phone , int provinceId, int cityId, int districtId, String areaInfo) {
     Log.e("response22",mplacename+"##"+mruzhu_detailsplace+"##"+mruzhu_name+"##"+mruzhu_phone+"##"+provinceId+"##"+cityId+"##"+districtId+"##"+areaInfo);
        if(TextUtils.isEmpty(mplacename)){
            RxToast.showShort("店铺名未填写");
            return;
        }

        if(TextUtils.isEmpty(mruzhu_detailsplace)){
            RxToast.showShort("详细地址未填写");
            return;
        }

        if(TextUtils.isEmpty(mruzhu_name)){
            RxToast.showShort("姓名未填写");
            return;
        }

        if(TextUtils.isEmpty(mruzhu_phone)){
            RxToast.showShort("手机号未填写");
            return;
        }
        if(TextUtils.isEmpty(areaInfo) ){
            RxToast.showShort("请选择所在区域");
            return;
        }

        OkGo.<BaseResponse<Object>>post(Constants.BASE_URL + Constants.JOIN_RETAIL)
                .tag(this)
                .params("storename",mplacename)
                .params("name",mruzhu_name)
                .params("mobile",mruzhu_phone)
                .params("area",areaInfo)
                .params("address_detail",mruzhu_detailsplace)
                .execute(new JsonCallback<BaseResponse<Object>>() {
                    @Override
                    public void onSuccess(Response<BaseResponse<Object>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;
                        if(body.isState()){
                            RxToast.showShort(body.getMsg());
                            if("提交成功".equals(body.getMsg())){
                                showCustomizeDialog();
                            }


                        }
                    }

                    @Override
                    public void onError(Response<BaseResponse<Object>> response) {
                        super.onError(response);
                        Log.e("response2",response.body().toString());


                    }
                });
    }

    private void showCustomizeDialog() {
    /* @setView 装入自定义View ==> R.layout.dialog_customize
     * 由于dialog_customize.xml只放置了一个EditView，因此和图8一样
     * dialog_customize.xml可自定义更复杂的View
     */
        final AlertDialog.Builder customizeDialog = new AlertDialog.Builder(ServiceZhihiuiActivity.this);
        final View dialogView = LayoutInflater.from(ServiceZhihiuiActivity.this)
                .inflate(R.layout.dialog_delete,null);
        TextView del_sure = (TextView) dialogView.findViewById(R.id.del_sure);
        customizeDialog.setView(dialogView);
        final AlertDialog s = customizeDialog.show();
        del_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s.dismiss();
            }
        });

    }

    @Override
    public void onMultiplePhotoUpLoad(List<String> list) {

    }

    @Override
    public void onPhotoUpLoad(ImageView imageView, File file) {

    }

    @Override
    public void onAddressSelected(String address, int provinceCode, int cityCode, int districtCode) {

        area_info = address;
        province_id = provinceCode;
        city_id = cityCode;
        district_id = districtCode;
        tvArea.setText(address);


    }
}
