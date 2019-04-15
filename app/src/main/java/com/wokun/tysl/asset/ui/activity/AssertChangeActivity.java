package com.wokun.tysl.asset.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.itheima.view.BridgeWebView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.Callback;
import com.lzy.okgo.model.Response;
import com.shantoo.widget.recyclerview.MItemDecoration;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.tysl.R;
import com.wokun.tysl.TyslApp;
import com.wokun.tysl.asset.bean.AssertChangeBean;
import com.wokun.tysl.base.BaseBindingActivity;
import com.wokun.tysl.callback.JsonCallback;
import com.wokun.tysl.config.Constants;

import com.wokun.tysl.goods.bean.GoodsDetailBean;
import com.wokun.tysl.model.response.BaseResponse;
import com.wokun.tysl.ucenter.ui.MymoneyToyalActivity;
import com.wokun.tysl.utils.ImageLoader;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/7/18/018.
 */

public class AssertChangeActivity extends BaseBindingActivity {
    @BindString(R.string.tysl_user_changedetails) String title;

    private AssertChangeBean data;
    @BindView(R.id.change_image)
    ImageView mChangeimage;
    @BindView(R.id.change_name)
    TextView mChangename;
    @BindView(R.id.change_zichan_price)
    TextView mChangezichanprice;
    @BindView(R.id.change_exchange_integral)
    TextView mChangeexchangeintegral;
    @BindView(R.id.bridge_web_view)
    BridgeWebView bridgeWebView;
    @Override
    public int createView() {
        return R.layout.activity_shejiao_changedetails;

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
        String ids = intent.getStringExtra("id");
        OkGo.<BaseResponse<AssertChangeBean>>post(Constants.BASE_URL + Constants.INTEGRAL_GOODS_DETAIL_URL)
                .tag(this)
                .params("id",ids)
                .execute(new JsonCallback<BaseResponse<AssertChangeBean>>() {
                    @Override
                    public void onSuccess(Response<BaseResponse<AssertChangeBean>> response) {
                        BaseResponse body = response.body();
                        if(body==null)return;
                        if(body.isState()){
                            mMultipleStatusView.showContent();
                            data = (AssertChangeBean) body.getData();
                            if(data==null){return;}
                            ImageLoader.loadImage(data.getGoods_picture(), mChangeimage);
                            mChangename.setText(data.getGoods_name());

                            mChangezichanprice.setText(data.getExchange_integral());
                            mChangeexchangeintegral.setText(data.getGoods_price());

                            bridgeWebView.loadUrl(data.getContent());

                        }
                    }
                });
    }
    @OnClick({R.id.change_edt})
         public  void  changeedt(){
          boolean isNeedLogin = TyslApp.getInstance().isLogin();
         //    startActivity(AssertSureChangeActivity.class, !isNeedLogin);
        Intent intent = new Intent();
        Bundle bundle = new Bundle();

        bundle.putString("zhihuanprice",data.getExchange_integral());
        bundle.putString("zhihuanimg",data.getGoods_picture());
        bundle.putString("zhihuanname",data.getGoods_name());
        bundle.putString("zhihuanid",data.getId());

        intent.putExtras(bundle);
        intent.setClass(AssertChangeActivity.this,AssertSureChangeActivity.class);
        startActivity(intent,!TyslApp.getInstance().isLogin());

    }
}
