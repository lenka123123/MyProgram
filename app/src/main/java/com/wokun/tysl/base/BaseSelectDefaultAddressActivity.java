package com.wokun.tysl.base;

import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.wokun.tysl.TyslApp;
import com.wokun.tysl.address.bean.AddressBean;
import com.wokun.tysl.callback.JsonCallback;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.model.bean.User;
import com.wokun.tysl.model.response.BaseResponse;
import com.wokun.tysl.utils.SignUtil;

public abstract class BaseSelectDefaultAddressActivity extends BaseBindingActivity {

    protected String name;
    protected String mobile;
    protected String sAddress;
    protected int province_id;

    public abstract RelativeLayout initSelectAddress();
    public abstract RelativeLayout initHasAddress();
    public abstract TextView initContacts();
    public abstract TextView initOrderPlacerTel();
    public abstract TextView initOrderPlacerAddress();

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 99 && resultCode == 20) {
            name = data.getStringExtra("contacts");
            mobile = data.getStringExtra("tel");
            sAddress = data.getStringExtra("address");
            province_id = data.getIntExtra("province_id", 0);

            setAddress(name, mobile, sAddress);
            initSelectAddress().setVisibility(View.GONE);
            initHasAddress().setVisibility(View.VISIBLE);
        }
    }

    private void setAddress(String contacts, String mobile, String address) {
        initContacts().setText(contacts);
        initOrderPlacerTel().setText(mobile);
        initOrderPlacerAddress().setText(address);
    }

    /**
     * 获取默认收货地址
     */
    public void getDefaultAddress() {
        User user = TyslApp.getInstance().getUser();
        String url = Constants.BASE_URL + Constants.ADDRESS_GET_DEFAULT_URL;
        String id = user.getUserId();
        String token = user.getAccessToken();
        long time_stamp = System.currentTimeMillis();
        String sign = SignUtil.getSign(url, id, token, time_stamp);

        /*Request request = ItheimaHttp.newPostRequest(Constants.ADDRESS_GET_DEFAULT_URL);
        request.putParams("user_id", id)
                .putParams("time_stamp", time_stamp)
                .putParams("sign", sign);

        ItheimaHttp.send(request, new BaseHttpResponseListener<BaseResponse<AddressBean>>() {
            @Override
            public void onResponse(BaseResponse<AddressBean> response) {
                if(response.isState()){
                    AddressBean address = response.getData();
                    if(address==null){return;}
                    if (address.getHave() == 0) {//没有收货地址
                        initSelectAddress().setVisibility(View.VISIBLE);
                        initHasAddress().setVisibility(View.GONE);
                    } else {//有收货地址
                        initSelectAddress().setVisibility(View.GONE);
                        initHasAddress().setVisibility(View.VISIBLE);
                        name = address.getContacts();
                        mobile = address.getTel();
                        province_id = address.getProvinceId();

                        String province = address.getProvince() == null ? "" : address.getProvince();
                        String city = address.getCity() == null ? "" : address.getCity();
                        String district = address.getDistrict() == null ? "" : address.getDistrict();
                        String a = address.getAddress() == null ? "" : address.getAddress();
                        sAddress = province + city + district + a;

                        setAddress(name, mobile, sAddress);
                    }
                }
            }
        });*/

        OkGo.<BaseResponse<AddressBean>>post(Constants.BASE_URL + Constants.ADDRESS_GET_DEFAULT_URL)//
                .tag(this)
                .params("id", getIntent().getStringExtra(Constants.SOURCE_ID))
                .execute(new JsonCallback<BaseResponse<AddressBean>>(Constants.WITH_TOKEN,Constants.ADDRESS_GET_DEFAULT_URL) {
                    @Override
                    public void onSuccess(Response<BaseResponse<AddressBean>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;

                        if(body.isState()){
                            AddressBean address = (AddressBean) body.getData();
                            if(address==null){return;}
                            if (address.getHave() == 0) {//没有收货地址
                                initSelectAddress().setVisibility(View.VISIBLE);
                                initHasAddress().setVisibility(View.GONE);
                            } else {//有收货地址
                                initSelectAddress().setVisibility(View.GONE);
                                initHasAddress().setVisibility(View.VISIBLE);
                                name = address.getContacts();
                                mobile = address.getTel();
                                province_id = address.getProvinceId();

                                String province = address.getProvince() == null ? "" : address.getProvince();
                                String city = address.getCity() == null ? "" : address.getCity();
                                String district = address.getDistrict() == null ? "" : address.getDistrict();
                                String a = address.getAddress() == null ? "" : address.getAddress();
                                sAddress = province + city + district + a;

                                setAddress(name, mobile, sAddress);
                            }
                        }
                    }
                });
    }
}
