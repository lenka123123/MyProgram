package com.wokun.tysl.ucenter.ui;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.tysl.R;
import com.wokun.tysl.address.AddressMgr;
import com.wokun.tysl.base.BaseLevelThreeLinkageActivity;
import com.wokun.tysl.config.Constants;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;

public class EditAddressActivity extends BaseLevelThreeLinkageActivity implements BaseLevelThreeLinkageActivity.OnAddressSelectedListener {

    @BindString(R.string.tysl_user_edit_address) String title;
    @BindView(R.id.toolbar) WidgetBar widgetBar;
    @BindView(R.id.et_contacts) EditText etContacts;
    @BindView(R.id.et_mobile) EditText etMobile;
    @BindView(R.id.et_address) EditText etAddress;
    @BindView(R.id.area) TextView tvArea;

    @BindView(R.id.action_submit) TextView actionAddAddress;

    private int mProvinceCode,mCityCode,mDistrictCode = -1;

    private String addressId;

    @Override
    public int createView() {
        return R.layout.activity_address_add;
    }

    @Override
    public WidgetBar createToolBar() {
        return widgetBar.setWidgetBarTitle(title);
    }

    @Override
    public void init() {
        addressId = getIntent().getStringExtra(Constants.ADDRESS_ID);
        String contacts =  getIntent().getStringExtra(Constants.CONTACTS);
        String tel = getIntent().getStringExtra(Constants.TEL);
        String area = getIntent().getStringExtra(Constants.AREA);
        String address = getIntent().getStringExtra(Constants.ADDRESS);

        mProvinceCode = getIntent().getIntExtra(Constants.PROVINCE_ID,0);
        mCityCode = Integer.valueOf(getIntent().getStringExtra(Constants.CITY_ID));
        mDistrictCode = Integer.valueOf(getIntent().getStringExtra(Constants.DISTRICT_ID));

        etContacts.setText(contacts);
        etMobile.setText(tel);
        tvArea.setText(area);
        etAddress.setText(address);

        setOnAddressSelectedListener(this);
    }

    @OnClick({R.id.action_submit, R.id.action_selector_area})
    public void action(View v) {
        if(v.getId() == R.id.action_submit){

            String contacts = etContacts.getText().toString().trim();
            String mobile = etMobile.getText().toString().trim();
            String address = etAddress.getText().toString().trim();
            AddressMgr.getInstance().editAddress(this, addressId,contacts,mobile,mProvinceCode,mCityCode,mDistrictCode,address);

        }else if(v.getId() == R.id.action_selector_area){

            showPickerView();
        }
    }

    @Override
    public void onAddressSelected(String address, int provinceCode, int cityCode, int districtCode) {
        tvArea.setText(address);
        mProvinceCode = provinceCode;
        mCityCode = cityCode;
        mDistrictCode = districtCode;
    }
}
