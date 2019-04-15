package com.wokun.tysl.goods.ui.fragment;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

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
import com.wokun.tysl.goods.adapter.GoodsOrderAdapter;
import com.wokun.tysl.goods.bean.GoodsBean;
import com.wokun.tysl.goods.bean.GoodsOrderBean;
import com.wokun.tysl.goods.controller.GoodsOrderMgr;
import com.wokun.tysl.goods.response.OrderStateListResponse;
import com.wokun.tysl.goods.ui.activity.GoodsOrderDetailActivity;
import com.wokun.tysl.model.response.BaseResponse;

import java.util.ArrayList;
import java.util.List;

public class GoodsOrderStateAll extends SimpleRefreshAndLoadMoreFragment<GoodsOrderBean> {

    private GoodsOrderAdapter mAdapter;


    @Override
    public boolean hasItemDecoration() {
        return false;
    }

    public Request initRequest() {
        int dietitianId = TyslApp.getInstance().getUserInfo().getDietitianId();
        return OkGo.<BaseResponse<OrderStateListResponse>>post(Constants.BASE_URL + Constants.ORDER_STATE_DATA_URL)
                .tag(this)
                .params(Constants.ORDER_STATE, 0);
    }

    @Override
    public BaseQuickAdapter<GoodsOrderBean, BaseViewHolder> initAdapter() {
        mAdapter = new GoodsOrderAdapter(R.layout.item_goods_order_body, R.layout.item_goods_order_header, R.layout.item_goods_order_footer, null);

        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                GoodsOrderBean bean = (GoodsOrderBean) adapter.getData().get(position);
                if(R.id.action_immediate_payment == view.getId()){//立即付款
                    GoodsOrderMgr.getInstance().onImmediatePayment(GoodsOrderStateAll.this,bean);
                }else if(R.id.action_cancel_order == view.getId()){//取消订单
                    GoodsOrderMgr.getInstance().onCancelOrder(GoodsOrderStateAll.this,bean);
                }else if(R.id.action_evaluate == view.getId()){//立即评价
                    GoodsOrderMgr.getInstance().onEvaluate(GoodsOrderStateAll.this,bean);
                }else if(R.id.action_confirm_receipt == view.getId()){//确认收货
                    GoodsOrderMgr.getInstance().onConfirmReceipt(GoodsOrderStateAll.this,bean);
                }else  if(R.id.action_send_goods == view.getId()){
                     Log.e("2312314","222222");
                    Toast.makeText(getContext(), "提醒成功", Toast.LENGTH_SHORT).show();
                }else  if(R.id.action_order_detail == view.getId()){
                    Intent intent = new Intent();
                    intent.putExtra(Constants.ORDER_ID,bean.getOrder_id());
                    intent.setClass(getContext(),GoodsOrderDetailActivity.class);
                    startActivity(intent);
                }


            }
        });

   /*     mAdapter.setOnItemClickListener(new GoodsOrderAdapter.OnItemClickListener() {//商品详情订单
            @Override
            public void onItemClick(GoodsOrderBean item) {
                Intent intent = new Intent();
                intent.putExtra(Constants.ORDER_ID,item.getOrder_id());
                intent.setClass(getContext(),GoodsOrderDetailActivity.class);
                startActivity(intent);
            }
        });*/
        return mAdapter;
    }

    @Override
    public void loadData(final boolean isRefresh) {
        mRequest.execute(new JsonCallback<BaseResponse<OrderStateListResponse>>(Constants.WITH_TOKEN,Constants.ORDER_STATE_DATA_URL) {
            @Override
            public void onSuccess(Response<BaseResponse<OrderStateListResponse>> response) {
                BaseResponse body = response.body();
                if(body == null)return;
                if(body.isState()){
                    OrderStateListResponse data = (OrderStateListResponse) body.getData();
                    if(data==null){return;}
                    List<GoodsOrderBean> list = data.getOrderStateList();
                    List<GoodsOrderBean> newData = new ArrayList<>();
                    if (list != null) {
                        for (GoodsOrderBean goodsOrder : list) {
                            newData.add(new GoodsOrderBean(mAdapter.HEADER, goodsOrder.getStore_name(), goodsOrder.getState_zh(),goodsOrder.getOrder_id()));
                            ArrayList<GoodsBean> subBeanList = goodsOrder.getGoods();
                            if (subBeanList != null) {
                                for (GoodsBean goods : subBeanList) {
                                    newData.add(new GoodsOrderBean(mAdapter.BODY, goods,goodsOrder.getOrder_id()));
                                }
                            }
                            newData.add(new GoodsOrderBean(mAdapter.FOOTER, goodsOrder.getGoodsAmount(), goodsOrder.getShipping_fee(), goodsOrder.getOrder_state(),goodsOrder.getOrder_id(),subBeanList));
                        }
                    }
                    setData(isRefresh, newData);
                }
            }
        });
    }
}