package com.wokun.tysl.stock.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wokun.tysl.R;
import com.wokun.tysl.address.bean.AddressBean;

import java.util.List;

/**
 * Created by xl on 2018/7/25.
 * 批量进货
 */

public class InventoryAdapter extends BaseQuickAdapter<AddressBean, BaseViewHolder> {


	public InventoryAdapter(int layoutResId, @Nullable List<AddressBean> data) {
		super(layoutResId, data);
	}

	@Override
	protected void convert(BaseViewHolder helper, AddressBean item) {
		ImageView img=  helper.getView(R.id.image);
		img.setImageResource(R.drawable.rc_ac_ram_icon);

		helper.setText(R.id.txtTitle,item.getAddress())
				.setText(R.id.txtCurrent,"当前库存： "+item.getAddressId())
				.setText(R.id.txtTop,"库存上限： "+item.getCity())
				.setText(R.id.btnAdd,item.getCityId())
				.addOnClickListener(R.id.btnAdd);
	}
}
