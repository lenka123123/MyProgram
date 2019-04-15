package com.wokun.tysl.other.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wokun.tysl.R;
import com.wokun.tysl.store.bean.BusinessShowBean;
import com.wokun.tysl.utils.ImageLoader;

import java.util.List;

public class MerchantEnterAdapter extends BaseQuickAdapter<BusinessShowBean,BaseViewHolder> {

    public MerchantEnterAdapter(@LayoutRes int layoutResId, @Nullable List<BusinessShowBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BusinessShowBean item) {
        ImageLoader.loadImage(item.getStoreLogo(), (ImageView) helper.getView(R.id.iv_store_logo));
        helper.setText(R.id.tv_store_name,item.getStoreName())
              .setText(R.id.tv_service_score,"服务评价: "+item.getServiceScore())
              .setText(R.id.tv_address,item.getAddress());

    }
}
