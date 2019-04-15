package com.wokun.tysl.utils;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.List;

public class TabLayoutBinder {

    private ViewPager mViewPager;

    private static TabLayoutBinder rxAndroid;

    private TabLayoutBinder() {}

    /** 单一实例 */
    public static TabLayoutBinder getInstance(){
        if (rxAndroid == null) {
            synchronized (TabLayoutBinder.class) {
                rxAndroid  = new TabLayoutBinder();
            }
        }
        return rxAndroid ;
    }

    public TabLayoutBinder bindViewPager(Context context, TabLayout tabLayout, ViewPager viewPager){
        mViewPager = viewPager;

        if(tabLayout!=null){
            tabLayout.setupWithViewPager(mViewPager);
            tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
            tabLayout.setTabMode(TabLayout.MODE_FIXED);//设置tab模式，当前为系统默认模式
        }
        return this;
    }

    public void bindSimpleAdapter(List<String> titles, List<Fragment> fragments, FragmentManager fm){
        mViewPager.setAdapter(new SimpleFragmentPagerAdapter(titles.toArray(new String[titles.size()]),fragments,fm));//给ViewPager设置适配器
    }

    public void bindSimpleAdapter(String[] titles, List<Fragment> fragments, FragmentManager fm){
        mViewPager.setAdapter(new SimpleFragmentPagerAdapter(titles,fragments,fm));//给ViewPager设置适配器
    }

    public void bindCustomAdapter(List<String> titles, List<Fragment> fragments, FragmentManager fm){
       // mViewPager.setAdapter(titles,fragments,fm);//给ViewPager设置适配器
    }


    private class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {
        private String[] titleList;
        private List<Fragment> fragmentList;

        private SimpleFragmentPagerAdapter(String[] titles, List<Fragment> fragments, FragmentManager fm) {
            super(fm);
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

        @Override
        public CharSequence getPageTitle(int position) {
            return titleList[position];
        }
    }

    /*class CustomFragmentPagerAdapter extends FragmentPagerAdapter {

        private List<String> titleList;
        private List<Fragment> fragmentList;

        public CustomFragmentPagerAdapter(List<String> titles, List<Fragment> fragments, FragmentManager fm) {
            super(fm);
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

        public View getTabView(int position){
            View view = LayoutInflater.from(Holder.rxAndroid).inflate(R.layout.item_my_apply_for_tab, null);
            TextView tv= (TextView) view.findViewById(R.id.tab_title);
            tv.setText(titleList.get(position));
            // ImageView img = (ImageView) view.findViewById(R.id.imageView);
            // img.setImageResource(imageResId[position]);
            return view;
        }
    }*/

}
