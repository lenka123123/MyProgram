package com.wokun.tysl.asset.ui.fragment;

import android.os.Bundle;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.wokun.tysl.R;
import com.wokun.tysl.TyslApp;
import com.wokun.tysl.asset.adapter.AssetMyDealAdapter;
import com.wokun.tysl.asset.bean.DealResponse;
import com.wokun.tysl.asset.bean.MyDealBean;
import com.wokun.tysl.base.SimpleRefreshAndLoadMoreFragment;
import com.wokun.tysl.callback.JsonCallback;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.model.response.BaseResponse;

/**
 * 交易记录
 */

public class AssetMyDealFragment extends SimpleRefreshAndLoadMoreFragment<MyDealBean> {

    @Override
    public void onResume() {
        super.onResume();
        doOnRefreshData();
    }

    /**
     * 传入需要的参数，设置给arguments
     */
    public static AssetMyDealFragment newInstance(int argument) {
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.STATE, argument);
        AssetMyDealFragment contentFragment = new AssetMyDealFragment();
        contentFragment.setArguments(bundle);
        return contentFragment;
    }

    @Override
    public Request initRequest() {
        int state = getArguments().getInt(Constants.STATE);
        return OkGo.<BaseResponse<MyDealBean>>post(Constants.BASE_URL + Constants.ASSETS_MY_DEAL_URL)
                .params(Constants.USER_ID, TyslApp.getInstance().getUser().getUserId())
                .params(Constants.TYPE,state)
                .tag(this);
    }

    @Override
    public BaseQuickAdapter<MyDealBean, BaseViewHolder> initAdapter() {
        int state = getArguments().getInt(Constants.STATE);
        return new AssetMyDealAdapter(R.layout.item_asset_my_newdeal,null,state);
    }

    @Override
    public void loadData(boolean isRefresh) {
        mRequest.execute(new JsonCallback<BaseResponse<DealResponse>>() {
            @Override
            public void onSuccess(Response<BaseResponse<DealResponse>> response) {
                BaseResponse body = response.body();
                if(body == null)return;
                if(body.isState()){
                    DealResponse data = (DealResponse) body.getData();
                    if(data==null){return;}
                    setData(true,data.getDeal());
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        OkGo.getInstance().cancelTag(this);
    }
}