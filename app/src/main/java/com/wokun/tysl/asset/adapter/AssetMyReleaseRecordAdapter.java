package com.wokun.tysl.asset.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wokun.tysl.R;
import com.wokun.tysl.asset.bean.MyReleaseRecordBean;

import java.util.List;

public class AssetMyReleaseRecordAdapter extends BaseQuickAdapter<MyReleaseRecordBean,BaseViewHolder>{

    private int state;

    public AssetMyReleaseRecordAdapter(@LayoutRes int layoutResId, @Nullable List<MyReleaseRecordBean> data) {
        super(layoutResId, data);
    }

    public AssetMyReleaseRecordAdapter(@LayoutRes int layoutResId, @Nullable List<MyReleaseRecordBean> data, int state) {
        super(layoutResId, data);
        this.state = state;
    }

    @Override
    protected void convert(BaseViewHolder helper, MyReleaseRecordBean item) {
        helper.setText(R.id.unit_price,item.getUnitPrice()+"元")
              .setText(R.id.release_num,item.getReleaseNum())
              .setText(R.id.surplus_num,item.getSurplusNum())
              .setText(R.id.create_time,item.getCreateTime())
              .addOnClickListener(R.id.action_asset_release_delete)
              .addOnClickListener(R.id.action_asset_release_edit);

        if(state == 2){
            helper.setText(R.id.tv_unit_price,"卖出单价:");
        }else if(state ==1){
            helper.setText(R.id.tv_unit_price,"买入单价:");
        }

        helper.setText(R.id.tv_release_num,"发布数量:")
              .setText(R.id.tv_surplus_num,"剩余数量:")
              .setText(R.id.tv_create_time,"发布时间:")
              .setText(R.id.action_asset_release_delete,"刪除")
              .setText(R.id.action_asset_release_edit,"编辑");
    }
}
