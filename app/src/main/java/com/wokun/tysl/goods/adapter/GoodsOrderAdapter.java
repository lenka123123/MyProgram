package com.wokun.tysl.goods.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.wokun.tysl.R;
import com.wokun.tysl.base.BaseHeadAndFooterAdapter;
import com.wokun.tysl.goods.bean.GoodsBean;
import com.wokun.tysl.goods.bean.GoodsOrderBean;
import com.wokun.tysl.utils.ImageLoader;

import java.util.List;

public class GoodsOrderAdapter extends BaseHeadAndFooterAdapter<GoodsOrderBean, BaseViewHolder> {

    public GoodsOrderAdapter(@LayoutRes int layoutResId, @LayoutRes int headerResId, @LayoutRes int footerResId, @Nullable List<GoodsOrderBean> data) {
        super(layoutResId, headerResId, footerResId, data);
    }

    @Override
    protected void convertHead(final BaseViewHolder helper, final GoodsOrderBean item) {
        helper.setText(R.id.store_name, item.getStore_name())
        .setText(R.id.order_state, item.getState_zh());

   /*     helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(item);
            }
        });*/
    }

    @Override
    protected void convertFoot(final BaseViewHolder helper, final GoodsOrderBean item) {
        TextView actionImmediatePayment = helper.getView(R.id.action_immediate_payment);
        TextView actionCancelOrder = helper.getView(R.id.action_cancel_order);
        TextView actionEvaluate = helper.getView(R.id.action_evaluate);
        TextView actionConfirmReceipt = helper.getView(R.id.action_confirm_receipt);
        TextView actionSendGoods = helper.getView(R.id.action_send_goods);


        helper.setText(R.id.order_amount, "￥" + item.getOrder_amount())
      //  .setText(R.id.shipping_fee, "￥" + item.getShipping_fee())
        .addOnClickListener(R.id.action_immediate_payment)
        .addOnClickListener(R.id.action_cancel_order)
        .addOnClickListener(R.id.action_evaluate)
        .addOnClickListener(R.id.action_confirm_receipt)
        .addOnClickListener(R.id.action_send_goods)
        .addOnClickListener(R.id.action_order_detail);

        //订单状态
        if(1==item.getOrder_state()){//待付款
            actionCancelOrder.setVisibility(View.VISIBLE);//取消订单
            actionImmediatePayment.setVisibility(View.VISIBLE);//立即付款
            actionEvaluate.setVisibility(View.GONE);//评价
            actionConfirmReceipt.setVisibility(View.GONE);//确认收货
            actionSendGoods.setVisibility(View.GONE);
        }else if(2==item.getOrder_state()){//待发货
            actionCancelOrder.setVisibility(View.GONE);//取消订单
            actionSendGoods.setVisibility(View.VISIBLE);
            actionImmediatePayment.setVisibility(View.GONE);
            actionEvaluate.setVisibility(View.GONE);
            actionConfirmReceipt.setVisibility(View.GONE);
        }else if(3==item.getOrder_state()){//待收货
            actionSendGoods.setVisibility(View.GONE);
            actionConfirmReceipt.setVisibility(View.VISIBLE);
            actionCancelOrder.setVisibility(View.GONE);//取消订单
            actionImmediatePayment.setVisibility(View.GONE);
            actionEvaluate.setVisibility(View.GONE);
        }else if(4==item.getOrder_state()){//待评价
            actionSendGoods.setVisibility(View.GONE);
            actionEvaluate.setVisibility(View.VISIBLE);
            actionConfirmReceipt.setVisibility(View.GONE);
            actionCancelOrder.setVisibility(View.GONE);
            actionImmediatePayment.setVisibility(View.GONE);
        }

        final String order_id = item.getOrder_id();
        final String order_amount = item.getOrder_amount();
        final String shipping_fee = item.getShipping_fee();
        final String true_price = String.valueOf(Double.valueOf(order_amount) + Double.valueOf(shipping_fee));

        /*actionCancelOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logger.e("TAG",order_id+"");
                mOnCancelOrderListener.onCancelOrder(order_id);
            }
        });*/
        /*actionImmediatePayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnImmediatePaymentListener.onImmediatePayment(order_id, true_price);
            }
        });*/
        /*actionEvaluate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnEvaluateListener.onEvaluate(item);
            }
        });*/
        /*actionConfirmReceipt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnConfirmReceiptListener.onConfirmReceipt(order_id);
            }
        });*/

     /*   helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(item);
            }
        });*/
    }

    @Override
    protected void convert(final BaseViewHolder helper, final GoodsOrderBean item) {
        GoodsBean goods = item.t;
        ImageView imageView = helper.getView(R.id.goods_picture);
        ImageLoader.loadImage(goods.getGoodsImage(), imageView);
        helper.setText(R.id.goods_name, goods.getGoodsName())
        .setText(R.id.goods_pay_price, "￥" + goods.getGoodsPayPrice())
        .setText(R.id.goods_num, "x" + goods.getGoodsNum());

      /*  helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(item);
            }
        });*/
    }

    private OnItemClickListener mOnItemClickListener;
    public interface OnItemClickListener{
        void onItemClick(GoodsOrderBean item);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        mOnItemClickListener = listener;
    }
  /*  private OnCancelOrderListener mOnCancelOrderListener;
    private OnImmediatePaymentListener mOnImmediatePaymentListener;
    private OnEvaluateListener mOnEvaluateListener;
    private OnConfirmReceiptListener mOnConfirmReceiptListener;
    private OnSendGoodsListener msendgoodsListener;


    public interface OnCancelOrderListener {
        void onCancelOrder(String order_id);
    }

    public interface OnImmediatePaymentListener {
        void onImmediatePayment(String order_id, String true_price);
    }
    public interface OnSendGoodsListener{
        void OnSendGoods(String order_id, String true_price);
    }
    public interface OnEvaluateListener {
        void onEvaluate(GoodsOrderBean goodsOrder);
    }

    public interface OnConfirmReceiptListener {
        void onConfirmReceipt(String order_id);
    }



    public void setOnCancelOrderListener(OnCancelOrderListener listener) {
        this.mOnCancelOrderListener = listener;
    }

    public void setOnImmediatePaymentListener(OnImmediatePaymentListener listener) {
        this.mOnImmediatePaymentListener = listener;
    }

    public void setOnEvaluateListener(OnEvaluateListener listener) {
        this.mOnEvaluateListener = listener;
    }

    public void setOnConfirmReceiptListener(OnConfirmReceiptListener listener) {
        this.mOnConfirmReceiptListener = listener;
    }*/
}
