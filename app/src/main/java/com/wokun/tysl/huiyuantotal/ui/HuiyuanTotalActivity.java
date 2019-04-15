package com.wokun.tysl.huiyuantotal.ui;


import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.tysl.R;
import com.wokun.tysl.base.BaseBindingActivity;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.huiyuantotal.adapter.HuiyuanFragmentAdapter;
import com.wokun.tysl.huiyuantotal.fragment.Addhuiyuanfragment;
import com.wokun.tysl.huiyuantotal.fragment.Historyhuiyuanfragment;
import com.wokun.tysl.myyijian.adapter.MyMessageFragmentAdapter;
import com.wokun.tysl.myyijian.fragment.HistoryMymessagefragment;
import com.wokun.tysl.myyijian.fragment.SendMymessagefragment;

import java.util.ArrayList;

import butterknife.BindString;
import butterknife.BindView;

/**
 * Created by Administrator on 2018/7/9/009.
 */

public class HuiyuanTotalActivity extends BaseBindingActivity {
    @BindString(R.string.tysl_user_huiyuanguanli)String title;
    @BindView(R.id.tab_layout)TabLayout mTabLayout;
    @BindView(R.id.view_pager)ViewPager mViewPager;
     private  String storeCode;

    @Override
    public int createView() {
        return R.layout.activity_message_control;
    }

    @Override
    public WidgetBar createToolBar() {
        return mWidgetBar.setWidgetBarTitle(title);
    }

    @Override
    public void init() {
        storeCode = getIntent().getStringExtra(Constants.STORE_CODE);




        // 创建一个集合,装填Fragment
        ArrayList<Fragment> fragments = new ArrayList<>();
        // 装填
        fragments.add(new Addhuiyuanfragment());
        fragments.add(new Historyhuiyuanfragment());


        // 创建ViewPager适配器
        HuiyuanFragmentAdapter myPagerAdapter = new HuiyuanFragmentAdapter(getSupportFragmentManager());
        myPagerAdapter.setFragments(fragments);

        // 给ViewPager设置适配器
        mViewPager.setAdapter(myPagerAdapter);
        // TabLayout 指示器 (记得自己手动创建4个Fragment,注意是 app包下的Fragment 还是 V4包下的 Fragment)
        mTabLayout.addTab(mTabLayout.newTab());
        mTabLayout.addTab(mTabLayout.newTab());
       // 使用 TabLayout 和 ViewPager 相关联
        mTabLayout.setupWithViewPager(mViewPager);
        // TabLayout指示器添加文本
        //商品收藏","店铺收藏","顾问收藏","文章收藏
        mTabLayout.getTabAt(0).setText("新增会员");
        mTabLayout.getTabAt(1).setText("历史会员");

      /*  myFragmentShoucangAdapter = new MyFragmentShoucangAdapter(getFragmentManager(), MyshoucangActivity.this);
        mViewPager.setAdapter(myFragmentShoucangAdapter);
        mTabLayout.setupWithViewPager(mViewPager);*/
    }
     public String getStoreCodes(){
            return storeCode;
    }





}
