package com.wokun.tysl.ucenter.ui;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shantoo.common.pay.AliPay;
import com.shantoo.common.pay.PayResultCallback;
import com.shantoo.common.pay.WXPay;
import com.shantoo.widget.imageview.SelectorImageView;
import com.shantoo.widget.toast.RxToast;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.tysl.R;
import com.wokun.tysl.TyslApp;
import com.wokun.tysl.base.BaseBindingActivity;
import com.wokun.tysl.callback.JsonCallback;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.goods.ui.activity.WXPayUtils;
import com.wokun.tysl.model.bean.Alipay;
import com.wokun.tysl.model.bean.WXPayResult;
import com.wokun.tysl.model.response.BaseResponse;

import butterknife.BindView;
import butterknife.OnClick;

public class PayActivity extends BaseBindingActivity {

    @BindView(R.id.et_pay_number)
    EditText etPayNumber;

    @BindView(R.id.alipay_selector)
    SelectorImageView alipaySelectorImageView;
    @BindView(R.id.weixing_selector)
    SelectorImageView weixingSelectorImageView;

    private String pay_type = Constants.ALIPAY;

    @Override
    public int createView() {
        return R.layout.activity_pay;
    }

    @Override
    public WidgetBar createToolBar() {
        return mWidgetBar
                .setWidgetBarTitle("余额充值")
                .setMenu("记录", null)
                .setOnMenuClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(MoneyDetailActivity.class);
                    }
                }, null);
    }

    @Override
    public void init() {

    }

    @OnClick(R.id.action_alipay)
    public void action_alipay() {
        alipaySelectorImageView.toggle(true);
        weixingSelectorImageView.toggle(false);
        pay_type = Constants.ALIPAY;
    }

    @OnClick(R.id.action_wxpay)
    public void action_wxpay() {
        alipaySelectorImageView.toggle(false);
        weixingSelectorImageView.toggle(true);
        pay_type = Constants.WXPAY;
    }

    @OnClick(R.id.action_pay)
    public void actionPay() {
        String money = etPayNumber.getText().toString().trim();
        if (TextUtils.isEmpty(money)) {
            RxToast.showShort("请输入充值金额");
            return;
        }
        if (Double.valueOf(money) <= 0) {
            RxToast.showShort("充值金额必须大于0");
            return;
        }

        if (Double.valueOf(money) > 200000) {  //容错待定
            RxToast.showShort("充值金额超过限额");
            return;
        }

        if (Constants.ALIPAY.equals(pay_type)) {
            alipay(this, money, pay_type);
        } else if (Constants.WXPAY.equals(pay_type)) {
            wxpay(this, money, pay_type);
        }
    }

    /**
     * 微信支付
     */
    public void wxpay(BaseBindingActivity activity, String money, String payType) {
        OkGo.<BaseResponse<WXPayResult>>post(Constants.BASE_URL + Constants.UCENTER_RECHARGE_URL)
                .tag(activity)
                .params(Constants.TYPE, payType)
                .params("money", money)
                .execute(new JsonCallback<BaseResponse<WXPayResult>>(Constants.WITH_TOKEN, Constants.UCENTER_RECHARGE_URL) {
                    @Override
                    public void onSuccess(Response<BaseResponse<WXPayResult>> response) {
                        BaseResponse body = response.body();
                        if (body == null) return;

                        if (body.isState()) {
                            WXPayResult data = (WXPayResult) body.getData();
                            if (data == null) {
                                return;
                            }
                            //在服务端签名
                            WXPayUtils.WXPayBuilder builder = new WXPayUtils.WXPayBuilder();
                            //    new WXPay.Builder()
                            builder.setAppId(data.getAppid())
                                    .setPartnerId(data.getPartnerid())
                                    .setPrepayId(data.getPrepayid())
                                    .setPackageValue(data.getPackageX())
                                    .setNonceStr(data.getNoncestr())
                                    .setTimeStamp(data.getTimestamp() + "")
                                    .setSign(data.getSign())
                                    .build()
                                    .toWXPayNotSign(PayActivity.this);
                            //.pay(TyslApp.getContext(), data.getAppid());
                        }
                    }
                });
    }

    /**
     * 支付宝支付
     */
    public static void alipay(final BaseBindingActivity activity, String money, String payType) {
        OkGo.<BaseResponse<Alipay>>post(Constants.BASE_URL + Constants.UCENTER_RECHARGE_URL)
                .tag(activity)
                .params("type", payType)
                .params("money", money)
                .execute(new JsonCallback<BaseResponse<Alipay>>(Constants.WITH_TOKEN, Constants.UCENTER_RECHARGE_URL) {
                    @Override
                    public void onSuccess(Response<BaseResponse<Alipay>> response) {
                        BaseResponse body = response.body();
                        if (body == null) return;

                        if (body.isState()) {
                            Alipay data = (Alipay) body.getData();
                            if (data == null) {
                                return;
                            }
                            AliPay.getInstance().pay(activity, data.getOrderString(),
                                    new PayResultCallback() {
                                        @Override
                                        public void onPaySuccess(String result, String message) {
                                            RxToast.showShort(message);
                                            //Toast.makeText(activity,"支付成功!",Toast.LENGTH_SHORT).show();
                                            //activity.finish();
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