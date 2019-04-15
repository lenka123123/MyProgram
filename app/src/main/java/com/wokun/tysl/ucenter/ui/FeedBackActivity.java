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

//意见反馈页面
public class FeedBackActivity extends BaseBindingActivity {

    @BindString(R.string.tysl_feedback)String title;
    @BindString(R.string.tysl_feed_back_hint)String feed_back_hint;
    @BindString(R.string.tysl_feed_back_mobile_hint)String feed_back_mobile_hint;

    @BindView(R.id.et_feed_back) EditText etFeedBack;
    @BindView(R.id.et_mobile) EditText etMobile;

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
        etFeedBack.setHint(feed_back_hint);
        etMobile.setHint(feed_back_mobile_hint);
    }

    @OnClick(R.id.action_submit)
    public void actionSubmit(View v) {
        String content = etFeedBack.getText().toString().trim();
        String mobile = etMobile.getText().toString().trim();
        UcenterMgr.getInstance().feedBack(content,mobile,etFeedBack,etMobile);
    }
}
