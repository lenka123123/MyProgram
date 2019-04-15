package com.wokun.tysl.order.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.wokun.tysl.R;
import com.wokun.tysl.base.BaseHeadAndFooterAdapter;
import com.wokun.tysl.order.bean.ZhihuiGenBean;
import com.wokun.tysl.order.bean.ZhihuiGenDetailBean;
import com.wokun.tysl.order.bean.ZhihuiOrderBean;
import com.wokun.tysl.order.bean.ZhihuiOrderDetaiBean;
import com.wokun.tysl.utils.ImageLoader;

import java.util.List;

public class ZhihuiGenAdapter extends BaseHeadAndFooterAdapter<ZhihuiGenBean, BaseViewHolder> {

    public ZhihuiGenAdapter(@LayoutRes int layoutResId, @LayoutRes int headerResId, @LayoutRes int footerResId, @Nullable List<ZhihuiGenBean> data) {
        super(layoutResId, headerResId, footerResId, data);
    }

    @Override
    protected void convertHead(final BaseViewHolder helper, final ZhihuiGenBean item) {
       helper.setText(R.id.zhihui_dinbannum, "付款时间："+item.getCreate_time())
              .setText(R.id.zhihui_dindan_time, item.getStore_follow());
         if(item.getStore_follow().equals("0")){
             TextView zhihui_dindan_genjin = helper.getView(R.id.zhihui_dindan_genjin);
             zhihui_dindan_genjin.setVisibility(View.VISIBLE);
             helper.addOnClickListener(R.id.zhihui_dindan_genjin);
         }else {
             TextView zhihui_dindan_genjin2 = helper.getView(R.id.zhihui_dindan_genjin2);
             zhihui_dindan_genjin2.setVisibility(View.VISIBLE);
             helper.addOnClickListener(R.id.zhihui_dindan_genjin2);
         }



    helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(item);
            }
        });
    }

    @Override
    protected void convertFoot(final BaseViewHolder helper, final ZhihuiGenBean item) {
        TextView zhihui_ft_phone = helper.getView(R.id.zhihui_ft_phone);
        TextView zhihui_ft_name = helper.getView(R.id.zhihui_ft_name);
        TextView zhihui_ft_number = helper.getView(R.id.zhihui_ft_number);
        TextView zhihui_ft_price = helper.getView(R.id.zhihui_ft_price);
        LinearLayout zhihui_ft_lin = helper.getView(R.id.zhihui_ft_lin);
        zhihui_ft_lin.setVisibility(View.GONE);

        helper.setText(R.id.zhihui_ft_phone, item.getMobile())
                .setText(R.id.zhihui_ft_name, item.getUsername())
        ;

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
    protected void convert(final BaseViewHolder helper, final ZhihuiGenBean item) {
        ZhihuiGenDetailBean goods = item.t;
        ImageView imageView = helper.getView(R.id.goods_picture);
        ImageLoader.loadImage(goods.getGoods_image(), imageView);
        helper.setText(R.id.goods_name, goods.getGoods_name())
        .setText(R.id.zhihui_price, "单价：￥" + goods.getGoods_price())
        .setText(R.id.zhihui_num, "数量：X" + goods.getGoods_num())
                .setText(R.id.goods_num, "收益：￥" + goods.getStore_reward());

     /*   helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(item);
            }
        });*/
    }

    private OnItemClickListener mOnItemClickListener;
    public interface OnItemClickListener{
       void onItemClick(ZhihuiGenBean item);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        mOnItemClickListener = listener;
    }

}
