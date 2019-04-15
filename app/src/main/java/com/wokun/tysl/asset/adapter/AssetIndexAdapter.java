package com.wokun.tysl.asset.adapter;

import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wokun.tysl.R;
import com.wokun.tysl.asset.bean.AssetGoodsBean;
import com.wokun.tysl.asset.ui.activity.AssertChangeActivity;
import com.wokun.tysl.utils.ImageLoader;

import java.util.List;

public class AssetIndexAdapter extends BaseQuickAdapter<AssetGoodsBean, BaseViewHolder> {

    public AssetIndexAdapter(@LayoutRes int layoutResId, @Nullable List<AssetGoodsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AssetGoodsBean item) {
        final String id = item.getId();

        ImageView imageView = helper.getView(R.id.goods_picture);
        ImageLoader.loadImage(item.getGoodsPicture(), imageView);
        helper.setText(R.id.goods_name, item.getGoodsName())
                .setText(R.id.exchange_integral, item.getExchangeIntegral());
             imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, AssertChangeActivity.class);
                intent.putExtra("id",id);
                mContext.startActivity(intent);
            }
        });
    }
}
