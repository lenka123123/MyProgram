package com.wokun.tysl.dietician.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shantoo.common.utils.UIUtil;
import com.wokun.tysl.R;
import com.wokun.tysl.dietician.bean.ServiceOrderBean;
import com.wokun.tysl.utils.ImageLoader;

import java.util.List;

public class ServiceOrderAdapter extends BaseQuickAdapter<ServiceOrderBean, BaseViewHolder> {

    public ServiceOrderAdapter(@LayoutRes int layoutResId, @Nullable List<ServiceOrderBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final ServiceOrderBean item) {
        TextView state = helper.getView(R.id.state);

        TextView action_accept = helper.getView(R.id.action_accept);
        TextView action_refuse = helper.getView(R.id.action_refuse);
        TextView action_send_message = helper.getView(R.id.action_send_message);
        TextView action_check_evaluate = helper.getView(R.id.action_check_evaluate);

        ImageLoader.loadImage(item.getAvatar(), (ImageView) helper.getView(R.id.logo));

        helper.setText(R.id.name, item.getUserName())
        .setText(R.id.service_time, "服务时间:  " + item.getStartTime() + "-" + item.getFinishTime())
        .setText(R.id.service_long, "服务时长:  " + item.getServiceLastTime()+"天")
        .setText(R.id.price, "￥ " + item.getOrderAmount())
        .addOnClickListener(R.id.action_accept)//确认服务
        .addOnClickListener(R.id.action_refuse)//拒绝服务
        .addOnClickListener(R.id.action_send_message)//发送消息
        .addOnClickListener(R.id.action_check_evaluate);//查看评价

        if ("2".equals(item.getOrderState())) {
            state.setText("待确认");
            state.setTextColor(UIUtil.getColor(R.color.colorPrimary));
            action_refuse.setVisibility(View.VISIBLE);
            action_accept.setVisibility(View.VISIBLE);
            action_send_message.setVisibility(View.GONE);
            action_check_evaluate.setVisibility(View.GONE);
        } else if ("6".equals(item.getOrderState())) {
            state.setText("已关闭");
            action_refuse.setVisibility(View.GONE);
            action_accept.setVisibility(View.GONE);
            action_send_message.setVisibility(View.GONE);
            action_check_evaluate.setVisibility(View.GONE);
        } else if ("3".equals(item.getOrderState())) {
            state.setText("服务中");
            action_refuse.setVisibility(View.GONE);
            action_accept.setVisibility(View.GONE);
            action_send_message.setVisibility(View.VISIBLE);
            action_check_evaluate.setVisibility(View.GONE);
        } else if ("4".equals(item.getOrderState())) {
            state.setText("服务结束未评价");
            action_refuse.setVisibility(View.GONE);
            action_accept.setVisibility(View.GONE);
            action_send_message.setVisibility(View.GONE);
            action_check_evaluate.setVisibility(View.GONE);
        } else if ("5".equals(item.getOrderState())) {
            state.setText("服务结束已评价");
            action_refuse.setVisibility(View.GONE);
            action_accept.setVisibility(View.GONE);
            action_send_message.setVisibility(View.GONE);
            action_check_evaluate.setVisibility(View.VISIBLE);
        }
    }
}
