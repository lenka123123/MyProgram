package com.wokun.tysl.myyijian.fragment;



import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v13.app.ActivityCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shantoo.common.utils.Logger;
import com.shantoo.widget.dialog.loaddialog.LoadDialog;
import com.shantoo.widget.multiplephotoselector.OnMultiplePhotoUpLoadListener;
import com.shantoo.widget.toast.RxToast;
import com.shantoo.widget.view.OnPhotoUpLoadListener;
import com.shantoo.widget.view.PhotoSelector;
import com.squareup.picasso.Picasso;
import com.wokun.tysl.R;
import com.wokun.tysl.TyslApp;
import com.wokun.tysl.base.BaseFragment;
import com.wokun.tysl.callback.JsonCallback;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.duotupianshangchuan.MyMultiplePhotoSelector;
import com.wokun.tysl.luban.Luban;
import com.wokun.tysl.luban.OnCompressListener;
import com.wokun.tysl.model.bean.UploadFileSingle;
import com.wokun.tysl.model.bean.User;
import com.wokun.tysl.model.response.BaseResponse;
import com.wokun.tysl.myview.GridViewForScrollView;
import com.wokun.tysl.myview.ImageGridAdapter;
import com.wokun.tysl.myyijian.SendjianyiBean;
import com.wokun.tysl.myyijian.SendjianyiBean2;
import com.wokun.tysl.other.controler.PhotoMgr;
import com.wokun.tysl.utils.SignUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.rong.imageloader.utils.L;
import me.iwf.photopicker.PhotoPicker;
import me.iwf.photopicker.PhotoPickerActivity;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Administrator on 2018/7/9/009.
 */
//提交建议
public class SendMymessagefragment extends BaseFragment implements OnMultiplePhotoUpLoadListener {

    private ArrayList<Object> mImageItems;
    private List<String> mImagePathList;
    private ImageGridAdapter mImageAdapter;
    private String pathImage;

    @BindView(R.id.pro_dindan)
    TextView Prodindan;
    @BindView(R.id.pro_login)
    TextView Prologin;
    @BindView(R.id.pro_other)
    TextView Proother;
    @BindView(R.id.pro_zhifu)
    TextView Prozhifu;
    @BindView(R.id.if_context)
    EditText ifContext;
    @BindView(R.id.multiple_photo_selector)
    MyMultiplePhotoSelector mMultiplePhotoSelector;
    private String mOtherContext;
    private int count = 0;
    private  int msgID =0;
    private List<String> pathList = new ArrayList<>();
   private  String uploadFileName;
    private PhotoSelector mPhotoSelector;

    private void initData() {
        EventBus.getDefault().register(this);
        mMultiplePhotoSelector.setOnMultiplePhotoUpLoadListener(this);

    }

/*

    */
/**
     * 多图片上传
     * */

    public void onMultiplePhotoUpLoad(List<String> photosPath) {
        if(photosPath!=null){
            Log.e("图片进来1","图片进来1");
            Log.e("图片路径2：：",photosPath+"");
            List<File> list = new ArrayList<>();
            for(int i=0;i<photosPath.size()-1;i++){
                list.add(new File(photosPath.get(i)));
                pathList.clear();
         //    PhotoMgr.getInstance().actionUploadFileMore(getActivity(), Constants.UPLOAD_FEEDBACK,list,pathList);

              actionUploadFile1(Constants.UPLOAD_FEEDBACK,list,pathList);
            }
        }
    }


    @Override
    public int createView() {
        return R.layout.fragment_my_sendmessage;
    }

    @Override
    public void initViews() {
        Prologin.setSelected(true);
        Prologin.setBackgroundResource(R.drawable.selector_bg_ac1);
        Prodindan.setSelected(false);
        Prozhifu.setSelected(false);
        Prodindan.setSelected(false);
        Prodindan.setBackgroundResource(R.drawable.selector_bg_ac1);
        Proother.setBackgroundResource(R.drawable.selector_bg_ac1);
        Prozhifu.setBackgroundResource(R.drawable.selector_bg_ac1);
    }

@Subscribe(threadMode = ThreadMode.MAIN)
public void monooth(MyBean myBean){
    Log.e("图片进来12","图片进来2");
    mMultiplePhotoSelector.onActivityResult(myBean.requestCode, myBean.resultCode, myBean.data);
}
   /* @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mPhotoSelector.onActivityResult(requestCode, resultCode, data);
        Log.e("图片进来2","图片进来2");
        mMultiplePhotoSelector.onActivityResult(requestCode, resultCode, data);


    }*/



    /**  */
    @OnClick({R.id.pro_dindan,
            R.id.pro_zhifu,
            R.id.pro_other,
            R.id.pro_login,
            R.id.action_submit
              })
    public void actionCheckProblem(View view){
        boolean isNeedLogin = TyslApp.getInstance().isLogin();
        if(R.id.pro_dindan == view.getId()){
            Prodindan.setSelected(true);
            Prozhifu.setSelected(false);
            Proother.setSelected(false);
            Prologin.setSelected(false);
            msgID =1;

            Prozhifu.setBackgroundResource(R.drawable.selector_bg_ac1);
            Proother.setBackgroundResource(R.drawable.selector_bg_ac1);
            Prologin.setBackgroundResource(R.drawable.selector_bg_ac1);

         /*   if (Prodindan.isSelected()){//duo xuan
                Prodindan.setSelected(false);
            }else {
                Prodindan.setSelected(true);
                Prodindan.setBackgroundResource(R.drawable.selector_bg_ac1);
            }*/

        } else if(R.id.pro_zhifu == view.getId()){
            Prozhifu.setSelected(true);
            Prodindan.setSelected(false);
            Proother.setSelected(false);
            Prologin.setSelected(false);
            msgID = 2;
            Prodindan.setBackgroundResource(R.drawable.selector_bg_ac1);
            Proother.setBackgroundResource(R.drawable.selector_bg_ac1);
            Prologin.setBackgroundResource(R.drawable.selector_bg_ac1);
          /*  if (Prozhifu.isSelected()){
                Prozhifu.setSelected(false);
            }else {
                Prozhifu.setSelected(true);
                Prozhifu.setBackgroundResource(R.drawable.selector_bg_ac1);
            }*/

        } else if(R.id.pro_other == view.getId()){
            Proother.setSelected(true);
            Prodindan.setSelected(false);
            Prozhifu.setSelected(false);
            Prologin.setSelected(false);
            msgID = 3;
            Prodindan.setBackgroundResource(R.drawable.selector_bg_ac1);
            Prozhifu.setBackgroundResource(R.drawable.selector_bg_ac1);
            Prologin.setBackgroundResource(R.drawable.selector_bg_ac1);
          /*  if (Proother.isSelected()){
                Proother.setSelected(false);
            }else {
                Proother.setSelected(true);
                Proother.setBackgroundResource(R.drawable.selector_bg_ac1);
            }*/

        } else if(R.id.pro_login == view.getId()){
            Prologin.setSelected(true);
            Prodindan.setSelected(false);
            Prozhifu.setSelected(false);
            Prodindan.setSelected(false);
            Prodindan.setBackgroundResource(R.drawable.selector_bg_ac1);
            Proother.setBackgroundResource(R.drawable.selector_bg_ac1);
            Prozhifu.setBackgroundResource(R.drawable.selector_bg_ac1);
            msgID = 0;
        /*    if (Prologin.isSelected()){
                Prologin.setSelected(false);
            }else {
                Prologin.setSelected(true);
                Prologin.setBackgroundResource(R.drawable.selector_bg_ac1);
            }*/
        } else if(R.id.action_submit == view.getId()){
            //提交
            commitData(msgID);

        }
    }
    String s1;
    String s3;
    private LoadDialog mLoadDialog;
    protected void showLP() {
        mLoadDialog.show();
    }

    protected void dismissLP() {
        mLoadDialog.dismiss();
    }

    public void actionUploadFile1(String type, final List<File> list, final List<String> pathList){

            LoadDialog.show(getActivity());
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

                                    pathList.add(data.getFilename());

                                    ++count;
                                    if(count == list.size()){
                                        LoadDialog.dismiss(getActivity());
                                        Logger.e("onResponse1", sb.toString());

                                        Logger.e("onResponse2", pathList.toString());
                                          s3 = listToString(pathList, ",");
                                        RxToast.showShort(body.getMsg());
                                    }



                                }
                            }

                            @Override
                            public void onError(Response<BaseResponse<UploadFileSingle>> response) {
                                super.onError(response);
                                LoadDialog.dismiss(getActivity());
                                RxToast.showShort(response.body().getMsg());
                            }
                        });
            }
            s1 = sb.toString();
        Logger.e("onResponse3", s1);

        }
    public String listToString(List list, String separator) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i)).append(separator);
        }
        return list.isEmpty()?"":sb.toString().substring(0, sb.toString().length() - 1);
    }



    private void commitData(int msgID) {
        mOtherContext = ifContext.getText().toString().trim();
          if(mOtherContext.isEmpty()){
              Toast.makeText(TyslApp.getContext(), "意见信息为空", Toast.LENGTH_SHORT).show();
              return;
          }
        Log.e("wwww22",mImageItems+"");
        Log.e("wwww22",mImagePathList+"");
        Logger.e("onResponse3", s3);
        OkGo.<BaseResponse<Object>>post(Constants.BASE_URL + Constants.FEEDBACK)//
                .tag(this)
                .params("content",mOtherContext)
                .params("images",s3)//
                .params("class_id",msgID)
                .execute(new JsonCallback<BaseResponse<Object>>(Constants.WITH_TOKEN,Constants.FEEDBACK) {
                    @Override
                    public void onSuccess(Response<BaseResponse<Object>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;
                        Toast.makeText(TyslApp.getContext(), body.getMsg(), Toast.LENGTH_SHORT).show();
                        if(body.isState()){

                        }
                    }
                });
    }

    @Override
    public void loadData() {
        initData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
