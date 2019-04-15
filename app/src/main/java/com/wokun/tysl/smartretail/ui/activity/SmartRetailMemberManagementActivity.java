package com.wokun.tysl.smartretail.ui.activity;

import android.support.v4.app.Fragment;

import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.tysl.base.BaseTabActivity;
import com.wokun.tysl.smartretail.ui.fragment.HistoryFragment;
import com.wokun.tysl.smartretail.ui.fragment.TodayFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 智慧零售-会员管理
 */

public class SmartRetailMemberManagementActivity extends BaseTabActivity {

    @Override
    protected int initState() {
        return 0;
    }

    @Override
    protected String initTitle() {
        return "会员管理";
    }
    @Override
    public WidgetBar createToolBar() {
        return mWidgetBar.setWidgetBarTitle("会员管理");
    }


    @Override
    protected List<String> initTabTitles() {
        mTitles = new ArrayList<>();
        mTitles.add("今日人数");
        mTitles.add("历史人数");
        return mTitles;
    }

    @Override
    protected List<Fragment> initFragments() {
        mFragments = new ArrayList<>();
        mFragments.add(new TodayFragment());
        mFragments.add(new HistoryFragment());
        return mFragments;
    }
}
