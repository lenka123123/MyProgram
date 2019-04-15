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
import com.wokun.tysl.earnings.adapter.EarningsDetailedAdapter;
import com.wokun.tysl.model.bean.ProfitLog;
import com.wokun.tysl.model.response.BaseResponse;
import com.wokun.tysl.model.response.ProfitLogResponse;

import butterknife.BindString;

//收益明细
public class EarningsDetailedActivity extends SimpleRefreshAndLoadMoreActivity<ProfitLog> {

    @BindString(R.string.tysl_returns_detailed)String title;

    @Override
    public WidgetBar createToolBar() {
        return mWidgetBar.setWidgetBarTitle(title);
    }

    @Override
    public BaseQuickAdapter<ProfitLog,BaseViewHolder> initAdapter() {
        return new EarningsDetailedAdapter(R.layout.item_earnings_detail, null);
    }

    @Override
    public Request initRequest() {
        return OkGo.<BaseResponse<ProfitLogResponse>>post(Constants.BASE_URL + Constants.DIETITIAN_PROFIT_LOG_URL).tag(this);
    }

    @Override
    public void loadData(final boolean isRefresh) {
        mRequest.execute(new JsonCallback<BaseResponse<ProfitLogResponse>>(Constants.WITH_TOKEN,Constants.DIETITIAN_PROFIT_LOG_URL) {
            @Override
            public void onSuccess(Response<BaseResponse<ProfitLogResponse>> response) {
                BaseResponse body = response.body();
                if(body == null)return;

                if(body.isState()){
                    ProfitLogResponse data = (ProfitLogResponse) body.getData();
                    if(data==null){return;}
                    setData(isRefresh,data.getProfitLogList());
                }
            }
        });
    }
}