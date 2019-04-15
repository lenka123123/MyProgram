package com.wokun.tysl.dietician.ui.activity;

import android.support.v4.app.Fragment;

import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.tysl.R;
import com.wokun.tysl.base.BaseTabActivity;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.dietician.ui.fragment.DietitianServiceOrderAll;
import com.wokun.tysl.dietician.ui.fragment.DietitianServiceOrderDqr;
import com.wokun.tysl.dietician.ui.fragment.DietitianServiceOrderFwz;
import com.wokun.tysl.dietician.ui.fragment.DietitianServiceOrderYgq;
import com.wokun.tysl.dietician.ui.fragment.DietitianServiceOrderYjj;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindString;

/** 服务订单*/
public class DietitianServiceOrderActivity extends BaseTabActivity {

    @BindString(R.string.tysl_service_order)
    String title;
    @BindString(R.string.tysl_state_all)
    String all;
    @BindString(R.string.tysl_state_wait_verify)
    String wait_verify;
    @BindString(R.string.tysl_state_refused)
    String refused;
    @BindString(R.string.tysl_state_service_ing)
    String service_ing;
    @BindString(R.string.tysl_state_have_expired)
    String have_expired;

    @Override
    protected int initState() {
        return getIntent().getIntExtra(Constants.STATE, 0);
    }

    @Override
    protected String initTitle() {
        return title;
    }    @Override
    public WidgetBar createToolBar() {
        return mWidgetBar.setWidgetBarTitle(title);
    }



    @Override
    protected List<String> initTabTitles() {
        mTitles = new ArrayList<>();
        mTitles.add(all);
        mTitles.add(wait_verify);
        mTitles.add(refused);
        mTitles.add(service_ing);
        mTitles.add(have_expired);
        return mTitles;
    }

    @Override
    protected List<Fragment> initFragments() {
        mFragments = new ArrayList<>();
        mFragments.add(new DietitianServiceOrderAll());
        mFragments.add(new DietitianServiceOrderDqr());
        mFragments.add(new DietitianServiceOrderYjj());
        mFragments.add(new DietitianServiceOrderFwz());
        mFragments.add(new DietitianServiceOrderYgq());
        return mFragments;
    }

    @Override
    public void init() {
        super.init();
    }
}