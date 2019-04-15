package com.wokun.tysl.dietician.ui.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;

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
import com.wokun.tysl.dietician.adapter.DietitianListAdapter;
import com.wokun.tysl.dietician.bean.DietitianListBean;
import com.wokun.tysl.dietician.response.DietitianListResponse;
import com.wokun.tysl.dietician.response.GetJobTypeResponse;
import com.wokun.tysl.model.response.BaseResponse;

//营养师列表页
public class DietitianListActivity extends SimpleRefreshAndLoadMoreActivity<DietitianListBean> {

    @Override
    public WidgetBar createToolBar() {
        return mWidgetBar.setWidgetBarTitle(getIntent().getStringExtra(Constants.TITLE));
    }

    @Override
    public Request initRequest() {
        String type_id = getIntent().getStringExtra(Constants.TYPE_ID);
        String field_id = getIntent().getStringExtra(Constants.FIELD_ID);
        Request request = OkGo.<BaseResponse<GetJobTypeResponse>>post(Constants.BASE_URL + Constants.DIETITIAN_LIST_URL).tag(this);
        if(!TextUtils.isEmpty(type_id)){
            request.params(Constants.TYPE_ID, type_id);
        }else if(!TextUtils.isEmpty(field_id)){
            request.params(Constants.FIELD_ID, field_id);
        }
        return request;
    }


    @Override
    public BaseQuickAdapter<DietitianListBean, BaseViewHolder> initAdapter() {
        return new DietitianListAdapter(R.layout.item_dietitian_list, null);
    }

    @Override
    public void loadData(final boolean isRefresh) {
        mRequest.execute(new JsonCallback<BaseResponse<DietitianListResponse>>() {
            @Override
            public void onSuccess(Response<BaseResponse<DietitianListResponse>> response) {
                BaseResponse body = response.body();
                if(body == null)return;
                if(body.isState()){
                    DietitianListResponse data = (DietitianListResponse) body.getData();
                    if(data==null){return;}
                    setData(isRefresh,data.getDietitianList());
                }
            }
        });
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        DietitianListBean data = mDataList.get(position);
        if(data==null){return;}
        Intent intent = new Intent();
        intent.putExtra(Constants.DIETITIAN_ID, data.getDietitianId());
        intent.setClass(this, DieticianDetailActivity.class);
        startActivity(intent);
    }
}