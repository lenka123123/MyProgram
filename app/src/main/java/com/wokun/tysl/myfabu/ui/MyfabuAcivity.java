package com.wokun.tysl.myfabu.ui;

import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.tysl.R;
import com.wokun.tysl.base.BaseBindingActivity;

import butterknife.BindString;

/**
 * Created by Administrator on 2018/7/5/005.
 */

public class MyfabuAcivity  extends BaseBindingActivity{
    @BindString(R.string.tysl_edit_fabu)
    String title;

    @Override
    public int createView() {
        return R.layout.activity_center_fabu;
    }

    @Override
    public WidgetBar createToolBar() {
        return mWidgetBar.setWidgetBarTitle(title);
    }

    @Override
    public void init() {

    }
}
