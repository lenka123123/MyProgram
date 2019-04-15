package com.wokun.tysl.dietician.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wokun.tysl.R;
import com.wokun.tysl.dietician.bean.DietitianListBean;
import com.wokun.tysl.utils.ImageLoader;

import java.util.List;

public class DietitianListAdapter extends BaseQuickAdapter<DietitianListBean, BaseViewHolder> {

    public DietitianListAdapter(@LayoutRes int layoutResId, @Nullable List<DietitianListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DietitianListBean item) {
        ImageLoader.loadImage(item.getHeadLogo(), (ImageView) helper.getView(R.id.head_logo));
        helper.setText(R.id.truename, item.getTrueName())
        .setText(R.id.jobtype, item.getJobType())
        .setText(R.id.item_content, item.getField_name())
        .setText(R.id.service_free,"￥"+item.getService_fee());
    }
}
