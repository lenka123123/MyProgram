package com.wokun.tysl.earnings.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wokun.tysl.R;
import com.wokun.tysl.model.bean.ProfitLog;

import java.util.List;

public class EarningsDetailedAdapter extends BaseQuickAdapter<ProfitLog, BaseViewHolder> {

    public EarningsDetailedAdapter(@LayoutRes int layoutResId, @Nullable List data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ProfitLog item) {
        helper.setText(R.id.source_type, item.getProfit_source_type())
        .setText(R.id.create_time, item.getLog_time())
        .setText(R.id.integral, item.getProfit_money());
    }
}
