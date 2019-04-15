package com.wokun.tysl.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;

import com.wokun.tysl.TyslApp;

public class ActionUtil {

    private static boolean flag;

    public static void actionCall(Activity activity, String mobile) {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + mobile));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //检查权限
            if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                //请求权限
                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CALL_PHONE}, 1);
            } else {
                flag = true;
            }
        } else {
            flag = true;
        }
        if(flag){
            TyslApp.getContext().startActivity(intent);
        }
    }
}
