package com.wokun.tysl.home.ui;

import android.content.Intent;
import android.webkit.WebChromeClient;
import android.webkit.WebViewClient;

import com.itheima.view.BridgeWebView;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.tysl.R;
import com.wokun.tysl.base.BaseBindingActivity;

import butterknife.BindString;
import butterknife.BindView;

/**
 * Created by Administrator on 2018/9/4 0004.
 */

public class HomeWebActivity extends BaseBindingActivity {
    @BindString(R.string.tysl_user_home_place) String title;
    @BindView(R.id.home_web)
    BridgeWebView home_web;
    @Override
    public int createView() {
        return R.layout.activity_home_web;
    }

    @Override
    public WidgetBar createToolBar() {
        return mWidgetBar.setWidgetBarTitle(title);
    }

    @Override
    public void init() {
        loadData();
    }

    private void loadData() {
        Intent intent = getIntent();
        String ids = intent.getStringExtra("jigouurl");
        home_web.loadUrl(ids);

        home_web.setWebViewClient(new WebViewClient());

        home_web.setWebChromeClient(new WebChromeClient());


    }
}
