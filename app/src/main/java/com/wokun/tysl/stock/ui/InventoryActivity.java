package com.wokun.tysl.stock.ui;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shantoo.widget.recyclerview.MItemDecoration;
import com.shantoo.widget.toast.RxToast;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.tysl.R;
import com.wokun.tysl.address.bean.AddressBean;
import com.wokun.tysl.base.BaseBindingActivity;
import com.wokun.tysl.stock.adapter.InventoryAdapter;

import java.util.ArrayList;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by xl on 2018/7/25.
 * 库存管理
 */

public class InventoryActivity extends BaseBindingActivity{
	@BindString(R.string.tysl_order_inventory) String title;
	@BindView(R.id.recycler_view)
	RecyclerView mRecyclerView;
	private InventoryAdapter mAdapter;

	@Override
	public int createView() {
		return R.layout.activity_inventory;
	}

	@Override
	public WidgetBar createToolBar() {
		return mWidgetBar.setWidgetBarTitle(title)
				.setMenu("进货单",null)
				.setOnMenuClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						startActivity(ReceiptActivity.class);
					}
				},null);
	}

	@Override
	public void init() {
		initRecyclerView();
	}

	private void initRecyclerView() {
		mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
		mRecyclerView.addItemDecoration(new MItemDecoration(this, DividerItemDecoration.VERTICAL));
		ArrayList<AddressBean> addressBean = new ArrayList<>();
		AddressBean addressBean1 = new AddressBean();
		addressBean1.setAddress("福神1EAGNM6CT187X男秋刺绣玫");
		addressBean1.setAddressId("15");
		addressBean1.setCity("50");
		addressBean1.setCityId("申请进货");
		addressBean.add(addressBean1);
		mAdapter = new InventoryAdapter(R.layout.item_order_purchase, addressBean);
		mAdapter.setEmptyView(R.layout.layout_no_purchase_view,(ViewGroup) mRecyclerView.getParent());

		mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
			@Override
			public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

				AddressBean data = (AddressBean) adapter.getData().get(position);

				if(R.id.btnAdd == view.getId()){
					RxToast.showShort("申请进货");
				}
			}
		});
		mRecyclerView.setAdapter(mAdapter);
	}

	@OnClick(R.id.action_batch_purchase)
	public void addPurchase(View v) {
		RxToast.showShort("批量进货");
	}
}
