package com.wokun.tysl.earnings.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shantoo.common.utils.UIUtil;
import com.wokun.tysl.R;
import com.wokun.tysl.model.bean.WithdrawCashLog;

import java.util.List;

public class WithdrawDepositDetailedAdapter extends BaseQuickAdapter<WithdrawCashLog, BaseViewHolder> {

    public WithdrawDepositDetailedAdapter(@LayoutRes int layoutResId, @Nullable List data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, WithdrawCashLog item) {
        ImageView ivImage = helper.getView(R.id.iv_image);
        int account_type = item.getAccountType();
        if( 1 == account_type ){ //支付宝
            ivImage.setImageResource(R.drawable.ic_alipay);
        } else if(2 == account_type){ //银行卡
            ivImage.setImageResource(R.drawable.ic_unionpay);
        }

        helper.setText(R.id.account_code, item.getAccountTypeName()+ " " +item.getAccountCode())
        .setText(R.id.apply_time, item.getApplyTime())
        .setText(R.id.withdraw_money, item.getWithdrawMoney())
        .setText(R.id.withdraw_state, item.getWithdrawState());

        TextView withdrawState = helper.getView(R.id.withdraw_state);
        String withdraw_state = item.getWithdrawState();
        if(withdraw_state.contains("审核")){
            withdrawState.setTextColor(UIUtil.getColor(R.color.colorState));
        }
    }
}
