package com.wokun.tysl.ucenter.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wokun.tysl.R;
import com.wokun.tysl.TyslApp;
import com.wokun.tysl.ucenter.bean.MoneyDetail;

import java.util.List;

public class MoneyDetailAdapter extends BaseQuickAdapter<MoneyDetail, BaseViewHolder> {

    public MoneyDetailAdapter(@LayoutRes int layoutResId, @Nullable List data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final MoneyDetail item) {
        //type类型: 1资产买进花费 2资产卖出收入 3充值

        if(item.getType()==1){//资产买进花费
            Glide.with(TyslApp.getContext())
                    .load(R.drawable.ic_money_detail_buy)
                    .into((ImageView) helper.getView(R.id.type_image));
        }else if(item.getType()==2){//资产卖出收入
            Glide.with(TyslApp.getContext())
                    .load(R.drawable.ic_money_detail_sell)
                    .into((ImageView) helper.getView(R.id.type_image));
        }else if(item.getType()==3){//充值
            Glide.with(TyslApp.getContext())
                    .load(R.drawable.ic_money_detail_rush)
                    .into((ImageView) helper.getView(R.id.type_image));
        }

        helper.setText(R.id.tv_type_name,item.getType_name())
        .setText(R.id.tv_create_time,item.getCreate_time())
        .setText(R.id.tv_money,item.getMoney());
    }
}