package com.wokun.tysl.huiyuantotal.ui;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.shantoo.widget.recyclerview.MItemDecoration;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.tysl.R;
import com.wokun.tysl.bankaccount.adapter.EvaAdapter3;
import com.wokun.tysl.bankaccount.bean.TixianBean;
import com.wokun.tysl.bankaccount.bean.TixianResponse;
import com.wokun.tysl.base.BaseBindingActivity;
import com.wokun.tysl.base.SimpleRefreshAndLoadMoreActivity;
import com.wokun.tysl.callback.JsonCallback;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.huiyuantotal.adapter.EvaAdapter4;
import com.wokun.tysl.huiyuantotal.bean.ChangkeResponse;
import com.wokun.tysl.huiyuantotal.bean.ChuangKeBean;
import com.wokun.tysl.model.response.BaseResponse;
import com.wokun.tysl.smartretail.adapter.MerchantAccountManagementAdapter3;
import com.wokun.tysl.smartretail.bean.OrderInfoBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindString;
import butterknife.BindView;

/**
 * Created by Administrator on 2018/7/25/025.
 */

public class ChuangkeToyalActivity extends SimpleRefreshAndLoadMoreActivity<ChuangKeBean> {
    @BindString(R.string.tysl_user_chuangke)String title;
    private EvaAdapter4 madapter4;
    private String stringExtra;
    /*   @BindView(R.id.recyclerView)RecyclerView mRecyclerView;*/
 /*   @Override
    public int createView() {
        return R.layout.activity_merchant_account_huiyuan;
    }*/

    @Override
    public WidgetBar createToolBar() {
        return mWidgetBar.setWidgetBarTitle(title);
    }

    @Override
    public void init() {
        stringExtra = getIntent().getStringExtra(Constants.STORE_CODE);

/*
        List<OrderInfoBean> list = new ArrayList<>();
        OrderInfoBean bean6 = new OrderInfoBean();
        bean6.setUserName("不曾逗留");
        bean6.setTotalPrice("147.00");
        bean6.setProductName("软件大厦a");
        bean6.setProductPrice("1553x1");
        bean6.setProductEarnings("14.54");
        OrderInfoBean bean7 = new OrderInfoBean();
        bean7.setUserName("不曾留2 ");
        bean7.setTotalPrice("147.00");
        bean7.setProductName("软件大厦b");
        bean7.setProductPrice("1553x12");
        bean7.setProductEarnings("122.12");
        OrderInfoBean bean8 = new OrderInfoBean();
        bean8.setUserName("曾逗留");
        bean8.setTotalPrice("147.00");
        bean8.setProductName("软件大厦c");
        bean8.setProductPrice("1553x1");
        bean8.setProductEarnings("23.54");
        list.add(bean6);
        list.add(bean7);
        list.add(bean8);

        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.addItemDecoration(new MItemDecoration(this));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new MerchantAccountManagementAdapter3(R.layout.item_zhihui_chuangke,list));*/
    }

    @Override
    public Request initRequest() {
        return OkGo.<BaseResponse<ChuangKeBean>>post(Constants.BASE_URL + Constants.RETAIL_MAKER_LIST)
                .tag(this);
    }

    @Override
    public BaseQuickAdapter<ChuangKeBean, BaseViewHolder> initAdapter() {
        madapter4 = new EvaAdapter4(R.layout.item_zhihui_chuangke, null);
        madapter4.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                ChuangKeBean bean = (ChuangKeBean) adapter.getData().get(position);
                 Log.e("点击了2221123","点击了2221123");
                Intent intent = new Intent(ChuangkeToyalActivity.this, ChuangkeDindanAcitivity.class);
                 intent.putExtra(Constants.STORE_CODE,stringExtra);
                startActivity(intent);



             /*   if(R.id.isCollect == view.getId()){
                    FavoritesMgr.getInstance().deleteFavorites(StoreFavoritesActivity.this,Constants.FAVORITES_TYPE_STORE, bean.getStore_id());
                }*/
            }
        });

        return madapter4;
    }

    @Override
    public void loadData(final boolean isRefresh) {
        mRequest.execute(new JsonCallback<BaseResponse<ChangkeResponse>>(Constants.WITH_TOKEN,Constants.RETAIL_MAKER_LIST) {
            @Override
            public void onSuccess(Response<BaseResponse<ChangkeResponse>> response) {
                BaseResponse body = response.body();
                Log.e("11body",""+body);
                if(body == null)return;

                if(body.isState()){
                    ChangkeResponse data = (ChangkeResponse) body.getData();
                    if(data == null){return;}
                    Log.e("11data",""+data);
                    setData(isRefresh,data.getMaker());
                }
            }
        });




    }
}
