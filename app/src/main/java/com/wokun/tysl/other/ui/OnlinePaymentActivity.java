package com.wokun.tysl.other.ui;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shantoo.common.pay.AliPay;
import com.shantoo.common.pay.PayResultCallback;
import com.shantoo.common.pay.WXPay;
import com.shantoo.widget.toast.RxToast;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.tysl.R;
import com.wokun.tysl.TyslApp;
import com.wokun.tysl.callback.JsonCallback;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.goods.ui.activity.WXPayUtils;
import com.wokun.tysl.model.bean.Alipay;
import com.wokun.tysl.model.bean.WXPayResult;
import com.wokun.tysl.model.response.BaseResponse;

import butterknife.BindView;
import butterknife.OnClick;

//在线支付
public class OnlinePaymentActivity extends PayActivity {

    @BindView(R.id.toolbar) WidgetBar widgetBar;

    @BindView(R.id.tv_true_price)TextView tvTruePrice;
    private String order_id;
    private String pay_price;

    private String online_payment_url;

    @Override
    public int createView() {
        return R.layout.activity_online_payment;
    }

    @Override
    public WidgetBar createToolBar() {
        return widgetBar.setWidgetBarTitle("在线支付");
    }

    @Override
    public void init() {
        order_id = getIntent().getStringExtra(Constants.ORDER_ID);
        pay_price = getIntent().getStringExtra(Constants.PAY_PRICE);
        online_payment_url = getIntent().getStringExtra(Constants.ONLINE_PAYMENT_URL);
        tvTruePrice.setText(pay_price);
    }

    @OnClick({R.id.action_alipay, R.id.action_wxpay})
    public void action(View v) {
        if (R.id.action_alipay == v.getId()) {
            pay(Constants.ALIPAY);
        } else if (R.id.action_wxpay == v.getId()) {
            pay(Constants.WXPAY);
        }
    }

    private void pay(String pay_type) {
        if (!TyslApp.getInstance().isLogin()) {
            Toast.makeText(this, "亲，您还未登录", Toast.LENGTH_SHORT).show();
            return;
        }
        if (Constants.ALIPAY.equals(pay_type)) {
            doAlipay(this);
        } else if (Constants.WXPAY.equals(pay_type)) {
            doWxpay(this);
        }
    }

    //支付宝支付
    private void doAlipay(final Activity activity) {
        OkGo.<BaseResponse<Alipay>>post(Constants.BASE_URL + online_payment_url)
                .tag(activity)
                .params("order_id", order_id)
                .params("do_id", order_id)
                .params("pay_price", pay_price)
                .params("pay_type", pay_type)
                .execute(new JsonCallback<BaseResponse<Alipay>>(Constants.WITH_TOKEN,online_payment_url) {
                    @Override
                    public void onSuccess(Response<BaseResponse<Alipay>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;

                        if (body.isState()) {
                            Alipay data = (Alipay) body.getData();
                            if(data == null ){return;}
                            AliPay.getInstance()
                                    .pay(activity, data.getOrderString(),
                                            new PayResultCallback() {
                                                @Override
                                                public void onPaySuccess(String result, String message) {
                                                    Toast.makeText(activity,"支付成功!",Toast.LENGTH_SHORT).show();
                                                    activity.finish();
                                                }

                                                @Override
                                                public void onPayFail(String result, String message) {
                                                    RxToast.showShort(message);
                                                }
                                            });
                        }
                    }
                });
    }

    //微信支付
    private void doWxpay(final Activity activity) {
        OkGo.<BaseResponse<WXPayResult>>post(Constants.BASE_URL + online_payment_url)
                .tag(activity)
                .params("order_id", order_id)
                .params("do_id", order_id)
                .params("pay_price", pay_price)
                .params("pay_type", pay_type)
                .execute(new JsonCallback<BaseResponse<WXPayResult>>(Constants.WITH_TOKEN,online_payment_url) {
                    @Override
                    public void onSuccess(Response<BaseResponse<WXPayResult>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;

                        if (body.isState()) {
                            WXPayResult data = (WXPayResult) body.getData();
                            //在服务端签名
                            WXPayUtils.WXPayBuilder builder = new WXPayUtils.WXPayBuilder();
                        //    new WXPay.Builder()
                            builder .setAppId(data.getAppid())
                                    .setPartnerId(data.getPartnerid())
                                    .setPrepayId(data.getPrepayid())
                                    .setPackageValue(data.getPackageX())
                                    .setNonceStr(data.getNoncestr())
                                    .setTimeStamp(data.getTimestamp() + "")
                                    .setSign(data.getSign())
                                    .build()
                                    .toWXPayNotSign(OnlinePaymentActivity.this);
                                    //.pay(activity, data.getAppid());
                        }
                    }
                });
    }
}
