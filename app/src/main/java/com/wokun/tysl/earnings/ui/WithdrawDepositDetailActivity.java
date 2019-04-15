package com.wokun.tysl.earnings.ui;

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
import com.wokun.tysl.earnings.adapter.WithdrawDepositDetailedAdapter;
import com.wokun.tysl.earnings.bean.WithdrawCashLogResponse;
import com.wokun.tysl.model.bean.WithdrawCashLog;
import com.wokun.tysl.model.response.BaseResponse;

//提现明细
public class WithdrawDepositDetailActivity extends SimpleRefreshAndLoadMoreActivity<WithdrawCashLog> {

    @Override
    public WidgetBar createToolBar() {
        return mWidgetBar.setWidgetBarTitle("提现明细");
    }

    @Override
    public BaseQuickAdapter<WithdrawCashLog, BaseViewHolder> initAdapter() {
        return new WithdrawDepositDetailedAdapter(R.layout.item_withdraw_deposit_detail, null);
    }

    @Override
    public Request initRequest() {
        return OkGo.<BaseResponse<WithdrawCashLogResponse>>post(Constants.BASE_URL + Constants.DIETITIAN_WITHDRAW_CASH_LOG_URL).tag(this);
    }

    @Override
    public void loadData(final boolean isRefresh) {
        mRequest.execute(new JsonCallback<BaseResponse<WithdrawCashLogResponse>>(Constants.WITH_TOKEN,Constants.DIETITIAN_WITHDRAW_CASH_LOG_URL) {
            @Override
            public void onSuccess(Response<BaseResponse<WithdrawCashLogResponse>> response) {
                BaseResponse body = response.body();
                if(body == null)return;

                if(body.isState()){
                    WithdrawCashLogResponse data = (WithdrawCashLogResponse) body.getData();
                    if(data==null){return;}
                    setData(isRefresh,data.getWithdrawList());
                }
            }
        });
    }
}
