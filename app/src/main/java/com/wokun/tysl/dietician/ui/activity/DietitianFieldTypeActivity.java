package com.wokun.tysl.dietician.ui.activity;

import android.content.Intent;
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
import com.wokun.tysl.dietician.adapter.DietitianFieldTypeAdapter;
import com.wokun.tysl.dietician.bean.DieticianFieldTypeBean;
import com.wokun.tysl.dietician.response.GetFieldResponse;
import com.wokun.tysl.model.response.BaseResponse;

import butterknife.BindString;

//擅长领域
public class DietitianFieldTypeActivity extends SimpleRefreshAndLoadMoreActivity<DieticianFieldTypeBean> {

    @BindString(R.string.tysl_the_field_of_training)String title;

    @Override
    public BaseQuickAdapter<DieticianFieldTypeBean, BaseViewHolder> initAdapter() {
        return new DietitianFieldTypeAdapter(R.layout.item_title, null);
    }

    @Override
    public WidgetBar createToolBar() {
        return mWidgetBar.setWidgetBarTitle(title);
    }

    @Override
    public Request initRequest() {
        return OkGo.<BaseResponse<GetFieldResponse>>post(Constants.BASE_URL + Constants.DIETITIAN_GET_FIELD_TYPE_URL).tag(this);
    }

    @Override
    public void loadData(final boolean isRefresh) {
        mRequest.execute(new JsonCallback<BaseResponse<GetFieldResponse>>() {
            @Override
            public void onSuccess(Response<BaseResponse<GetFieldResponse>> response) {
                BaseResponse body = response.body();
                if(body == null)return;
                if(body.isState()){
                    GetFieldResponse data = (GetFieldResponse) body.getData();
                    if(data==null){return;}
                    setData(isRefresh,data.getField());
                }
            }
        });
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        DieticianFieldTypeBean bean = mDataList.get(position);
        Intent intent = new Intent();
        intent.putExtra(Constants.TITLE, bean.getFieldName());
        intent.putExtra(Constants.FIELD_ID, bean.getFieldId());
        intent.setClass(this, DietitianListActivity.class);
        startActivity(intent);
    }
}
