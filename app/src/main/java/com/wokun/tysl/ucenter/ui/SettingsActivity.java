package com.wokun.tysl.ucenter.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.kyleduo.switchbutton.SwitchButton;
import com.lzy.okgo.OkGo;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.tysl.R;
import com.wokun.tysl.base.BaseBindingActivity;
import com.wokun.tysl.login.LoginMgr;
import com.wokun.tysl.other.controler.ActionMgr;
import com.wokun.tysl.utils.DataCleanManager;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;

import static com.wokun.tysl.TyslApp.getContext;

//设置页面
public class SettingsActivity extends BaseBindingActivity {

    @BindString(R.string.tysl_settings)String title;
    @BindView(R.id.clear) TextView clear;
    @BindView(R.id.switch_button) SwitchButton switchButton;

    private String changemobile;
    @Override
    public int createView() {
        return R.layout.activity_settings;
    }

    @Override
    public WidgetBar createToolBar() {
        return mWidgetBar.setWidgetBarTitle(title);
    }

    @Override
    public void init() {
        Intent intent = getIntent();
        changemobile = intent.getStringExtra("changemobile");
        if (switchButton.isChecked()) {//接受消息
            JPushInterface.resumePush(this);
        } else {//拒绝消息
            if(!JPushInterface.isPushStopped(this)){
                JPushInterface.stopPush(this);
            }
        }
        try {
            clear.setText(DataCleanManager.getTotalCacheSize(this));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**个人信息*/
    @OnClick(R.id.action_edit_personal)
    public void actionEditPersonal(View v) {
        Intent intent = new Intent(SettingsActivity.this, PersonalInfoActivity.class);
 /*       Bundle bundle = new Bundle();
        bundle.putString("key_username", txt_username.getText().toString());*/





        startActivity(intent);
    }
    /**修改密码*/
    @OnClick(R.id.action_edit_password)
    public void actionEditPassword(View v) {
        Intent intent = new Intent(SettingsActivity.this, EditPwdActivity.class);
        intent.putExtra("changemobile",changemobile);

        startActivity(intent);
    }

    /**修改绑定手机*/
    @OnClick(R.id.action_edit_mobile)
    public void actionEditMobile(View v) {
        Intent intent = new Intent(SettingsActivity.this, NewEditMobileActivity.class);


        startActivity(intent);
    }

    /**是否接收消息通知*/
    @OnClick(R.id.action_message_toast)
    public void actionMessageToast(View v) {
        //接收消息通知
        //关闭接收信息通知，则取消推送，只能通过打开APP获取刷新数据
        switchButton.setChecked(!switchButton.isChecked());
    }

    /**关于我们*/
    @OnClick(R.id.action_about_us)
    public void actionAboutUs(View v) {
        startActivity(AboutUsActivity.class);
    }

    /** 拨打客服2 */
    @OnClick(R.id.call_kefu)
    public void actionCallService(){
        Log.e("进来了1122","进来了2");
        ActionMgr.getInstance().callService(SettingsActivity.this);
    }


    /**意见反馈*/
    @OnClick(R.id.action_feed_back)
    public void actionFeedBack(View v) {
        startActivity(FeedBackActivity.class);
    }

    /**检查更新*/
    @OnClick(R.id.action_check_update)
    public void actionCheckUpdate(View v) {

    }

    /**清空缓存*/
    @OnClick(R.id.action_clear_cache)
    public void actionClearCache(View v) {
        DataCleanManager.clearAllCache(this);
        try {
            clear.setText(DataCleanManager.getTotalCacheSize(this));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**退出登录*/
    @OnClick(R.id.action_logout)
    public void action_logout(View v) {
        LoginMgr.getInstance().logout(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OkGo.getInstance().cancelTag(this);
    }
}
