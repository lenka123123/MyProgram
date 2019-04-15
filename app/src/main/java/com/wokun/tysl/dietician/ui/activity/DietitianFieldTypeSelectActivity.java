package com.wokun.tysl.dietician.ui.activity;

import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shantoo.widget.recyclerview.MItemDecoration;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.tysl.R;
import com.wokun.tysl.base.BaseBindingActivity;
import com.wokun.tysl.callback.JsonCallback;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.dietician.adapter.DietitianFieldTypeSelectAdapter;
import com.wokun.tysl.dietician.response.GetFieldResponse;
import com.wokun.tysl.model.response.BaseResponse;

import butterknife.BindString;
import butterknife.BindView;

//擅长领域
public class DietitianFieldTypeSelectActivity extends BaseBindingActivity {

    @BindString(R.string.tysl_the_field_of_training)String title;

    @BindView(R.id.recycler_view)
    protected RecyclerView mRecyclerView;

    private String mFieldIds;

    @Override
    public int createView() {
        return R.layout.activity_service_in;
    }

    @Override
    public WidgetBar createToolBar() {
        return mWidgetBar
            .setWidgetBarTitle(title)
            .setMenu("确定",null)
            .setOnMenuClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//记录营养师擅长领域选择结果
                Intent intent = new Intent();
                intent.putExtra(Constants.FIELD_IDS,mFieldIds);
                setResult(77,intent);
                finish();
            }
        },null);
    }

    @Override
    public void init() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new MItemDecoration(this, DividerItemDecoration.VERTICAL));
        final DietitianFieldTypeSelectAdapter adapter = new DietitianFieldTypeSelectAdapter(R.layout.item_title, null);
        adapter.setOnFieldTypeSelectListener(new DietitianFieldTypeSelectAdapter.OnFieldTypeSelectListener() {
            @Override
            public void onFieldTypeSelect(String fieldIds) {
                //Logger.e(TAG,mFieldIds);
                mFieldIds = fieldIds;
            }
        });
        mRecyclerView.setAdapter(adapter);
        OkGo.<BaseResponse<GetFieldResponse>>post(Constants.BASE_URL + Constants.DIETITIAN_GET_FIELD_TYPE_URL)//
                .tag(this)
                .params(Constants.ID, getIntent().getStringExtra(Constants.SOURCE_ID))
                .execute(new JsonCallback<BaseResponse<GetFieldResponse>>() {
                    @Override
                    public void onSuccess(Response<BaseResponse<GetFieldResponse>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;
                        if(body.isState()){
                            GetFieldResponse data = (GetFieldResponse) body.getData();
                            if(data==null){
                                return;
                            }
                            adapter.setNewData(data.getField());
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