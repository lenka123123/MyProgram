package com.wokun.tysl.goods.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wokun.tysl.R;
import com.wokun.tysl.model.bean.Store;
import com.wokun.tysl.utils.ImageLoader;

import java.util.List;

//店铺收藏数据适配器
public class StoreFavoritesAdapter extends BaseQuickAdapter<Store, BaseViewHolder> {

    public StoreFavoritesAdapter(@LayoutRes int layoutResId, @Nullable List<Store> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final Store item) {
        ImageLoader.loadImage(item.getStore_logo(), (ImageView) helper.getView(R.id.store_logo));
        helper.setText(R.id.store_name, item.getStore_name())
              .addOnClickListener(R.id.isCollect);
    }
}
