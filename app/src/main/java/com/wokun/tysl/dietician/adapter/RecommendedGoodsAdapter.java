package com.wokun.tysl.dietician.adapter;

import android.graphics.Paint;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wokun.tysl.R;
import com.wokun.tysl.dietician.bean.RecGoodsBean;
import com.wokun.tysl.utils.ImageLoader;

import java.util.List;

public class RecommendedGoodsAdapter extends BaseQuickAdapter<RecGoodsBean, BaseViewHolder> {

    public RecommendedGoodsAdapter(@LayoutRes int layoutResId, @Nullable List<RecGoodsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final RecGoodsBean item) {
        TextView actionCut =helper.getView(R.id.action_cut);

        TextView tv =helper.getView(R.id.tv);
        TextView goodsCommission =helper.getView(R.id.goods_commission);

        ImageView goods_picture = helper.getView(R.id.goods_picture);
        ImageLoader.loadImage(item.getGoodsImage(), goods_picture);
        helper.setText(R.id.goods_name, item.getGoodsName())
        .setText(R.id.goods_price, "¥"+item.getGoodsPrice())
        .setText(R.id.goods_commission, "¥"+item.getGoodsCommission());

        if(item.getGoodsState() == 0){
            helper.setText(R.id.goods_price, "¥"+item.getGoodsPrice()+" 商家已取消荐购奖励");
            tv.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG );
            goodsCommission.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG );
        }

        //取消推荐
        actionCut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnCutListener.onCut(item,helper.getPosition());
            }
        });
    }

    private OnCutListener mOnCutListener;

    public interface OnCutListener{
        void onCut(RecGoodsBean recGoodsBean, int position);
    }
    public void setOnCutListener(OnCutListener listener){
        this.mOnCutListener = listener;
    }
}
