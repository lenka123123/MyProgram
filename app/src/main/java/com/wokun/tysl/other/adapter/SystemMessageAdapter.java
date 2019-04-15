package com.wokun.tysl.other.adapter;


import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wokun.tysl.R;
import com.wokun.tysl.other.bean.SystemNotice;

import java.util.List;

public class SystemMessageAdapter extends BaseQuickAdapter<SystemNotice,BaseViewHolder> {

    public SystemMessageAdapter(@LayoutRes int layoutResId, @Nullable List<SystemNotice> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SystemNotice item) {
        helper.setText(R.id.tv_create_time,item.getCreate_time())
        .setText(R.id.tv_title,item.getTitle())
        .setText(R.id.tv_content,item.getContent());
    }
}
