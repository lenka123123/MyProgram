package com.wokun.tysl.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.classic.common.MultipleStatusView;
import com.shantoo.widget.dialog.loaddialog.LoadDialog;
import com.shantoo.widget.toast.RxToast;
import com.wokun.tysl.R;
import com.wokun.tysl.TyslApp;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.login.ui.LoginActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment1 extends Fragment {

    protected MultipleStatusView mMultipleStatusView;
    protected String TAG = this.getClass().getSimpleName();
    protected Unbinder unbinder;

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_base, container, false);

        View contentView = inflater.inflate(createView(), null);
        unbinder = ButterKnife.bind(this, contentView);

        mMultipleStatusView = (MultipleStatusView) root.findViewById(R.id.multipleStatusView);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mMultipleStatusView.addView(contentView,params);
        mMultipleStatusView.setOnRetryClickListener(mRetryClickListener);

        return contentView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
        initToolBar();
        loadData();
    }

    View.OnClickListener mRetryClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            RxToast.showShort("您点击了重试视图");
        }
    };

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

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }

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
    protected boolean isVisible;

 /*   @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if(getUserVisibleHint()) {
            isVisible = true;
        } else {
            isVisible = false;
        }}
*/
    protected void showLoadingProgress() {
        LoadDialog.show(getContext());
    }

    protected void dismissLoadingProgress() {
        LoadDialog.dismiss(getContext());
    }
}