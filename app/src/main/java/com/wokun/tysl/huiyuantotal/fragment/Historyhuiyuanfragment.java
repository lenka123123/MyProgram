package com.wokun.tysl.huiyuantotal.fragment;



import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.wokun.tysl.R;
import com.wokun.tysl.base.SimpleRefreshAndLoadMoreFragment;
import com.wokun.tysl.callback.JsonCallback;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.huiyuantotal.adapter.EvaAdapter5;
import com.wokun.tysl.huiyuantotal.bean.PeopleBean;
import com.wokun.tysl.huiyuantotal.bean.PeopleHistoryResponse;
import com.wokun.tysl.huiyuantotal.bean.PeopleResponse;
import com.wokun.tysl.huiyuantotal.ui.HuiyuanTotalActivity;
import com.wokun.tysl.model.response.BaseResponse;

/**
 * Created by Administrator on 2018/7/9/009.
 */
//历史会员
public class Historyhuiyuanfragment extends SimpleRefreshAndLoadMoreFragment<PeopleBean> {

    private      String storeCodes;
    @Override
    public Request initRequest() {
        return  OkGo.<BaseResponse<PeopleBean>>post(Constants.BASE_URL + Constants.RETAIL_STORE_USER)
                .tag(this)
                .params("store_code",storeCodes)
                .params("type","history");
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        storeCodes = ((HuiyuanTotalActivity) activity).getStoreCodes();
        Log.e("storeCodes",storeCodes+"");
    }


    @Override
    public BaseQuickAdapter<PeopleBean, BaseViewHolder> initAdapter() {
        return new EvaAdapter5(R.layout.item_zhihui_huiyuan, null);
    }

    @Override
    public void loadData(final boolean isRefresh) {
        mRequest.execute(new JsonCallback<BaseResponse<PeopleHistoryResponse>>(Constants.WITH_TOKEN,Constants.RETAIL_STORE_USER) {
            @Override
            public void onSuccess(Response<BaseResponse<PeopleHistoryResponse>> response) {
                BaseResponse body = response.body();
                Log.e("11body",""+body);
                if(body == null)return;

                if(body.isState()){
                    PeopleHistoryResponse data = (PeopleHistoryResponse) body.getData();
                    if(data == null){return;}
                    Log.e("11data",""+data);
                    setData(isRefresh,data.getHistoryUser());
                }
            }
        });




    }
}
