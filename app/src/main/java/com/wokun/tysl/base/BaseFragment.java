package com.wokun.tysl.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shantoo.widget.dialog.loaddialog.LoadDialog;
import com.wokun.tysl.TyslApp;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.login.ui.LoginActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment {

    protected String TAG = this.getClass().getSimpleName();
    protected Unbinder unbinder;

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(createView(), container, false);
        unbinder = ButterKnife.bind(this, view);

        initToolBar();
        initViews();
        loadData();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //Logger.e(TAG,"onActivityCreated");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    public void initToolBar() {}

    public abstract int createView();

    public abstract void initViews();

    public abstract void loadData();

    public void startActivity(Class<?> cls) {
        startActivity(new Intent(getContext(), cls));
    }

    //打开一个Activity时需要携带数据
    public void startActivity(Intent intent, boolean isNeedLogin) {
        if (isNeedLogin) {
            if (TyslApp.getInstance().isLogin()) { //已登录
                startActivity(intent);
            } else { //未登录
                intent.setClass(TyslApp.getContext(),LoginActivity.class);
                startActivityForResult(intent, Constants.LOGIN_REQUEST_CODE);
            }
        } else {
            startActivity(intent);
        }
    }

    //打开一个Activity时不需要携带数据
    public void startActivity(Class<?> cls, boolean isNeedLogin) {
        if (isNeedLogin) {
           // User user = TyslApp.getInstance().getUser();
           // boolean hasLogin = TyslApp.getInstance().checkLogin();
            if (TyslApp.getInstance().isLogin()) {//已登录
                startActivity(cls);
            } else {//未登录
                Intent intent = new Intent();
                intent.setClass(TyslApp.getContext(),LoginActivity.class);
                startActivityForResult(intent, Constants.LOGIN_REQUEST_CODE);
            }
        } else {
            startActivity(cls);
        }
    }

    public void startActivityForResult(Intent intent, int requestCode, boolean isNeedLogin){
        if(isNeedLogin){
            intent.setClass(TyslApp.getContext(),LoginActivity.class);
            startActivity(intent);
            return;
        }
        startActivityForResult(intent,requestCode);
    }

    protected void showLoadingProgress() {
        LoadDialog.show(getContext());
    }

    protected void dismissLoadingProgress() {
        LoadDialog.dismiss(getContext());
    }
}