package com.wokun.tysl.earnings.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wokun.tysl.R;
import com.wokun.tysl.earnings.bean.AccountList;

import java.util.List;

public class AccountListAdapter extends BaseQuickAdapter<AccountList.AccountListBean, BaseViewHolder> {

    public AccountListAdapter(@LayoutRes int layoutResId, @Nullable List<AccountList.AccountListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AccountList.AccountListBean item) {

        ImageView icAccountImage = helper.getView(R.id.ic_account_image);
        helper.setText(R.id.account_name,item.getAccount_type_name());
        if(item.getAccount_type()==1){
            icAccountImage.setImageResource(R.drawable.ic_alipay);
            helper.setText(R.id.account_code,item.getAccount_code());
        }else if(item.getAccount_type()==2){
            icAccountImage.setImageResource(R.drawable.ic_bank_card);
            helper.setText(R.id.account_code,"储蓄卡 "+item.getAccount_code());
        }
    }
}
