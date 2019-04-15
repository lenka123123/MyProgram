package com.wokun.tysl.asset.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wokun.tysl.R;
import com.wokun.tysl.asset.bean.AssetReleaseBean;

import java.util.List;

public class AssetReleaseBuyOutAdapter extends BaseQuickAdapter<AssetReleaseBean, BaseViewHolder> {

    private int state;

    public AssetReleaseBuyOutAdapter(@LayoutRes int layoutResId, @Nullable List<AssetReleaseBean> data) {
        super(layoutResId, data);
    }

    public AssetReleaseBuyOutAdapter(@LayoutRes int layoutResId, @Nullable List<AssetReleaseBean> data, int state) {
        super(layoutResId, data);
        this.state = state;
    }

    @Override
    protected void convert(BaseViewHolder helper, AssetReleaseBean item) {
        helper.setText(R.id.username, item.getUserName())
                .setText(R.id.surplus_num, item.getSurplusNum())
                .setText(R.id.unit_price, item.getUnitPrice())
                .setText(R.id.create_time, item.getCreateTime())
                .setText(R.id.tv_create_time, "发布时间:")
                .addOnClickListener(R.id.action_asset_release_buy)
                .addOnClickListener(R.id.action_asset_release_sell);

        TextView buy = helper.getView(R.id.action_asset_release_buy);
        TextView sell = helper.getView(R.id.action_asset_release_sell);

        if (state == 1) {
            helper.setText(R.id.tv_username, "买家昵称:")
                  .setText(R.id.tv_surplus_num, "买入数量:")
                  .setText(R.id.tv_unit_price, "买入单价:")
                  .setText(R.id.action_asset_release_sell, "我要出售");
            buy.setVisibility(View.GONE);
            sell.setVisibility(View.VISIBLE);
        } else if (state == 2) {
            helper.setText(R.id.tv_username, "卖家昵称:")
                  .setText(R.id.tv_surplus_num, "卖出数量:")
                  .setText(R.id.tv_unit_price, "卖出单价:")
                  .setText(R.id.action_asset_release_buy, "我要买入");
            buy.setVisibility(View.VISIBLE);
            sell.setVisibility(View.GONE);
        }
    }
}