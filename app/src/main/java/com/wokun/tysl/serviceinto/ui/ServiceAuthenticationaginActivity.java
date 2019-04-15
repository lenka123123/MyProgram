package com.wokun.tysl.serviceinto.ui;

import android.view.View;
import android.widget.ImageView;

import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.tysl.R;
import com.wokun.tysl.base.BaseBindingActivity;
import com.wokun.tysl.servicein.ui.ServiceAuthenticationActivity;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/7/10/010.
 */
//服务入驻认证页面
public class ServiceAuthenticationaginActivity extends BaseBindingActivity{
    @BindString(R.string.tysl_edit_userruzhu)String title;
    @BindView(R.id.sc_fuwu)
    ImageView mScfuwu; //服务入住
    @BindView(R.id.sc_shangjia)
    ImageView mSchangjia;
    @BindView(R.id.sc_zhihui)
    ImageView mSczhihui;
    @Override
    public int createView() {
        return R.layout.activity_service_into;
    }

    @Override
    public WidgetBar createToolBar() {
        return mWidgetBar.setWidgetBarTitle(title);
    }

    @Override
    public void init() {
    }
    /**服务入住
     * ServiceAuthenticationActivity
     * ServiceYinyangshiActivity
     * */
    @OnClick(R.id.sc_fuwu)
        public void actionFuwuinto(View v) {
        startActivity(ServiceAuthenticationActivity.class);
    }

    /**商家入住*/
    @OnClick(R.id.sc_shangjia)
    public void actionShangjiainto(View v) {
        startActivity(ServiceShangjiaActivity.class);
    }
    /**智慧零售*/
    @OnClick(R.id.sc_zhihui)
    public void actionZhihuiLinshou(View v) {
        startActivity(ServiceZhihiuiActivity.class);
    }




}
