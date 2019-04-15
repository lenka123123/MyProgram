package com.wokun.tysl.dietician.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wokun.tysl.R;
import com.wokun.tysl.dietician.bean.DieticianJobTypeBean;
import com.wokun.tysl.utils.ImageLoader;

import java.util.List;

public class DieticianJobTypeAdapter extends BaseQuickAdapter<DieticianJobTypeBean, BaseViewHolder> {

    public DieticianJobTypeAdapter(@LayoutRes int layoutResId, @Nullable List<DieticianJobTypeBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DieticianJobTypeBean item) {
        ImageLoader.loadImage(item.getTypeIcon(), (ImageView) helper.getView(R.id.type_icon));
        helper.setText(R.id.type_name, item.getTypeName());
    }
}
