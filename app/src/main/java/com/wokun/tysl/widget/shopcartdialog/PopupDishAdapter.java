package com.wokun.tysl.widget.shopcartdialog;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wokun.tysl.R;
import com.wokun.tysl.smartretail.bean.GoodsDataBean;
import com.wokun.tysl.smartretail.ui.activity.SmartRetailOrderListActivity;

import java.util.ArrayList;

public class PopupDishAdapter extends RecyclerView.Adapter {

    private static String TAG = PopupDishAdapter.class.getSimpleName();
    private int itemCount;
    private ShopCart mShopCart;
    private ArrayList<GoodsDataBean> dishList;
    private ShopCartImp shopCartImp;
    private Context mContext;
    private boolean isVisable = true;

    public PopupDishAdapter(Context context, ShopCart shopCart) {
        this.mContext = context;
        this.mShopCart = shopCart;
        this.itemCount = shopCart.getDishAccount();
             this.dishList = new ArrayList<>();
             dishList.addAll(shopCart.getShoppingSingleMap().keySet());
        Log.e(TAG, "PopupDishAdapter: " + this.itemCount);
    }

    public void setInVisable(boolean isVisable) {
        this.isVisable = isVisable;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.widget_shopcart_item, parent, false);
        return new DataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        DataViewHolder dataHolder = (DataViewHolder) holder;
        final GoodsDataBean dish = getDishByPosition(position);
        if (dish != null) {
           if (!isVisable){
                dataHolder.right_product_remove_iv.setVisibility(View.INVISIBLE);
                dataHolder.right_product_add_iv.setVisibility(View.INVISIBLE);
               dataHolder.select_more.setVisibility(View.INVISIBLE);


            }
            dataHolder.right_product_name_tv.setText(dish.getGoodsName());
            dataHolder.right_product_price_tv.setText(dish.getGoodsPrice());
            Glide.with(mContext).load(dish.getGoodsPicture()).into(dataHolder.imGoods);
            int num = mShopCart.getShoppingSingleMap().get(dish);
            dataHolder.right_product_account_tv.setText(String.valueOf(num));
            dataHolder.right_product_add_iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mShopCart.addShoppingSingle(dish)) {
                        notifyItemChanged(position);
                        if (shopCartImp != null) shopCartImp.add(view, position);
                    }
                }
            });

            dataHolder.right_product_remove_iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mShopCart.subShoppingSingle(dish)) {
                        dishList.clear();
                        dishList.addAll(mShopCart.getShoppingSingleMap().keySet());
                        itemCount = mShopCart.getDishAccount();
                        notifyDataSetChanged();
                        if (shopCartImp != null)
                            shopCartImp.remove(view, position);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return this.itemCount;
    }

    public GoodsDataBean getDishByPosition(int position) {
        return dishList.get(position);
    }

    public ShopCartImp getShopCartImp() {
        return shopCartImp;
    }

    public void setShopCartImp(ShopCartImp shopCartImp) {
        this.shopCartImp = shopCartImp;
    }


    private class DataViewHolder extends RecyclerView.ViewHolder {

        private TextView right_product_name_tv;
        private TextView right_product_price_tv;
        private LinearLayout right_product_layout;
        private ImageView right_product_remove_iv, imGoods;
        private ImageView right_product_add_iv;
        private ImageView select_more;

        private TextView right_product_account_tv;

        private DataViewHolder(View itemView) {
            super(itemView);
            right_product_layout = (LinearLayout) itemView.findViewById(R.id.right_product_item);
            right_product_name_tv = (TextView) itemView.findViewById(R.id.right_product_name);
            right_product_price_tv = (TextView) itemView.findViewById(R.id.right_product_price);
            right_product_remove_iv = (ImageView) itemView.findViewById(R.id.right_product_remove);
            imGoods = (ImageView) itemView.findViewById(R.id.imGoods);
            right_product_add_iv = (ImageView) itemView.findViewById(R.id.right_product_add);
            select_more = (ImageView) itemView.findViewById(R.id.select_more);
            right_product_account_tv = (TextView) itemView.findViewById(R.id.right_product_account);
        }
    }
}