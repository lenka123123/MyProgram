package com.wokun.tysl.ucenter.ui;

import android.view.View;
import android.widget.EditText;

import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.tysl.R;
import com.wokun.tysl.base.BaseBindingActivity;
import com.wokun.tysl.ucenter.controler.UcenterMgr;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;

//修改个人简介
public class EditIndividualResumeActivity extends BaseBindingActivity {

    @BindString(R.string.tysl_edit_individual_resume)
    String title;

    @BindView(R.id.et_feed_back)
    EditText etFeedBack;
    @BindView(R.id.et_mobile)
    EditText etMobile;

    @Override
    public int createView() {
        return R.layout.activity_ucenter_feed_back;
    }

    @Override
    public WidgetBar createToolBar() {
        return mWidgetBar.setWidgetBarTitle(title);
    }

    @Override
    public void init() {
        etMobile.setVisibility(View.GONE);
        etFeedBack.setHint("");
    }

    @OnClick(R.id.action_submit)
    public void submit(View v) {
        String profile = etFeedBack.getText().toString().trim();
        UcenterMgr.getInstance().alterProfile(profile);
    }
}