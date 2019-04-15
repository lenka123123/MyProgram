package com.wokun.tysl.ucenter.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wokun.tysl.R;
import com.wokun.tysl.ucenter.bean.UserServiceOrder;
import com.wokun.tysl.utils.ImageLoader;

import java.util.List;

public class MyHealthAdapter2 extends BaseQuickAdapter<UserServiceOrder,BaseViewHolder> {

    public MyHealthAdapter2(@LayoutRes int layoutResId, @Nullable List<UserServiceOrder> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final UserServiceOrder item) {
        ImageLoader.loadImage(item.getHeadLogo(), (ImageView) helper.getView(R.id.head_logo));
        helper.setText(R.id.truename,item.getTrueName())
        .setText(R.id.jobType,item.getJobType())
        .setText(R.id.order_amount,"¥"+item.getOrderAmount() + "/30天")
        //.setText(R.id.field,item.getField())
        .setText(R.id.finish_time,"服务结束："+ item.getFinishTime())
       // helper.setText(R.id.action,"发起咨询");
        .addOnClickListener(R.id.action_consult)
        .getView(R.id.action_consult).setVisibility(View.VISIBLE);
    }
}
