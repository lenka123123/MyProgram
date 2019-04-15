package com.wokun.tysl.stock.fragnent;

import android.content.Intent;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.request.base.Request;
import com.shantoo.widget.toast.RxToast;
import com.wokun.tysl.R;
import com.wokun.tysl.TyslApp;
import com.wokun.tysl.base.SimpleRefreshAndLoadMoreFragment;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.goods.adapter.GoodsOrderAdapter;
import com.wokun.tysl.goods.bean.GoodsOrderBean;
import com.wokun.tysl.goods.controller.GoodsOrderMgr;
import com.wokun.tysl.goods.ui.activity.GoodsOrderDetailActivity;
import com.wokun.tysl.goods.ui.fragment.GoodsOrderStateAll;
import com.wokun.tysl.model.response.BaseResponse;
import com.wokun.tysl.model.response.OrderListResponse;
import com.wokun.tysl.order.bean.MyOrderBean;
import com.wokun.tysl.stock.adapter.MyReceiptAdapter;


import java.util.ArrayList;

//历史进货单
public class HistoryReceipt extends SimpleRefreshAndLoadMoreFragment<MyOrderBean> {

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
//        mRequest.execute(new JsonCallback<BaseResponse<OrderListResponse>>(Constants.WITH_TOKEN,Constants.DIETITIAN_ORDER_LIST_URL) {
//            @Override
//            public void onSuccess(Response<BaseResponse<OrderListResponse>> response) {
//                BaseResponse body = response.body();
//                if(body == null)return;
//                if(body.isState()){
//                    OrderListResponse data = (OrderListResponse) body.getData();
//                    if(data==null){return;}
//                    setData(isRefresh,data.getOrderList());
//                }
//            }
//        });
        ArrayList<MyOrderBean> data = new ArrayList<>();
        MyOrderBean myOrderBean = new MyOrderBean();
        myOrderBean.setStartTime("2018-05-06");
        myOrderBean.setOrderAmount(4);
        myOrderBean.setOrderId("666561");
        myOrderBean.setOrderProfit("244.00");
        myOrderBean.setorderPrice("244.00");
        myOrderBean.setUserId("33");
        myOrderBean.setUserName("历史下单");
        myOrderBean.setUserPhone("1484531351");
        myOrderBean.setOrderState("0");
        data.add(myOrderBean);
        setData(isRefresh,data);
    }

    @Override
    public BaseQuickAdapter<MyOrderBean, BaseViewHolder> initAdapter() {
        MyReceiptAdapter adapter = new MyReceiptAdapter(R.layout.item_service_my_receipt, null);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                MyOrderBean bean = (MyOrderBean) adapter.getData().get(position);
                if(R.id.txtClick == view.getId()){
                    RxToast.showShort("查看物流");
                }
            }
        });

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                RxToast.showShort("点击第"+position+"条数据");
            }
        });
        return adapter;
    }
}