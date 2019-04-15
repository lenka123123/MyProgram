package com.wokun.tysl.dietician.ui.activity;

import android.content.Intent;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.shantoo.widget.toolbar.WidgetBar;
import com.shantoo.widget.utils.UITool;
import com.wokun.tysl.R;
import com.wokun.tysl.base.SimpleRefreshAndLoadMoreActivity;
import com.wokun.tysl.callback.JsonCallback;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.dietician.adapter.DietitianJobTypeAdapter;
import com.wokun.tysl.dietician.bean.DieticianJobTypeBean;
import com.wokun.tysl.dietician.response.GetJobTypeResponse;
import com.wokun.tysl.model.response.BaseResponse;
import com.wokun.tysl.servicein.ui.ServiceAuthenticationActivity;

//职业类型
public class DietitianJobTypeActivity extends SimpleRefreshAndLoadMoreActivity<DieticianJobTypeBean> {

    private int type;//0 打开营养师列表页，1 打开服务入驻页

    @Override
    public WidgetBar createToolBar() {
        if(0 == type){
            mWidgetBar.setTitle(UITool.getString(R.string.tysl_type_of_profession));
        }else if(1 == type){
            mWidgetBar.setTitle(UITool.getString(R.string.tysl_dietitian_join));
        }
        return mWidgetBar;
    }

    @Override
    public void init() {
        type = getIntent().getIntExtra(Constants.TYPE,0);
    }

    @Override
    public BaseQuickAdapter<DieticianJobTypeBean,BaseViewHolder> initAdapter() {
        return new DietitianJobTypeAdapter(R.layout.item_title, null);
    }

    @Override
    public Request initRequest() {
        return OkGo.<BaseResponse<GetJobTypeResponse>>post(Constants.BASE_URL + Constants.DIETITIAN_GET_JOB_TYPE_URL).tag(this);
    }

    @Override
    public void loadData(final boolean isRefresh) {
        mRequest.execute(new JsonCallback<BaseResponse<GetJobTypeResponse>>() {
            @Override
            public void onSuccess(Response<BaseResponse<GetJobTypeResponse>> response) {
                BaseResponse body = response.body();
                if(body == null)return;
                if(body.isState()){
                    GetJobTypeResponse data = (GetJobTypeResponse) body.getData();
                    if(data==null){return;}
                    setData(isRefresh,data.getJobType());
                }
            }
        });
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        DieticianJobTypeBean data = mDataList.get(position);
        if(data==null){return;}
        Intent intent = new Intent();
        intent.putExtra(Constants.TYPE_ID, data.getTypeId());
        if(0 == type){
            intent.putExtra(Constants.TITLE, data.getTypeName());
            intent.setClass(this, DietitianListActivity.class);
        }else if(1 == type){
            intent.putExtra(Constants.TYPE_NAME, data.getTypeName());
            intent.setClass(this, ServiceAuthenticationActivity.class);
        }
        startActivity(intent);
    }
}