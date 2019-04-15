package com.wokun.tysl.dietician.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wokun.tysl.R;
import com.wokun.tysl.dietician.bean.DieticianFieldTypeBean;

import java.util.List;

public class DietitianFieldTypeAdapter extends BaseQuickAdapter<DieticianFieldTypeBean, BaseViewHolder> {

    public DietitianFieldTypeAdapter(@LayoutRes int layoutResId, @Nullable List<DieticianFieldTypeBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DieticianFieldTypeBean item) {
        helper.setText(R.id.title, item.getFieldName());
    }
}
