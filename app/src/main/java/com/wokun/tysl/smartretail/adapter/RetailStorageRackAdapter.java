package com.wokun.tysl.smartretail.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wokun.tysl.R;
import com.wokun.tysl.smartretail.bean.GoodsDataBean;
import com.wokun.tysl.utils.ImageLoader;
import com.wokun.tysl.widget.shopcartdialog.ShopCart;
import com.wokun.tysl.widget.shopcartdialog.ShopCartImp;

import java.util.List;

public class RetailStorageRackAdapter extends BaseQuickAdapter<GoodsDataBean, BaseViewHolder> {

    private ShopCart mShopCart;
    private ShopCartImp mShopCartImp;

    public RetailStorageRackAdapter(@LayoutRes int layoutResId, @Nullable List<GoodsDataBean> data, ShopCart shopCart) {
        super(layoutResId, data);
        this.mShopCart = shopCart;
    }

    @Override
    protected void convert(final BaseViewHolder helper, final GoodsDataBean item) {
       // ImageView imageView = helper.getView(R.id.iv_image);
        //ImageLoader.loadImage(item.getProductImage(), imageView);

        ImageView rightProductAdd = helper.getView(R.id.right_product_add);
        ImageView rightProductRemove = helper.getView(R.id.right_product_remove);
        TextView rightProductAccount = helper.getView(R.id.right_product_account);
        ImageView goods_image = helper.getView(R.id.goods_image);
        helper.setText(R.id.right_product_name, item.getGoodsName())
                .setText(R.id.right_product_price, String.valueOf(item.getGoodsPrice()))
                .setText(R.id.scalenumber,"销量："+item.getSaleSum())
                .addOnClickListener(R.id.right_product_add)
                .addOnClickListener(R.id.goods_image);
        ImageLoader.loadImage(item.getGoodsPicture(),goods_image);

        int count = 0;
        if(mShopCart.getShoppingSingleMap().containsKey(item)){
            count = mShopCart.getShoppingSingleMap().get(item);
        }
        if(count<=0){
            rightProductRemove.setVisibility(View.GONE);
            rightProductAccount.setVisibility(View.GONE);
        }else {
            rightProductRemove.setVisibility(View.VISIBLE);
            rightProductAccount.setVisibility(View.VISIBLE);
            rightProductAccount.setText(""+count+"");
        }

        rightProductRemove.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mShopCart.subShoppingSingle(item)){
                        notifyItemChanged(helper.getAdapterPosition());
                        if(mShopCartImp!=null)
                            mShopCartImp.remove(view,helper.getAdapterPosition());
                    }
                }
        });
    }

    public ShopCartImp getShopCartImp() {
        return mShopCartImp;
    }

    public void setShopCartImp(ShopCartImp shopCartImp) {
        this.mShopCartImp = shopCartImp;
    }
}