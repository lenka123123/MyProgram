package com.wokun.tysl.dietician.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wokun.tysl.R;
import com.wokun.tysl.dietician.bean.DieticianFieldTypeBean;
import com.wokun.tysl.utils.ImageLoader;

import java.util.List;

public class DieticianFieldAdapter extends BaseQuickAdapter<DieticianFieldTypeBean, BaseViewHolder> {

    public DieticianFieldAdapter(@LayoutRes int layoutResId, @Nullable List<DieticianFieldTypeBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DieticianFieldTypeBean item) {
        ImageLoader.loadImage(item.getFieldIcon(), (ImageView) helper.getView(R.id.field_icon));
        helper.setText(R.id.field_name, item.getFieldName());
    }
}
