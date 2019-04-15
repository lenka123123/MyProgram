package com.wokun.tysl.base;

import com.shantoo.widget.toolbar.WidgetBar;
import com.shantoo.widget.view.ProgressWebView;
import com.wokun.tysl.R;

import butterknife.BindColor;
import butterknife.BindView;

public abstract class BaseWebViewActivity extends BaseBindingActivity {

    @BindColor(R.color.colorPrimary)int color;

    @BindView(R.id.bridge_web_view)

    public ProgressWebView mWebView;

    public abstract WidgetBar createToolBar();

    @Override
    public int createView() {
        return R.layout.layout_webview;
    }

    @Override
    public void init() {
        mWebView.setTopLoadingColor(color);
        loadUrl();
    }

    public abstract void loadUrl();

    private void initWebControl() {
        //此方法可以在WebView中打开链接而不会跳转到外部浏览器
        //webView.setWebViewClient(new Client());
        //WebSettings webSettings = webView.getSettings();
        //webSettings.setBuiltInZoomControls(true);
        //webSettings.setDefaultFontSize(8);
        //webSettings.setJavaScriptEnabled(true);
        //webSettings.setDisplayZoomControls(false);
        //webSettings.setSupportZoom(true);
        //webSettings.setDefaultZoom(WebSettings.ZoomDensity.FAR);
        //自适应屏幕
        //webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        //webSettings.setLoadWithOverviewMode(true);
        //webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        //webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        //webSettings.setDefaultTextEncodingName("UTF-8");
        //webSettings.setUseWideViewPort(true);
        //webSettings.setAppCacheEnabled(true);
        //webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
    }

    /*class Client extends WebViewClient{
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (!url.startsWith("http") & !url.startsWith("https")) {
                return false;
            } else {
                view.loadUrl(url);
                return true;
            }
        }

        @Override
        public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
            if (url.startsWith("http") || url.startsWith("https")) {
                return super.shouldInterceptRequest(view, url);
            } else {
                Intent in = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(in);
                return null;
            }
        }
    }*/
}
