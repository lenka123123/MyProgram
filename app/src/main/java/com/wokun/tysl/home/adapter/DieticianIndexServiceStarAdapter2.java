package com.wokun.tysl.home.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wokun.tysl.R;
import com.wokun.tysl.dietician.bean.DietitianListBean;
import com.wokun.tysl.home.bean.ServiceStarBean;
import com.wokun.tysl.utils.ImageLoader;

import java.util.List;

public class DieticianIndexServiceStarAdapter2 extends BaseQuickAdapter<ServiceStarBean, BaseViewHolder> {

    public DieticianIndexServiceStarAdapter2(@LayoutRes int layoutResId, @Nullable List<ServiceStarBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ServiceStarBean item) {
        ImageLoader.loadImage(item.getHead_logo(), (ImageView) helper.getView(R.id.head_logo));
        helper.setText(R.id.true_name, item.getTruename())
              .setText(R.id.job_type, item.getJobtype());
    }

}
