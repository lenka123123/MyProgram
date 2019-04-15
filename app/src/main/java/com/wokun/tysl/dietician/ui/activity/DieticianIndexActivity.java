package com.wokun.tysl.dietician.ui.activity;

import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shantoo.widget.recyclerview.MItemDecoration;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.tysl.R;
import com.wokun.tysl.TyslApp;
import com.wokun.tysl.base.BaseBindingActivity;
import com.wokun.tysl.callback.JsonCallback;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.dietician.adapter.DieticianFieldAdapter;
import com.wokun.tysl.dietician.adapter.DieticianIndexServiceStarAdapter;
import com.wokun.tysl.dietician.adapter.DieticianJobTypeAdapter;
import com.wokun.tysl.dietician.bean.DieticianFieldTypeBean;
import com.wokun.tysl.dietician.bean.DieticianIndexBean;
import com.wokun.tysl.dietician.bean.DieticianJobTypeBean;
import com.wokun.tysl.dietician.bean.DietitianListBean;
import com.wokun.tysl.model.response.BaseResponse;

import butterknife.BindString;
import butterknife.BindViews;
import butterknife.OnClick;

/**
 * 问健康首页
 * */
public class DieticianIndexActivity extends BaseBindingActivity {

    @BindString(R.string.tysl_ask_service)
    String title;

    @BindViews({R.id.recycler_view1,R.id.recycler_view2,R.id.recycler_view3})
    RecyclerView[] mRecyclerViews;

    private DieticianFieldAdapter fieldAdapter;
    private DieticianJobTypeAdapter jobTypeAdapter;
    private DieticianIndexServiceStarAdapter serviceStarAdapter;

    @Override
    public int createView() {
        return R.layout.activity_dietician_index;
    }

    @Override
    public WidgetBar createToolBar() {
        return mWidgetBar.setWidgetBarTitle(title);
    }

    /** 职业类型更多*/
    @OnClick(R.id.action_look_up_by_job_type)
    public void actionLookUpByJobType(View v) {
        Intent intent = new Intent();
        intent.putExtra(Constants.TYPE,0);
        intent.setClass(this,DietitianJobTypeActivity.class);
        startActivity(intent);
    }

    /** 擅长领域更多*/
    @OnClick(R.id.action_find_it_in_your_field)
    public void actionFindItInYourField(View v) {
        startActivity(DietitianFieldTypeActivity.class);
    }

    @Override
    public void init() {
        mMultipleStatusView.showLoading();
        //按职业类型
        jobTypeAdapter = new DieticianJobTypeAdapter(R.layout.item_dietician_job_type, null);
        mRecyclerViews[0].setAdapter(jobTypeAdapter);
        mRecyclerViews[0].setNestedScrollingEnabled(false);
        mRecyclerViews[0].setLayoutManager(new GridLayoutManager(this, 4));
        //按擅长领域
        fieldAdapter = new DieticianFieldAdapter(R.layout.item_dietician_field, null);
        mRecyclerViews[1].setAdapter(fieldAdapter);
        mRecyclerViews[1].setNestedScrollingEnabled(false);
        mRecyclerViews[1].addItemDecoration(new MItemDecoration(this, DividerItemDecoration.HORIZONTAL, R.color.colorLine));
        mRecyclerViews[1].addItemDecoration(new MItemDecoration(this, DividerItemDecoration.VERTICAL, R.color.colorLine));
        mRecyclerViews[1].setLayoutManager(new GridLayoutManager(this, 4));
        //服务之星
        serviceStarAdapter = new DieticianIndexServiceStarAdapter(R.layout.item_dietician_service_star, null);
        mRecyclerViews[2].setAdapter(serviceStarAdapter);
        mRecyclerViews[2].setNestedScrollingEnabled(false);
      //  mRecyclerViews[2].setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mRecyclerViews[2].setLayoutManager(new GridLayoutManager(this, 4));
        jobTypeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                DieticianJobTypeBean bean = (DieticianJobTypeBean) adapter.getData().get(position);
                Intent intent = new Intent();
                intent.putExtra(Constants.TITLE, bean.getTypeName());
                intent.putExtra(Constants.TYPE_ID, bean.getTypeId());
                intent.setClass(TyslApp.getContext(), DietitianListActivity.class);
                startActivity(intent);
            }
        });
        fieldAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                DieticianFieldTypeBean bean = (DieticianFieldTypeBean) adapter.getData().get(position);
                Intent intent = new Intent();
                intent.putExtra(Constants.TITLE, bean.getFieldName());
                intent.putExtra(Constants.FIELD_ID, bean.getFieldId());
                intent.setClass(TyslApp.getContext(), DietitianListActivity.class);
                startActivity(intent);
            }
        });
        serviceStarAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                DietitianListBean bean = (DietitianListBean) adapter.getData().get(position);
                Intent intent = new Intent();
                intent.putExtra(Constants.DIETITIAN_ID, bean.getDietitianId());
                intent.setClass(TyslApp.getContext(), DieticianDetailActivity.class);
                startActivity(intent);
            }
        });

        loadData();
    }

    private void loadData() {
        OkGo.<BaseResponse<DieticianIndexBean>>post(Constants.BASE_URL + Constants.DIETITIAN_INDEX_URL)
                .tag(this)
                .execute(new JsonCallback<BaseResponse<DieticianIndexBean>>() {
                    @Override
                    public void onSuccess(Response<BaseResponse<DieticianIndexBean>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;
                        if(body.isState()){
                            mMultipleStatusView.showContent();
                            DieticianIndexBean data = (DieticianIndexBean) body.getData();
                            if(data==null){return;}
                            jobTypeAdapter.setNewData(data.getJob());
                            fieldAdapter.setNewData(data.getField());
                            serviceStarAdapter.setNewData(data.getServiceStar());
                        }
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OkGo.getInstance().cancelTag(this);
    }
}