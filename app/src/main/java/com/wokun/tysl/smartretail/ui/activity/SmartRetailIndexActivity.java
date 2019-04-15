package com.wokun.tysl.smartretail.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.classic.common.MultipleStatusView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.shantoo.common.location.OnLocationListener;
import com.shantoo.common.location.RxLocationManager;
import com.shantoo.common.utils.UIUtil;
//import com.shantoo.qrcode.OnScanQRCodeListener;
//import com.shantoo.qrcode.RxQRCode;
import com.shantoo.widget.recyclerview.MItemDecoration;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.tysl.R;
import com.wokun.tysl.base.BaseRefreshAndLoadMoreActivity;
import com.wokun.tysl.callback.DialogCallback;
import com.wokun.tysl.callback.JsonCallback;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.model.response.BaseResponse;
import com.wokun.tysl.mypersonmoney.ui.MylastmoneyActivity;
import com.wokun.tysl.smartretail.adapter.SmartRetailIndexAdapter;
import com.wokun.tysl.smartretail.bean.RetailSaomaBean;
import com.wokun.tysl.smartretail.bean.StoreListBean;
import com.wokun.tysl.smartretail.bean.StoreListResponse;
import com.wokun.tysl.utils.LocationUtils;

import java.util.List;

import butterknife.BindView;

public class SmartRetailIndexActivity extends BaseRefreshAndLoadMoreActivity<StoreListBean>
        implements ActivityCompat.OnRequestPermissionsResultCallback {

    @BindView(R.id.recyclerView)RecyclerView recyclerView;
    @BindView(R.id.multipleStatusView)MultipleStatusView multipleStatusView;
    @BindView(R.id.swipeRefreshLayout)SwipeRefreshLayout swipeRefreshLayout;

    private boolean isBind = true;

    @Override
    public SwipeRefreshLayout initSwipeRefreshLayout() {
        return swipeRefreshLayout;
    }

    @Override
    public RecyclerView initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new MItemDecoration(this));
        return recyclerView;
    }

    @Override
    public int createView() {
        return R.layout.activity_smart_retail_index;
    }

    @Override
    public WidgetBar createToolBar() {
        return mWidgetBar
                .setWidgetBarTitle("智慧零售")
             //  .setMenu(R.drawable.ic_sys,"扫一扫",0,"")
                .setMenu(R.drawable.ic_sys,0)
                .setMenuTextColor(UIUtil.getColor(R.color.colorPrimary))
                .setOnMenuClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //actionRichScan();
                        actionRichScanNew();
                    }
                },null);
    }

    private void actionRichScanNew() {

        Intent intent = new Intent();
        intent.setClass(this,SaoyisaoActivity.class);

        startActivity(intent);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //RxQRCode.getInstance().onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
      //  RxQRCode.getInstance().onRequestPermissionsResult(requestCode, permissions, grantResults);
      //  RxLocationManager.getInstance().onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public Request initRequest() {
        return OkGo.<BaseResponse<StoreListResponse>>post(Constants.BASE_URL + Constants.RETAIL_STORE_LIST_URL).tag(this);
    }

    @Override
    public BaseQuickAdapter<StoreListBean, BaseViewHolder> initAdapter() {
        return new SmartRetailIndexAdapter(R.layout.item_smart_retail,null);
    }

    @Override
    public void loadData(final boolean isRefresh) {
        //mMultipleStatusView.showLoading();
        //定位
//        RxLocationManager.getInstance()
//                .beginLocation(this, new OnLocationListener() {
//                    @Override
//                    public void onSuccess(Location location, List<Address> list) {
//                        //Logger.e(TAG,"定位成功 latitude= "+location.getLatitude()+"longitude="+location.getLongitude()+addresses.get(0).toString());
//                        //TyslApp.getInstance().setLocation(location);
//                        //Location location = TyslApp.getInstance().getLocation();
//                        if(location==null)return;
//                        OkGo.<BaseResponse<StoreListResponse>>post(Constants.BASE_URL + Constants.RETAIL_STORE_LIST_URL)
//                                .tag(this)
//                                .params(Constants.LAT, location.getLatitude())
//                                .params(Constants.LONG,location.getLongitude())
//                                .execute(new JsonCallback<BaseResponse<StoreListResponse>>(Constants.WITH_TOKEN,Constants.RETAIL_STORE_LIST_URL,mMultipleStatusView) {
//                                    @Override
//                                    public void onSuccess(Response<BaseResponse<StoreListResponse>> response) {
//                                        BaseResponse body = response.body();
//                                        if(body == null)return;
//                                        if(body.isState()){
//                                            mMultipleStatusView.showContent();
//                                            StoreListResponse data = (StoreListResponse) body.getData();
//                                            setData(isRefresh,data.getStoreList());
//                                        }
//                                    }
//                                });
//                    }
//                });
        Log.e("我进来了2323215","我进来了2");
        if (LocationUtils.getLatLng()!=null){
            OkGo.<BaseResponse<StoreListResponse>>post(Constants.BASE_URL + Constants.RETAIL_STORE_LIST_URL)
                    .tag(this)
                    .params(Constants.LAT, LocationUtils.getLatLng().latitude)
                    .params(Constants.LONG,LocationUtils.getLatLng().longitude)
                    .execute(new JsonCallback<BaseResponse<StoreListResponse>>(Constants.WITH_TOKEN,Constants.RETAIL_STORE_LIST_URL,mMultipleStatusView) {
                        @Override
                        public void onSuccess(Response<BaseResponse<StoreListResponse>> response) {
                            BaseResponse body = response.body();
                            if(body == null)return;
                            if(body.isState()){
                                mMultipleStatusView.showContent();
                                StoreListResponse data = (StoreListResponse) body.getData();
                                setData(isRefresh,data.getStoreList());
                            }
                        }
                    });
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        StoreListBean data = (StoreListBean) adapter.getData().get(position);
        Intent intent = new Intent();
        intent.setClass(this,SmartRetailStorageRackActivity.class);
        intent.putExtra(Constants.STORE_CODE,data.getStoreCode());

        startActivity(intent);
    }

    /** 扫一扫*/
  /*  private void actionRichScan(){
        RxQRCode.getInstance()
                .ScanQRCode(this)
                .setOnScanQRCodeListener(new OnScanQRCodeListener() {
                    @Override
                    public void onSuccess(String content, Bitmap bitmap) {
                        Log.e("我进来了","我进来了");
                        if(!TextUtils.isEmpty(content)){
                              final String storeCode = content.split("store_code=")[1];
                            OkGo.<BaseResponse<RetailSaomaBean>>post(Constants.BASE_URL + Constants.RETAIL_SAOMA_URL)
                                    .tag(this)
                                    .params(Constants.STORE_CODE,storeCode)
                                    .execute(new DialogCallback<BaseResponse<RetailSaomaBean>>(SmartRetailIndexActivity.this,Constants.WITH_TOKEN,Constants.RETAIL_SAOMA_URL) {
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
                                                    intent.setClass(SmartRetailIndexActivity.this,MerchantAccountManagementActivity.class);
                                                }//跳转货架商城
                                                else if(Constants.SHOP.equals(data.getOpenPage())){
                                                    intent.setClass(SmartRetailIndexActivity.this,SmartRetailStorageRackActivity1.class);
                                                }//跳转货架绑定页面
                                                else if(Constants.BIND.equals(data.getOpenPage())){
                                                    intent.setClass(SmartRetailIndexActivity.this,SmartRetailMerchantsBindingActivity.class);
                                                }
                                                startActivity(intent);
                                            }
                                        }
                                    });
                        }
                    }
                });
    }*/
    @Override
    protected void onResume() {
        super.onResume();
        // 开启定位
        LocationUtils.getInstance().start(3000, true, null);
    }

    @Override
    protected void onPause() {
        LocationUtils.getInstance().stop();
        super.onPause();
    }
}