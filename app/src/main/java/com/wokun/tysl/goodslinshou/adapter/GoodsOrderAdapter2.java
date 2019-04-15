package com.wokun.tysl.goodslinshou.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.wokun.tysl.R;
import com.wokun.tysl.base.BaseHeadAndFooterAdapter;
import com.wokun.tysl.goodslinshou.bean.GoodsZHBean;
import com.wokun.tysl.goodslinshou.bean.GoodsZhihuibean;
import com.wokun.tysl.goodslinshou.bean.ZhihuiDetaiBean;
import com.wokun.tysl.utils.ImageLoader;

import java.util.List;

public class GoodsOrderAdapter2 extends BaseHeadAndFooterAdapter<GoodsZhihuibean, BaseViewHolder> {

    public GoodsOrderAdapter2(@LayoutRes int layoutResId, @LayoutRes int headerResId, @LayoutRes int footerResId, @Nullable List<GoodsZhihuibean> data) {
        super(layoutResId, headerResId, footerResId, data);
    }

    @Override
    protected void convertHead(final BaseViewHolder helper, final GoodsZhihuibean item) {
        helper.setText(R.id.store_name, item.getStore_name());

        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(item);
            }
        });

        if( item.getOrder_state().equals("4")){
            helper.setText(R.id.order_state,"待自提");

        } else if (item.getOrder_state().equals("2")){
            helper.setText(R.id.order_state,"待发货");
        }
        else if (item.getOrder_state().equals("3")){
            helper.setText(R.id.order_state,"待收货");
        }
        else if (item.getOrder_state().equals("5")){
            helper.setText(R.id.order_state,"待评价");
         }
        else if (item.getOrder_state().equals("6")){
            helper.setText(R.id.order_state,"已完成");

        }
    }

    @Override
    protected void convertFoot(final BaseViewHolder helper, final GoodsZhihuibean item) {
        TextView actionImmediatePayment = helper.getView(R.id.action_immediate_payment);
        TextView actionCancelOrder = helper.getView(R.id.action_cancel_order);
        TextView actionEvaluate = helper.getView(R.id.action_evaluate);
        TextView actionConfirmReceipt = helper.getView(R.id.action_confirm_receipt);
        TextView actionSendGoods = helper.getView(R.id.action_send_goods);

                 List<GoodsZHBean> subOrder = item.getSubOrder();
                  int totleSum=0;

                 for (int i = 0; i <subOrder.size(); i++) {
                     totleSum    +=  Integer.parseInt(subOrder.get(i).getGoods_num());
                 }
                 helper.setText(R.id.zhihui_total_goods, "共"+totleSum+"件商品");

//        Log.e("subOrder.size",""+subOrder.size());




        helper.setText(R.id.order_amount, "￥" + item.getPay_price())
      //  .setText(R.id.shipping_fee, "￥" + item.getShipping_fee())
        .addOnClickListener(R.id.action_immediate_payment)
        .addOnClickListener(R.id.action_cancel_order)
        .addOnClickListener(R.id.action_evaluate)
        .addOnClickListener(R.id.action_confirm_receipt)
        .addOnClickListener(R.id.action_send_goods);

        //订单状态
        if(item.getOrder_state().equals("4")){//待自提
            actionCancelOrder.setVisibility(View.GONE);//取消订单
            actionImmediatePayment.setVisibility(View.GONE);//立即付款
            actionEvaluate.setVisibility(View.GONE);//评价
            actionConfirmReceipt.setVisibility(View.GONE);//确认收货
            actionSendGoods.setVisibility(View.GONE);
        }else if(item.getOrder_state().equals("2")){//待发货
            actionCancelOrder.setVisibility(View.GONE);//取消订单
            actionSendGoods.setVisibility(View.VISIBLE);
            actionImmediatePayment.setVisibility(View.GONE);
            actionEvaluate.setVisibility(View.GONE);
            actionConfirmReceipt.setVisibility(View.GONE);
        }else if(item.getOrder_state().equals("3")){//待收货
            actionSendGoods.setVisibility(View.GONE);
            actionConfirmReceipt.setVisibility(View.VISIBLE);
            actionCancelOrder.setVisibility(View.GONE);//取消订单
            actionImmediatePayment.setVisibility(View.GONE);
            actionEvaluate.setVisibility(View.GONE);
        }else if(item.getOrder_state().equals("5")){//待评价
            actionSendGoods.setVisibility(View.GONE);
            actionEvaluate.setVisibility(View.VISIBLE);
            actionConfirmReceipt.setVisibility(View.GONE);
            actionCancelOrder.setVisibility(View.GONE);
            actionImmediatePayment.setVisibility(View.GONE);
        }

        final String order_id = item.getOrder_id();
        final String order_amount = item.getPay_price();
      //  final String shipping_fee = "0";
        final String true_price = String.valueOf(Double.valueOf(order_amount) );


        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(item);
            }
        });
    }

    @Override
    protected void convert(final BaseViewHolder helper, final GoodsZhihuibean item) {

       GoodsZHBean goods = (GoodsZHBean) item.t;
        ImageView imageView = helper.getView(R.id.goods_picture);
        ImageLoader.loadImage(goods.getGoods_image(), imageView);
        helper.setText(R.id.goods_name, goods.getGoods_name())
        .setText(R.id.goods_pay_price, "￥" + goods.getGoods_price())
        .setText(R.id.goods_num, "x" + goods.getGoods_num());

        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(item);
            }
        });





    }

    private OnItemClickListener mOnItemClickListener;
    public interface OnItemClickListener{
        void onItemClick(GoodsZhihuibean item);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        mOnItemClickListener = listener;
    }

}
