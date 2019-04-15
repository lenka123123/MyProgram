package com.wokun.tysl.myyijian.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/7/9/009.
 */

public class MyMessageFragmentAdapter extends FragmentPagerAdapter {



    private List<Fragment> mFragmentList;

    public MyMessageFragmentAdapter(FragmentManager fm) {
        super(fm);
    }


    public void setFragments(ArrayList<Fragment> fragments) {
        mFragmentList = fragments;
    }
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = mFragmentList.get(position);
        return fragment;
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

  /*  @Override
    public CharSequence getPageTitle(int position) {
        return  tabTitles[position];
    }*/
}
