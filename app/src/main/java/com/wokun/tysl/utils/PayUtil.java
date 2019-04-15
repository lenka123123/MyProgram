package com.wokun.tysl.utils;


import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shantoo.common.pay.AliPay;
import com.shantoo.common.pay.PayResultCallback;
import com.shantoo.common.pay.WXPay;
import com.shantoo.widget.toast.RxToast;
import com.wokun.tysl.TyslApp;
import com.wokun.tysl.base.BaseBindingActivity;
import com.wokun.tysl.callback.JsonCallback;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.goods.ui.activity.WXPayUtils;
import com.wokun.tysl.login.ui.LoginActivity;
import com.wokun.tysl.model.bean.Alipay;
import com.wokun.tysl.model.bean.WXPayResult;
import com.wokun.tysl.model.response.BaseResponse;

public class PayUtil {

    /**
     * 微信支付
     * */
    public static void wxpay(final BaseBindingActivity activity, String orderNumber, String payType) {
        if (!TyslApp.getInstance().isLogin()) {
            activity.startActivity(LoginActivity.class);
            return;
        }
        OkGo.<BaseResponse<WXPayResult>>post(Constants.BASE_URL + Constants.PAYMENT_BUY_SERVICE_URL)
                .tag(activity)
                .params(Constants.ORDER_NUMBER,orderNumber)
                .params(Constants.PAY_TYPE, payType)
                .execute(new JsonCallback<BaseResponse<WXPayResult>>(Constants.WITH_TOKEN,Constants.PAYMENT_BUY_SERVICE_URL) {
                    @Override
                    public void onSuccess(Response<BaseResponse<WXPayResult>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;

                        if (body.isState()) {
                            WXPayResult data = (WXPayResult) body.getData();
                            if(data==null){return;}
                            //在服务端签名
                       //     new WXPay.Builder()
                            WXPayUtils.WXPayBuilder builder = new WXPayUtils.WXPayBuilder();
                            builder  .setAppId(data.getAppid())
                                    .setPartnerId(data.getPartnerid())
                                    .setPrepayId(data.getPrepayid())
                                    .setPackageValue(data.getPackageX())
                                    .setNonceStr(data.getNoncestr())
                                    .setTimeStamp(data.getTimestamp() + "")
                                    .setSign(data.getSign())
                                    .build()
                                    .toWXPayNotSign(activity);
                                   // .pay(TyslApp.getContext(), data.getAppid());
                        }
                    }
                });
    }

    /**
     * 支付宝支付
     * */
    public static void alipay(final BaseBindingActivity activity, String orderNumber, String payType) {
        if (!TyslApp.getInstance().isLogin()) {
            activity.startActivity(LoginActivity.class);
            return;
        }
        OkGo.<BaseResponse<Alipay>>post(Constants.BASE_URL + Constants.PAYMENT_BUY_SERVICE_URL)
                .tag(activity)
                .params(Constants.ORDER_NUMBER,orderNumber)
                .params(Constants.PAY_TYPE, payType)
                .execute(new JsonCallback<BaseResponse<Alipay>>(Constants.WITH_TOKEN,Constants.PAYMENT_BUY_SERVICE_URL) {
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
}