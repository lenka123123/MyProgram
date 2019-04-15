package com.wokun.tysl.earnings.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wokun.tysl.R;
import com.wokun.tysl.earnings.bean.Bank;

import java.util.List;

public class SelectBankAdapter extends BaseQuickAdapter<Bank, BaseViewHolder> {

    public SelectBankAdapter(@LayoutRes int layoutResId, @Nullable List<Bank> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Bank item) {
        helper.setText(R.id.bank_name, item.getBank_name());
    }
}
