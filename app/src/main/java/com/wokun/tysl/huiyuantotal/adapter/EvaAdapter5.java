package com.wokun.tysl.huiyuantotal.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wokun.tysl.R;
import com.wokun.tysl.huiyuantotal.bean.ChuangKeBean;
import com.wokun.tysl.huiyuantotal.bean.PeopleBean;
import com.wokun.tysl.utils.ImageLoader;

import java.util.List;

public class EvaAdapter5 extends BaseQuickAdapter<PeopleBean, BaseViewHolder> {

    public EvaAdapter5(@LayoutRes int layoutResId, @Nullable List<PeopleBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PeopleBean item) {
        if(!TextUtils.isEmpty(item.getHeadimgurl())){
             ImageLoader.loadImage(item.getHeadimgurl(), (ImageView) helper.getView(R.id.zhihui_it_img));}
        helper.setText(R.id.zhihui_it_call, item.getUsername())
       .setText(R.id.zhihui_it_title, "手机号："+item.getMobile())
       .setText(R.id.zhihui_last_time, item.getLast_buy_time());



    }
}
