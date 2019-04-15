package com.wokun.tysl.goodslinshou.fragment;

import android.content.Intent;
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
import com.wokun.tysl.goods.controller.GoodsOrderMgr;
import com.wokun.tysl.goodslinshou.adapter.GoodsOrderAdapter2;
import com.wokun.tysl.goodslinshou.bean.GoodsZHBean;
import com.wokun.tysl.goodslinshou.bean.GoodsZhihuibean;
import com.wokun.tysl.goodslinshou.bean.OrderStateListResponse2;
import com.wokun.tysl.goodslinshou.ui.GoodsOrderDetailActivity2;
import com.wokun.tysl.model.bean.User;
import com.wokun.tysl.model.response.BaseResponse;
import com.wokun.tysl.utils.SignUtil;

import java.util.ArrayList;
import java.util.List;

public class GoodsOrderlinshouStateAll extends SimpleRefreshAndLoadMoreFragment<GoodsZhihuibean> {
    private GoodsOrderAdapter2 mAdapter;
    @Override
    public boolean hasItemDecoration() {
        return false;
    }

    public Request initRequest() {
        User user = TyslApp.getInstance().getUser();
        //Log.e(TAG,user+"");
        String userId = user.getUserId();
        String token = user.getAccessToken();

        long time_stamp = System.currentTimeMillis();
        String sign = SignUtil.getSign(Constants.BASE_URL +  Constants.USER_ORDER, userId, token, time_stamp);
        Log.e("参数","userId:"+userId+"time_stamp:"+time_stamp+"sign:"+sign);
     //   int dietitianId = TyslApp.getInstance().getUserInfo().getDietitianId();
        return OkGo.<BaseResponse<OrderStateListResponse2>>post(Constants.BASE_URL + Constants.USER_ORDER)
                .tag(this)
                .params("status", 0)
                .params(Constants.USER_ID, userId)
                .params(Constants.TIME_STAMP, time_stamp)
                .params(Constants.SIGN, sign)
                .params("page", 1)
                .params("page_size", 10);


    }

    @Override
    public BaseQuickAdapter<GoodsZhihuibean, BaseViewHolder> initAdapter() {
        mAdapter = new GoodsOrderAdapter2(R.layout.item_goods_order_body, R.layout.item_goods_order_header, R.layout.item_goods_order_footer, null);
           mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                GoodsZhihuibean bean = (GoodsZhihuibean) adapter.getData().get(position);
                if(R.id.action_immediate_payment == view.getId()){//立即付款
         //    GoodsOrderMgr.getInstance().onImmediatePayment(GoodsOrderlinshouStateAll.this,bean);
                }else if(R.id.action_cancel_order == view.getId()){//取消订单
              //      GoodsOrderMgr.getInstance().onCancelOrder(GoodsOrderlinshouStateAll.this,bean);
                }else if(R.id.action_evaluate == view.getId()){//立即评价
             //       GoodsOrderMgr.getInstance().onEvaluate(GoodsOrderlinshouStateAll.this,bean);
                }else if(R.id.action_confirm_receipt == view.getId()){//确认收货
              //      GoodsOrderMgr.getInstance().onConfirmReceipt(GoodsOrderlinshouStateAll.this,bean);
                }else  if(R.id.action_send_goods == view.getId()){
                    Log.e("2312314","222222");
                    Toast.makeText(getContext(), "提醒成功", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mAdapter.setOnItemClickListener(new GoodsOrderAdapter2.OnItemClickListener() {//智慧商品详情订单
            @Override
            public void onItemClick(GoodsZhihuibean item) {
                Intent intent = new Intent();
                intent.putExtra(Constants.ORDER_ID,item.getOrder_id());
                intent.setClass(getContext(),GoodsOrderDetailActivity2.class);
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
                    Log.e("fwer3",data+"");
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

            @Override
            public void onError(Response<BaseResponse<OrderStateListResponse2>> response) {

                Log.e("21131d31",""+response);
                super.onError(response);
            }
        });
    }
}