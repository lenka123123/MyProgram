package com.wokun.tysl.goods.controller;

import android.content.Intent;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shantoo.widget.toast.RxToast;
import com.wokun.tysl.base.BaseRefreshAndLoadMoreFragment;
import com.wokun.tysl.callback.JsonCallback;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.goods.bean.GoodsOrderBean;
import com.wokun.tysl.goods.ui.activity.GoodsEvaluateActivity;
import com.wokun.tysl.model.response.BaseResponse;
import com.wokun.tysl.order.bean.ZhihuiGenBean;
import com.wokun.tysl.other.ui.OnlinePaymentActivity;

public class GoodsOrderMgr {

    private GoodsOrderMgr(){

    }

    private static class GoodsOrderMgrHolder{
        private static GoodsOrderMgr instance = new GoodsOrderMgr();
    }

    public static GoodsOrderMgr getInstance(){
        return GoodsOrderMgrHolder.instance;
    }

    //立即支付
    public void onImmediatePayment(final BaseRefreshAndLoadMoreFragment fragment, GoodsOrderBean goodsOrder) {
        final String orderAmount = goodsOrder.getOrder_amount();
        final String shippingFee = goodsOrder.getShipping_fee();
        final String truePrice = String.valueOf(Double.valueOf(orderAmount) + Double.valueOf(shippingFee));

        Intent intent = new Intent();
        intent.putExtra(Constants.ORDER_ID,goodsOrder.getOrder_id());
        intent.putExtra(Constants.PAY_PRICE,truePrice);
        intent.putExtra(Constants.ONLINE_PAYMENT_URL, Constants.ORDER_PAY_URL);
        intent.setClass(fragment.getContext(),OnlinePaymentActivity.class);
        fragment.startActivity(intent);
    }

    //取消订单
    public void onCancelOrder(final BaseRefreshAndLoadMoreFragment fragment, GoodsOrderBean goodsOrder) {
        OkGo.<BaseResponse<Object>>post(Constants.BASE_URL + Constants.ORDER_CANCEL_ORDER_URL)
                .tag(this)
                .params(Constants.ORDER_ID, goodsOrder.getOrder_id())
                .execute(new JsonCallback<BaseResponse<Object>>(Constants.WITH_TOKEN,Constants.ORDER_CANCEL_ORDER_URL) {
                    @Override
                    public void onSuccess(Response<BaseResponse<Object>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;

                        if(body.isState()){
                            fragment.loadData(true);
                        }
                        RxToast.showShort(body.getMsg());
                    }
                });
    }

    //确认收货
    public void onConfirmReceipt(final BaseRefreshAndLoadMoreFragment fragment, GoodsOrderBean goodsOrder) {
        OkGo.<BaseResponse<Object>>post(Constants.BASE_URL + Constants.ORDER_CONFIRM_ACCEPT_URL)
            .tag(this)
            .params(Constants.ORDER_ID, goodsOrder.getOrder_id())
            .execute(new JsonCallback<BaseResponse<Object>>(Constants.WITH_TOKEN,Constants.ORDER_CONFIRM_ACCEPT_URL) {
                @Override
                public void onSuccess(Response<BaseResponse<Object>> response) {
                    BaseResponse body = response.body();
                    if(body == null)return;

                    if(body.isState()){
                        fragment.loadData(true);
                    }
                    RxToast.showShort(body.getMsg());
                }
        });
    }

    //评价
    public void onEvaluate(BaseRefreshAndLoadMoreFragment fragment, GoodsOrderBean goodsOrder) {
        Intent intent = new Intent();
        intent.putExtra(Constants.DATA,goodsOrder.getGoods());
        intent.putExtra(Constants.ORDER_ID,goodsOrder.getOrder_id());
        intent.setClass(fragment.getContext(),GoodsEvaluateActivity.class);
        fragment.startActivity(intent);
    }

   //标记跟进

    public void onGenjinState(final BaseRefreshAndLoadMoreFragment fragment, ZhihuiGenBean goodsOrder) {
        OkGo.<BaseResponse<Object>>post(Constants.BASE_URL + Constants.RETAIL_STORE_FOLLOW)
                .tag(this)
                .params(Constants.ORDER_ID, goodsOrder.getOrder_id())
                .params("sub_oid",goodsOrder.getSub_oid())
                .execute(new JsonCallback<BaseResponse<Object>>(Constants.WITH_TOKEN,Constants.RETAIL_STORE_FOLLOW) {
                    @Override
                    public void onSuccess(Response<BaseResponse<Object>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;

                        if(body.isState()){
                            fragment.loadData(true);
                        }
                        RxToast.showShort(body.getMsg());
                    }
                });
    }







}
