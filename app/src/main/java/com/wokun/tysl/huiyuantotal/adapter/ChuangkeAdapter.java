package com.wokun.tysl.huiyuantotal.adapter;

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
import com.wokun.tysl.huiyuantotal.bean.ChuankeBean;
import com.wokun.tysl.huiyuantotal.bean.ChuankeDetaiBean;
import com.wokun.tysl.utils.ImageLoader;

import java.util.List;

public class ChuangkeAdapter extends BaseHeadAndFooterAdapter<ChuankeBean, BaseViewHolder> {

    public ChuangkeAdapter(@LayoutRes int layoutResId, @LayoutRes int headerResId, @LayoutRes int footerResId, @Nullable List<ChuankeBean> data) {
        super(layoutResId, headerResId, footerResId, data);
    }

    @Override
    protected void convertHead(final BaseViewHolder helper, final ChuankeBean item) {
       helper.setText(R.id.zhihui_dinbannum, "订单编号："+item.getOrder_sn())
              .setText(R.id.zhihui_dindan_time, item.getPay_time());

     /*   helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(item);
            }
        });*/
    }

    @Override
    protected void convertFoot(final BaseViewHolder helper, final ChuankeBean item) {
        TextView zhihui_ft_phone = helper.getView(R.id.zhihui_ft_phone);
        TextView zhihui_ft_name = helper.getView(R.id.zhihui_ft_name);
        TextView zhihui_ft_number = helper.getView(R.id.zhihui_ft_number);
        TextView zhihui_ft_price = helper.getView(R.id.zhihui_ft_price);

        helper.setText(R.id.zhihui_ft_phone, item.getMobile())
                .setText(R.id.zhihui_ft_name, item.getUsername())
                .setText(R.id.zhihui_ft_number,"共"+ item.getNums()+"件商品，")
                .setText(R.id.zhihui_ft_price, "合计收益：￥"+item.getReward());

     /*helper.setText(R.id.order_amount, "￥" + item.getOrder_amount())
      //  .setText(R.id.shipping_fee, "￥" + item.getShipping_fee())
        .addOnClickListener(R.id.action_immediate_payment)
        .addOnClickListener(R.id.action_cancel_order)
        .addOnClickListener(R.id.action_evaluate)
        .addOnClickListener(R.id.action_confirm_receipt)
        .addOnClickListener(R.id.action_send_goods);*/

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

       /* helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(item);
            }
        });*/
    }

    @Override
    protected void convert(final BaseViewHolder helper, final ChuankeBean item) {
        ChuankeDetaiBean goods = item.t;
        ImageView imageView = helper.getView(R.id.goods_picture);
        ImageLoader.loadImage(goods.getGoods_image(), imageView);
        helper.setText(R.id.goods_name, goods.getGoods_name())
        .setText(R.id.zhihui_price, "单价：￥" + goods.getGoods_price())
        .setText(R.id.zhihui_num, "数量：X" + goods.getGoods_num())
                .setText(R.id.goods_num, "收益：￥" + goods.getStore_maker_reward());

     /*   helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(item);
            }
        });*/
    }

    private OnItemClickListener mOnItemClickListener;
    public interface OnItemClickListener{
        void onItemClick(ChuankeBean item);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        mOnItemClickListener = listener;
    }

}
