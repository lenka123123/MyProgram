package com.wokun.tysl.asset.ui.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shantoo.widget.recyclerview.MItemDecoration;
import com.shantoo.widget.toast.RxToast;
import com.shantoo.widget.toolbar.WidgetBar;
import com.sunfusheng.marqueeview.MarqueeView;
import com.wokun.tysl.R;
import com.wokun.tysl.TyslApp;
import com.wokun.tysl.asset.adapter.AssetIndexAdapter;
import com.wokun.tysl.asset.bean.AssetIndexDataBean;
import com.wokun.tysl.base.BaseBindingActivity;
import com.wokun.tysl.callback.JsonCallback;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.model.response.BaseResponse;
import com.wokun.tysl.utils.ImageLoader;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 社交资产首页
 * 置换中心
 */

public class AssetIndexActivity extends BaseBindingActivity implements BaseQuickAdapter.OnItemClickListener{

    @BindView(R.id.marqueeView)MarqueeView mMarqueeView;
    @BindView(R.id.recycler_view)RecyclerView mRecyclerView;
 //   @BindView(R.id.allIntegral)TextView allIntegral;
    @BindView(R.id.banner)ImageView banner;


    private AssetIndexDataBean data;
    private AssetIndexAdapter adapter;

    @Override
    public int createView() {
        return R.layout.activity_asset;
    }

    @Override
    public WidgetBar createToolBar() {
        return mWidgetBar
                .setWidgetBarTitle("置换中心");
               /* .setMenu("规则","")
                .setOnMenuClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(AssetRulesActivity.class);
                    }
                },null)*/
    }

    @Override
    public void init() {
        mMultipleStatusView.showLoading();
        initRecyclerView();
        loadData();
    }

    private void initRecyclerView(){
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.addItemDecoration(new MItemDecoration(this, RecyclerView.VERTICAL, R.color.colorLine));
        mRecyclerView.setHasFixedSize(true);
        adapter = new AssetIndexAdapter(R.layout.item_integral_shop,null);
        adapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(adapter);
    }

    private void loadData() {
        OkGo.<BaseResponse<AssetIndexDataBean>>post(Constants.BASE_URL + Constants.INTEGRAL_INDEX_URL)
                .tag(this)
                .execute(new JsonCallback<BaseResponse<AssetIndexDataBean>>(Constants.LOGIN_WITH_TOKEN,Constants.INTEGRAL_INDEX_URL) {
                    @Override
                    public void onSuccess(Response<BaseResponse<AssetIndexDataBean>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;
                        if (body.isState()) {
                            mMultipleStatusView.showContent();
                            data = (AssetIndexDataBean) body.getData();
                            if(data==null){return;}
                            adapter.setNewData(data.getGoods());
                           // banner.setBackground(Drawable.createFromPath(data.getBanner()));
                            ImageLoader.loadImage(data.getBanner(),banner);
                            mMarqueeView.startWithList(data.getDeal());
                         //   allIntegral.setText(data.getAllIntegral());
                        }
                    }
                });
    }

    private void updateAsset(){
        OkGo.<BaseResponse<AssetIndexDataBean>>post(Constants.BASE_URL + Constants.INTEGRAL_INDEX_URL)
                .tag(this)
                .execute(new JsonCallback<BaseResponse<AssetIndexDataBean>>(Constants.LOGIN_WITH_TOKEN,Constants.INTEGRAL_INDEX_URL) {
                    @Override
                    public void onSuccess(Response<BaseResponse<AssetIndexDataBean>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;
                        if (body.isState()) {

                            RxToast.showShort("更新资产"+data.getAllIntegral());
                     //       allIntegral.setText(data.getAllIntegral());
                        }
                    }
                });
    }

    /** 资产交易 跳转交易市场 */
    @OnClick(R.id.action_asset_transaction)
    public void actionAssetTransaction(){
        startActivity(AssetTransactionActivity.class);
    }

    /** 我要发布 */
    @OnClick(R.id.action_asset_release)
    public void actionAssetRelease(){
        Intent intent = new Intent();
        intent.setClass(this, AssetReleaseActivity.class);
        startActivityForResult(intent, Constants.ASSET_INDEX_REQUEST_CODE,!TyslApp.getInstance().isLogin());
    }

    /** 交易记录 */
    @OnClick(R.id.action_asset_transaction_record)
    public void actionAssetTransactionRecord(){
        Intent intent = new Intent();
        intent.setClass(this, AssetMyDealActivity.class);
        startActivityForResult(intent, Constants.ASSET_INDEX_REQUEST_CODE,!TyslApp.getInstance().isLogin());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(Constants.LOGIN_REQUEST_CODE == requestCode){
            loadData();
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Log.e("社交资产置换点击","1111111111111");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OkGo.getInstance().cancelTag(this);
    }
}