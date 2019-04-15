package com.wokun.tysl.smartretail.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wokun.tysl.R;
import com.wokun.tysl.smartretail.bean.BusinessData;
import com.wokun.tysl.smartretail.bean.OrderInfoBean;
import com.wokun.tysl.utils.ImageLoader;

import java.util.List;

public class MerchantAccountManagementAdapter1 extends BaseQuickAdapter<BusinessData, BaseViewHolder> {

    public MerchantAccountManagementAdapter1(@LayoutRes int layoutResId, @Nullable List<BusinessData> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BusinessData item) {
        helper.setText(R.id.tv_count,item.getCount())
              .setText(R.id.tv_title,item.getTitle());
    }
}