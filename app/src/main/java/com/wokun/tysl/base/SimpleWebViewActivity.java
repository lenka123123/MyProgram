package com.wokun.tysl.base;

import com.shantoo.widget.toolbar.WidgetBar;

public class SimpleWebViewActivity extends BaseWebViewActivity {

    @Override
    public WidgetBar createToolBar() {
        return mWidgetBar.setWidgetBarTitle(getIntent().getStringExtra("title"));
    }

    @Override
    public void loadUrl() {
        mWebView.loadUrl(getIntent().getStringExtra("url"));
    }
}
