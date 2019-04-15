package com.wokun.tysl.address.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shantoo.widget.imageview.SelectorImageView;
import com.wokun.tysl.R;
import com.wokun.tysl.address.bean.AddressBean;

import java.util.List;

public class AddressAdapter extends BaseQuickAdapter<AddressBean, BaseViewHolder> {

    public AddressAdapter(@LayoutRes int layoutResId, @Nullable List<AddressBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final AddressBean item) {
        SelectorImageView selectorImageView =  helper.getView(R.id.selector_image_view);
        selectorImageView.toggle(item.isDefaultAddress());

        helper.setText(R.id.user_contacts,item.getContacts())
        .setText(R.id.user_mobile,item.getTel())
        .setText(R.id.user_address,item.getProvince()+item.getCity()+item.getDistrict()+item.getAddress())
        .addOnClickListener(R.id.action_set_normal_address)
        .addOnClickListener(R.id.action_edit_address)
        .addOnClickListener(R.id.action_delete_address);
    }
}