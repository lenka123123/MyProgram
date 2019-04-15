package com.wokun.tysl.huiyuantotal.fragment;



import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.shantoo.widget.recyclerview.MItemDecoration;
import com.wokun.tysl.R;
import com.wokun.tysl.bankaccount.adapter.EvaAdapter3;
import com.wokun.tysl.bankaccount.bean.TixianBean;
import com.wokun.tysl.bankaccount.bean.TixianResponse;
import com.wokun.tysl.base.BaseFragment1;
import com.wokun.tysl.base.SimpleRefreshAndLoadMoreFragment;
import com.wokun.tysl.callback.JsonCallback;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.goods.bean.GoodsOrderBean;
import com.wokun.tysl.huiyuantotal.adapter.EvaAdapter5;
import com.wokun.tysl.huiyuantotal.bean.PeopleBean;
import com.wokun.tysl.huiyuantotal.bean.PeopleResponse;
import com.wokun.tysl.huiyuantotal.ui.HuiyuanTotalActivity;
import com.wokun.tysl.model.response.BaseResponse;
import com.wokun.tysl.smartretail.adapter.MerchantAccountManagementAdapter1;
import com.wokun.tysl.smartretail.adapter.MerchantAccountManagementAdapter2;
import com.wokun.tysl.smartretail.adapter.MerchantAccountManagementAdapter3;
import com.wokun.tysl.smartretail.bean.BusinessData;
import com.wokun.tysl.smartretail.bean.OrderInfoBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/7/9/009.
 */
//新增会员
public class Addhuiyuanfragment extends SimpleRefreshAndLoadMoreFragment<PeopleBean> {
    private      String storeCodes;

    @Override
    public Request initRequest() {
        return OkGo.<BaseResponse<PeopleBean>>post(Constants.BASE_URL + Constants.RETAIL_STORE_USER)
                .tag(this)
                .params("store_code",storeCodes)
                .params("type","today");
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

        mRequest.execute(new JsonCallback<BaseResponse<PeopleResponse>>(Constants.WITH_TOKEN,Constants.RETAIL_STORE_USER) {
            @Override
            public void onSuccess(Response<BaseResponse<PeopleResponse>> response) {
                BaseResponse body = response.body();
                Log.e("11body",""+body);
                if(body == null)return;

                if(body.isState()){
                    PeopleResponse data = (PeopleResponse) body.getData();
                    if(data == null){return;}
                    Log.e("11data",""+data);
                    setData(isRefresh,data.getTodayUser());
                }
            }
        });









    }
/*
    @BindView(R.id.recyclerView)RecyclerView mRecyclerView;

    @Override
    public int createView() {
        return R.layout.activity_merchant_account_huiyuan;
    }

    @Override
    public void initViews() {
        List<OrderInfoBean> list = new ArrayList<>();
        OrderInfoBean bean6 = new OrderInfoBean();
        bean6.setUserName("不曾逗留 435345345");
        bean6.setTotalPrice("147.00");
        bean6.setProductName("酵素");
        bean6.setProductPrice("1553x1");
        bean6.setProductEarnings("14.54");
        OrderInfoBean bean7 = new OrderInfoBean();
        bean7.setUserName("不曾逗留2 435345345");
        bean7.setTotalPrice("147.00");
        bean7.setProductName("酵素3");
        bean7.setProductPrice("1553x12");
        bean7.setProductEarnings("122.12");
        OrderInfoBean bean8 = new OrderInfoBean();
        bean8.setUserName("不曾逗留3 435345345");
        bean8.setTotalPrice("147.00");
        bean8.setProductName("酵素2");
        bean8.setProductPrice("1553x1");
        bean8.setProductEarnings("23.54");
        list.add(bean6);
        list.add(bean7);
        list.add(bean8);

        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.addItemDecoration(new MItemDecoration(getContext()));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(new MerchantAccountManagementAdapter3(R.layout.item_zhihui_huiyuan,list));








    }

    @Override
    public void loadData() {

    }
*/


}
