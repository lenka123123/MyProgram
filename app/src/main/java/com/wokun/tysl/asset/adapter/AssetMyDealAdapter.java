package com.wokun.tysl.asset.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wokun.tysl.R;
import com.wokun.tysl.asset.bean.MyDealBean;

import java.util.List;

public class AssetMyDealAdapter extends BaseQuickAdapter<MyDealBean,BaseViewHolder>{

    private int state;

    public AssetMyDealAdapter(@LayoutRes int layoutResId, @Nullable List<MyDealBean> data) {
        super(layoutResId, data);
    }

    public AssetMyDealAdapter(@LayoutRes int layoutResId, @Nullable List<MyDealBean> data, int state) {
        super(layoutResId, data);
        this.state = state;
    }

    @Override
    protected void convert(BaseViewHolder helper, MyDealBean item) {
        helper.setText(R.id.numbers,item.getNumbers())
              .setText(R.id.unit_price,item.getUnitPrice())
              .setText(R.id.create_time,item.getCreateTime())
              .setText(R.id.tv_create_time,"交易时间:");

        if(state==2){
            helper.setText(R.id.tv_username,"买家昵称:")
                  .setText(R.id.username,item.getBuyerName())
                  .setText(R.id.tv_unit_price,"卖出单价:")
                  .setText(R.id.tv_numbers,"卖出数量:");
        }else if(state==1){
            helper.setText(R.id.tv_username,"卖家昵称:")
                  .setText(R.id.username,item.getSellerName())
                  .setText(R.id.tv_unit_price,"买入单价:")
                  .setText(R.id.tv_numbers,"买入数量:");
        }
    }
}
