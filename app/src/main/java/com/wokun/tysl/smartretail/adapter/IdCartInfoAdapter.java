package com.wokun.tysl.smartretail.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wokun.tysl.R;
import com.wokun.tysl.smartretail.bean.IdCartInfoBean;

import java.util.List;

public class IdCartInfoAdapter extends BaseQuickAdapter<IdCartInfoBean, BaseViewHolder> {

    public IdCartInfoAdapter(@LayoutRes int layoutResId, @Nullable List<IdCartInfoBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, IdCartInfoBean item) {
        helper.setText(R.id.tv_id_cart_name,item.getIdCartName())
                .setText(R.id.id_user_name,item.getUserName())
                .setText(R.id.tv_id_cart_number,"卡号: "+item.getIdCartNumber())
                .addOnClickListener(R.id.action_delete);
    }
}