package com.wokun.tysl.other.ui;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.shantoo.widget.recyclerview.MItemDecoration;
import com.wokun.tysl.R;
import com.wokun.tysl.base.BaseRefreshAndLoadMoreFragment;
import com.wokun.tysl.callback.JsonCallback;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.dietician.response.GetFieldResponse;
import com.wokun.tysl.model.response.BaseResponse;
import com.wokun.tysl.other.adapter.SystemMessageAdapter;
import com.wokun.tysl.other.bean.SystemMessageResponse;
import com.wokun.tysl.other.bean.SystemNotice;

import butterknife.BindView;

//系统消息
public class SystemMessageFragment extends BaseRefreshAndLoadMoreFragment<SystemNotice> {

    @BindView(R.id.swipeRefreshLayout)
    protected SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.recyclerView)
    protected RecyclerView mRecyclerView;

    @Override
    public int createView() {
        return R.layout.layout_swipe_refresh;
    }

    @Override
    public SwipeRefreshLayout initSwipeRefreshLayout() {
        return mSwipeRefreshLayout;
    }

    @Override
    public RecyclerView initRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.addItemDecoration(new MItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        return mRecyclerView;
    }

    @Override
    public Request initRequest() {
        return OkGo.<BaseResponse<GetFieldResponse>>post(Constants.BASE_URL + Constants.UCENTER_GET_MY_NOTICE_URL).tag(this);
    }

    @Override
    public BaseQuickAdapter<SystemNotice, BaseViewHolder> initAdapter() {
        return new SystemMessageAdapter(R.layout.item_system_message,null);
    }

    @Override
    public void loadData(final boolean isRefresh) {
        mRequest.execute(new JsonCallback<BaseResponse<SystemMessageResponse>>(Constants.WITH_TOKEN,Constants.UCENTER_GET_MY_NOTICE_URL) {
            @Override
            public void onSuccess(Response<BaseResponse<SystemMessageResponse>> response) {
                BaseResponse body = response.body();
                if(body == null)return;

                if(body.isState()){
                    SystemMessageResponse data = (SystemMessageResponse) body.getData();
                    if(data==null){return;}
                    setData(isRefresh,data.getMyNotice());
                    Log.e("极光消息","极光消息"+data.getMyNotice());
                }
            }
        });
    }
}
