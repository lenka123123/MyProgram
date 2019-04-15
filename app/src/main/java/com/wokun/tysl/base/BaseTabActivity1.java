package com.wokun.tysl.base;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.shantoo.common.utils.Logger;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.tysl.R;

import java.util.List;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class BaseTabActivity1 extends AppCompatActivity {

    @BindColor(R.color.colorToolBarTitle)int color;
    @BindView(R.id.tab_layout) public TabLayout mTabLayout;
    protected WidgetBar mWidgetBar;
    protected Fragment mCurrentFragment;

    protected List<String> mTitles;
    protected List<Fragment> mFragments;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_tab);
        mWidgetBar = (WidgetBar) findViewById(R.id.toolbar);
    //    mWidgetBar.setWidgetBarTitle(initTitle());
        initToolBar(mWidgetBar);

        ButterKnife.bind(this);
        init();
    }


    public void initToolBar(WidgetBar toolBar) {
        if (toolBar == null) {return;}
        toolBar
                .setWidgetBarTitleTextSize(18)
                .setWidgetBarTitleTextColor(color)
                .setWidgetBarNavigation(R.drawable.ic_back)
                .setOnNavigationClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
    }







    @Override
    protected void onResume() {
        super.onResume();
    }


    public void init() {

        mTitles = initTabTitles();
        mFragments = initFragments();
        int state = initState();
        for (int i = 0; i < mTitles.size(); i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(mTitles.get(i)));
        }
        mCurrentFragment = mFragments.get(state);
        setCurrentTab(mCurrentFragment.getClass());
        mTabLayout.setScrollPosition(state,0,true);
        mTabLayout.addOnTabSelectedListener(new OnTabSelectedListener());
    }

    protected void setCurrentTab(Class<? extends Fragment> clazz){
        showAndHide(R.id.content, clazz);
    }

    protected void showAndHide(int contentId, Class<? extends Fragment> clazz) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Fragment showFragment = getSupportFragmentManager().findFragmentByTag(clazz.getSimpleName());//判断fragment有没有添加过

        if(showFragment == null){
            try {
                //Logger.e(TAG,"showFragment 为空");
                //通过无参的 公开的构造函数来创建Fragment实例
                showFragment = clazz.newInstance();
                //当前的Fragment没有添加过 把Fragment添加到manager里面
                transaction.add(contentId, showFragment, clazz.getSimpleName());

                if(mCurrentFragment.getClass() != clazz){
                    //隐藏当前的Fragment
                    transaction.hide(mCurrentFragment);
                    //让记录当前的fragment赋值为显示的fragment
              //      Logger.e(TAG,"mCurrentFragment："+ mCurrentFragment.getClass().getSimpleName() +"/n showFragment："+showFragment.getClass().getSimpleName());
                    mCurrentFragment = showFragment;
                }
                transaction.show(mCurrentFragment);
            //    Logger.e(TAG,"mCurrentFragment："+ mCurrentFragment.getClass().getSimpleName() +"showFragment："+showFragment.getClass().getSimpleName());
                transaction.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            //Logger.e(TAG,"showFragment 不为空");
            if(mCurrentFragment.getClass() != clazz){
                //隐藏当前的fragment
                transaction.hide(mCurrentFragment);
                //让记录当前的fragment赋值为显示的fragment
                //Logger.e(TAG,"currentFragment："+mCurrentFragment.getClass().getSimpleName()+"showFragment："+showFragment.getClass().getSimpleName());
                mCurrentFragment = showFragment;
            }
            //Logger.e(TAG,"currentFragment："+mCurrentFragment.getClass().getSimpleName()+"showFragment："+showFragment.getClass().getSimpleName());
            transaction.show(mCurrentFragment); //显示需要的fragment
            transaction.commit();
        }
    }

    private class OnTabSelectedListener implements TabLayout.OnTabSelectedListener {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            setCurrentTab(mFragments.get(tab.getPosition()).getClass());
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {
        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {
        }
    }
     // public abstract WidgetBar createToolBar();
    protected abstract int initState();

   protected abstract String initTitle();

    protected abstract List<String> initTabTitles();

    protected abstract List<Fragment> initFragments();
}