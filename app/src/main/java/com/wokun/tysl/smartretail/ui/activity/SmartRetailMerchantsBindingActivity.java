package com.wokun.tysl.smartretail.ui.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shantoo.widget.toast.RxToast;
import com.shantoo.widget.toolbar.WidgetBar;
import com.shantoo.widget.view.OnPhotoUpLoadListener;
import com.shantoo.widget.view.PhotoSelector;
import com.wokun.tysl.R;
import com.wokun.tysl.base.BaseLevelThreeLinkageActivity;
import com.wokun.tysl.callback.DialogCallback;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.login.LoginMgr;
import com.wokun.tysl.model.bean.SingleParam;
import com.wokun.tysl.model.response.BaseResponse;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 智慧零售-商家绑定
 */

public class SmartRetailMerchantsBindingActivity extends BaseLevelThreeLinkageActivity implements BaseLevelThreeLinkageActivity.OnAddressSelectedListener {

    @BindView(R.id.et1)EditText et1;
    @BindView(R.id.et2)EditText et2;
    @BindView(R.id.et3)EditText et3;
    @BindView(R.id.et4)TextView et4;
    @BindView(R.id.et5)EditText et5;
    @BindView(R.id.et6)EditText et6;
    @BindView(R.id.iv_image_logo)ImageView ivImageLogo;

    private String url;
    private PhotoSelector mPhotoSelector;

    private int province_id,city_id,district_id;

    @Override
    public int createView() {
        return R.layout.activity_merchants_binding;
    }

    @Override
    public WidgetBar createToolBar() {
        return mWidgetBar.setWidgetBarTitle("商家绑定");
    }

    @Override
    public void init() {
        et1.setHint("请输入商家店铺名称");
        et2.setHint("请输入商家电话");
        et3.setHint("请输入验证码");
        et4.setHint("请选择商家店铺地址");
        et5.setHint("请输入详细地址");
        et6.setHint("请输入商家店铺联系人");
        mPhotoSelector = new PhotoSelector(this);
        mPhotoSelector.setOnPhotoUpLoadListener(new OnPhotoUpLoadListener() {
            @Override
            public void onPhotoUpLoad(ImageView photoImage, File photoFile) {
                actionUploadFile(photoFile);
            }
        });

        setOnAddressSelectedListener(this);
    }

    @OnClick(R.id.action_update_logo)
    public void action(View view) {
        mPhotoSelector.showView(ivImageLogo);
    }

    @OnClick(R.id.action_select_address)
    public void actionSelectAddress(View view){
        showPickerView();
    }

   /* @OnClick(R.id.et4_new)
    public void actionSelectAddress2(View view){
        showPickerView();
    }*/



    @OnClick(R.id.action_send_verify_code)
    public void actionSendVerifyCode(View view){
        String mobile = et2.getText().toString().trim();
        LoginMgr.getInstance().sendVerifyCode(mobile);
    }

    @OnClick(R.id.action_submit)
    public void actionSubmit(View view){
        final String storeCode = getIntent().getStringExtra(Constants.STORE_CODE);

        String storeName = et1.getText().toString().trim();
        String mobile = et2.getText().toString().trim();
        String smsCode = et3.getText().toString().trim();
        String address = et5.getText().toString().trim();
        String linkman = et6.getText().toString().trim();

        if(TextUtils.isEmpty(storeCode)){
            RxToast.showShort("店铺编码未找到");
            return;
        }
        if(TextUtils.isEmpty(url)){
            RxToast.showShort("请上传商家店铺LOGO");
            return;
        }
        if(TextUtils.isEmpty(storeName)){
            RxToast.showShort("请输入商家店铺名称");
            return;
        }
        if(TextUtils.isEmpty(linkman)){
            RxToast.showShort("请输入商家店铺联系人");
            return;
        }
        if(TextUtils.isEmpty(mobile)){
            RxToast.showShort("请输入商家电话");
            return;
        }
        if(TextUtils.isEmpty(smsCode)){
            RxToast.showShort("请输入验证码");
            return;
        }
        if(province_id==0 || city_id==0 || district_id==0){
            RxToast.showShort("请选择商家店铺地址");
            return;
        }
        if(TextUtils.isEmpty(address)){
            RxToast.showShort("请输入详细地址");
            return;
        }

        OkGo.<BaseResponse<Object>>post(Constants.BASE_URL + Constants.RETAIL_STORE_BIND_URL)
                .tag(this)
                .params(Constants.STORE_CODE, storeCode)
                .params(Constants.Store_Name, storeName)
                .params(Constants.Store_Picture, url)
                .params(Constants.LinkMan, linkman)
                .params(Constants.MOBILE, mobile)
                .params(Constants.SMS_CODE, smsCode)
                .params(Constants.ADDRESS, address)
                .params(Constants.CITY_ID, city_id)
                .params(Constants.DISTRICT_ID, district_id)
                .params(Constants.PROVINCE_ID, province_id)
                .execute(new DialogCallback<BaseResponse<Object>>(this,Constants.WITH_TOKEN,Constants.RETAIL_STORE_BIND_URL) {
                    @Override
                    public void onSuccess(Response<BaseResponse<Object>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;
                        if (body.isState()) {
                            RxToast.showShort(body.getMsg());
                            Intent intent = new Intent();
                            intent.putExtra(Constants.STORE_CODE,storeCode);
                            intent.setClass(SmartRetailMerchantsBindingActivity.this,MechantAccountMangermentNewActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                });
    }

    /**
     * 单图片上传
     * @param file 图片文件
     * */
    public void actionUploadFile(File file){
        OkGo.<BaseResponse<SingleParam>>post(Constants.BASE_URL + Constants.OTHER_UPLOAD_FILE_SINGLE_URL)
                .tag(this)
                .params(Constants.TYPE,"retail")
                .params(Constants.UPLOAD_FILE, file)
                .execute(new DialogCallback<BaseResponse<SingleParam>>(this,Constants.WITH_TOKEN,Constants.OTHER_UPLOAD_FILE_SINGLE_URL) {

                    @Override
                    public void onSuccess(Response<BaseResponse<SingleParam>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;
                        if(body.isState()){
                            SingleParam data = (SingleParam) body.getData();
                            url = data.getUrl();
                            RxToast.showShort("商家店铺LOGO上传成功");
                        }
                    }
                    @Override
                    public void onError(Response<BaseResponse<SingleParam>> response) {
                        super.onError(response);
                        RxToast.showShort("商家店铺LOGO上传失败");
                    }
                });
    }

    @Override
    public void onAddressSelected(String address, int provinceCode, int cityCode, int districtCode) {
        this.province_id = provinceCode;
        this.city_id = cityCode;
        this.district_id = districtCode;
        et4.setText(address);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mPhotoSelector.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OkGo.getInstance().cancelTag(this);
    }
}