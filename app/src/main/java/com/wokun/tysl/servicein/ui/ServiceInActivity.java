package com.wokun.tysl.servicein.ui;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.tysl.R;
import com.wokun.tysl.base.BaseBindingActivity;
import com.wokun.tysl.callback.DialogCallback;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.dietician.bean.DieticianJobTypeBean;
import com.wokun.tysl.model.response.BaseResponse;
import com.wokun.tysl.servicein.adapter.ServiceInAdapter;

import java.util.List;

import butterknife.BindString;
import butterknife.BindView;

//服务入驻列表页面
public class ServiceInActivity extends BaseBindingActivity implements BaseQuickAdapter.OnItemClickListener {

    @BindString(R.string.tysl_service_in)String title;

    @BindView(R.id.recycler_view) RecyclerView mRecyclerView;

    private ServiceInAdapter mAdapter;

    @Override
    public int createView() {
        return R.layout.activity_service_in;
    }

    @Override
    public WidgetBar createToolBar() {
        return mWidgetBar
                .setWidgetBarTitle(title)
                .setMenu("说明",null);
    }

    @Override
    public void init() {
        loadData();
        mAdapter = new ServiceInAdapter(R.layout.item_service_in, null);
        mAdapter.setOnItemClickListener(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
    }

    private void loadData() {
        OkGo.<BaseResponse<List<DieticianJobTypeBean>>>post(Constants.BASE_URL + Constants.DIETITIAN_GET_JOB_TYPE_URL)//
                .tag(this)
                .execute(new DialogCallback<BaseResponse<List<DieticianJobTypeBean>>>(this) {
                    @Override
                    public void onSuccess(Response<BaseResponse<List<DieticianJobTypeBean>>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;
                        if (body.isState()) {
                            List<DieticianJobTypeBean> data = (List<DieticianJobTypeBean>) body.getData();
                            mAdapter.setNewData(data);
                        }
                    }
                });
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        DieticianJobTypeBean bean = (DieticianJobTypeBean) adapter.getData().get(position);
        Intent intent = new Intent();
        intent.putExtra(Constants.TYPE_ID, bean.getTypeId());
        intent.putExtra(Constants.TYPE_NAME, bean.getTypeName());
        intent.setClass(this, ServiceAuthenticationActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OkGo.getInstance().cancelTag(this);
    }
}