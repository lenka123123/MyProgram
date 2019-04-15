package com.wokun.tysl.asset.ui.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.tysl.R;
import com.wokun.tysl.asset.ui.fragment.AssetReleasedFragment;
import com.wokun.tysl.base.BaseBindingActivity;
import com.wokun.tysl.utils.TabLayoutBinder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 我要发布页面
 */

public class AssetReleaseActivity extends BaseBindingActivity {

    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    private String tabTitles[] = new String[]{"出售发布","求购发布"};

    @Override
    public int createView() {
        return R.layout.activity_tablayout;
    }

    @Override
    public WidgetBar createToolBar() {
        return mWidgetBar
                .setWidgetBarTitle("我要发布")
                .setMenu("发布记录",null)
                .setOnMenuClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(AssetMyReleaseRecordActivity.class);
                    }
                },null);
    }

    @Override
    public void init() {
        List<Fragment> list = new ArrayList<>(2);
        list.add(AssetReleasedFragment.newInstance(2));
        list.add(AssetReleasedFragment.newInstance(1));
        TabLayoutBinder.getInstance()
                .bindViewPager(this, mTabLayout, mViewPager)
                .bindSimpleAdapter(tabTitles, list, getSupportFragmentManager());
    }
}