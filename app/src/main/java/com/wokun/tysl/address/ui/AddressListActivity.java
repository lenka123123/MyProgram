package com.wokun.tysl.address.ui;

import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shantoo.widget.recyclerview.MItemDecoration;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.tysl.R;
import com.wokun.tysl.TyslApp;
import com.wokun.tysl.address.AddressMgr;
import com.wokun.tysl.address.adapter.AddressAdapter;
import com.wokun.tysl.address.bean.AddressBean;
import com.wokun.tysl.base.BaseBindingActivity;
import com.wokun.tysl.config.Constants;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;

//收货地址
public class AddressListActivity extends BaseBindingActivity {

    @BindString(R.string.tysl_user_address) String title;

    @BindView(R.id.recycler_view) RecyclerView mRecyclerView;

    private AddressAdapter mAdapter;

    private static final int REQUEST_ADD_ADDRESS = 1;
    private static final int REQUEST_EDIT_ADDRESS = 2;

    @Override
    public int createView() {
        return R.layout.activity_ucenter_shipping_address;
    }

    @Override
    public WidgetBar createToolBar() {
        return mWidgetBar.setWidgetBarTitle(title);
    }

    @Override
    public void init() {
        initRecyclerView();
        AddressMgr.getInstance().loadAddressList(mAdapter);
    }

    private void initRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new MItemDecoration(this, DividerItemDecoration.VERTICAL));

        mAdapter = new AddressAdapter(R.layout.item_shipping_address, null);
        mAdapter.setEmptyView(R.layout.layout_no_address_view,(ViewGroup) mRecyclerView.getParent());

        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                AddressBean data = (AddressBean) adapter.getData().get(position);

                if(R.id.action_set_normal_address == view.getId()){
                    onSetNormalAddress(data);
                }else if(R.id.action_edit_address == view.getId()){
                    onEditAddress(data);
                }else if(R.id.action_delete_address == view.getId()){
                    onDeleteAddress(data);
                }
            }
        });

        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String requestCode = getIntent().getStringExtra(Constants.REQUEST_CODE);
                if(!TextUtils.isEmpty(requestCode)){
                    Intent data = new Intent();
                    AddressBean address = (AddressBean) adapter.getData().get(position);
                    data.putExtra(Constants.CONTACTS, address.getContacts());
                    data.putExtra(Constants.TEL, address.getTel());
                    data.putExtra(Constants.PROVINCE_ID, address.getProvinceId());
                    data.putExtra(Constants.ADDRESS, address.getProvince() + address.getCity() + address.getDistrict() + address.getAddress());
                    setResult(20, data);
                    finish();
                }
            }
        });
        mRecyclerView.setAdapter(mAdapter);
    }

    @OnClick(R.id.action_add_address)
    public void addAddress(View v) {
        onAddAddress();
    }

    //添加地址
    private void onAddAddress(){
        Intent intent = new Intent();
        intent.setClass(this, AddressAddActivity.class);
        startActivityForResult(intent, REQUEST_ADD_ADDRESS, !TyslApp.getInstance().isLogin());
    }

    //设置默认地址
    private void onSetNormalAddress(AddressBean bean) {
        AddressMgr.getInstance().setDefaultAddress(mAdapter, bean);
    }

    //删除地址
    private void onDeleteAddress(AddressBean bean) {
        AddressMgr.getInstance().deleteAddress(mAdapter, bean.getAddressId());
    }

    //编辑地址
    private void onEditAddress(AddressBean bean) {
        Intent intent = new Intent();
        intent.putExtra(Constants.ADDRESS_ID, bean.getAddressId());
        intent.putExtra(Constants.CONTACTS, bean.getContacts());
        intent.putExtra(Constants.TEL, bean.getTel());
        intent.putExtra(Constants.AREA, bean.getProvince() + bean.getCity() + bean.getDistrict());
        intent.putExtra(Constants.PROVINCE_ID, bean.getProvinceId());
        intent.putExtra(Constants.CITY_ID, bean.getCityId());
        intent.putExtra(Constants.DISTRICT_ID, bean.getDistrictId());
        intent.putExtra(Constants.ADDRESS, bean.getAddress());
        intent.setClass(this, AddressEditActivity.class);
        startActivityForResult(intent, REQUEST_EDIT_ADDRESS, !TyslApp.getInstance().isLogin());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_ADD_ADDRESS || requestCode == REQUEST_EDIT_ADDRESS) {
            AddressMgr.getInstance().loadAddressList(mAdapter);
        }
    }
}
