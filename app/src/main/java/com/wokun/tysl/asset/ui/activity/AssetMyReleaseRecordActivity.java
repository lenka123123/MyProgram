package com.wokun.tysl.asset.ui.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.tysl.R;
import com.wokun.tysl.asset.ui.fragment.AssetMyReleaseRecordFragment;
import com.wokun.tysl.base.BaseBindingActivity;
import com.wokun.tysl.utils.TabLayoutBinder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 发布记录明细
 */
public class AssetMyReleaseRecordActivity extends BaseBindingActivity {

    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    @Override
    public int createView() {
        return R.layout.activity_tablayout;
    }

    @Override
    public WidgetBar createToolBar() {
        return mWidgetBar.setWidgetBarTitle("发布记录");
    }

    private String tabTitles[] = new String[]{"资产出售","资产买入"};

    @Override
    public void init() {
        List<Fragment> list = new ArrayList<>(2);
        list.add(AssetMyReleaseRecordFragment.newInstance(2));
        list.add(AssetMyReleaseRecordFragment.newInstance(1));
        TabLayoutBinder.getInstance()
                .bindViewPager(this, mTabLayout, mViewPager)
                .bindSimpleAdapter(tabTitles, list, getSupportFragmentManager());
    }
}
