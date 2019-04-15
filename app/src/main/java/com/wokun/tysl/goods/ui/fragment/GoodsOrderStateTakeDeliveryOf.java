package com.wokun.tysl.goods.ui.fragment;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.shantoo.widget.recyclerview.MItemDecoration;
import com.wokun.tysl.R;
import com.wokun.tysl.TyslApp;
import com.wokun.tysl.base.BaseRefreshAndLoadMoreFragment;
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

import butterknife.BindView;

//商品订单状态待收货
public class GoodsOrderStateTakeDeliveryOf extends SimpleRefreshAndLoadMoreFragment<GoodsOrderBean> {

    private GoodsOrderAdapter mAdapter;

    @Override
    public boolean hasItemDecoration() {
        return false;
    }

    @Override
    public Request initRequest() {
        int dietitianId = TyslApp.getInstance().getUserInfo().getDietitianId();
        return OkGo.<BaseResponse<OrderStateListResponse>>post(Constants.BASE_URL + Constants.ORDER_STATE_DATA_URL)
                .tag(this)
                .params(Constants.ORDER_STATE, 3);
    }

    @Override
    public BaseQuickAdapter<GoodsOrderBean, BaseViewHolder> initAdapter() {
        mAdapter = new GoodsOrderAdapter(R.layout.item_goods_order_body, R.layout.item_goods_order_header, R.layout.item_goods_order_footer, null);

        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                GoodsOrderBean bean = (GoodsOrderBean) adapter.getData().get(position);
                if(R.id.action_confirm_receipt == view.getId()){
                    GoodsOrderMgr.getInstance().onConfirmReceipt(GoodsOrderStateTakeDeliveryOf.this,bean);
                }
            }
        });

        mAdapter.setOnItemClickListener(new GoodsOrderAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(GoodsOrderBean item) {
                Intent intent = new Intent();
                intent.setClass(getContext(),GoodsOrderDetailActivity.class);
                intent.putExtra(Constants.ORDER_ID,item.getOrder_id());
                startActivity(intent);
            }
        });
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
