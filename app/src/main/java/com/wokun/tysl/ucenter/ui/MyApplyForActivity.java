package com.wokun.tysl.ucenter.ui;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.shantoo.common.utils.UIUtil;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.tysl.R;
import com.wokun.tysl.base.BaseBindingActivity;
import com.wokun.tysl.utils.TabLayoutBinder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindString;
import butterknife.BindView;

//我的申请
public class MyApplyForActivity extends BaseBindingActivity {

    @BindString(R.string.tysl_my_apply_for)
    String title;

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
        return mWidgetBar.setWidgetBarTitle(title);
    }

    private String tabTitles[] = new String[]{"全部","服务中","待评价"};

    @Override
    public void init() {
        List<Fragment> list = new ArrayList<>(3);
        list.add(MyApplyForFragment.newInstance(0));
        list.add(MyApplyForFragment.newInstance(3));
        list.add(MyApplyForFragment.newInstance(4));
        // mTabLayout.setTabsFromPagerAdapter(adapter);//给Tabs设置适配器

        TabLayoutBinder.getInstance().bindViewPager(this, mTabLayout, mViewPager).bindSimpleAdapter(tabTitles, list, getSupportFragmentManager());

        /*CustomFragmentPagerAdapter pagerAdapter = new CustomFragmentPagerAdapter(this, list);
        mViewPager.setAdapter(pagerAdapter);//给ViewPager设置适配器
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);//设置tab模式，当前为系统默认模式

        for (int i = 0; i < mTabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = mTabLayout.getTabAt(i);
            tab.setCustomView(pagerAdapter.getTabView(i));
        }*/
    }

    //ViewPager适配器
    class CustomFragmentPagerAdapter extends FragmentPagerAdapter {
        private Context mContext;
        private List<String> titleList;
        private List<Fragment> fragmentList;

        public CustomFragmentPagerAdapter(Context context, List<String> titles, List<Fragment> fragments) {
            super(getSupportFragmentManager());
            mContext = context;
            titleList = titles;
            fragmentList = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        public View getTabView(int position) {
            View view = UIUtil.createView(R.layout.item_my_apply_for_tab);
            //  View view = LayoutInflater.from(mContext).inflate(R.layout.item_my_apply_for_tab, null);
            TextView tv = (TextView) view.findViewById(R.id.tab_title);
            tv.setText(titleList.get(position));
            // ImageView img = (ImageView) view.findViewById(R.id.imageView);
            // img.setImageResource(imageResId[position]);
            return view;
        }
    }
}
