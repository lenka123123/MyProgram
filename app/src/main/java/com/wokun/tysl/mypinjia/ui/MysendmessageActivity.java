package com.wokun.tysl.mypinjia.ui;

import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.tysl.R;
import com.wokun.tysl.base.BaseBindingActivity;

import butterknife.BindString;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/7/30/030.
 */

public class MysendmessageActivity extends BaseBindingActivity {

    @BindString(R.string.tysl_user_messagetwo) String title;
    @Override
    public int createView() {
        return R.layout.activity_my_pinjia;
    }

    @Override
    public WidgetBar createToolBar() {
        return mWidgetBar.setWidgetBarTitle(title);
    }

    @Override
    public void init() {

    }
    /** 我的评价 */
    @OnClick(R.id.my_pj_btn)
    public void actionPJbtn() {
        startActivity(MymessagedetailsActivity.class);
    }


}
