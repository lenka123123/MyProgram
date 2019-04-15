package com.wokun.tysl.utils;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.TimePickerView;
import com.bigkoo.pickerview.listener.CustomListener;
import com.shantoo.widget.dialog.sheetdialog.ActionSheetDialog;
import com.shantoo.widget.dialog.sheetdialog.OnSheetItemClickListener;
import com.wokun.tysl.R;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SheetDialogUtil {

    public static final int REQUEST_CODE_CAMERA = 1;

    private static void camera(Activity activity, File file) {
        if (!android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)) {
            Toast.makeText(activity, "sd卡不存在", Toast.LENGTH_SHORT).show();
            return;
        }
        activity.startActivityForResult(new Intent(MediaStore.ACTION_IMAGE_CAPTURE).putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file)), REQUEST_CODE_CAMERA);
    }

    public static void showPhotoSheetDialog(final Activity activity, final File file) {
        new ActionSheetDialog(activity)
                .builder()
                .addSheetItem("拍照", R.color.colorPrimary,
                        new OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                camera(activity,file);
                            }
                        })
                .addSheetItem("我的相册选择", R.color.colorPrimary,
                        new OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {

                            }
                        }).show();
    }

    public static void showSexSheetDialog(Activity activity, final TextView textView) {
        new ActionSheetDialog(activity)
                .builder()
                .addSheetItem("男", R.color.colorPrimary,
                        new OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                textView.setText("男");
                            }
                        })
                .addSheetItem("女", R.color.colorPrimary,
                        new OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                textView.setText("女");
                            }
                        }).show();
    }

    private static TimePickerView pvCustomLunar;

    public static void showDateSheetDialog(Activity activity, final TextView textView) {
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(2014, 1, 23);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2027, 2, 28);
        //时间选择器 ，自定义布局
        pvCustomLunar = new TimePickerView.Builder(activity, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                //Toast.makeText(TyslApp.getContext(), getTime(date), Toast.LENGTH_SHORT).show();
                textView.setText(getTime(date));
            }
        })
                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
                .setLayoutRes(R.layout.widget_pickerview_custom_lunar, new CustomListener() {

                    @Override
                    public void customLayout(final View v) {
                        final TextView tvSubmit = (TextView) v.findViewById(R.id.tv_finish);
                        ImageView ivCancel = (ImageView) v.findViewById(R.id.iv_cancel);
                        tvSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomLunar.returnData();
                                pvCustomLunar.dismiss();
                            }
                        });
                        ivCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomLunar.dismiss();
                            }
                        });
                    }
                })
                .setType(new boolean[]{true, true, true, false, false, false})
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setDividerColor(Color.RED)
                .build();
        pvCustomLunar.show();
    }

    private static String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }
}