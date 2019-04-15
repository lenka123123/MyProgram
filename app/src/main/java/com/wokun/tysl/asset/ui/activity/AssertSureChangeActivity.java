package com.wokun.tysl.asset.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.tysl.R;
import com.wokun.tysl.TyslApp;
import com.wokun.tysl.address.AddressMgr;
import com.wokun.tysl.address.bean.AddressBean;
import com.wokun.tysl.address.ui.AddressListActivity;
import com.wokun.tysl.asset.bean.ChangeBean;
import com.wokun.tysl.base.BaseBindingActivity;
import com.wokun.tysl.callback.JsonCallback;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.model.bean.User;
import com.wokun.tysl.model.response.BaseResponse;
import com.wokun.tysl.utils.ImageLoader;
import com.wokun.tysl.utils.SignUtil;
import com.wokun.tysl.zichanchange.bean.ZhihuanjilvBean;
import com.wokun.tysl.zichanchange.ui.MyzichanChange;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;

import static com.wokun.tysl.TyslApp.getContext;

/**
 * Created by Administrator on 2018/7/18/018.
 */

public class AssertSureChangeActivity  extends BaseBindingActivity {
    @BindString(R.string.tysl_user_surechange) String title;
    @BindView(R.id.sure_goods_name)
    TextView sure_goods_name;
    @BindView(R.id.zhihuan_price)
    TextView zhihuan_price;
    @BindView(R.id.sure_goods_img)
    ImageView sure_goods_img;
    @BindView(R.id.zhihuan_zichan)
    TextView zhihuan_zichan;

    @BindView(R.id.action_edit_personal)
    RelativeLayout action_edit_personal;

    @BindView(R.id.sure_name)
    TextView sure_name;
    @BindView(R.id.sure_number)
    TextView sure_number;
    @BindView(R.id.sure_place_name)
    TextView sure_place_name;
    @BindView(R.id.action_edit_nopersonal)
    RelativeLayout action_edit_nopersonal;
    @BindView(R.id.zhihuan_totalprice)
    TextView zhihuan_totalprice;
    @BindView(R.id.sure_change_btn)
    TextView sure_change_btn;
    @BindView(R.id.majia_msg)
    EditText majia_msg;



     private  String zhihuanid;
     private   String zhihuanprice;

    protected String name;
    protected String mobile;
    protected String sAddress;
    protected int province_id;

    @Override
    public int createView() {
        return  R.layout.activity_shejiao_surechange;
    }

    @Override
    public WidgetBar createToolBar() {
        return mWidgetBar.setWidgetBarTitle(title);
    }

    @Override
    public void init() {
        Bundle bundle = this.getIntent().getExtras();
          zhihuanprice = bundle.getString("zhihuanprice");
        String zhihuanimg=bundle.getString("zhihuanimg");
        String zhihuanname=bundle.getString("zhihuanname");
        zhihuanid=bundle.getString("zhihuanid");
        sure_goods_name.setText(zhihuanname);
        zhihuan_price.setText(zhihuanprice+"资产");
        zhihuan_zichan.setText(zhihuanprice+"资产");
        zhihuan_totalprice.setText(zhihuanprice);
        ImageLoader.loadImage(zhihuanimg,sure_goods_img);

            loadData();
    }

    private void loadData() {
        User user = TyslApp.getInstance().getUser();
        String url = Constants.BASE_URL + Constants.ADDRESS_GET_DEFAULT_URL;
        String id = user.getUserId();
        String token = user.getAccessToken();
        long time_stamp = System.currentTimeMillis();
        String sign = SignUtil.getSign(url, id, token, time_stamp);
        OkGo.<BaseResponse<AddressBean>>post(Constants.BASE_URL + Constants.ADDRESS_GET_DEFAULT_URL)//
                .tag(this)
                .params("user_id", user.getUserId())
                .execute(new JsonCallback<BaseResponse<AddressBean>>(Constants.WITH_TOKEN,Constants.ADDRESS_GET_DEFAULT_URL) {
                    @Override
                    public void onSuccess(Response<BaseResponse<AddressBean>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;

                        if(body.isState()){
                            AddressBean address = (AddressBean) body.getData();
                            if(address==null){return;}
                            if (address.getHave() == 0) {//没有收货地址
                          action_edit_nopersonal.setVisibility(View.VISIBLE);
                                action_edit_personal.setVisibility(View.GONE);
                            } else {//有收货地址
                              action_edit_nopersonal.setVisibility(View.GONE);
                                action_edit_personal.setVisibility(View.VISIBLE);
                                name = address.getContacts();
                                mobile = address.getTel();
                                province_id = address.getProvinceId();
                                String province = address.getProvince() == null ? "" : address.getProvince();
                                String city = address.getCity() == null ? "" : address.getCity();
                                String district = address.getDistrict() == null ? "" : address.getDistrict();
                                String a = address.getAddress() == null ? "" : address.getAddress();
                                  sAddress = province + city + district + a;
                                  sure_name.setText(name);
                                 sure_place_name.setText(sAddress);
                                  sure_number.setText(mobile);

                              //  setAddress(name, mobile, sAddress);
                            }
                        }
                    }
                });






    }


    /** 选择收货地址*/
    @OnClick(R.id.sure_choose_place)
    public  void  actionKuaijieSetting(){
        boolean isNeedLogin = TyslApp.getInstance().isLogin();
        Intent intent = new Intent();
        intent.setClass(getContext(), AddressListActivity.class);
        startActivityForResult(intent, 101, !isNeedLogin);

    }
    @OnClick(R.id.action_edit_personal)
    public  void  actionEditpersonSetting(){
        boolean isNeedLogin = TyslApp.getInstance().isLogin();
        Intent intent = new Intent();
        intent.setClass(getContext(), AddressListActivity.class);
        startActivityForResult(intent, 101, !isNeedLogin);

    }
//确认置换
    @OnClick(R.id.sure_change_btn)
    public  void  actionChangeBtn(){

        loadChangeData();

    }

    private void loadChangeData() {
      if(name.length()<=0) {
          Toast.makeText(AssertSureChangeActivity.this, "地址不能为空", Toast.LENGTH_SHORT).show();
          return;
      }
        String Ohermsg = majia_msg.getText().toString().trim();
        User user = TyslApp.getInstance().getUser();
        String url = Constants.BASE_URL + Constants.INTEGRAL_EXCHANGE_GOODS_URL;
        String id = user.getUserId();
        String token = user.getAccessToken();
        long time_stamp = System.currentTimeMillis();
        String sign = SignUtil.getSign(url, id, token, time_stamp);

        OkGo.<BaseResponse<ChangeBean>>post(Constants.BASE_URL + Constants.INTEGRAL_EXCHANGE_GOODS_URL)//
                .tag(this)
                .params("user_id", id)
                .params("goods_id",zhihuanid)
                .params("nums",1)
                .params("pay_integral",zhihuanprice)
                .params("name",name)
                .params("mobile",mobile)
                .params("address",sAddress)
                .params("remark",Ohermsg)
                .execute(new JsonCallback<BaseResponse<ChangeBean>>(Constants.WITH_TOKEN,Constants.INTEGRAL_EXCHANGE_GOODS_URL) {
                    @Override
                    public void onSuccess(Response<BaseResponse<ChangeBean>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;
                        Log.e("changemsg",body+"");
                        if(body.isState()){
                            Toast.makeText(AssertSureChangeActivity.this, body.getMsg(), Toast.LENGTH_SHORT).show();
                             if(body.getMsg().equals("兑换成功")){
                                  startActivity(MyzichanChange.class);
                             }


                        }


                    }
                });




    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 99 && resultCode == 20) {
            name = data.getStringExtra("contacts");
            mobile = data.getStringExtra("tel");
            sAddress = data.getStringExtra("address");
            province_id = data.getIntExtra("province_id", 0);
            sure_name.setText(name);
       sure_place_name.setText(sAddress);
         sure_number.setText(mobile);
           // setAddress(name, mobile, sAddress);
         action_edit_nopersonal.setVisibility(View.GONE);
            action_edit_personal.setVisibility(View.VISIBLE);
        }
    }
}
