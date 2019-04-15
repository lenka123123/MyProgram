package com.wokun.tysl.other.ui;

import android.os.Handler;
import android.os.Message;

import com.alipay.sdk.app.PayTask;
import com.wokun.tysl.base.BaseBindingActivity;
import com.wokun.tysl.config.Constants;

import java.util.Map;

public abstract class PayActivity extends BaseBindingActivity {

    protected String pay_type = Constants.ALIPAY;
    protected static final int SDK_PAY_FLAG = 1;
    protected static final int SDK_AUTH_FLAG = 2;

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
                        Toast.makeText(PayActivity.this, "支付成功!", Toast.LENGTH_SHORT).show();
                        setResult(1);
                        finish();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(PayActivity.this, "支付失败!", Toast.LENGTH_SHORT).show();
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
                PayTask alipay = new PayTask(PayActivity.this);
                Map<String, String> result = alipay.payV2(body, true);
               // Log.i("msp", result.toString());
                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        }).start();
    }
}
