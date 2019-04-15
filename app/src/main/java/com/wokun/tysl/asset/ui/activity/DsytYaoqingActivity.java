package com.wokun.tysl.asset.ui.activity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaActionSound;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v13.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shantoo.widget.toast.RxToast;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.tysl.R;
import com.wokun.tysl.TyslApp;
import com.wokun.tysl.asset.bean.DsytyaoqinBean;
import com.wokun.tysl.base.BaseBindingActivity;
import com.wokun.tysl.callback.JsonCallback;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.goods.ui.activity.GoodsDetailActivity;
import com.wokun.tysl.model.response.BaseResponse;
import com.wokun.tysl.utils.ImageLoader;
import com.wokun.tysl.utils.ImgUtils;
import com.wokun.tysl.utils.ScreenShotUtils;
import com.wokun.tysl.utils.ShareSDKUtil;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.onekeyshare.ShareContentCustomizeCallback;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;
import io.rong.imageloader.utils.L;

/**
 * Created by Administrator on 2018\11\28 0028.
 */

public class DsytYaoqingActivity extends BaseBindingActivity {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.dsyt_yaoqin_img)
    ImageView mDsytYaoqinImg1;
    @BindView(R.id.dsyt_yaoqin_txt)
    TextView mDsytYaoqinTxt1;
    //  @BindView(R.id.yaoqin_share)ImageView yaoqin_share;
    @BindView(R.id.my_yaoqinma1)
    LinearLayout yaoqin_share;
    String picPath;
    private DsytyaoqinBean dsytyaoqinBean;
    private MediaActionSound mCameraSound;
    private Bitmap saveBitmap;
    private ImageView mDsytYaoqinImg;
    private TextView mDsytYaoqinTxt;

    @Override
    public int createView() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        return R.layout.activity_dsyt_yaoqin;
    }

    @Override
    public WidgetBar createToolBar() {
        mWidgetBar.setVisibility(View.GONE);
        return mWidgetBar;
    }

    @Override
    public void init() {

        loadData();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
            return;
        }
        mCameraSound = new MediaActionSound();
        mCameraSound.load(MediaActionSound.SHUTTER_CLICK);
/*
        mDsytYaoqinImg.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Log.e("长按进来了", "长按进来了");

                ImgUtils.saveImageToGallery(DsytYaoqingActivity.this, mDsytYaoqinImg);
                return false;}
        });
*/


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        loadData();
    }


    private void loadData() {
        OkGo.<BaseResponse<DsytyaoqinBean>>post(Constants.BASE_URL + Constants.INDEX_INVITE)
                .tag(this)
                .execute(new JsonCallback<BaseResponse<DsytyaoqinBean>>(Constants.WITH_TOKEN, Constants.INDEX_INVITE) {
                    @Override
                    public void onSuccess(Response<BaseResponse<DsytyaoqinBean>> response) {

                        BaseResponse body = response.body();
                        if (body == null) return;
                        dsytyaoqinBean = (DsytyaoqinBean) body.getData();
                        Glide.with(DsytYaoqingActivity.this).load("http://api.tyitop.com/images/app_download.png").into(mDsytYaoqinImg1);
                        mDsytYaoqinTxt1.setText(dsytyaoqinBean.getInvite_code());
                        if (dsytyaoqinBean == null) {
                            return;
                        }
                        Log.e("userInfo.getUserType()", "" + dsytyaoqinBean.getCode_pic());
                        View v = LayoutInflater.from(DsytYaoqingActivity.this).inflate(R.layout.activity_dsyt_yaoqin, null, false);
                        mDsytYaoqinTxt = (TextView) v.findViewById(R.id.dsyt_yaoqin_txt);
                        mDsytYaoqinImg = (ImageView) v.findViewById(R.id.dsyt_yaoqin_img);
                        mDsytYaoqinTxt.setText(dsytyaoqinBean.getInvite_code());
                        //   Glide.with(DsytYaoqingActivity.this).load("http://api.tyitop.com/images/app_download.png").into(mDsytYaoqinImg);
                        ImageLoader.loadImage("http://api.tyitop.com/images/app_download.png", mDsytYaoqinImg);
//                         mDsytYaoqinImg.post(new Runnable() {
//                            @Override
//                            public void run() {
//                                Glide.with(DsytYaoqingActivity.this).load("http://api.tyitop.com/images/app_download.png").into(mDsytYaoqinImg);
//
//                            }
//                        });
                        DisplayMetrics metric = new DisplayMetrics();
                        getWindowManager().getDefaultDisplay().getMetrics(metric);
                        int width = metric.widthPixels;     // 屏幕宽度（像素）
                        int height = metric.heightPixels;   // 屏幕高度（像素）
                        ImageUtils.layoutView(v, width, height);
                        final ScrollView tv = (ScrollView) v.findViewById(R.id.my_yaoqinma);
                        final Runnable runnable = new Runnable() {
                            @Override
                            public void run() {
                                picPath = ImageUtils.viewSaveToImage(tv, "makemone");
                                //  Glide.with(DsytYaoqingActivity.this).load(picPath).into(mDsytYaoqinImg);
                                Log.i("2333", picPath);
                                Uri imageUri = Uri.fromFile(new File(picPath));
                                Log.i("2333", imageUri.toString());
                                Intent intent = new Intent();
                                intent.setAction(Intent.ACTION_SEND);
                                intent.putExtra(Intent.EXTRA_STREAM, imageUri);
                                intent.setType("image/*");
                                startActivity(Intent.createChooser(intent, "分享到 "));
                            }
                        };
                        yaoqin_share.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                picPath = ImageUtils.viewSaveToImage(tv, "makemone");
                                //   Glide.with(DsytYaoqingActivity.this).load(picPath).into(mDsytYaoqinImg);
                                new Handler().post(runnable);
                                // new  Handler().postDelayed(runnable,1000);
                            }
                        });


                    }
                });
    }
}
