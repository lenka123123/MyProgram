package com.wokun.tysl.other.controler;

import android.app.Activity;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shantoo.widget.dialog.loaddialog.LoadDialog;
import com.shantoo.widget.toast.RxToast;
import com.wokun.tysl.R;
import com.wokun.tysl.TyslApp;
import com.wokun.tysl.callback.JsonCallback;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.model.bean.SingleParam;
import com.wokun.tysl.model.bean.UploadFileSingle;
import com.wokun.tysl.model.bean.User;
import com.wokun.tysl.model.response.BaseResponse;
import com.shantoo.common.utils.Logger;
import com.wokun.tysl.utils.SignUtil;

import java.io.File;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.rong.eventbus.EventBus;

public class PhotoMgr {

    private PhotoMgr(){}

    private static class PhotoPresenterHolder{
        private static PhotoMgr instance = new PhotoMgr();
    }

    public static PhotoMgr getInstance(){
        return PhotoPresenterHolder.instance;
    }

    /**
     * 头像上传
     * */
    public void upLoadPicture(final Activity activity, File file){
        LoadDialog.show(activity, R.color.colorPrimary);
        User user = TyslApp.getInstance().getUser();
        long time_stamp = System.currentTimeMillis();
        String url = Constants.BASE_URL+ Constants.UCENTER_PICTURE_UPLOAD_URL;
        String sign = SignUtil.getSign(url, user.getUserId(), user.getAccessToken(), time_stamp);

        Map<String,String> params = new HashMap<>();
        params.put("user_id",user.getUserId());
        params.put("time_stamp",time_stamp+"");
        params.put("sign",sign);

        OkGo.<BaseResponse<SingleParam>>post(url)
                .tag(this)
                .params(params)
                .params(Constants.UPLOAD_FILE, file) 	// 支持多文件同时添加上传
                .execute(new JsonCallback<BaseResponse<SingleParam>>() {
                    @Override
                    public void onSuccess(Response<BaseResponse<SingleParam>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;
                        if(body.isState()){
                            LoadDialog.dismiss(activity);
                            RxToast.showShort(body.getMsg());
                        }
                    }

                    @Override
                    public void onError(Response<BaseResponse<SingleParam>> response) {
                        super.onError(response);
                        LoadDialog.dismiss(activity);
                        RxToast.showShort("文件上传出错");
                    }
                });

        /*OkHttpUtils.post()//
                .addFile("UploadForm[file]", file.getAbsolutePath(), file)//
                .url(Constants.BASE_URL+ Constants.UCENTER_PICTURE_UPLOAD_URL)
                .params(params)//
                .build()//
                .execute(new StringCallback() {
                    @Override
                    public void onError(okhttp3.Call call, Exception e, int id) {
                        LoadDialog.dismiss(activity);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LoadDialog.dismiss(activity);
                        Type typeToken = new TypeToken<BaseResponse<SingleParam>>(){}.getType();
                        BaseResponse<SingleParam> bean = new Gson().fromJson(response, typeToken);
                        Toast.makeText(TyslApp.getContext(), bean.getMsg(), Toast.LENGTH_SHORT).show();
                        //Logger.e("onResponse","onResponse"+response);
                    }
                });*/
    }

    /**
     * 单图片上传
     * @param activity Activity
     * @param type 上传类型 avatar 头像 id_card 身份证
     * @param file 图片文件
     * @param successMessage 图片上传成功提示消息
     * @param errMessage 图片上传失败提示消息
     * */
    public void actionUploadFile(final Activity activity, String type, File file, final String successMessage, final String errMessage){
        LoadDialog.show(activity);

        String url = Constants.BASE_URL + Constants.OTHER_UPLOAD_FILE_SINGLE_URL;
        User user = TyslApp.getInstance().getUser();
        long time_stamp = System.currentTimeMillis();
        String sign = SignUtil.getSign(url, user.getUserId(), user.getAccessToken(), time_stamp);

        Map<String,String> params = new HashMap<>();
        params.put(Constants.USER_ID,user.getUserId());
        params.put(Constants.TIME_STAMP,time_stamp+"");
        params.put(Constants.SIGN,sign);
        params.put(Constants.TYPE,type);

        OkGo.<BaseResponse<SingleParam>>post(url)
                .tag(this)
                .params(params)
                .params(Constants.UPLOAD_FILE, file) 	// 支持多文件同时添加上传
                .execute(new JsonCallback<BaseResponse<SingleParam>>() {
                    @Override
                    public void onSuccess(Response<BaseResponse<SingleParam>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;
                        if(body.isState()){
                            LoadDialog.dismiss(activity);
                            RxToast.showShort(successMessage);
                        }
                    }

                    @Override
                    public void onError(Response<BaseResponse<SingleParam>> response) {
                        super.onError(response);
                        LoadDialog.dismiss(activity);
                        RxToast.showShort(errMessage);
                    }
                });

        /*OkHttpUtils.post()
                .url(url)
                .addFile(Constants.UPLOAD_FILE, file.getAbsolutePath(), file)
                .params(params)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onResponse(String response, int id) {
                        LoadDialog.dismiss(activity);
                        RxToast.showShort(successMessage);
                    }
                    @Override
                    public void onError(okhttp3.Call call, Exception e, int id) {
                        LoadDialog.dismiss(activity);
                        RxToast.showShort(errMessage);
                    }
                });*/

        /*OkGo.<BaseResponse<UploadFileSingle>>post(Constants.BASE_URL + Constants.OTHER_UPLOAD_FILE_SINGLE_URL)
                .tag(this)
                .params(Constants.TYPE,type)
                .params(Constants.UPLOAD_FILE, file)
                .execute(new JsonCallback<BaseResponse<UploadFileSingle>>(Constants.WITH_TOKEN,Constants.OTHER_UPLOAD_FILE_SINGLE_URL) {
                    @Override
                    public void onStart(Request<BaseResponse<UploadFileSingle>, ? extends Request> request) {
                        RxToast.shortToast(TyslApp.getContext(),"正在上传中...");
                    }

                    @Override
                    public void onSuccess(Response<BaseResponse<UploadFileSingle>> response) {
                        RxToast.shortToast(TyslApp.getContext(),"上传完成");
                    }

                    @Override
                    public void onError(Response<BaseResponse<UploadFileSingle>> response) {
                        RxToast.shortToast(TyslApp.getContext(),"上传出错");
                    }

                    @Override
                    public void uploadProgress(Progress progress) {
                    }
                });*/
    }

    private int count = 0;

    /**
     * 多图片上传
     * @param activity Activity
     * @param type 上传供什么使用的图片取值
     * type 的取值（eval_goods：评价商品 eval_service：评价营养师 enclosure 认证资料上传）
     * @param list 文件
     * */
    public void actionUploadFileMore(final Activity activity, String type, final List<File> list, final List<String> pathList){
        LoadDialog.show(activity);
        boolean upLoadOk = false;

        String url = Constants.BASE_URL + Constants.OTHER_UPLOAD_FILE_SINGLE_URL;
        User user = TyslApp.getInstance().getUser();
        long time_stamp = System.currentTimeMillis();
        String sign = SignUtil.getSign(url, user.getUserId(), user.getAccessToken(), time_stamp);
        final StringBuilder sb = new StringBuilder();
        Map<String,String> params = new HashMap<>();
        params.put("user_id",user.getUserId());
        params.put("time_stamp",time_stamp+"");
        params.put("sign",sign);
        params.put("type",type);

        count = 0;
        for(int i=0;i<list.size();i++){
            OkGo.<BaseResponse<UploadFileSingle>>post(url)
                    .tag(this)
                    .params(params)
                    .params(Constants.UPLOAD_FILE, list.get(i)) 	// 支持多文件同时添加上传
                    .execute(new JsonCallback<BaseResponse<UploadFileSingle>>(Constants.WITH_TOKEN,Constants.OTHER_UPLOAD_FILE_SINGLE_URL) {
                        @Override
                        public void onSuccess(Response<BaseResponse<UploadFileSingle>> response) {
                            BaseResponse body = response.body();
                            if(body == null)return;
                            if(body.isState()){
                                UploadFileSingle data = (UploadFileSingle) body.getData();
                                sb.append(data.getFilename());
                                Logger.e("onResponse", sb.toString());
                                pathList.add(data.getFilename());

                                ++count;
                                if(count == list.size()){
                                    LoadDialog.dismiss(activity);
                                    Logger.e("onResponse1", sb.toString());
                                    Logger.e("onResponse2", pathList.toString());

                                    RxToast.showShort(body.getMsg());
                                }
                            }
                        }

                        @Override
                        public void onError(Response<BaseResponse<UploadFileSingle>> response) {
                            super.onError(response);
                            LoadDialog.dismiss(activity);
                            RxToast.showShort(response.body().getMsg());
                        }
                    });
        }


        /*PostFormBuilder postFormBuilder = OkHttpUtils.post();//
        for(int i=0;i<list.size();i++){
            postFormBuilder.addFile("UploadForm[file]", list.get(i).getAbsolutePath(), list.get(i));//
        }
        postFormBuilder
                .url(Constants.BASE_URL + Constants.OTHER_UPLOAD_FILE_SINGLE_URL)
                .params(params)//
                .build()//
                .execute(new StringCallback() {
                    @Override
                    public void onError(okhttp3.Call call, Exception e, int id) {
                        LoadDialog.dismiss(activity);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LoadDialog.dismiss(activity);
                        Type typeToken = new TypeToken<BaseResponse<UploadFileSingle>>(){}.getType();
                        BaseResponse<UploadFileSingle> bean = new Gson().fromJson(response, typeToken);
                        sb.append(bean.getData().getFilename());
                        Logger.e("onResponse", sb.toString());
                        pathList.add(bean.getData().getFilename());
                        Toast.makeText(TyslApp.getContext(), bean.getMsg(), Toast.LENGTH_SHORT).show();
                        //Logger.e("onResponse","onResponse"+response);
                    }
                });*/
    }
}
