package com.wokun.tysl.address;

import android.app.Activity;
import android.text.TextUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.classic.common.MultipleStatusView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shantoo.widget.toast.RxToast;
import com.wokun.tysl.TyslApp;
import com.wokun.tysl.address.bean.AddressBean;
import com.wokun.tysl.address.bean.AddressListResponse;
import com.wokun.tysl.callback.JsonCallback;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.model.response.BaseResponse;

public class AddressMgr {

    private AddressMgr(){

    }

    private static class AddressMgrHolder{
        private static AddressMgr instance = new AddressMgr();
    }

    public static AddressMgr getInstance(){
        return AddressMgrHolder.instance;
    }

    /**
     * 加载收货地址列表
     * @param adapter BaseQuickAdapter
     */
    public void loadAddressList(final BaseQuickAdapter adapter) {
        OkGo.<BaseResponse<AddressListResponse>>post(Constants.BASE_URL + Constants.ADDRESS_INDEX_URL)
                .execute(new JsonCallback<BaseResponse<AddressListResponse>>(Constants.WITH_TOKEN,Constants.ADDRESS_INDEX_URL) {
                    @Override
                    public void onSuccess(Response<BaseResponse<AddressListResponse>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;
                        if (body.isState()) {
                            AddressListResponse data = (AddressListResponse) body.getData();
                            adapter.setNewData(data.getAddress_list());
                        } else {
                            RxToast.showShort(body.getMsg());
                        }
                    }
                });
    }

    /**
     * 获取默认收货地址
     * @param contacts TextView
     * @param orderPlacerTel TextView
     * @param orderPlacerAddress TextView
     */
    public void getDefaultAddress(final TextView contacts, final TextView orderPlacerTel, final TextView orderPlacerAddress) {
        OkGo.<BaseResponse<AddressBean>>post(Constants.BASE_URL + Constants.ADDRESS_GET_DEFAULT_URL)
                .execute(new JsonCallback<BaseResponse<AddressBean>>(Constants.WITH_TOKEN,Constants.ADDRESS_GET_DEFAULT_URL) {
                    @Override
                    public void onSuccess(Response<BaseResponse<AddressBean>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;
                        if (body.isState()) {
                            AddressBean bean = (AddressBean) body.getData();
                            contacts.setText(bean.getContacts());
                            orderPlacerTel.setText(bean.getTel());
                            String province = bean.getProvince()==null?"":bean.getProvince();
                            String city = bean.getCity()==null?"":bean.getCity();
                            String district = bean.getDistrict()==null?"":bean.getDistrict();
                            String address = bean.getAddress()==null?"":bean.getAddress();
                            String str = province + city + district + address;
                            orderPlacerAddress.setText(str);
                        }
                    }
                });
    }

    /**
     * 设置默认收货地址
     * @param address AddressBean 地址
     */
    public void setDefaultAddress(final BaseQuickAdapter adapter, AddressBean address) {
        OkGo.<BaseResponse<Object>>post(Constants.BASE_URL + Constants.ADDRESS_SET_DEFAULT_URL)
                .tag(this)
                .params(Constants.ADDRESS_ID, address.getAddressId())
                .execute(new JsonCallback<BaseResponse<Object>>(Constants.WITH_TOKEN,Constants.ADDRESS_SET_DEFAULT_URL) {
                    @Override
                    public void onSuccess(Response<BaseResponse<Object>> response) {
                        BaseResponse body = response.body();
                        if(body==null)return;
                        RxToast.showShort(body.getMsg());
                        if (body.isState()) {
                            loadAddressList(adapter);
                        }
                    }
                });
    }

    /**
     * 删除收货地址
     * @param addressId 地址ID
     */
    public void deleteAddress(final BaseQuickAdapter adapter, String addressId) {
        OkGo.<BaseResponse<Object>>post(Constants.BASE_URL + Constants.ADDRESS_DELETE_ADDRESS_URL)
                .tag(this)
                .params(Constants.ADDRESS_ID, addressId)
                .execute(new JsonCallback<BaseResponse<Object>>(Constants.WITH_TOKEN,Constants.ADDRESS_DELETE_ADDRESS_URL) {
                    @Override
                    public void onSuccess(Response<BaseResponse<Object>> response) {
                        BaseResponse body = response.body();
                        if(body==null)return;
                        RxToast.showShort(body.getMsg());
                        if (body.isState()) {
                            loadAddressList(adapter);
                        }
                    }
                });
    }

    /**
     * 新增用戶地址
     *
     * @param contacts   联系人
     * @param mobile     电话
     * @param provinceId 省份ID
     * @param cityId     城市ID
     * @param districtId 区ID
     * @param address    详细地址
     */
    public void addAddress(final Activity activity, String contacts, String mobile, int provinceId, int cityId, int districtId, String address) {
        if (TextUtils.isEmpty(contacts) || TextUtils.isEmpty(mobile) || TextUtils.isEmpty(address) || provinceId == -1 || cityId == -1 || districtId == -1) {
            Toast.makeText(TyslApp.getContext(), "填写信息不完整", Toast.LENGTH_SHORT).show();
            return;
        }
        OkGo.<BaseResponse<Object>>post(Constants.BASE_URL + Constants.ADDRESS_ADD_ADDRESS_URL)
                .tag(this)
                .params(Constants.USER_TYPE, TyslApp.getInstance().getUser().getUserType())
                .params(Constants.CONTACTS, contacts)
                .params(Constants.TEL, mobile)
                .params(Constants.PROVINCE_ID, provinceId)
                .params(Constants.CITY_ID, cityId)
                .params(Constants.DISTRICT_ID, districtId)
                .params(Constants.ADDRESS, address)
                .execute(new JsonCallback<BaseResponse<Object>>(Constants.WITH_TOKEN,Constants.ADDRESS_ADD_ADDRESS_URL) {
                    @Override
                    public void onSuccess(Response<BaseResponse<Object>> response) {
                        BaseResponse body = response.body();
                        if(body==null)return;
                        RxToast.showShort(body.getMsg());
                        if (body.isState()) {
                            activity.finish();
                        }
                    }
                });
    }

    /**
     * 修改用戶地址
     * @param activity Activity
     * @param addressId 地址ID
     * @param contacts 联系人
     * @param mobile 电话
     * @param provinceId 省份ID
     * @param cityId 城市ID
     * @param districtId 区ID
     * @param address 详细地址
     */
    public void editAddress(final Activity activity, String addressId, String contacts, String mobile, int provinceId, int cityId, int districtId, String address) {
        if (TextUtils.isEmpty(addressId) || TextUtils.isEmpty(contacts) || TextUtils.isEmpty(mobile) || TextUtils.isEmpty(address) || provinceId == -1 || cityId == -1 || districtId == -1) {
            Toast.makeText(TyslApp.getContext(), "填写信息不完整", Toast.LENGTH_SHORT).show();
            return;
        }
        OkGo.<BaseResponse<Object>>post(Constants.BASE_URL + Constants.ADDRESS_ALTER_ADDRESS_URL)
                .tag(this)
                .params(Constants.USER_TYPE, TyslApp.getInstance().getUser().getUserType())
                .params(Constants.CONTACTS, contacts)
                .params(Constants.TEL, mobile)
                .params(Constants.PROVINCE_ID, provinceId)
                .params(Constants.CITY_ID, cityId)
                .params(Constants.DISTRICT_ID, districtId)
                .params(Constants.ADDRESS, address)
                .params(Constants.ADDRESS_ID, addressId)
                .execute(new JsonCallback<BaseResponse<Object>>(Constants.WITH_TOKEN,Constants.ADDRESS_ALTER_ADDRESS_URL) {
                    @Override
                    public void onSuccess(Response<BaseResponse<Object>> response) {
                        BaseResponse body = response.body();
                        if(body==null)return;
                        RxToast.showShort(body.getMsg());
                        if (body.isState()) {
                            activity.finish();
                        }
                    }
                });
    }
}