package com.wokun.tysl.dietician.adapter;


import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wokun.tysl.R;
import com.wokun.tysl.dietician.bean.DieticianJobTypeBean;

import java.util.List;

public class DietitianJobTypeAdapter extends BaseQuickAdapter<DieticianJobTypeBean, BaseViewHolder> {

    public DietitianJobTypeAdapter(@LayoutRes int layoutResId, @Nullable List<DieticianJobTypeBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DieticianJobTypeBean item) {
        helper.setText(R.id.title, item.getTypeName());
    }
}
