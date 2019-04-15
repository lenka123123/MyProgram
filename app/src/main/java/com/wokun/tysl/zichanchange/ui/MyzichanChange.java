package com.wokun.tysl.zichanchange.ui;

import android.content.Context;
import android.content.Intent;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.shantoo.widget.recyclerview.MItemDecoration;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.tysl.R;
import com.wokun.tysl.asset.ui.activity.AssertChangeActivity;
import com.wokun.tysl.base.BaseBindingActivity;
import com.wokun.tysl.base.SimpleRefreshAndLoadMoreActivity;
import com.wokun.tysl.callback.JsonCallback;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.model.response.BaseResponse;
import com.wokun.tysl.model.response.MyHealthyResponse;
import com.wokun.tysl.zichanchange.adapter.ZichanChangeAdapter;
import com.wokun.tysl.zichanchange.bean.ZhihuanjilvBean;
import com.wokun.tysl.zichanchange.bean.ZhihuanjilvRespone;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindString;
import butterknife.BindView;

/**
 * Created by Administrator on 2018/7/5/005.
 */

public class MyzichanChange  extends SimpleRefreshAndLoadMoreActivity<ZhihuanjilvBean>{
    @BindString(R.string.tysl_edit_change)
    String title;
   private  ZichanChangeAdapter  mAdapter;
    @Override
    public WidgetBar createToolBar() {
        return mWidgetBar.setWidgetBarTitle(title);
    }


    @Override
    public Request initRequest() {
        return OkGo.<BaseResponse<ZhihuanjilvRespone>>post(Constants.BASE_URL + Constants.INTEGRAL_GET_EXCHANGE_RECORD_URL).tag(this);

    }




    @Override
    public BaseQuickAdapter<ZhihuanjilvBean, BaseViewHolder> initAdapter() {

        mAdapter =  new ZichanChangeAdapter(R.layout.item_my_change, null);

   /*     mAdapter.setOnItemClickListener(new ZichanChangeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ZhihuanjilvBean item) {
                Log.e("点击再次置换","再次置换了");
                Intent intent = new Intent(MyzichanChange.this, AssertChangeActivity.class);
               intent.putExtra("id",item.getIntegral_gid());
                startActivity(intent);
            }
        });*/
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                ZhihuanjilvBean bean = (ZhihuanjilvBean) adapter.getData().get(position);
                Log.e("点击再次置换22","再次置换了");
                if(R.id.change_agin == view.getId()){
                    Intent intent = new Intent(MyzichanChange.this, AssertChangeActivity.class);
                    intent.putExtra("id",bean.getIntegral_gid());
                    startActivity(intent);

                }


            }
        });


        return mAdapter;



    }

    @Override
    public void loadData(final boolean isRefresh) {
        mRequest.execute(new JsonCallback<BaseResponse<ZhihuanjilvRespone>>(Constants.WITH_TOKEN,Constants.INTEGRAL_GET_EXCHANGE_RECORD_URL) {
            @Override
            public void onSuccess(Response<BaseResponse<ZhihuanjilvRespone>> response) {
                BaseResponse body = response.body();
                if(body == null)return;
                if(body.isState()){
                    ZhihuanjilvRespone data = (ZhihuanjilvRespone) body.getData();
                    if(data == null){return;}
                    setData(isRefresh, data.getExchangeList());
                }

            }
        });

    }


}
