package com.wokun.tysl.goods.ui.activity;

import android.content.Intent;
import android.webkit.WebChromeClient;
import android.webkit.WebViewClient;

import com.itheima.view.BridgeWebView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shantoo.widget.toast.RxToast;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.tysl.R;
import com.wokun.tysl.base.BaseBindingActivity;
import com.wokun.tysl.callback.JsonCallback;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.goods.bean.WuliuBean;
import com.wokun.tysl.model.response.BaseResponse;

import butterknife.BindView;

/**
 * Created by Administrator on 2018\12\11 0011.
 */

public class CheckWuliuActivity extends BaseBindingActivity {

    @BindView(R.id.bride_web)
    BridgeWebView brideWeb;
    @Override
    public int createView() {
        return R.layout.activity_checkwuliu;
    }

    @Override
    public WidgetBar createToolBar() {
        return mWidgetBar.setWidgetBarTitle("查看物流");
    }

    @Override
    public void init() {
        Intent intent = getIntent();
        String type = intent.getStringExtra("type");
        String number = intent.getStringExtra("number");

        OkGo.<BaseResponse<WuliuBean>>post(Constants.BASE_URL + Constants.INTEGRAL_EXPRESS_URL)
                .tag(this)
                .params("type", type)
                .params("number",number)
                .execute(new JsonCallback<BaseResponse<WuliuBean>>() {
                    @Override
                    public void onSuccess(Response<BaseResponse<WuliuBean>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;
                        RxToast.showShort(body.getMsg());
                        WuliuBean data = (WuliuBean) body.getData();
                        brideWeb.loadUrl( data.getKd_url());
                        brideWeb.setWebViewClient(new WebViewClient());
                        brideWeb.setWebChromeClient(new WebChromeClient());
                    }
                });


    }
}
