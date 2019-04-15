package com.wokun.tysl.goodslinshou.fragment;

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
import com.wokun.tysl.goods.adapter.GoodsOrderAdapter;
import com.wokun.tysl.goods.bean.GoodsBean;
import com.wokun.tysl.goods.bean.GoodsOrderBean;
import com.wokun.tysl.goods.controller.GoodsOrderMgr;
import com.wokun.tysl.goods.response.OrderStateListResponse;
import com.wokun.tysl.goods.ui.activity.GoodsOrderDetailActivity;
import com.wokun.tysl.goodslinshou.adapter.GoodsOrderAdapter2;
import com.wokun.tysl.goodslinshou.bean.GoodsZHBean;
import com.wokun.tysl.goodslinshou.bean.GoodsZhihuibean;
import com.wokun.tysl.goodslinshou.bean.OrderStateListResponse2;
import com.wokun.tysl.goodslinshou.ui.GoodsOrderDetailActivity2;
import com.wokun.tysl.model.response.BaseResponse;

import java.util.ArrayList;
import java.util.List;

//智慧商品订单状态待收货
public class GoodsOrderlinshouStateTakeDeliveryOf extends SimpleRefreshAndLoadMoreFragment<GoodsZhihuibean> {

    private GoodsOrderAdapter2 mAdapter;

    @Override
    public boolean hasItemDecoration() {
        return false;
    }

    @Override
    public Request initRequest() {
        int dietitianId = TyslApp.getInstance().getUserInfo().getDietitianId();
        return OkGo.<BaseResponse<OrderStateListResponse2>>post(Constants.BASE_URL + Constants.USER_ORDER)
                .tag(this)
                .params("status", 3);
    }

    @Override
    public BaseQuickAdapter<GoodsZhihuibean, BaseViewHolder> initAdapter() {
        mAdapter = new GoodsOrderAdapter2(R.layout.item_goods_order_body, R.layout.item_goods_order_header, R.layout.item_goods_order_footer, null);

        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                GoodsZhihuibean bean = (GoodsZhihuibean) adapter.getData().get(position);
                if(R.id.action_confirm_receipt == view.getId()){
                   // GoodsOrderMgr.getInstance().onConfirmReceipt(GoodsOrderlinshouStateTakeDeliveryOf.this,bean);
                }
            }
        });

        mAdapter.setOnItemClickListener(new GoodsOrderAdapter2.OnItemClickListener() {
            @Override
            public void onItemClick(GoodsZhihuibean item) {
                Intent intent = new Intent();
                intent.setClass(getContext(),GoodsOrderDetailActivity2.class);
                intent.putExtra(Constants.ORDER_ID,item.getOrder_id());
                startActivity(intent);
            }
        });
        return mAdapter;
    }

    @Override
    public void loadData(final boolean isRefresh) {

        mRequest.execute(new JsonCallback<BaseResponse<OrderStateListResponse2>>(Constants.WITH_TOKEN,Constants.USER_ORDER) {
            @Override
            public void onSuccess(Response<BaseResponse<OrderStateListResponse2>> response) {
                BaseResponse body = response.body();
                if(body == null)return;

                if(body.isState()){
                    OrderStateListResponse2 data = (OrderStateListResponse2) body.getData();
                    if(data==null){return;}
                    List<GoodsZhihuibean> list = data.getOrder();
                    List<GoodsZhihuibean> newData = new ArrayList<>();
                    if (list != null) {
                        for (GoodsZhihuibean goodsOrder : list) {
                            newData.add(new GoodsZhihuibean(mAdapter.HEADER, goodsOrder.getStore_name(), goodsOrder.getOrder_state(),goodsOrder.getOrder_id()));
                            ArrayList<GoodsZHBean> subBeanList = (ArrayList<GoodsZHBean>) goodsOrder.getSubOrder();
                            if (subBeanList != null) {
                                for (GoodsZHBean goods : subBeanList) {
                                    newData.add(new GoodsZhihuibean(mAdapter.BODY, goods,goodsOrder.getOrder_id()));
                                }
                            }
                            newData.add(new GoodsZhihuibean(mAdapter.FOOTER, goodsOrder.getPay_price(),goodsOrder.getOrder_state(),goodsOrder.getStore_name(),goodsOrder.getOrder_id(),subBeanList));
                        }
                    }
                    setData(isRefresh, newData);
                }
            }
        });
    }
}
