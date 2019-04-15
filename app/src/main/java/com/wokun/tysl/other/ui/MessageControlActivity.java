package com.wokun.tysl.other.ui;

import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.tysl.R;
import com.wokun.tysl.base.BaseBindingActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import io.rong.imkit.fragment.ConversationListFragment;
import io.rong.imlib.model.Conversation;

//消息中心
public class MessageControlActivity extends BaseBindingActivity {

    @BindString(R.string.tysl_message_control)String title;
    @BindView(R.id.tab_layout)TabLayout mTabLayout;
    @BindView(R.id.view_pager)ViewPager mViewPager;
    private Fragment mConversationFragment = null;
    private List<Fragment> mFragment;
    private String[] mTitles = {"服务消息","系统消息"};

    private Fragment initConversationList() {
        // appendQueryParameter 对具体的会话列表做展示
        if (mConversationFragment == null) {
            ConversationListFragment listFragment = new ConversationListFragment();
            Uri uri = Uri.parse("rong://" + getApplicationInfo().packageName).buildUpon()
                    .appendPath("conversationList")
                    .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "false")     // 设置私聊会话是否聚合显示
                    .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "false")
                    //.appendQueryParameter(Conversation.ConversationType.PUBLIC_SERVICE.getName(), "false")    // 公共服务号
                    //.appendQueryParameter(Conversation.ConversationType.APP_PUBLIC_SERVICE.getName(), "false")// 公共服务号
                    .appendQueryParameter(Conversation.ConversationType.DISCUSSION.getName(), "false")  // 设置私聊会话是否聚合显示
                    .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "false")      // 设置私聊会是否聚合显示
                    .build();
            listFragment.setUri(uri);
            return listFragment;
        } else {
            return mConversationFragment;
        }
    }

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
        mFragment = new ArrayList<>();
        mFragment.add(initConversationList());
        mFragment.add(new SystemMessageFragment());

        // 配置ViewPager的适配器
        FragmentPagerAdapter mFragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragment.get(position);
            }

            @Override
            public int getCount() {
                return mFragment.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mTitles[position];
            }

            /*@Override
            public boolean isViewFromObject(View arg0, Object arg1) {
                return arg0 != arg1;    //这行代码很重要，它用于判断你当前要显示的页面
            }*/
        };
        mViewPager.setAdapter(mFragmentPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager,false);
    }
}
