package com.wokun.tysl.store.ui;

import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.tysl.R;
import com.wokun.tysl.base.BaseBindingActivity;
import com.wokun.tysl.other.controler.ActionMgr;
import com.wokun.tysl.store.MerchantMgr;

import butterknife.BindView;
import butterknife.OnClick;

public class MerchantApplyForActivity extends BaseBindingActivity {

    @BindView(R.id.et_contacts) EditText etContacts;//联系人姓名
    @BindView(R.id.et_mobile) EditText etMobile;//联系人电话
    @BindView(R.id.et_address) EditText etAddress;//联系人邮箱

    @BindView(R.id.ll_default) LinearLayout llDefault;//默认布局
    @BindView(R.id.ll_success) LinearLayout llSuccess;//申请成功后需要显示的布局

    @Override
    public int createView() {
        return R.layout.activity_merchant_apply_for;
    }

    @Override
    public WidgetBar createToolBar() {
        return mWidgetBar.setWidgetBarTitle("填写入驻申请");
    }

    @Override
    public void init() {

    }

    @OnClick({R.id.action_submit, R.id.action_service})
    public void action(View v){
        if(R.id.action_submit == v.getId()){

            String contacts = etContacts.getText().toString().trim();
            String mobile = etMobile.getText().toString().trim();
            String address = etAddress.getText().toString().trim();
            MerchantMgr.getInstance().doJoinBusiness(llDefault,llSuccess,contacts,mobile,address);

        }else if(R.id.action_service == v.getId()){
            ActionMgr.getInstance().callService(this);
        }
    }
}
