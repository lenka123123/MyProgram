package com.wokun.tysl.mypersonmoney.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wokun.tysl.R;
import com.wokun.tysl.mypersonmoney.bean.MylastMoneyBean;

import java.util.List;

/**
 * Created by Administrator on 2018/9/6 0006.
 */

public class MylastmoneyAdapter  extends BaseQuickAdapter<MylastMoneyBean, BaseViewHolder> {
    public MylastmoneyAdapter(int layoutResId, @Nullable List<MylastMoneyBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MylastMoneyBean item) {
        helper.setText(R.id.item_yue,  item.getSource_type())
                .setText(R.id.item_time, item.getCreate_time())
                .setText(R.id.item_price,item.getIntegral());

    }
}
