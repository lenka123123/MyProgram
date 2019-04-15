package com.wokun.tysl.mypinjia.ui;

import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.tysl.R;
import com.wokun.tysl.base.BaseBindingActivity;

import butterknife.BindString;

/**
 * Created by Administrator on 2018/7/30/030.
 */

public class MymessagedetailsActivity  extends BaseBindingActivity{
    @BindString(R.string.tysl_user_writemessage) String title;
    @Override
    public int createView() {
        return R.layout.activity_fabiao_pinjia;
    }

    @Override
    public WidgetBar createToolBar() {
        return mWidgetBar.setWidgetBarTitle(title);
    }

    @Override
    public void init() {

    }
}
