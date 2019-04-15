package com.wokun.tysl.ucenter.ui;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.tysl.R;
import com.wokun.tysl.TyslApp;
import com.wokun.tysl.base.SimpleRefreshAndLoadMoreActivity;
import com.wokun.tysl.callback.JsonCallback;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.model.response.BaseResponse;
import com.wokun.tysl.model.response.MyHealthyResponse;
import com.wokun.tysl.ucenter.adapter.MyHealthAdapter;
import com.wokun.tysl.ucenter.bean.UserServiceOrder;

import butterknife.BindString;
import io.rong.imkit.RongIM;

//专属顾问
public class MyHealthActivity extends SimpleRefreshAndLoadMoreActivity<UserServiceOrder> {

    @BindString(R.string.tysl_my_health) String title;

    @Override
    public WidgetBar createToolBar() {
        return mWidgetBar.setWidgetBarTitle(title);
    }

    @Override
    public Request initRequest() {
        return OkGo.<BaseResponse<MyHealthyResponse>>post(Constants.BASE_URL + Constants.UCENTER_MY_HEALTHY_URL).tag(this);
    }

    @Override
    public BaseQuickAdapter<UserServiceOrder, BaseViewHolder> initAdapter() {
        MyHealthAdapter adapter = new MyHealthAdapter(R.layout.item_my_health, null);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                UserServiceOrder bean = (UserServiceOrder) adapter.getData().get(position);
                if(R.id.action_consult == view.getId()){//发起咨询
                    if (RongIM.getInstance() != null) {
                        RongIM.getInstance().startPrivateChat(TyslApp.getContext(), bean.getDietitianUserId(), bean.getTrueName());
                    }
                }
            }
        });
        return adapter;
    }

    @Override
    public void loadData(final boolean isRefresh) {
        mMultipleStatusView.showLoading();
        mRequest.execute(new JsonCallback<BaseResponse<MyHealthyResponse>>(Constants.WITH_TOKEN,Constants.UCENTER_MY_HEALTHY_URL) {
            @Override
            public void onSuccess(Response<BaseResponse<MyHealthyResponse>> response) {
                BaseResponse body = response.body();
                if(body == null)return;
                if(body.isState()){
                    mMultipleStatusView.showContent();
                    MyHealthyResponse data = (MyHealthyResponse) body.getData();
                    if(data == null){return;}
                    setData(isRefresh, data.getHealthy());
                }
            }
        });
    }
}