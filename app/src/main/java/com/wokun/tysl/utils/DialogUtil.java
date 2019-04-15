package com.wokun.tysl.utils;

import android.content.Context;

import com.afollestad.materialdialogs.MaterialDialog;

public class DialogUtil {

    /**  弹出一个对话框
     * @param context Context
     * @param content 弹窗内容文本
     * @param neutral 取消按钮
     * @param positive 确定按钮
     * */
    public static void showDialog(Context context,String content,String neutral,String positive,MaterialDialog.SingleButtonCallback callback){
        new MaterialDialog.Builder(context)
                .content(content)
                .neutralText(neutral)
                .positiveText(positive)
                .onAny(callback)
                .show();
    }

    /**  弹出一个对话框
     * @param context Context
     * @param content 弹窗内容文本
     * @param positive 确定按钮
     * */
    public static void showDialog(Context context,String content,String positive,MaterialDialog.SingleButtonCallback positiveCallback){
        new MaterialDialog.Builder(context)
                .content(content)
                .positiveText(positive)
                .onAny(positiveCallback)
                .show();
    }
}
