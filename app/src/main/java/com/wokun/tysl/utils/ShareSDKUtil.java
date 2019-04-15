package com.wokun.tysl.utils;

import android.content.Context;
import android.text.TextUtils;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shantoo.common.utils.Logger;
import com.shantoo.widget.toast.RxToast;
import com.wokun.tysl.R;
import com.wokun.tysl.callback.JsonCallback;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.model.response.BaseResponse;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.onekeyshare.OnekeyShare;

public class ShareSDKUtil {

    /**
     * ShareSDK分享
     * @param context 上下文
     * @param title title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
     * @param url titleUrl是标题的网络链接，仅在人人网和QQ空间使用
     * @param url url仅在微信（包括好友和朋友圈）中使用
     * @param text text是分享文本，所有平台都需要这个字段
     * @param imagePath imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
     * @param comment comment是我对这条分享的评论，仅在人人网和QQ空间使用
     * */
    public static void showShare(Context context,
                     String title,String url,String text,String imagePath,String comment) {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        if(!TextUtils.isEmpty(title)){
            oks.setTitle(title);
        }
        // text是分享文本，所有平台都需要这个字段
        if(!TextUtils.isEmpty(text)){
            oks.setText(text);
        }
        // url仅在微信（包括好友和朋友圈）中使用
        if(!TextUtils.isEmpty(url)){
            oks.setUrl(url);
        }
        if(!TextUtils.isEmpty(imagePath)){
            oks.setImageUrl(imagePath);
        }

        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        if(!TextUtils.isEmpty(url)){
            oks.setTitleUrl(url);
        }

        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        if(!TextUtils.isEmpty(imagePath)){
            oks.setImagePath(imagePath);//确保SDcard下面存在此张图片
        }
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        if(!TextUtils.isEmpty(comment)){
            oks.setComment(comment);
        }
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(context.getResources().getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl(Constants.BASE_URL);

        oks.setCallback(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                Logger.e("ShareSDK","分享成功"+platform.getName());
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                Logger.e("ShareSDK","分享错误"+platform.getName() + throwable.getMessage());
            }

            @Override
            public void onCancel(Platform platform, int i) {
                Logger.e("ShareSDK","分享取消"+platform.getName());
            }
        });
        // 启动分享GUI
        oks.show(context);
    }

    public static void showShare(Context context, String shareTitle,String shareText,String shareUrl,String shareImage) {
        OnekeyShare oks = new OnekeyShare();

        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        if(!TextUtils.isEmpty(shareTitle)){
            oks.setTitle(shareTitle);
        }
        // text是分享文本，所有平台都需要这个字段
        if(!TextUtils.isEmpty(shareText)){
            oks.setText(shareText);
        }
        // url仅在微信（包括好友和朋友圈）中使用
        if(!TextUtils.isEmpty(shareUrl)){
            oks.setUrl(shareUrl);
        }
        if(!TextUtils.isEmpty(shareImage)){
            oks.setImageUrl(shareImage);
        }

        oks.setCallback(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                Logger.e("ShareSDK","分享成功"+platform.getName());
       /*         OkGo.<BaseResponse<Object>>post(Constants.BASE_URL + Constants.INTEGRAL_SHARE)
                        .execute(new JsonCallback<BaseResponse<Object>>(Constants.WITH_TOKEN, Constants.INTEGRAL_SHARE) {
                            @Override
                            public void onSuccess(Response<BaseResponse<Object>> response) {
                                BaseResponse body = response.body();
                                if (body == null) return;
                                RxToast.showShort(body.getMsg());
                                if (body.isState()) {

                                }
                            }
                        });*/



            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                Logger.e("ShareSDK","分享错误" + platform.getName() + throwable.getMessage());
            }

            @Override
            public void onCancel(Platform platform, int i) {
                Logger.e("ShareSDK","分享取消" + platform.getName());
            }
        });
        // 启动分享GUI
        oks.show(context);
    }


















}
