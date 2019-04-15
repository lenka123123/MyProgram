package com.wokun.tysl.goods.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wokun.tysl.R;
import com.wokun.tysl.goods.bean.GoodsBean;
import com.wokun.tysl.utils.ImageLoader;

import java.util.List;

//商品收藏数据适配器
public class GoodsFavoritesAdapter extends BaseQuickAdapter<GoodsBean, BaseViewHolder> {

    public GoodsFavoritesAdapter(@LayoutRes int layoutResId, @Nullable List<GoodsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final GoodsBean item) {
        ImageView goodsImage = helper.getView(R.id.goods_image);
        ImageLoader.loadImage(item.getGoodsImage(), goodsImage);
        helper.setText(R.id.goods_name, item.getGoodsName())
        .setText(R.id.goods_price, item.getGoodsPrice()+"")
        .addOnClickListener(R.id.favorites);
       // String state = item.getGoods_state();

//        switch (state) {
//            case "0":
//                helper.setText(R.id.goods_state, "待审核");
//                break;
//            case "1":
//                helper.setText(R.id.goods_state, "正常");
//                break;
//            case "2":
//                helper.setText(R.id.goods_state, "下架");
//                break;
//            case "3":
//                helper.setText(R.id.goods_state, "删除");
//                break;
//        }
    }
}
