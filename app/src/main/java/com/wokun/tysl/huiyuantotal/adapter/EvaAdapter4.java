package com.wokun.tysl.huiyuantotal.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wokun.tysl.R;
import com.wokun.tysl.bankaccount.bean.TixianBean;
import com.wokun.tysl.huiyuantotal.bean.ChuangKeBean;
import com.wokun.tysl.utils.ImageLoader;

import java.util.List;

public class EvaAdapter4 extends BaseQuickAdapter<ChuangKeBean, BaseViewHolder> {

    public EvaAdapter4(@LayoutRes int layoutResId, @Nullable List<ChuangKeBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ChuangKeBean item) {
      ImageLoader.loadImage(item.getStore_picture(), (ImageView) helper.getView(R.id.zhihui_ch_img));
        helper.setText(R.id.zhihui_it_call, item.getStore_name())
       .setText(R.id.zhihui_ch_name, item.getUsername())
       .setText(R.id.zhihui_mobile, item.getMobile())
       .setText(R.id.zhihui_it_title, item.getProfit())
       .setText(R.id.zhihui_it_time, item.getAddress());



    }
}
