package com.wokun.tysl.bankaccount.adapter;

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
import com.wokun.tysl.bankaccount.bean.TixianBean;
import com.wokun.tysl.dietician.bean.EvalDataBean;
import com.wokun.tysl.utils.ImageLoader;

import java.util.List;

public class EvaAdapter3 extends BaseQuickAdapter<TixianBean, BaseViewHolder> {

    public EvaAdapter3(@LayoutRes int layoutResId, @Nullable List<TixianBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TixianBean item) {
     //   ImageLoader.loadImage(item.getHeadImgUrl(), (ImageView) helper.getView(R.id.headimgurl));
        helper.setText(R.id.mx_money, "￥"+item.getMoney())
       .setText(R.id.zhihui_it_call, item.getRealname())
       .setText(R.id.zhihui_it_title, item.getBankname())
       .setText(R.id.mx_number, item.getAccount_code())
       .setText(R.id.zhihui_it_time, item.getCreate_time())
       .setText(R.id.mx_beizhu, "备注："+item.getRemark());

        if(item.getState().equals("0")){
            helper.setText(R.id.mx_state, "等待审核");
        }
        if(item.getState().equals("1")){
            helper.setText(R.id.mx_state, "提现成功");
        }
        if(item.getState().equals("2")){
            helper.setText(R.id.mx_state, "审核失败");
        }







    }
}
