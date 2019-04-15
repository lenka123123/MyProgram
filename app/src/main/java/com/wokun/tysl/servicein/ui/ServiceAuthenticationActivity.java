package com.wokun.tysl.servicein.ui;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.itheima.roundedimageview.RoundedImageView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shantoo.common.utils.Logger;
import com.shantoo.widget.multiplephotoselector.MultiplePhotoSelector;
import com.shantoo.widget.multiplephotoselector.OnMultiplePhotoUpLoadListener;
import com.shantoo.widget.toast.RxToast;
import com.shantoo.widget.toolbar.WidgetBar;
import com.shantoo.widget.view.OnPhotoUpLoadListener;
import com.shantoo.widget.view.PhotoSelector;
import com.wokun.tysl.R;
import com.wokun.tysl.base.BaseLevelThreeLinkageActivity;
import com.wokun.tysl.base.SimpleWebViewActivity;
import com.wokun.tysl.callback.DialogCallback;
import com.wokun.tysl.callback.JsonCallback;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.dietician.ui.activity.DietitianFieldTypeSelectActivity;
import com.wokun.tysl.duotupianshangchuan.MyMultiplePhotoSelector;
import com.wokun.tysl.model.bean.SingleParam;
import com.wokun.tysl.model.response.BaseResponse;
import com.wokun.tysl.model.response.IntegralRecordResponse;
import com.wokun.tysl.other.controler.ActionMgr;
import com.wokun.tysl.other.controler.PhotoMgr;
import com.wokun.tysl.utils.SheetDialogUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

//服务入驻认证页面
public class ServiceAuthenticationActivity extends BaseLevelThreeLinkageActivity
        implements BaseLevelThreeLinkageActivity.OnAddressSelectedListener,
        OnPhotoUpLoadListener, OnMultiplePhotoUpLoadListener {

    @BindView(R.id.et_name) EditText etName;//真实姓名
    @BindView(R.id.et_id_card_hint) EditText etIdCardHint;//身份证号码
    @BindView(R.id.et_work_life) EditText etWorkLife;//从业时间

    @BindView(R.id.iv_image_header)RoundedImageView ivImageHeader;//用户头像

    @BindView(R.id.tv_sex) TextView tvSex;//性别
    @BindView(R.id.tv_the_field_of_expertise) TextView tvTheFieldOfExpertise;//擅长领域
    @BindView(R.id.tv_birthday) TextView tvBirthday;//出生日期
    @BindView(R.id.tv_area) TextView tvArea;//所在区域

    @BindView(R.id.iv_idc_front) ImageView ivIdcFront;//身份证正面照
    @BindView(R.id.iv_idc_back)ImageView ivIdcBack;//身份证反面照
    @BindView(R.id.iv_idc_pic)ImageView ivIdcPic; //身份证手持照

    @BindView(R.id.update_id_card_front_photo)ImageView updateIdCardFrontPhoto;
    @BindView(R.id.update_id_card_reverse_photo)ImageView updateIdCardReversePhoto;
    @BindView(R.id.update_id_card_hand_photo)ImageView updateIdCardHandPhoto;

    @BindView(R.id.multiple_photo_selector)MyMultiplePhotoSelector mMultiplePhotoSelector;

    private int count = 0;
    private String fieldIds; //已选择的擅长领域
    private int province_id,city_id,district_id = 0;
    //头像图片名称,身份证正面照,身份证反面照,身份证手持照
    private String headImgPath,idcFrontPath,idcBackPath,idcPicPath;
    private String area_info;
    private String credentials; //认证资料 多张图片名用逗号隔开
    private PhotoSelector mPhotoSelector;
    private ActionMgr mServicePresenter;
    private List<String> pathList = new ArrayList<>();

    @Override
    public int createView() {
        return R.layout.activity_service_authentication;
    }

    @Override
    public WidgetBar createToolBar() {
        return mWidgetBar.setWidgetBarTitle("服务入驻");
    }

    @Override
    public void init() {
        setOnAddressSelectedListener(this);                             //设置选择地址监听
        mPhotoSelector = new PhotoSelector(this);
        mPhotoSelector.setOnPhotoUpLoadListener(this);                  //设置单图上传监听
        mMultiplePhotoSelector.setOnMultiplePhotoUpLoadListener(this);  //设置多图上传监听
    }

    /**
     * 选择用户性别
     * */
    @OnClick(R.id.action_select_sex)
    public void actionSelectSex(View v) {
        SheetDialogUtil.showSexSheetDialog(this,tvSex);
    }

    /**
     * 选择出生日期
     * */
    @OnClick(R.id.action_select_birthday)
    public void actionSelectBirthday(View v) {
        SheetDialogUtil.showDateSheetDialog(this,tvBirthday);
    }

    /**
     * 选择所在地区
     * */
    @OnClick(R.id.action_select_area)
    public void actionSelectArea(View v) {
        showPickerView();
    }

    /**
     * 选择擅长领域
     * */
    @OnClick(R.id.action_select_the_field_of_expertise)
    public void actionSelectTheFieldOfExpertise(View v) {
        Intent intent = new Intent();
        intent.setClass(this,DietitianFieldTypeSelectActivity.class);
        startActivityForResult(intent,66);
    }

    /**
     * 上传真实头像
     * */
    @OnClick(R.id.action_select_true_head_portrait)
    public void actionSelectTrueHeadPortrait(View v) {
        mPhotoSelector.showView(ivImageHeader);
    }

    /**
     * 上传身份证正面照
     * */
    @OnClick(R.id.update_id_card_front_photo)
    public void updateIdCardFrontPhoto(View v) {
        mPhotoSelector.showView(ivIdcFront);
    }

    /**
     * 上传身份证反面照
     * */
    @OnClick(R.id.update_id_card_reverse_photo)
    public void updateIdCardReversePhoto(View v) {
        mPhotoSelector.showView(ivIdcBack);
    }   /**
     * 上传身份证手持正面照
     * */
    @OnClick(R.id.update_id_card_hand_photo)
    public void updateIdCardHandPhoto(View v) {

        mPhotoSelector.showView(ivIdcPic);
    }


    /**
     * 多图片上传
     * */
    @Override
    public void onMultiplePhotoUpLoad(List<String> photosPath) {
        if(photosPath!=null){
            Log.e("图片路径：：",photosPath+"");
            List<File> list = new ArrayList<>();
            for(int i=0;i<photosPath.size()-1;i++){
                list.add(new File(photosPath.get(i)));
                pathList.clear();
                actionUploadFile1(Constants.UPLOAD_TYPE_ENCLOSURE,list,pathList);
            }
        }
    }

    /**
     * 单图片上传，用于真实头像上传，身份证上传
     * */
    @Override
    public void onPhotoUpLoad(ImageView photoImage, File photoFile) {
        if(R.id.iv_image_header == photoImage.getId()){//上传真实头像

            headImgPath = photoFile.getAbsolutePath();
            PhotoMgr.getInstance().actionUploadFile(this, Constants.UPLOAD_TYPE_AVATAR,photoFile,"真实头像上传成功","真实头像上传失败");

        }else if(R.id.iv_idc_front == photoImage.getId()){//上传身份证正面照

            idcFrontPath = photoFile.getAbsolutePath();
            PhotoMgr.getInstance().actionUploadFile(this, Constants.UPLOAD_TYPE_ID_CARD,photoFile,"身份证正面照上传成功","身份证正面照上传失败");

        }else if(R.id.iv_idc_back == photoImage.getId()){//上传身份证反面照

            idcBackPath = photoFile.getAbsolutePath();
            PhotoMgr.getInstance().actionUploadFile(this, Constants.UPLOAD_TYPE_ID_CARD,photoFile,"身份证反面照上传成功","身份证反面照上传失败");

        }else if(R.id.iv_idc_pic == photoImage.getId()){//上传手持正面照

            idcPicPath = photoFile.getAbsolutePath();
            PhotoMgr.getInstance().actionUploadFile(this, Constants.UPLOAD_TYPE_ID_CARD,photoFile,"手持正面照上传成功","手持正面照上传失败");
        }
    }

    /**
     * 营养师服务协议
     * */
    @OnClick(R.id.action_join_xie_yi)
    public void actionJoinXieYi(View view){
        OkGo.<BaseResponse<SingleParam>>post(Constants.BASE_URL + Constants.JOIN_XIE_YI_URL)
                .tag(this)
                .execute(new JsonCallback<BaseResponse<SingleParam>>() {
                    @Override
                    public void onSuccess(Response<BaseResponse<SingleParam>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;
                        SingleParam data = (SingleParam) body.getData();
                        if(data==null)return;
                        Intent intent = new Intent();
                        intent.putExtra(Constants.TITLE, "营养师服务协议");
                        intent.putExtra(Constants.URL, data.getUrl());
                        intent.setClass(ServiceAuthenticationActivity.this, SimpleWebViewActivity.class);
                        startActivity(intent);
                    }
                });
    }

    public void actionUploadFile1(String type, final List<File> list, final List<String> pathList){
        showLP();
        String url = Constants.BASE_URL + Constants.OTHER_UPLOAD_FILE_SINGLE_URL;
        Log.e("图片进来4","图片进来3type"+type+"list"+list+"pathList"+pathList);
        //Map<String,String> params = new HashMap<>();
        //params.put(Constants.TYPE,type);
        //params.put(Constants.SIGN,sign);
        //params.put(Constants.USER_ID,user.getUserId());
        //params.put(Constants.TIME_STAMP,String.valueOf(timeStamp));

        count = 0 ;
        for(int i=0;i<list.size();i++){
            OkGo.post(url).tag(this)
                    .params(Constants.TYPE,type)//这边不是有么
                    .params(Constants.UPLOAD_FILE, list.get(i)) 	// 支持多文件同时添加上传
                    .execute(new JsonCallback<Object>(Constants.WITH_TOKEN,Constants.OTHER_UPLOAD_FILE_MORE_URL) {
                        @Override
                        public void onSuccess(Response<Object> response) {
                            ++count;
                            if(count == list.size()){
                                dismissLP();
                                RxToast.showShort("认证证明上传成功");
                            }
                        }

                        @Override
                        public void onError(Response<Object> response) {
                            super.onError(response);
                            dismissLP();
                            Log.e("232",response+"");
                            RxToast.showShort("认证证明上传失败");
                        }
                    });
        }
    }

    /**
     * 提交营养师认证信息
     * */
    @OnClick(R.id.action_submit)
    public void action_submit(View v) {
        //Logger.e(TAG,pathList.toString());
        //[1521698520735.jpg, 1521698520646.jpg, 1521698520397.jpg, 1521698520680.jpg]

        int sex = -1;
        String str = tvSex.getText().toString().trim();
        if("男".equals(str))sex = 1;
        if("女".equals(str))sex = 0;

        int typeId = Integer.valueOf(getIntent().getStringExtra(Constants.TYPE_ID));
        String name = etName.getText().toString().trim();
        String birthday = tvBirthday.getText().toString().trim();
        String idCart = etIdCardHint.getText().toString().trim();
        String workLife = etWorkLife.getText().toString().trim();

        actionJoinService(typeId, name, sex, birthday, idCart, fieldIds, workLife,pathList.toString(),
                headImgPath, idcFrontPath, idcBackPath, idcPicPath,
                province_id, city_id, district_id, area_info);
    }

    /**
     * 营养师入驻
     * @param typeId 职业类型id
     * @param name 姓名
     * @param sex 0女1男
     * @param birthday 出生年月 1990-02-12
     * @param idcNo 身份证号
     * @param workLife 从业时间 如 4 （4年）
     * @param credentials 认证资料 多张图片名用逗号隔开
     * @param headImgPath 头像图片名称
     * @param idcFrontPath 身份证正面照
     * @param idcBackPath 身份证反面照
     * @param idcPicPath 身份证手持照
     * @param provinceId 省份id
     * @param cityId 城市id
     * @param districtId 区id
     * @param areaInfo 所在区域，中文 如江苏南京
     * @param fieldIds 擅长领域id 如1,6,12,18(用逗号隔开的字符串)
     * */
    public void actionJoinService(
        int typeId, String name,int sex, String birthday,String idcNo,String fieldIds,String workLife, String credentials,
        String headImgPath, String idcFrontPath, String idcBackPath, String idcPicPath,
        int provinceId, int cityId, int districtId, String areaInfo){

        if(TextUtils.isEmpty(name)){
            RxToast.showShort("请填写真实姓名");
            return;
        }
        if(sex!=0 && sex!=1){
            RxToast.showShort("请选择性别");
            return;
        }
        if(TextUtils.isEmpty(birthday)){
            RxToast.showShort("请选择出生日期");
            return;
        }
        if(TextUtils.isEmpty(idcNo)){
            RxToast.showShort("请填写身份证号");
            return;
        }
        if(TextUtils.isEmpty(areaInfo) || provinceId==0 || cityId==0 || districtId==0){
            RxToast.showShort("请选择所在区域");
            return;
        }
        if(TextUtils.isEmpty(workLife)){
            RxToast.showShort("请填写从业时间");
            return;
        }
        if(TextUtils.isEmpty(fieldIds)){
            RxToast.showShort("请选择擅长领域");
            return;
        }
        if(TextUtils.isEmpty(idcFrontPath)){
            RxToast.showShort("请上传身份证正面照");
            return;
        }
        if(TextUtils.isEmpty(idcBackPath)){
            RxToast.showShort("请上传身份证反面照");
            return;
        }
        if(TextUtils.isEmpty(idcPicPath)){
            RxToast.showShort("请上传身份证手持照");
            return;
        }
        if(TextUtils.isEmpty(credentials)){
            RxToast.showShort("请上传认证证明");
            return;
        }

        OkGo.<BaseResponse<IntegralRecordResponse>>post(Constants.BASE_URL + Constants.JOIN_SERVICE_URL)
                .tag(this)
                .params(Constants.TYPE_ID,typeId)
                .params(Constants.NAME,name)
                .params(Constants.SEX,sex)
                .params(Constants.BIRTHDAY,birthday)
                .params(Constants.IDC_NO,idcNo)
                .params(Constants.FIELD_IDS,fieldIds)
                .params(Constants.WORK_LIFE,workLife)
                .params(Constants.CREDENTIALS,credentials)
                .params(Constants.HEADIMG,headImgPath)
                .params(Constants.IDC_FRONT,idcFrontPath)
                .params(Constants.IDC_BACK,idcBackPath)
                .params(Constants.IDC_PIC,idcPicPath)
                .params(Constants.AREA_INFO,areaInfo)
                .params(Constants.PROVINCE_ID,provinceId)
                .params(Constants.CITY_ID,cityId)
                .params(Constants.DISTRICT_ID,districtId)
                .execute(new DialogCallback<BaseResponse<IntegralRecordResponse>>(this,Constants.WITH_TOKEN,Constants.JOIN_SERVICE_URL) {
                    @Override
                    public void onSuccess(Response<BaseResponse<IntegralRecordResponse>> response) {
                        BaseResponse body = response.body();
                        if(body==null)return;
                        if(body.isState()){
                            startActivity(ServiceAuthenticationResultActivity.class);
                        }else{
                            RxToast.showShort("认证资料提交失败");
                        }
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mPhotoSelector.onActivityResult(requestCode, resultCode, data);
        mMultiplePhotoSelector.onActivityResult(requestCode, resultCode, data);
        if(66 == requestCode && data!=null){
            fieldIds = data.getStringExtra(Constants.FIELD_IDS);//擅长领域字段返回
            Logger.e(TAG, fieldIds);

       //     tvTheFieldOfExpertise.setText();


        }
    }

    /**
     * 选择地址
     * @param address 中文详细地址
     * @param provinceCode 省ID
     * @param cityCode 市ID
     * @param districtCode 区ID
     */
    @Override
    public void onAddressSelected(String address, int provinceCode, int cityCode, int districtCode) {
        area_info = address;
        province_id = provinceCode;
        city_id = cityCode;
        district_id = districtCode;
        tvArea.setText(address);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OkGo.getInstance().cancelTag(this);
    }
}