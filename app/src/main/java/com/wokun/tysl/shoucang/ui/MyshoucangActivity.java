package com.wokun.tysl.shoucang.ui;


import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import com.shantoo.widget.toast.RxToast;
import com.wokun.tysl.R;
import com.wokun.tysl.article.ui.ArticleFavoritesActivity;
import com.wokun.tysl.dietician.ui.activity.DieticianFavoritesActivity;
import com.wokun.tysl.goods.ui.activity.GoodsFavoritesActivity;
import com.wokun.tysl.store.ui.StoreFavoritesActivity;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/7/9/009.
 */

public class MyshoucangActivity extends TabActivity {
    protected TabHost tabhost;

    private static final String TAB_PAGE1="page1";
    private static final String TAB_PAGE2="page2";
    private static final String TAB_PAGE3="page3";
    private static final String TAB_PAGE4="page4";

    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_MAX_OFF_PATH = 250;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;
    private GestureDetector gestureDetector;
    View.OnTouchListener gestureListener;

    private static int maxTabIndex = 3;
    //选择的Tab
    private int tab_last_position;
    //通过TabWidget可以获取选项卡的布局
    private TabWidget mTabWidget;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_controltab);
        InitView();
    }

    public void InitView() {
        ImageView ic_back = (ImageView) findViewById(R.id.ic_back);
        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        tabhost=getTabHost();

        TabHost.TabSpec tabSpec = tabhost.newTabSpec(TAB_PAGE4);
        tabSpec.setIndicator(createTabView("商品收藏"));
        Intent intent = new Intent(this, GoodsFavoritesActivity.class);
        tabSpec.setContent(intent);
        tabhost.addTab(tabSpec);

        tabSpec = tabhost.newTabSpec(TAB_PAGE2);
        tabSpec.setIndicator(createTabView("店铺收藏"));
        intent = new Intent(this, StoreFavoritesActivity.class);
        tabSpec.setContent(intent);
        tabhost.addTab(tabSpec);

        tabSpec = tabhost.newTabSpec(TAB_PAGE3);
        intent = new Intent(this, DieticianFavoritesActivity.class);
        tabSpec.setContent(intent);
        tabSpec.setIndicator(createTabView("顾问收藏"));
        tabhost.addTab(tabSpec);

        tabSpec = tabhost.newTabSpec(TAB_PAGE1);
        tabSpec.setIndicator(createTabView("文章收藏"));
        intent = new Intent(this, ArticleFavoritesActivity.class);
        tabSpec.setContent(intent);
        tabhost.addTab(tabSpec);
        //

        //默认使其选中
        tabhost.setCurrentTab(0);
        tab_last_position=0;
        View view =  tabhost.getCurrentTabView();
        ImageView imageView = (ImageView) view.findViewById(R.id.tab_line);
        TextView mTextView = (TextView) view.findViewById(R.id.tab_name);
        imageView.setVisibility(View.VISIBLE);
       // mTextView.setTextColor(Color.GREEN);
        mTextView.setTextColor(getResources().getColor(R.color.colorPrimary));

        mTabWidget = tabhost.getTabWidget();



        tabhost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                ImageView imageView = null;
                //获取到视图，可以对其进行样式的改变
                View lastView = mTabWidget.getChildAt(tab_last_position);
                imageView = (ImageView) lastView.findViewById(R.id.tab_line);
                TextView mTextView = (TextView) lastView.findViewById(R.id.tab_name);
                if (imageView!=null) {
                    imageView.setVisibility(View.GONE);
                    mTextView.setTextColor(getResources().getColor(R.color.black));
                }

                View view =  tabhost.getCurrentTabView();
                imageView = (ImageView) view.findViewById(R.id.tab_line);
                mTextView = (TextView) view.findViewById(R.id.tab_name);
                imageView.setVisibility(View.VISIBLE);
                mTextView.setTextColor(getResources().getColor(R.color.colorPrimary));

                mTabWidget = tabhost.getTabWidget();
                tab_last_position = tabhost.getCurrentTab();

            }
        });


        //滑动事件
        gestureDetector = new GestureDetector(new TabGestureDetector());


    }

    // 创建tab标签
    protected View createTabView(String name) {
        View tabView = getLayoutInflater().inflate(R.layout.tab_background, null);
        TextView textView = (TextView) tabView.findViewById(R.id.tab_name);// 找到textview控件
        textView.setText(name);
        return tabView;
    }


    // 左右滑动刚好页面也有滑动效果
    private class TabGestureDetector extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                               float velocityY) {

            try {
                if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH)
                    return false;
                // right to left swipe
                int currentView = tabhost.getCurrentTab();

                if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE
                        && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    Log.i("test", "right" +currentView);
                    if (currentView<maxTabIndex) {
                        currentView++;
                        tabhost.setCurrentTab(currentView);
                    }
                } else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE
                        && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    Log.i("test", "left" +currentView);
                    if (currentView > 0) {
                        currentView--;
                        tabhost.setCurrentTab(currentView);
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (gestureDetector.onTouchEvent(event)) {
            Log.i("test", "---------------dispatch ... ");
            event.setAction(MotionEvent.ACTION_CANCEL);
        }
        return super.dispatchTouchEvent(event);
    }


}
