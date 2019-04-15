package com.wokun.tysl.asset.ui.activity;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.tysl.R;
import com.wokun.tysl.base.BaseWebViewActivity;
import com.wokun.tysl.callback.JsonCallback;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.model.bean.SingleParam;
import com.wokun.tysl.model.response.BaseResponse;

import butterknife.BindString;

/** 资产规则*/
public class AssetRulesActivity extends BaseWebViewActivity {

    @BindString(R.string.tysl_integration_rule)String title;

    @Override
    public WidgetBar createToolBar() {
        return mWidgetBar.setWidgetBarTitle(title);
    }

    @Override
    public void init() {
        super.init();
    }

    @Override
    public void loadUrl() {
        OkGo.<BaseResponse<SingleParam>>post(Constants.BASE_URL + Constants.INTEGRAL_RULE_URL)
                .tag(this)
                .execute(new JsonCallback<BaseResponse<SingleParam>>() {
                    @Override
                    public void onSuccess(Response<BaseResponse<SingleParam>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;
                        if (body.isState()) {
                            SingleParam data = (SingleParam) body.getData();
                            if(data==null){return;}
                            mWebView.loadUrl(data.getUrl());
                        }
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OkGo.getInstance().cancelTag(this);
    }
}