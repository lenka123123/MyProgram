package com.wokun.tysl.store.ui;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shantoo.widget.recyclerview.MItemDecoration;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.tysl.R;
import com.wokun.tysl.base.BaseBindingActivity;
import com.wokun.tysl.callback.DialogCallback;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.model.response.BaseResponse;
import com.wokun.tysl.model.response.BusinessShowResponse;
import com.wokun.tysl.other.adapter.MerchantEnterAdapter;
import com.wokun.tysl.store.bean.BusinessShowBean;

import butterknife.BindString;
import butterknife.BindView;

//商家入驻
public class MerchantEnterActivity extends BaseBindingActivity implements BaseQuickAdapter.OnItemClickListener{

    @BindString(R.string.tysl_enter_merchant) String title;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private MerchantEnterAdapter mAdapter;

    @Override
    public int createView() {
        return R.layout.activity_merchant_enter;
    }

    @Override
    public WidgetBar createToolBar() {
        return mWidgetBar.setWidgetBarTitle(title)
                .setMenu("申请",null)
                .setOnMenuClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(MerchantApplyForActivity.class);
                    }
                }, null);
    }

    @Override
    public void init() {
        mMultipleStatusView.showLoading();
        mAdapter = new MerchantEnterAdapter(R.layout.item_merchant_enter, null);
        mAdapter.setOnItemClickListener(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new MItemDecoration(this, RecyclerView.VERTICAL));
        mRecyclerView.setAdapter(mAdapter);
        loadData();
    }

    private void loadData() {
        OkGo.<BaseResponse<BusinessShowResponse>>post(Constants.BASE_URL + Constants.JOIN_BUSINESS_SHOW_URL)
            .tag(this)
            .execute(new DialogCallback<BaseResponse<BusinessShowResponse>>(this) {
                @Override
                public void onSuccess(Response<BaseResponse<BusinessShowResponse>> response) {
                    BaseResponse body = response.body();
                    if(body == null)return;
                    //Logger.e(TAG, JSONUtil.toJSON(body));
                    if(body.isState()){
                        mMultipleStatusView.showContent();
                        BusinessShowResponse data = (BusinessShowResponse) body.getData();
                        if(data==null){return;}
                        mAdapter.setNewData(data.getStoreList());
                    }
                }
            });
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        BusinessShowBean data = (BusinessShowBean) adapter.getData().get(position);
        Intent intent = new Intent();
        intent.putExtra(Constants.STORE_ID,data.getStoreId());
        intent.setClass(this,StoreIndexActivity.class);
        startActivity(intent);
    }
}