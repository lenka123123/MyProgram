package com.wokun.tysl.zichanchange.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wokun.tysl.R;
import com.wokun.tysl.TyslApp;
import com.wokun.tysl.asset.ui.activity.AssertChangeActivity;
import com.wokun.tysl.ucenter.ui.PayActivity;
import com.wokun.tysl.zichanchange.bean.ZhihuanjilvBean;

import java.util.List;

/**
 * Created by Administrator on 2018/7/5/005.
 */

public class ZichanChangeAdapter extends BaseQuickAdapter<ZhihuanjilvBean, BaseViewHolder>{

    public ZichanChangeAdapter(int layoutResId, @Nullable List<ZhihuanjilvBean> data) {
       super(layoutResId, data);

    }




    @Override
    protected void convert(final BaseViewHolder helper, final ZhihuanjilvBean item) {
        helper.setText(R.id.zhihuanjilv_order_number,"订单编号："+item.getOrder_number())
                .setText(R.id.zhihuanjilv_title,item.getGoods_name())
                .setText(R.id.item_my_count,item.getNums())
                .setText(R.id.item_my_shuliang,item.getExchange_integral())
                .addOnClickListener(R.id.change_agin);
        Glide.with(TyslApp.getContext())
                .load(item.getGoods_picture())
                .into((ImageView) helper.getView(R.id.zhihuanjilv_img));

  /*      helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(item);
            }
        });*/



        final String id = item.getIntegral_gid();

  /*      helper.setOnClickListener(R.id.change_agin, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("点击再次置换","再次置换了");

          Intent intent = new Intent(context, AssertChangeActivity.class);
                intent.putExtra("id",id);
                context.startActivity(intent);
              }
        });*/

    }
    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    public interface OnItemClickListener{
        void onItemClick(ZhihuanjilvBean item);
    }

}
