package com.wokun.tysl.asset.ui.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.itheima.view.BridgeWebView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shantoo.widget.toast.RxToast;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.tysl.R;
import com.wokun.tysl.base.BaseBindingActivity;
import com.wokun.tysl.callback.JsonCallback;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.model.bean.DsytAddmoneyBean;
import com.wokun.tysl.model.bean.SingleParam;
import com.wokun.tysl.model.response.BaseResponse;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018\11\23 0023.
 */

public class AssertDsytAddmoney extends BaseBindingActivity{
  @BindView(R.id.bridge_web_view)
  WebView bridgeWebView;
    @Override
    public int createView() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        return R.layout.activity_dsyt_addmoney;
    }

    @Override
    public WidgetBar createToolBar() {
        mWidgetBar.setVisibility(View.GONE);
        return mWidgetBar;
    }

    @Override
    public void init() {
         loadData();
          WebSettings webSettings = bridgeWebView.getSettings();
        //设置WebView属性，能够执行Javascript脚本
        webSettings.setJavaScriptEnabled(true);
        //设置支持js交互点击
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        //设置可以访问文件
        webSettings.setAllowFileAccess(true);
        //设置支持缩放
        webSettings.setBuiltInZoomControls(true);
        bridgeWebView.setBackgroundColor(0); // 设置背景色
      //  bridgeWebView.getBackground().setAlpha(2); // 设置填充透明度 范围：0-255
        bridgeWebView.setVisibility(View.VISIBLE);
        //加载需要显示的网页
     //   bridgeWebView.loadUrl(urls);
        // 设置支持Js,必须设置的,不然网页基本上不能看
        bridgeWebView.getSettings().setJavaScriptEnabled(true);
        // 设置缓存模式,我这里使用的默认,不做多讲解
        bridgeWebView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        // 设置为true表示支持使用js打开新的窗口
//        webview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        // 大部分网页需要自己保存一些数据,这个时候就的设置下面这个属性
        bridgeWebView.getSettings().setDomStorageEnabled(true);
        // 设置为使用webview推荐的窗口
        bridgeWebView.getSettings().setUseWideViewPort(true);
        // 设置网页自适应屏幕大小 ---这个属性应该是跟上面一个属性一起用
        bridgeWebView.getSettings().setLoadWithOverviewMode(true);
        // 设置是否允许webview使用缩放的功能,我这里设为false,不允许
        bridgeWebView.getSettings().setBuiltInZoomControls(false);
        // 设置显示水平滚动条,就是网页右边的滚动条.我这里设置的不显示
        bridgeWebView.setHorizontalScrollBarEnabled(false);
        // 设置显示垂直滚动条,就是网页右边的滚动条.我这里设置的不显示
        bridgeWebView.setVerticalScrollBarEnabled(false);
     //   bridgeWebView.loadUrl("http://api.tyitop.com/v1/integral/huzhao-rule");
    }
    private   DsytAddmoneyBean data;
    private void loadData() {
        OkGo.<BaseResponse<DsytAddmoneyBean>>post(Constants.BASE_URL + Constants.INTERGRAL_HUZHAO_SHOW)
                .tag(this)
                .execute(new JsonCallback<BaseResponse<DsytAddmoneyBean>>() {
                    @Override
                    public void onSuccess(Response<BaseResponse<DsytAddmoneyBean>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;
                     data = (DsytAddmoneyBean) body.getData();
                        if(data==null)return;

                        bridgeWebView.loadUrl(data.getRule_url());


                    }
                });
    }


    /**
     * 申请增值护照
     *
     */
  String  mymobile;
    @OnClick(R.id.dsyt_zengzhi_txt)
    public void actionZengzhiDialog() {
        Log.e("isFirstStw了2", "进来了21");

        AlertDialog.Builder builder= new AlertDialog.Builder(AssertDsytAddmoney.this);
        View view= LayoutInflater.from(AssertDsytAddmoney.this).inflate(R.layout.activity_dsyt_zengzhi_dialog, null);
        TextView sure =(TextView)view.findViewById(R.id.txt_comit);
        final EditText edittext = (EditText)view.findViewById(R.id.edit_mymobile);
        final Dialog dialog= builder.create();
        dialog.show();
        dialog.getWindow().setContentView(view);
        //使editext可以唤起软键盘
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //    Toast.makeText(MainActivity.this, "sure", Toast.LENGTH_SHORT).show();
                mymobile = edittext.getText().toString().trim();
                Log.e("mymobile",mymobile);
                loadDataMessage();
                dialog.dismiss();
            }
        });




    }

    private void loadDataMessage() {
        Log.e("mymobile",""+mymobile.length()+"#"+mymobile);
     if (mymobile.length()<11){
        RxToast.showShort("手机号码不正确");
        return;
    }
        OkGo.<BaseResponse<Object>>post(Constants.BASE_URL + Constants.INTERGRAL_HUZHAO)
                .tag(this)
                .params("mobile",mymobile)
                .execute(new JsonCallback<BaseResponse<Object>>(Constants.WITH_TOKEN,Constants.INTERGRAL_HUZHAO) {
                    @Override
                    public void onSuccess(Response<BaseResponse<Object>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;
                         RxToast.showShort(body.getMsg());
                        if(data==null)return;
                        if(body.isState()){
                      //     finish();
                        }
                    }
                });




    }

    @OnClick(R.id.android_down)
    public void actionAndroidDown() {
        Uri uri = Uri.parse(data.getDown_android());//要跳转的网址
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);



    }
    @OnClick(R.id.ios_down)
    public void actionIosDown() {
        Uri uri = Uri.parse(data.getDown_android());//要跳转的网址
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);

    }


}
