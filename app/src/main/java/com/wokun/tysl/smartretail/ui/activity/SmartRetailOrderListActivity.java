package com.wokun.tysl.smartretail.ui.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.shantoo.common.pay.WXPay;
import com.shantoo.common.utils.JSONUtil;
import com.shantoo.widget.imageview.SelectorImageView;
import com.shantoo.widget.recyclerview.MItemDecoration;
import com.shantoo.widget.toast.RxToast;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.tysl.R;
import com.wokun.tysl.TyslApp;
import com.wokun.tysl.address.ui.AddressListActivity;
import com.wokun.tysl.base.BaseBindingActivity;
import com.wokun.tysl.base.BaseRefreshAndLoadMoreActivity;
import com.wokun.tysl.base.SimpleRefreshAndLoadMoreActivity;
import com.wokun.tysl.callback.JsonCallback;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.goods.ui.activity.GoodsOrderConfirmationActivity;
import com.wokun.tysl.goods.ui.activity.PayResult;
import com.wokun.tysl.goods.ui.activity.WXPayUtils;
import com.wokun.tysl.goods.ui.activity.ZhihuiSuccessfulActivity;
import com.wokun.tysl.model.bean.Alipay;
import com.wokun.tysl.model.bean.Alipay2;
import com.wokun.tysl.model.bean.WXPayResult;
import com.wokun.tysl.model.response.BaseResponse;
import com.wokun.tysl.smartretail.adapter.OrderListAdapter;
import com.wokun.tysl.smartretail.bean.GoodsDataBean;
import com.wokun.tysl.smartretail.bean.GoodsSetteleBean;
import com.wokun.tysl.smartretail.bean.OrderListBean;
import com.wokun.tysl.smartretail.bean.ShouhuoBean;
import com.wokun.tysl.smartretail.bean.SureGoodsListResponse;
import com.wokun.tysl.smartretail.bean.ZitiBean;
import com.wokun.tysl.widget.shopcartdialog.PopupDishAdapter;
import com.wokun.tysl.widget.shopcartdialog.ShopCart;
import com.wokun.tysl.widget.shopcartdialog.ShopCartImp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

import static com.wokun.tysl.TyslApp.getContext;

/**
 * 智慧零售-订单确认
 */

public class SmartRetailOrderListActivity extends BaseBindingActivity {
    private ShopCart mShopCart;
    @BindView(R.id.true_price)
    TextView totalPriceTextView;
    @BindView(R.id.tv_contacts4)
    TextView tv_contacts4;
    @BindView(R.id.tv_order_placer_tel4)
    TextView tv_order_placer_tel4;
    @BindView(R.id.tv_order_placer_address4)
    TextView tv_order_placer_address4;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.select_address)
    RelativeLayout selectAddress;
    @BindView(R.id.show_address)
    RelativeLayout hasAddress;
    @BindView(R.id.often_place)
    LinearLayout oftenPlace;
    @BindView(R.id.tv_contacts)
    TextView tvContacts;
    @BindView(R.id.tv_order_placer_tel)
    TextView tvOrderPlacerTel;
    @BindView(R.id.tv_order_placer_address)
    TextView tvOrderPlacerAddress;
    @BindView(R.id.zhihui_goods_price)
    TextView zhihui_goods_price;


    @BindView(R.id.zitidian)
    LinearLayout zitidian;

    @BindView(R.id.alipay_selector)
    SelectorImageView alipaySelectorImageView;
    @BindView(R.id.weixing_selector)
    SelectorImageView weixingSelectorImageView;
    @BindView(R.id.oftenplace_selector)
    SelectorImageView oftenplace_selector;
    @BindView(R.id.zitiplace_selector)
    SelectorImageView zitiplace_selector;
    private String name;
    private String mobile;
    private String sAddress;
    private String province_id;

    String store_code;
    private String pay_type = ALIPAY;
    private final static String ALIPAY = "AliPay";
    private final static String WXPAY = "wxpay";
    private double shoppingTotalPrice = 0;
    private String totalgid = "";

    private int self_lifting = BUZITI;
    private final static int ZITI = 1;
    private final static int BUZITI = 0;


    private static final int SDK_PAY_FLAG = 1;
    private String address_id;
    private ZitiBean selfLifting;
     private     String goodsjson;
    private String order_id;
      private String buy_gid;

    @Override
    public int createView() {
        return R.layout.activity_zhihui_jiesuan;
    }

    @Override
    public WidgetBar createToolBar() {
        return mWidgetBar.setWidgetBarTitle("确认订单");
    }


    @Override
    public void init() {

        GoodsDataBean bean = new GoodsDataBean();
        Intent intent = getIntent();
        store_code = intent.getStringExtra(Constants.STORE_CODE);
        //上页传递过来的数据
        ArrayList<GoodsSetteleBean> data = (ArrayList<GoodsSetteleBean>) getIntent().getSerializableExtra("data");
        Log.e("传递过来的数据大小", data.size() + "");

        //String s = JSONUtil.toJSON(data);
        JSONArray jsonArray = new JSONArray();
        try {
            for (int i = 0; i < data.size(); i++) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("gid", data.get(i).getBean().getGid());
                jsonObject.put("price", data.get(i).getBean().getGoodsPrice());
                jsonObject.put("goodsname", data.get(i).getBean().getGoodsName());
                jsonObject.put("goodsimage", data.get(i).getBean().getGoodsPicture());
                jsonObject.put("number", data.get(i).getGoodsNumber());
                jsonArray.put(jsonObject).toString();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
     goodsjson = jsonArray.toString();//json这个


        mShopCart = new ShopCart();
        if (data != null && data.size() > 0) {
            for (int i = 0; i < data.size(); i++) {
                mShopCart.addGoodsSettle(data.get(i));
                String goodsPrice = data.get(i).getBean().getGoodsPrice();
                int goodsNumber = data.get(i).getGoodsNumber();
                shoppingTotalPrice = shoppingTotalPrice + Double.valueOf(goodsPrice) * goodsNumber;

                String gid = data.get(i).getBean().getGid();
                if (totalgid.isEmpty()) {
                    totalgid = gid;
                } else {
                    totalgid = totalgid + "," + gid;
                }

            }
        } else {
            RxToast.showShort("没有数据传过来");
        }
        Log.e("totalgid大小", totalgid + "");
        PopupDishAdapter dishAdapter = new PopupDishAdapter(this, mShopCart);
        dishAdapter.setInVisable(false);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new MItemDecoration(this));
        mRecyclerView.setAdapter(dishAdapter);
        zhihui_goods_price.setText("￥ " + shoppingTotalPrice);
        showTotalPrice();
        loadData();

    }

    private void showTotalPrice() {
        if (mShopCart != null && shoppingTotalPrice > 0) {
            totalPriceTextView.setVisibility(View.VISIBLE);
            totalPriceTextView.setText("￥ " + shoppingTotalPrice);
         /*   totalPriceNumTextView.setVisibility(View.VISIBLE);
            totalPriceNumTextView.setText(""+ mShopCart.getShoppingAccount()+"");*/
        } else {
            totalPriceTextView.setVisibility(View.GONE);
            //   totalPriceNumTextView.setVisibility(View.GONE);
        }
    }

    private void loadData() {
        OkGo.<BaseResponse<SureGoodsListResponse>>post(Constants.BASE_URL + Constants.ORDER_CONFIRM)
                .tag(this).params(Constants.STORE_CODE, store_code)
                .execute(new JsonCallback<BaseResponse<SureGoodsListResponse>>(Constants.WITH_TOKEN, Constants.ORDER_CONFIRM) {
                    @Override
                    public void onSuccess(Response<BaseResponse<SureGoodsListResponse>> response) {
                        BaseResponse body = response.body();
                        Log.e("订单确认", "订单确认2");
                        if (body == null) return;
                        if (body.isState()) {
                            SureGoodsListResponse data = (SureGoodsListResponse) body.getData();
                            Log.e("订单确认", "" + data);
                            Log.e("订单确认", "订单确认1");
                            if (data == null) {
                                return;
                            }
                            ShouhuoBean defaultAddress = data.getDefaultAddress();
                            int haveSelfLifting = data.getHaveSelfLifting();
                            if (haveSelfLifting == 1) {//有自提点
                                selfLifting = data.getSelfLifting();
                                if (selfLifting == null) {
                                    return;
                                }
                                tv_contacts4.setText(selfLifting.getStore_name());
                                tv_order_placer_tel4.setText(selfLifting.getMobile());
                                tv_order_placer_address4.setText(selfLifting.getAddress());
                            } else {//没有自提点
                                zitidian.setVisibility(View.GONE);
                            }
                            if (defaultAddress == null) {
                                return;
                            }
                            if (defaultAddress.getHave() == 0) {//没有收货地址
                                selectAddress.setVisibility(View.VISIBLE);
                                hasAddress.setVisibility(View.GONE);
                                oftenPlace.setVisibility(View.GONE);
                            } else {//有收货地址
                                selectAddress.setVisibility(View.GONE);
                                hasAddress.setVisibility(View.VISIBLE);
                                oftenPlace.setVisibility(View.VISIBLE);
                                name = defaultAddress.getContacts();
                                address_id = defaultAddress.getAddress_id();
                                mobile = defaultAddress.getTel();
                                province_id = defaultAddress.getProvince_id();
                                String province = defaultAddress.getProvince() == null ? "" : defaultAddress.getProvince();
                                String city = defaultAddress.getCity() == null ? "" : defaultAddress.getCity();
                                String district = defaultAddress.getDistrict() == null ? "" : defaultAddress.getDistrict();
                                String a = defaultAddress.getAddress() == null ? "" : defaultAddress.getAddress();
                                sAddress = province + city + district + a;
                                setAddress(name, mobile, sAddress);
                            }

                        }
                    }
                });

    }

    private void setAddress(String contacts, String mobile, String address) {
        tvContacts.setText(contacts);
        tvOrderPlacerTel.setText(mobile);
        tvOrderPlacerAddress.setText(address);
    }

    @OnClick({R.id.action_alipay, R.id.action_wxpay, R.id.action_pay, R.id.select_address, R.id.show_address, R.id.zitidian, R.id.iv_go1})
    public void action(View v) {
        switch (v.getId()) {
            case R.id.action_alipay: //支付宝支付
                alipaySelectorImageView.toggle(true);
                weixingSelectorImageView.toggle(false);
                pay_type = ALIPAY;
                break;
            case R.id.action_wxpay: //微信支付
                alipaySelectorImageView.toggle(false);
                weixingSelectorImageView.toggle(true);
                pay_type = WXPAY;
                break;
            case R.id.action_pay: //确认支付
                if (ALIPAY.equals(pay_type)) {
                    pay(pay_type);
                } else if (WXPAY.equals(pay_type)) {
                    pay(pay_type);
                }
                break;
            case R.id.show_address://选择收货地址


            case R.id.select_address: //选择收货地址
                oftenplace_selector.toggle(true);
                zitiplace_selector.toggle(false);
                Log.e("点击了3", "点击了3");
                self_lifting = BUZITI;

                break;
            case R.id.zitidian: //

                oftenplace_selector.toggle(false);
                zitiplace_selector.toggle(true);
                self_lifting = ZITI;
                Log.e("点击了2", "点击了2");
                break;
            case R.id.iv_go1: //
                Intent intent = new Intent();
                intent.setClass(this, AddressListActivity.class);
                startActivityForResult(intent, 99);
                Log.e("点击了1", "点击了1");
                break;
        }






    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 99 && resultCode == 20) {
            //Toast.makeText(this, "返回", Toast.LENGTH_SHORT).show();
            name = data.getStringExtra(Constants.CONTACTS);
            mobile = data.getStringExtra(Constants.TEL);
            sAddress = data.getStringExtra(Constants.ADDRESS);
            province_id = data.getStringExtra(Constants.PROVINCE_ID);
            //   actionCartGetFreightPrice(cart_id_str, province_id);
            setAddress(name, mobile, sAddress);
            selectAddress.setVisibility(View.GONE);
            hasAddress.setVisibility(View.VISIBLE);
        }
    }


    //支付
    private void pay(String pay_type) {


        Log.e("支付商品列表","pay_type:"+pay_type+"/gid:"+ totalgid+" /self_lifting:"+
                self_lifting +"/addressId:"+address_id +"/store_name:"+
                selfLifting.getStore_name() +"/mobile:"+ selfLifting.getMobile() +"/addressDetail:"+ sAddress
                +"/store_code"+ store_code+"/totalPrice:"+shoppingTotalPrice+"/goodsData:"+goodsjson);

        if (!TyslApp.getInstance().isLogin()) {
            Toast.makeText(this, "亲，您还未登录", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(mobile) || TextUtils.isEmpty(sAddress)) {
            Toast.makeText(this, "请选择收货地址", Toast.LENGTH_SHORT).show();
            return;
        }
        if (ALIPAY.equals(pay_type)) {
            doAlipay();
        } else if (WXPAY.equals(pay_type)) {
            doWxpay();
        }
    }

    //支付宝支付
    private void doAlipay() {

        Log.e("支付商品列表","pay_type:"+pay_type+"/gid:"+ totalgid+"/self_lifting:"+
                self_lifting +"/addressId:"+address_id +"/store_name:"+
                        selfLifting.getStore_name() +"/mobile:"+ selfLifting.getMobile() +"/addressDetail:"+ sAddress
                        +"/store_code"+ store_code+"/totalPrice:"+shoppingTotalPrice+"/goodsData:"+goodsjson);
        OkGo.<BaseResponse<Alipay2>>post(Constants.BASE_URL + Constants.RETAILPAYORDER)
                .params("pay_type", pay_type)
                .params("gid", totalgid)
                .params("self_lifting", self_lifting)
                .params("addressId", address_id)
                .params("store_name", selfLifting.getStore_name())
                .params("mobile", selfLifting.getMobile())
                .params("addressDetail", sAddress)
                .params("store_code", store_code)
                .params("totalPrice", shoppingTotalPrice)
                .params("goodsData", goodsjson)
                .execute(new JsonCallback<BaseResponse<Alipay2>>(Constants.WITH_TOKEN, Constants.RETAILPAYORDER) {
                    @Override
                    public void onSuccess(Response<BaseResponse<Alipay2>> response) {
                        BaseResponse body = response.body();
                        if (body == null) return;
                        if (body.isState()) {
                            Alipay2 data = (Alipay2) body.getData();
                            if (data == null || data.getOrderString() == null) {
                                return;
                            }
                            finish();
                            alipay(data.getOrderString());
                          buy_gid = data.getBuy_gid();
                            order_id = data.getOrder_id();
                            Log.e("进来了4","进来了4"+buy_gid);

                        } else {
                            RxToast.showShort(body.getMsg());
                        }
                    }
                });
    }

    //微信支付
    private void doWxpay() {
        OkGo.<BaseResponse<WXPayResult>>post(Constants.BASE_URL + Constants.RETAILPAYORDER)
                .params("pay_type", pay_type)
                .params("gid", totalgid)
                .params("self_lifting", self_lifting)
                .params("addressId", address_id)
                .params("store_name", selfLifting.getStore_name())
                .params("mobile", selfLifting.getMobile())
                .params("addressDetail", sAddress)
                .params("store_code", store_code)
                .params("totalPrice", shoppingTotalPrice)
                .params("goodsData", goodsjson)
                .execute(new JsonCallback<BaseResponse<WXPayResult>>(Constants.WITH_TOKEN, Constants.RETAILPAYORDER) {
                    @Override
                    public void onSuccess(Response<BaseResponse<WXPayResult>> response) {
                        BaseResponse body = response.body();
                        if (body == null) return;
                        if (body.isState()) {
                            finish();
                            WXPayResult data = (WXPayResult) body.getData();
                            //在服务端签名

                            WXPayUtils.WXPayBuilder builder = new WXPayUtils.WXPayBuilder();
                          //  new WXPay.Builder()
                            builder .setAppId(data.getAppid())
                                    .setPartnerId(data.getPartnerid())
                                    .setPrepayId(data.getPrepayid())
                                    .setPackageValue(data.getPackageX())
                                    .setNonceStr(data.getNoncestr())
                                    .setTimeStamp(data.getTimestamp() + "")
                                    .setSign(data.getSign())
                                    .build()
                                    .toWXPayNotSign(SmartRetailOrderListActivity.this);
                                    //.pay(TyslApp.getContext(), data.getAppid());


                        } else {
                            Toast.makeText(TyslApp.getContext(), body.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    protected void alipay(final String body) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                PayTask alipay = new PayTask(SmartRetailOrderListActivity.this);
                Map<String, String> result = alipay.payV2(body, true);
                Log.e("进来了5","进来了5");
                // Log.i("msp", result.toString());
                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        }).start();
    }


    protected Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    Log.e("进来了6","进来了6");
                  PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        Log.e("进来了7","进来了7");
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        Toast.makeText(TyslApp.getContext(), "支付成功!", Toast.LENGTH_SHORT).show();
                        setResult(1);
                        finish();
                        Intent intent = new Intent(SmartRetailOrderListActivity.this, ZhihuiSuccessfulActivity.class);
                        intent.putExtra("order_id",order_id);
                        intent.putExtra("buy_gid",buy_gid);
                        startActivity(intent);
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(TyslApp.getContext(), "支付失败!", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    break;
                }
            }
        }
    };


}