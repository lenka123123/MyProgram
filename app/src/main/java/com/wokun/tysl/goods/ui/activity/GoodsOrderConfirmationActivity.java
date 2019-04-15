package com.wokun.tysl.goods.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shantoo.common.pay.WXPay;
import com.shantoo.common.utils.JSONUtil;
import com.shantoo.widget.imageview.SelectorImageView;
import com.shantoo.widget.toast.RxToast;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.tysl.R;
import com.wokun.tysl.TyslApp;
import com.wokun.tysl.address.bean.AddressBean;
import com.wokun.tysl.address.ui.AddressListActivity;
import com.wokun.tysl.base.BaseBindingActivity;
import com.wokun.tysl.callback.DialogCallback;
import com.wokun.tysl.callback.JsonCallback;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.goods.adapter.GoodsOrderConfirmationAdapter;
import com.wokun.tysl.goods.bean.CartInfoBean;
import com.wokun.tysl.goods.bean.GoodsBean;
import com.wokun.tysl.goods.bean.OrderShowBean;
import com.wokun.tysl.goods.response.CartGetFreightPriceResponse;
import com.wokun.tysl.model.bean.Alipay;
import com.wokun.tysl.model.bean.WXPayResult;
import com.wokun.tysl.model.response.BaseResponse;
import com.wokun.tysl.utils.DecimalUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;

//商品订单确认页面
public class GoodsOrderConfirmationActivity extends BaseBindingActivity {

    @BindString(R.string.tysl_order_confirmation)
    String title;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.tv_contacts)
    TextView tvContacts;
    @BindView(R.id.tv_order_placer_tel)
    TextView tvOrderPlacerTel;
    @BindView(R.id.tv_order_placer_address)
    TextView tvOrderPlacerAddress;

    @BindView(R.id.show_address)
    RelativeLayout hasAddress;
    @BindView(R.id.select_address)
    RelativeLayout selectAddress;

    @BindView(R.id.alipay_selector)
    SelectorImageView alipaySelectorImageView;
    @BindView(R.id.weixing_selector)
    SelectorImageView weixingSelectorImageView;
    @BindView(R.id.remark)
    EditText etRemark;
    @BindView(R.id.freight_total_price)
    TextView freightTotalPrice;
    @BindView(R.id.order_total)
    TextView orderTotal;
    @BindView(R.id.true_price)
    TextView truePrice;

    private String pay_type = ALIPAY;
    private final static String ALIPAY = "AliPay";
    private final static String WXPAY = "wxpay";
    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;
    private String name;
    private String mobile;
    private String sAddress;
    private int province_id;
    private String cart_id_str;
    private double pay_price;//实际支付金额
    private double old_freight_total_price; //运费总价
    private double new_freight_total_price; //运费总价
    private double order_total; //订单商品总价
    private String remark;
    private GoodsOrderConfirmationAdapter mAdapter;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    public int createView() {
        return R.layout.activity_goods_order_confirmation;
    }

    @Override
    public WidgetBar createToolBar() {
        return mWidgetBar.setWidgetBarTitle(title);
    }

    @Override
    public void init() {
        mAdapter = new GoodsOrderConfirmationAdapter(R.layout.item_goods_order, null);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);

        cart_id_str = getIntent().getStringExtra(Constants.CART_ID_STR);
        //   order_total = getIntent().getDoubleExtra("order_total",0.00d);
        //Toast.makeText(this, "orderTotal: " + order_total, Toast.LENGTH_SHORT).show();
        //加载数据
        loadData(cart_id_str);
    }

    private void loadData(String cart_id_str) {
        Log.e("订单", cart_id_str + "//%%" + getIntent().getDoubleExtra(Constants.ORDER_TOTAL, 0.00d));

        OkGo.<BaseResponse<OrderShowBean>>post(Constants.BASE_URL + Constants.CART_ORDER_SHOW_URL)
                .tag(this)
                .params(Constants.CART_ID_STR, cart_id_str)
                .params(Constants.ORDER_TOTAL, getIntent().getDoubleExtra(Constants.ORDER_TOTAL, 0.00d))
                .execute(new JsonCallback<BaseResponse<OrderShowBean>>(Constants.WITH_TOKEN, Constants.CART_ORDER_SHOW_URL) {
                    @Override
                    public void onSuccess(Response<BaseResponse<OrderShowBean>> response) {
                        BaseResponse body = response.body();
                        String s = body.toString();
                      //  RxToast.showShort();
                    Log.e("订单2",body.getMsg()+"");
                        if (body == null) return;
                        if (body.isState()) {
                            OrderShowBean orderShow = (OrderShowBean) body.getData();
                            List<CartInfoBean> list = orderShow.getCartInfo();
                            Log.e("订单3", orderShow.getCartInfo() + "**"+JSONUtil.toJSON(s));
                            List<GoodsBean> data = new ArrayList<>();
                            if (list != null && list.size() > 0) {
                                for (CartInfoBean cartInfoBean : list) {
                                    List<GoodsBean> goodsList = cartInfoBean.getGoodsList();
                                    if (goodsList != null && goodsList.size() > 0) {
                                        for (GoodsBean goods : goodsList) {
                                            goods.setStoreId(cartInfoBean.getStoreId());
                                            goods.setStoreName(cartInfoBean.getStoreName());
                                            data.add(goods);
                                        }
                                    }
                                    Log.e("订单4", data + "");
                                }
                            }
                            order_total = orderShow.getOrderTotal();
                            Log.e("订单5", order_total + "");
                            old_freight_total_price = orderShow.getFreightTotalPrice();
                            new_freight_total_price = old_freight_total_price;
                            pay_price = orderShow.getPayPrice();
                            Log.e("订单62", old_freight_total_price + "//" + orderShow.getFreightTotalPrice());
                            Log.e("订单62", pay_price + "//" + orderShow.getPayPrice());
                            mAdapter.setNewData(data);
                            orderTotal.setText("￥ " + order_total + "");
                            freightTotalPrice.setText("费用 ￥ " + old_freight_total_price + "");
                            truePrice.setText("￥ " + pay_price + "");

                            AddressBean address = orderShow.getDefaultAddress();
                            if (address == null) {
                                return;
                            }

                            if (address.getHave() == 0) {//没有收货地址
                                selectAddress.setVisibility(View.VISIBLE);
                                hasAddress.setVisibility(View.GONE);
                            } else {//有收货地址
                                selectAddress.setVisibility(View.GONE);
                                hasAddress.setVisibility(View.VISIBLE);
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

    private void setAddress(String contacts, String mobile, String address) {
        tvContacts.setText(contacts);
        tvOrderPlacerTel.setText(mobile);
        tvOrderPlacerAddress.setText(address);
    }

    @OnClick({R.id.action_alipay, R.id.action_wxpay, R.id.action_pay, R.id.select_address, R.id.show_address})
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
                    Log.e("点击进来了1", "进来了进来了");
                    pay(pay_type);
                } else if (WXPAY.equals(pay_type)) {
                    pay(pay_type);
                }
                break;
            case R.id.show_address://选择收货地址


            case R.id.select_address: //选择收货地址
                Intent intent = new Intent();
                intent.setClass(this, AddressListActivity.class);
                startActivityForResult(intent, 99);
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
            province_id = data.getIntExtra(Constants.PROVINCE_ID, 0);
            actionCartGetFreightPrice(cart_id_str, province_id);
            setAddress(name, mobile, sAddress);
            selectAddress.setVisibility(View.GONE);
            hasAddress.setVisibility(View.VISIBLE);
        }
    }

    //支付
    private void pay(String pay_type) {
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
        Log.e("支付宝数据", "name:"+name+"mobile"+mobile+"sAddress"+sAddress+"remark"+ etRemark.getText().toString().trim()
                +"order_total"+order_total+"freight_total_price"+new_freight_total_price+"pay_price"+pay_price
                +"cart_id_str"+cart_id_str+"province_id"+province_id);

        OkGo.<BaseResponse<Alipay>>post(Constants.BASE_URL + Constants.ORDER_SUBMIT_URL)
                .params("name", name)
                .params("mobile", mobile)
                .params("address", sAddress)
                .params("remark", etRemark.getText().toString().trim())
                .params("order_total", order_total)
                .params("freight_total_price", new_freight_total_price + "")
                .params("pay_price", pay_price + "")
                .params("pay_type", pay_type)
                .params("cart_id_str", cart_id_str)
                .params("province_id", province_id)
                .execute(new JsonCallback<BaseResponse<Alipay>>(Constants.WITH_TOKEN, Constants.ORDER_SUBMIT_URL) {
                    @Override
                    public void onSuccess(Response<BaseResponse<Alipay>> response) {
                        BaseResponse body = response.body();
                        if (body == null) return;
                        if (body.isState()) {
                            Alipay data = (Alipay) body.getData();
                            Log.e("支付宝进来了", "进来了w进来了"+data);
                            Log.e("支付宝进来了", "进来2了w进来了"+body.getMsg());
                            Log.e("支付宝进来了", "进来21了w进来了"+body);
                            if (data == null || data.getOrderString() == null) {
                                Log.e("支付宝进来了", data.toString()+"进来4了进来了"+data.getOrder_total()+data.getPay_price()+data.getFreight_total_price()+data.getoPayPrice());

                                return;
                            }
                            finish();
                            alipay(data.getOrderString());
                            Log.e("支付宝进来了", data.toString()+"进来5了进来了"+data.getOrder_total()+data.getPay_price()+data.getFreight_total_price()+data.getoPayPrice());

                        } else {
                            RxToast.showShort(body.getMsg());


                        }
                    }
                });
    }

    //微信支付
    private void doWxpay() {
        OkGo.<BaseResponse<WXPayResult>>post(Constants.BASE_URL + Constants.ORDER_SUBMIT_URL)
                .params("name", name)
                .params("mobile", mobile)
                .params("address", sAddress)
                .params("remark", etRemark.getText().toString().trim())
                .params("order_total", order_total)
                .params("freight_total_price", new_freight_total_price + "")
                .params("pay_price", pay_price + "")
                .params("pay_type", pay_type)
                .params("cart_id_str", cart_id_str)
                .params("province_id", province_id)
                .execute(new JsonCallback<BaseResponse<WXPayResult>>(Constants.WITH_TOKEN, Constants.ORDER_SUBMIT_URL) {
                    @Override
                    public void onSuccess(Response<BaseResponse<WXPayResult>> response) {
                        BaseResponse body = response.body();
                        if (body == null) return;
                        if (body.isState()) {
                            finish();
                            WXPayResult data = (WXPayResult) body.getData();
                            //在服务端签名
                            WXPayUtils.WXPayBuilder builder = new WXPayUtils.WXPayBuilder();
                       //     new WXPay.Builder()
                            builder .setAppId(data.getAppid())
                                    .setPartnerId(data.getPartnerid())
                                    .setPrepayId(data.getPrepayid())
                                    .setPackageValue(data.getPackageX())
                                    .setNonceStr(data.getNoncestr())
                                    .setTimeStamp(data.getTimestamp() + "")
                                    .setSign(data.getSign())
                                    .build()
                                    .toWXPayNotSign(GoodsOrderConfirmationActivity.this);
                        } else {
                            Toast.makeText(TyslApp.getContext(), body.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    protected Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    /*PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        Toast.makeText(TyslApp.getContext(), "支付成功!", Toast.LENGTH_SHORT).show();
                        setResult(1);
                        finish();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(TyslApp.getContext(), "支付失败!", Toast.LENGTH_SHORT).show();
                        finish();
                    }*/
                    break;
                }
            }
        }
    };

    protected void alipay(final String body) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                PayTask alipay = new PayTask(GoodsOrderConfirmationActivity.this);
                Map<String, String> result = alipay.payV2(body, true);
                // Log.i("msp", result.toString());
                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        }).start();
    }

    //通过地址计算运费
    public void actionCartGetFreightPrice(String cart_id_str, int province_id) {
        OkGo.<BaseResponse<CartGetFreightPriceResponse>>post(Constants.BASE_URL + Constants.CART_GET_FREIGHT_PRICE_URL)
                .tag(this)
                .params("cart_id_str", cart_id_str)
                .params("province_id", province_id)
                .execute(new DialogCallback<BaseResponse<CartGetFreightPriceResponse>>(this, Constants.WITH_TOKEN, Constants.CART_GET_FREIGHT_PRICE_URL) {
                    @Override
                    public void onSuccess(Response<BaseResponse<CartGetFreightPriceResponse>> response) {
                        BaseResponse body = response.body();
                        if (body == null) return;
                        if (body.isState()) {
                            CartGetFreightPriceResponse data = (CartGetFreightPriceResponse) body.getData();
                            if (data != null) {
                                double v1 = DecimalUtil.add(old_freight_total_price, data.getFreightTotalPrice());
                                freightTotalPrice.setText("费用 ￥ " + v1 + "");
                                new_freight_total_price = v1;
                                pay_price = v1 + order_total;
                                truePrice.setText("￥ " + pay_price + "");
                            }
                        }
                        RxToast.showShort(body.getMsg());

                    }
                });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("GoodsOrderConfirmation Page")
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
