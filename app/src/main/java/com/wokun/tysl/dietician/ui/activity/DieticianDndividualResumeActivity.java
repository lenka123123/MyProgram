package com.wokun.tysl.dietician.ui.activity;

import android.widget.TextView;

import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.tysl.R;
import com.wokun.tysl.base.BaseBindingActivity;
import com.wokun.tysl.config.Constants;

import butterknife.BindView;

//个人简介
public class DieticianDndividualResumeActivity extends BaseBindingActivity {

    @BindView(R.id.profile)
    TextView tvProfile;

    @Override
    public int createView() {
        return R.layout.activity_dietician_dndividual_resume;
    }

    @Override
    public WidgetBar createToolBar() {
        return mWidgetBar.setWidgetBarTitle("个人简介");
    }

    @Override
    public void init() {
        tvProfile.setText(getIntent().getStringExtra(Constants.PROFILE));
    }
}
