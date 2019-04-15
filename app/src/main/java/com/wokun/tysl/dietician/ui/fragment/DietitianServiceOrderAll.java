package com.wokun.tysl.dietician.ui.fragment;

import android.content.Intent;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.wokun.tysl.R;
import com.wokun.tysl.TyslApp;
import com.wokun.tysl.base.SimpleRefreshAndLoadMoreFragment;
import com.wokun.tysl.callback.JsonCallback;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.dietician.adapter.ServiceOrderAdapter;
import com.wokun.tysl.dietician.bean.ServiceOrderBean;
import com.wokun.tysl.dietician.controler.DieticianServiceOrderMgr;
import com.wokun.tysl.goods.ui.activity.GoodsOrderDetailActivity;
import com.wokun.tysl.model.response.BaseResponse;
import com.wokun.tysl.model.response.OrderListResponse;

//所有服务订单
public class DietitianServiceOrderAll extends SimpleRefreshAndLoadMoreFragment<ServiceOrderBean> {

    @Override
    public boolean hasItemDecoration() {
        return false;
    }

    @Override
    public Request initRequest() {
        int dietitianId = TyslApp.getInstance().getUserInfo().getDietitianId();
        return OkGo.<BaseResponse<OrderListResponse>>post(Constants.BASE_URL + Constants.DIETITIAN_ORDER_LIST_URL)
                .tag(this)
                .params(Constants.ORDER_STATE, Constants.ALL)
                .params(Constants.DIETITIAN_ID, dietitianId);
    }

    @Override
    public void loadData(final boolean isRefresh) {
        mRequest.execute(new JsonCallback<BaseResponse<OrderListResponse>>(Constants.WITH_TOKEN,Constants.DIETITIAN_ORDER_LIST_URL) {
            @Override
            public void onSuccess(Response<BaseResponse<OrderListResponse>> response) {
                BaseResponse body = response.body();
                if(body == null)return;
                if(body.isState()){
                    OrderListResponse data = (OrderListResponse) body.getData();
                    if(data==null){return;}
                    setData(isRefresh,data.getOrderList());
                }
            }
        });
    }

    @Override
    public BaseQuickAdapter<ServiceOrderBean, BaseViewHolder> initAdapter() {
        ServiceOrderAdapter adapter = new ServiceOrderAdapter(R.layout.item_service_order, null);

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                ServiceOrderBean bean = (ServiceOrderBean) adapter.getData().get(position);
                if(R.id.action_accept == view.getId()){ //同意服务
                    DieticianServiceOrderMgr.getInstance().onAccept(DietitianServiceOrderAll.this,bean);
                }else if(R.id.action_refuse == view.getId()){//拒绝服务
                    DieticianServiceOrderMgr.getInstance().onRefuse(DietitianServiceOrderAll.this,bean);
                }else if(R.id.action_send_message == view.getId()){//发送消息
                    DieticianServiceOrderMgr.getInstance().onSendMessage(bean);
                }else if(R.id.action_check_evaluate == view.getId()){//查看评价
                    DieticianServiceOrderMgr.getInstance().onCheckEvaluate(DietitianServiceOrderAll.this,bean);
                }
            }
        });

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ServiceOrderBean data = (ServiceOrderBean) adapter.getData().get(position);
                if(data==null){return;}
                Intent intent = new Intent();
                intent.putExtra(Constants.ORDER_ID,data.getOrderId());
                intent.setClass(getContext(),GoodsOrderDetailActivity.class);
                startActivity(intent);
            }
        });
        return adapter;
    }
}