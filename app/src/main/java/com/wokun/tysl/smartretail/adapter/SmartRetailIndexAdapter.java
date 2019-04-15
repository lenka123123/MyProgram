package com.wokun.tysl.smartretail.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wokun.tysl.R;
import com.wokun.tysl.smartretail.bean.StoreListBean;
import com.wokun.tysl.utils.ImageLoader;

import java.util.List;

public class SmartRetailIndexAdapter extends BaseQuickAdapter<StoreListBean, BaseViewHolder> {

    public SmartRetailIndexAdapter(@LayoutRes int layoutResId, @Nullable List<StoreListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, StoreListBean item) {
        ImageView imageView = helper.getView(R.id.iv_image);
        ImageLoader.loadImage(item.getStorePicture(), imageView);
        helper.setText(R.id.tv_title, "店铺名称:"+item.getStoreName())
              .setText(R.id.tv_content, "地址:"+item.getAddress())
              .setText(R.id.tv_address, item.getDistance()+"km");
    }
}