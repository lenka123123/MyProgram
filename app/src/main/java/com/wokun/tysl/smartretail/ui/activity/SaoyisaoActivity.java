package com.wokun.tysl.smartretail.ui.activity;

import android.content.Intent;
        import android.graphics.Bitmap;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.util.Log;
        import android.view.View;
        import android.widget.ImageView;

        import com.google.zxing.Result;
        import com.google.zxing.client.result.ParsedResult;
        import com.google.zxing.client.result.ParsedResultType;
import com.google.zxing.client.result.TextParsedResult;
import com.google.zxing.client.result.URIParsedResult;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.mylhyl.zxing.scanner.OnScannerCompletionListener;
        import com.mylhyl.zxing.scanner.ScannerOptions;
        import com.mylhyl.zxing.scanner.ScannerView;
import com.wokun.tysl.R;
import com.wokun.tysl.callback.DialogCallback;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.model.response.BaseResponse;
import com.wokun.tysl.mypersonmoney.ui.MylastmoneyActivity;
import com.wokun.tysl.smartretail.bean.RetailSaomaBean;

public class SaoyisaoActivity extends AppCompatActivity implements View.OnClickListener, OnScannerCompletionListener {

    private ScannerView mScannerView;
    private ImageView mScanner_light;
    private ImageView mScanner_finish;
    private boolean isOpenLight = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sayisao);

        initViews();
        initEvents();

        //自定义UI
        initScannerOptions();

        mScannerView.setOnScannerCompletionListener(this);
    }


    /**
     * 自定义UI
     */
    private void initScannerOptions() {
        Log.e("扫一扫","扫一扫1");
        ScannerOptions options = new ScannerOptions.Builder()
                .setTipTextToFrameTop(true)
                .setTipTextToFrameMargin(30)
                .setFrameCornerColor(getResources().getColor(R.color.colorPrimary))
                .setLaserLineColor(getResources().getColor(R.color.colorPrimary))
                .build();

        mScannerView.setScannerOptions(options);
    }

    /**
     * 初始化View
     */
    private void initViews() {
        mScannerView = (ScannerView) findViewById(R.id.scanner_view);
        mScanner_light = (ImageView) findViewById(R.id.scanner_light);
        mScanner_finish = (ImageView) findViewById(R.id.scanner_finish);
    }


    private void initEvents() {
        mScanner_light.setOnClickListener(this);
        mScanner_finish.setOnClickListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mScannerView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mScannerView.onResume();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.scanner_light:
                //灯光开关
                if (isOpenLight) {
                    mScannerView.toggleLight(false);
                    isOpenLight = false;
                } else {
                    mScannerView.toggleLight(true);
                    isOpenLight = true;
                }
                break;
            case R.id.scanner_finish:
                finish();
                break;
        }
    }

    /**
     * 扫描成功监听回调
     *
     * @param rawResult    扫描结果
     * @param parsedResult 扫描类型
     * @param barcode      扫描图像
     */
    @Override
    public void onScannerCompletion(Result rawResult, ParsedResult parsedResult, Bitmap barcode) {
        Log.e("扫一扫","扫一扫2");
        ParsedResultType type = parsedResult.getType();
        Log.e("扫一扫结果类型:",type+""+"\n"+rawResult.toString());
        switch (type) {
            case URI:
                URIParsedResult uriParsedResult2 = (URIParsedResult) parsedResult;
                String uri = uriParsedResult2.getURI();
                final String storeCode2 = uri.split("store_code=")[1];
                Log.e("扫一扫6",storeCode2+"");
                OkGo.<BaseResponse<RetailSaomaBean>>post(Constants.BASE_URL + Constants.RETAIL_SAOMA_URL)
                        .tag(this)
                        .params(Constants.STORE_CODE,storeCode2)
                        .execute(new DialogCallback<BaseResponse<RetailSaomaBean>>(SaoyisaoActivity.this,Constants.WITH_TOKEN,Constants.RETAIL_SAOMA_URL) {
                            @Override
                            public void onSuccess(Response<BaseResponse<RetailSaomaBean>> response) {
                                Log.e("我进来了2","我进来了2");
                                BaseResponse body = response.body();
                                if(body == null)return;
                                if(body.isState()){
                                    RetailSaomaBean data = (RetailSaomaBean) body.getData();
                                    Intent intent = new Intent();
                                    intent.putExtra(Constants.STORE_CODE,storeCode2);
                                    //跳转商家管理页面
                                    if(Constants.MANAGE.equals(data.getOpenPage())){
                                        intent.setClass(SaoyisaoActivity.this,MechantAccountMangermentNewActivity.class);
                                    }//跳转货架商城
                                    else if(Constants.SHOP.equals(data.getOpenPage())){
                                        //intent.setClass(SaoyisaoActivity.this,SmartRetailStorageRackActivity1.class);
                                        intent.setClass(SaoyisaoActivity.this,MechantAccountMangermentNewActivity.class);
                                    }//跳转货架绑定页面
                                    else if(Constants.BIND.equals(data.getOpenPage())){
                                        intent.setClass(SaoyisaoActivity.this,SmartRetailMerchantsBindingActivity.class);
                                    }
                                    startActivity(intent);

                                }
                            }
                        });
                break;
            case TEXT:
                TextParsedResult uriParsedResult = (TextParsedResult) parsedResult;
                String textw = uriParsedResult.getText();
                Log.e("扫一扫3",textw+"");
                final String storeCode = textw.split("store_code=")[1];
                Log.e("扫一扫4",storeCode+"");
                OkGo.<BaseResponse<RetailSaomaBean>>post(Constants.BASE_URL + Constants.RETAIL_SAOMA_URL)
                        .tag(this)
                        .params(Constants.STORE_CODE,storeCode)
                        .execute(new DialogCallback<BaseResponse<RetailSaomaBean>>(SaoyisaoActivity.this,Constants.WITH_TOKEN,Constants.RETAIL_SAOMA_URL) {
                            @Override
                            public void onSuccess(Response<BaseResponse<RetailSaomaBean>> response) {
                                Log.e("我进来了2","我进来了2");
                                BaseResponse body = response.body();
                                if(body == null)return;
                                if(body.isState()){
                                    RetailSaomaBean data = (RetailSaomaBean) body.getData();
                                    Intent intent = new Intent();
                                    intent.putExtra(Constants.STORE_CODE,storeCode);
                                    //跳转商家管理页面
                                    if(Constants.MANAGE.equals(data.getOpenPage())){
                                        intent.setClass(SaoyisaoActivity.this,MechantAccountMangermentNewActivity.class);
                                    }//跳转货架商城
                                    else if(Constants.SHOP.equals(data.getOpenPage())){
                                        //intent.setClass(SaoyisaoActivity.this,SmartRetailStorageRackActivity1.class);
                                        intent.setClass(SaoyisaoActivity.this,MechantAccountMangermentNewActivity.class);
                                    }//跳转货架绑定页面
                                    else if(Constants.BIND.equals(data.getOpenPage())){
                                        intent.setClass(SaoyisaoActivity.this,SmartRetailMerchantsBindingActivity.class);
                                    }
                                    startActivity(intent);

                                }
                            }
                        });
                break;

        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }
}
