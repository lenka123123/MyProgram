package com.wokun.tysl.dietician.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hedgehog.ratingbar.RatingBar;
import com.wokun.tysl.R;
import com.wokun.tysl.dietician.bean.EvalDataBean;
import com.wokun.tysl.utils.ImageLoader;

import java.util.List;

public class EvaAdapter extends BaseQuickAdapter<EvalDataBean, BaseViewHolder> {

    public EvaAdapter(@LayoutRes int layoutResId, @Nullable List<EvalDataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, EvalDataBean item) {
        ImageLoader.loadImage(item.getHeadImgUrl(), (ImageView) helper.getView(R.id.headimgurl));

        RatingBar mRatingBar = helper.getView(R.id.eid);
        mRatingBar.setStarCount(item.getEid());
        helper.setText(R.id.username, item.getUserName())
        .setText(R.id.evalution_text, item.getEvalutionText())
        .setText(R.id.evalution_time, item.getEvalutionTime());

        TextView replyText = helper.getView(R.id.reply_text);
        if(TextUtils.isEmpty(item.getReplyText())){
            replyText.setVisibility(View.GONE);
        }else{
            helper.setText(R.id.reply_text, item.getReplyText());
        }
    }
}
