package com.wokun.tysl.ucenter.ui;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shantoo.widget.toolbar.WidgetBar;
import com.shantoo.widget.view.OnPhotoUpLoadListener;
import com.shantoo.widget.view.PhotoSelector;
import com.wokun.tysl.R;
import com.wokun.tysl.TyslApp;
import com.wokun.tysl.base.BaseLevelThreeLinkageActivity;
import com.wokun.tysl.callback.DialogCallback;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.dietician.bean.DieticianInfoBean;
import com.wokun.tysl.model.bean.User;
import com.wokun.tysl.model.bean.UserInfo;
import com.wokun.tysl.model.response.BaseResponse;
import com.wokun.tysl.other.controler.PhotoMgr;
import com.wokun.tysl.utils.ImageLoader;

import java.io.File;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;

//用户信息
public class EditUserInfoActivity extends BaseLevelThreeLinkageActivity implements BaseLevelThreeLinkageActivity.OnAddressSelectedListener {

    @BindString(R.string.tysl_edit_userinfo) String title;
    @BindView(R.id.toolbar) WidgetBar widgetBar;
    @BindView(R.id.iv_image_header) ImageView ivImageHeader; //用户头像
    @BindView(R.id.tv_area) TextView tvArea;         //所在地区
    @BindView(R.id.tv_ture_name) TextView tvTureName;//真实姓名
    @BindView(R.id.tv_sex) TextView tvSex;           //性别
    @BindView(R.id.tv_birthday) TextView tvBirthday; //出身日期
    @BindView(R.id.tv_idc_no) TextView tvIdcNo;      //身份证号

    @BindView(R.id.action_individual_resume)RelativeLayout actionIndividualResume;
    @BindView(R.id.tv_auth_info)TextView tvAuthInfo;
    @BindView(R.id.ll_auth_info_list)LinearLayout llAuthInfoList;

    private PhotoSelector mPhotoSelector;

    @Override
    public int createView() {
        return R.layout.activity_ucenter_edit_user_info;
    }

    @Override
    public WidgetBar createToolBar() {
        return widgetBar.setWidgetBarTitle(title);
    }

    @Override
    public void init() {
        setOnAddressSelectedListener(this);
        mPhotoSelector = new PhotoSelector(this);
        mPhotoSelector.setOnPhotoUpLoadListener(new OnPhotoUpLoadListener() {
            @Override
            public void onPhotoUpLoad(ImageView photoImage, File photoFile) {
                PhotoMgr.getInstance().upLoadPicture(EditUserInfoActivity.this,photoFile);
            }
        });
        initData(TyslApp.getInstance().getUser());
    }

    private void initData(User user) {
        if (0==user.getUserType()) {//获取普通用户信息
            UserInfo userInfo = TyslApp.getInstance().getUserInfo();
            ImageLoader.loadImage(userInfo.getAvatar(), ivImageHeader);
            actionIndividualResume.setVisibility(View.GONE);
            tvAuthInfo.setVisibility(View.GONE);
            llAuthInfoList.setVisibility(View.GONE);

        } else if (1==user.getUserType()) {//获取营养师信息
            loadDieticianInfo();
        }
    }

    @Override
    public void onAddressSelected(String address, int provinceCode, int cityCode, int districtCode) {
        tvArea.setText(address);
    }

    @OnClick({R.id.action_edit_user_head_img, R.id.action_selector_area, R.id.action_individual_resume})
    public void action(View view) {
        if (R.id.action_edit_user_head_img == view.getId()) {//修改用户头像
            mPhotoSelector.showView(ivImageHeader);
        } else if (R.id.action_selector_area == view.getId()) { //选择所在区域
            showPickerView();
        } else if (R.id.action_individual_resume == view.getId()) {//修改个人简介
            startActivity(EditIndividualResumeActivity.class);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mPhotoSelector.onActivityResult(requestCode, resultCode, data);
    }

    //获取营养师信息
    private void loadDieticianInfo() {
        User user = TyslApp.getInstance().getUser();
        OkGo.<BaseResponse<DieticianInfoBean>>post(Constants.BASE_URL + Constants.UCENTER_GET_USER_INFO_URL)
                .tag(this)
                .params(Constants.USER_TYPE, user.getUserType())
                .execute(new DialogCallback<BaseResponse<DieticianInfoBean>>(this,Constants.WITH_TOKEN,Constants.UCENTER_GET_USER_INFO_URL) {
                    @Override
                    public void onSuccess(Response<BaseResponse<DieticianInfoBean>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;
                        if (body.isState()) {
                            DieticianInfoBean dieticianInfo = (DieticianInfoBean) body.getData();
                            if(dieticianInfo == null){return;}
                            ImageLoader.loadImage(dieticianInfo.getHeadLogo(), ivImageHeader);
                            tvTureName.setText(dieticianInfo.getTrueName());
                            tvBirthday.setText(dieticianInfo.getBirthday());
                            tvIdcNo.setText(dieticianInfo.getIdcNo());
                        }
                    }
                });
    }

    /*private void initWheelPicker() {
        //初始化职业等级相关
        dieticianTypeWP = (WheelPicker) UITool.createView(R.layout.widget_wheel_picker);
        dieticianTypeWP.setOnItemSelectedListener(new WheelPicker.OnItemSelectedListener() {
            @Override
            public void onItemSelected(WheelPicker wheelPicker, Object o, int i) {
                selectRankDialog.dismiss();
                dieticianType.setText(o.toString());
            }
        });
        selectRankDialog = new BottomSheetDialog(this);
        selectRankDialog.setContentView(dieticianTypeWP);
        //初始化擅长领域相关
        dieticianFieldWP = (WheelPicker) UITool.createView(R.layout.widget_wheel_picker);
        dieticianFieldWP.setOnItemSelectedListener(new WheelPicker.OnItemSelectedListener() {
            @Override
            public void onItemSelected(WheelPicker wheelPicker, Object o, int i) {
                dieticianFieldDialog.dismiss();
                dieticianField.setText(o.toString());
            }
        });
        dieticianFieldDialog = new BottomSheetDialog(this);
        dieticianFieldDialog.setContentView(dieticianFieldWP);
    }*/


    /*//获取营养师职业类型
    private void loadJobTypeData() {
        Request request = ItheimaHttp.newPostRequest(Constants.GET_JOB_TYPE);
        ItheimaHttp.send(request, new BaseHttpResponseListener<BaseListResponse<DieticianJobTypeBean>>() {
            @Override
            public void onResponse(BaseListResponse<DieticianJobTypeBean> response) {
                if (response.isState()) {
                    dieticianTypeWP.setData(response.getData());
                } else {
                    Toast.makeText(TyslApp.getContext(), response.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }*/

    /*//获取营养师擅长领域列表
    private void loadFieldListData() {
        Request request = ItheimaHttp.newPostRequest(Constants.GET_FIELD_URL);
        ItheimaHttp.send(request, new BaseHttpResponseListener<BaseListResponse<Field>>() {
            @Override
            public void onResponse(BaseListResponse<Field> response) {
                if (response.isState()) {
                    dieticianFieldWP.setData(response.getData());
                } else {
                    Toast.makeText(TyslApp.getContext(), response.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }*/

    //用戶头像上传
    /*private void uploadPicture(Context context, String key, File uploadFile) {
        String url = "http://192.168.0.108/index.php/v1/ucenter/picture-upload";
        Request request = ItheimaHttp.newUploadRequest(url, RequestMethod.POST);
        String user_id = UserCache.getUser(context).getUserId();
        String token = UserCache.getUser(context).getAccess_token();
        long time_stamp = System.currentTimeMillis();
        String sign = SignUtil.getSign(url, user_id, token, time_stamp);
        request.putParams("user_id", user_id)
                .putParams("time_stamp", time_stamp)
                .putParams("UploadForm", uploadFile)
                .putParams("sign", sign);

        request.putUploadFile(key, uploadFile)
                .putMediaType(MediaType.parse("multipart/form-data"));
        ProgressDialog dialog = new ProgressDialog(context);

        ItheimaHttp.upload(request, new UploadListener() {
            @Override
            public void onResponse(Call call, Response response) {
                //上传成功回调
                Toast.makeText(context, "头像上传成功", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }

            @Override
            public void onProgress(long progress, long total, boolean done) {
                //上传进度回调progress:上传进度，total:文件长度， done:上传是否完成
                if (done) {
                    dialog.dismiss();
                }
                dialog.setProgress((int) progress);
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(context, "头像上传失败", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
    }*/
}
