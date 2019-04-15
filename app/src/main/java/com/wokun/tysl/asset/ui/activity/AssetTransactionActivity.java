package com.wokun.tysl.asset.ui.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.tysl.R;
import com.wokun.tysl.asset.ui.fragment.AssetReleaseBuyFragment;
import com.wokun.tysl.asset.ui.fragment.AssetReleaseSellFragment;
import com.wokun.tysl.base.BaseBindingActivity;
import com.wokun.tysl.utils.TabLayoutBinder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 资产交易页面
 */

public class AssetTransactionActivity extends BaseBindingActivity {

    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    private String tabTitles[] = new String[]{"资产出售","资产买入"};

    @Override
    public int createView() {
        return R.layout.activity_tablayout;
    }

    @Override
    public WidgetBar createToolBar() {
        return mWidgetBar.setWidgetBarTitle("交易市场");
    }

    @Override
    public void init() {
        List<Fragment> list = new ArrayList<>(2);
        list.add(new AssetReleaseSellFragment());
        list.add(new AssetReleaseBuyFragment());
        TabLayoutBinder.getInstance()
                       .bindViewPager(this, mTabLayout, mViewPager)
                       .bindSimpleAdapter(tabTitles, list, getSupportFragmentManager());
    }
}