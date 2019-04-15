package com.wokun.tysl.ucenter.ui;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.tysl.R;
import com.wokun.tysl.base.SimpleRefreshAndLoadMoreActivity;
import com.wokun.tysl.callback.JsonCallback;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.model.response.BaseResponse;
import com.wokun.tysl.model.response.MyHealthyResponse;
import com.wokun.tysl.ucenter.adapter.MoneyDetailAdapter;
import com.wokun.tysl.ucenter.bean.MoneyDetail;
import com.wokun.tysl.ucenter.bean.MoneyDetailResponse;

/**
 * 余额明细页面
 */

public class MoneyDetailActivity extends SimpleRefreshAndLoadMoreActivity<MoneyDetail> {

    @Override
    public WidgetBar createToolBar() {
        return mWidgetBar.setWidgetBarTitle("余额明细");
    }

    @Override
    public Request initRequest() {
        return OkGo.<BaseResponse<MoneyDetailResponse>>post(Constants.BASE_URL + Constants.UCENTER_MONEY_DETAIL_URL).tag(this);


    }

    @Override
    public BaseQuickAdapter<MoneyDetail, BaseViewHolder> initAdapter() {
        return new MoneyDetailAdapter(R.layout.item_money_detail, null);
    }

    @Override
    public void loadData(final boolean isRefresh) {
        mRequest.execute(new JsonCallback<BaseResponse<MoneyDetailResponse>>(Constants.WITH_TOKEN,Constants.UCENTER_MONEY_DETAIL_URL) {
            @Override
            public void onSuccess(Response<BaseResponse<MoneyDetailResponse>> response) {
                BaseResponse body = response.body();
                if(body == null)return;
                if(body.isState()){
                    MoneyDetailResponse data = (MoneyDetailResponse) body.getData();
                    if(data == null){return;}
                    setData(isRefresh, data.getMoneyList());
                }
            }
        });
    }
}