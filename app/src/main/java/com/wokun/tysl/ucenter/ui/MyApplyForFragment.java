package com.wokun.tysl.ucenter.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.wokun.tysl.R;
import com.wokun.tysl.base.SimpleRefreshAndLoadMoreFragment;
import com.wokun.tysl.callback.JsonCallback;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.dietician.bean.ServiceOrderBean;
import com.wokun.tysl.dietician.controler.DieticianServiceOrderMgr;
import com.wokun.tysl.dietician.ui.fragment.DietitianServiceOrderAll;
import com.wokun.tysl.model.response.BaseResponse;
import com.wokun.tysl.model.response.MyApplyResponse;
import com.wokun.tysl.servicein.ui.ServiceEvaluateActivity;
import com.wokun.tysl.ucenter.adapter.MyApplyForAdapter;
import com.wokun.tysl.ucenter.bean.UserServiceOrder;

import butterknife.BindView;

//我的申请子页面
public class MyApplyForFragment extends SimpleRefreshAndLoadMoreFragment<UserServiceOrder> {

    @Override
    public void onResume() {
        super.onResume();
        doOnRefreshData();
    }

    /**
     * 传入需要的参数，设置给arguments
     */
    public static MyApplyForFragment newInstance(int argument) {
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.STATE, argument);
        MyApplyForFragment contentFragment = new MyApplyForFragment();
        contentFragment.setArguments(bundle);
        return contentFragment;
    }

    @Override
    public Request initRequest() {
        int state = getArguments().getInt(Constants.STATE);
     //   Constants.PAGE_SIZE
        return OkGo.<BaseResponse<MyApplyResponse>>post(Constants.BASE_URL + Constants.UCENTER_MY_APPLY_URL)
                .tag(this)
                .params(Constants.ORDER_STATE, state);
    }

    @Override
    public BaseQuickAdapter<UserServiceOrder, BaseViewHolder> initAdapter() {
        MyApplyForAdapter adapter = new MyApplyForAdapter(R.layout.item_my_health, null);
       // ServiceOrderAdapter adapter = new ServiceOrderAdapter(R.layout.item_service_order, null);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

            UserServiceOrder bean = (UserServiceOrder) adapter.getData().get(position);

                    if(R.id.action_refund_detail == view.getId()){//s申请解绑
                  /* 申请退款  Intent intent = new Intent();
                    intent.putExtra(Constants.ORDER_ID,bean.getServiceOrderId());
                    intent.setClass(getContext(),MyApplyForRefundDetailActivity.class);
                    startActivity(intent);*/

                        DieticianServiceOrderMgr.getInstance().getUnite(MyApplyForFragment.this,bean);

                }

              else if(R.id.action_evaluate == view.getId()){//去评价
                    Intent intent = new Intent();
                    intent.putExtra(Constants.HEAD_LOGO,bean.getHeadLogo());
                    intent.putExtra(Constants.ORDER_ID,bean.getServiceOrderId());
                    intent.putExtra("truename",bean.getTrueName());
                    intent.putExtra("jobType",bean.getJobType());
                    intent.setClass(getContext(),ServiceEvaluateActivity.class);
                    startActivity(intent);
                }else if(R.id.action_consult == view.getId()){//咨询
                       //这里操作


                    DieticianServiceOrderMgr.getInstance().onSendMessage2(bean);


                }





            }
        });
        return adapter;
    }

    @Override
    public void loadData(final boolean isRefresh) {
        mRequest.execute(new JsonCallback<BaseResponse<MyApplyResponse>>(Constants.WITH_TOKEN,Constants.UCENTER_MY_APPLY_URL) {
            @Override
            public void onSuccess(Response<BaseResponse<MyApplyResponse>> response) {
                BaseResponse body = response.body();
                if(body == null)return;
                if(body.isState()){
                    MyApplyResponse data = (MyApplyResponse) body.getData();
                    if(data==null){return;}
                    setData(isRefresh,data.getMyApply());
                }
            }
        });
    }
}