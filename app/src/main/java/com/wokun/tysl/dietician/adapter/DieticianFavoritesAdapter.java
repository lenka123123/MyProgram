package com.wokun.tysl.dietician.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wokun.tysl.R;
import com.wokun.tysl.dietician.bean.DieticianFavoritesBean;
import com.wokun.tysl.utils.ImageLoader;

import java.util.List;

//营养师收藏数据适配器
public class DieticianFavoritesAdapter extends BaseQuickAdapter<DieticianFavoritesBean, BaseViewHolder> {

    public DieticianFavoritesAdapter(@LayoutRes int layoutResId, @Nullable List<DieticianFavoritesBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DieticianFavoritesBean item) {
        ImageView goodsImage = helper.getView(R.id.head_logo);
        ImageLoader.loadImage(item.getHeadLogo(), goodsImage);
        helper.setText(R.id.truename, item.getTrueName())
        .setText(R.id.jobname, item.getJobName());
    }
}
