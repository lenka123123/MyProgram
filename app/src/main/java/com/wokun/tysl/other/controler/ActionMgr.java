package com.wokun.tysl.other.controler;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.shantoo.widget.toast.RxToast;
import com.wokun.tysl.R;
import com.wokun.tysl.TyslApp;

public class ActionMgr {

    private ActionMgr(){

    }

    private static class ActionMgrHolder{
        private static ActionMgr instance = new ActionMgr();
    }

    public static ActionMgr getInstance(){
        return ActionMgrHolder.instance;
    }

    //客服
    public void callService(final Context context) {
        if(!TyslApp.getInstance().isLogin()) return;

        new MaterialDialog.Builder(context)
                .content("是否确认拨打电话")
                .neutralText("取消")
                .positiveText("拨打")
                .onAny(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        if(which == which.POSITIVE){
                            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "400-025-2166"));
                            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE)
                                    != PackageManager.PERMISSION_GRANTED) {
                                return;
                            }
                            context.startActivity(intent);
                        }
                    }
                })
                .show();
    }
}