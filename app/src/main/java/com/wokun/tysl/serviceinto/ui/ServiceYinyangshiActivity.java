package com.wokun.tysl.serviceinto.ui;

import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.tysl.R;
import com.wokun.tysl.base.BaseBindingActivity;

import butterknife.BindString;

/**
 * Created by Administrator on 2018/7/10/010.
 */

public class ServiceYinyangshiActivity  extends BaseBindingActivity{
    @BindString(R.string.tysl_edit_yinyangshi)String title;
    @Override
    public int createView() {
        return R.layout.activity_service_fuwu;
    }

    @Override
    public WidgetBar createToolBar() {
        return mWidgetBar.setWidgetBarTitle(title);
    }

    @Override
    public void init() {

    }
}
