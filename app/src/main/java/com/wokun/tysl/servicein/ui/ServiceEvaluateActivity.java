package com.wokun.tysl.servicein.ui;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.hedgehog.ratingbar.RatingBar;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import com.shantoo.widget.multiplephotoselector.OnMultiplePhotoUpLoadListener;
import com.shantoo.widget.toast.RxToast;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.tysl.R;
import com.wokun.tysl.base.BaseBindingActivity;
import com.wokun.tysl.callback.JsonCallback;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.duotupianshangchuan.MyMultiplePhotoSelector;
import com.wokun.tysl.model.response.BaseResponse;
import com.wokun.tysl.utils.ImageLoader;

import java.io.File;
import java.util.List;


import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//服务评价
public class ServiceEvaluateActivity extends BaseBindingActivity {

    @BindString(R.string.tysl_service_evaluate) String title;

    @BindView(R.id.yingyanshi_name)
    TextView yingyanshi_name;
    @BindView(R.id.yingyangshi_zhiye)
    TextView yingyangshi_zhiye;

    @BindView(R.id.iv_goods_image)
    ImageView ivGoodsImage;
    @BindView(R.id.et_comment)
    EditText etComment;
    @BindView(R.id.rating_bar1)
    RatingBar ratingBar1;
    @BindView(R.id.rating_bar2)
    RatingBar ratingBar2;
    @BindView(R.id.multiple_photo_selector)

   MyMultiplePhotoSelector mMultiplePhotoSelector;

    private StringBuilder sb = new StringBuilder();

    public int mRatingCount1;
    public int mRatingCount2;
    private int count;
    private int size;

    @Override
    public int createView() {
        return R.layout.activity_service_evaluate;
    }

    @Override
    public WidgetBar createToolBar() {
        return mWidgetBar.setWidgetBarTitle(title);
    }

    @Override
    public void init() {
      /*  RatingBar ratingBar1 = (RatingBar) findViewById(R.id.rating_bar1);
        RatingBar ratingBar2 = (RatingBar) findViewById(R.id.rating_bar2);*/
        ratingBar1.setOnRatingChangeListener(new RatingBar.OnRatingChangeListener() {
            @Override
            public void onRatingChange(float RatingCount) {
           mRatingCount1= (int)RatingCount;
            }
        });
        ratingBar2.setOnRatingChangeListener(new RatingBar.OnRatingChangeListener() {
            @Override
            public void onRatingChange(float RatingCount) {
            mRatingCount2= (int)RatingCount;
            }
        });
        ImageLoader.loadImage(getIntent().getStringExtra("head_logo"),ivGoodsImage);
        yingyanshi_name.setText(getIntent().getStringExtra("truename"));
        yingyangshi_zhiye.setText(getIntent().getStringExtra("jobType"));
    final String url = Constants.BASE_URL + Constants.OTHER_UPLOAD_FILE_SINGLE_URL;
        mMultiplePhotoSelector.setOnMultiplePhotoUpLoadListener(new OnMultiplePhotoUpLoadListener() {
            @Override
            public void onMultiplePhotoUpLoad(final List<String> photosPath) {
                if(photosPath!=null){
                    sb.replace(0,sb.length(),"");
                    count = 0;
                    size = photosPath.size()-1;
                    showLP();
                    for(int i=0;i<photosPath.size()-1;i++){
                        OkGo.post(url).tag(this)
                                .params(Constants.TYPE,Constants.UPLOAD_TYPE_EVAL_SERVICE)
                                .params(Constants.UPLOAD_FILE, new File(photosPath.get(i))) 	// 支持多文件同时添加上传
                                .execute(new JsonCallback<Object>(Constants.WITH_TOKEN,Constants.OTHER_UPLOAD_FILE_SINGLE_URL) {
                                    @Override
                                    public void onSuccess(Response<Object> response) {
                                        ++count;
                                        if(count == photosPath.size()){
                                            dismissLP();
                                            RxToast.showShort("认证证明上传成功");
                                        }
                                    }

                                    @Override
                                    public void onError(Response<Object> response) {
                                        super.onError(response);
                                        dismissLP();
                                        RxToast.showShort("认证证明上传失败");
                                    }
                                });
                        //actionUploadFileMore1(Constants.UPLOAD_TYPE_EVAL_SERVICE,new File(photosPath.get(i)));
                    }
                }
            }
        });
    }

    public void actionUploadFileMore1(String type, final File file){

        /*User user = TyslApp.getInstance().getUser();
        long time_stamp = System.currentTimeMillis();
        String url = Constants.BASE_URL + Constants.OTHER_UPLOAD_FILE_SINGLE_URL;
        String sign = SignUtil.getSign(url, user.getUserId(), user.getAccessToken(), time_stamp);
        Map<String,String> params = new HashMap<>();
        params.put(Constants.USER_ID,user.getUserId());
        params.put(Constants.TIME_STAMP,time_stamp+"");
        params.put(Constants.SIGN,sign);
        params.put(Constants.TYPE,type);
        PostFormBuilder postFormBuilder = OkHttpUtils.post();
        postFormBuilder.url(url)
                .addFile(Constants.UPLOAD_FILE, file.getAbsolutePath(), file)
                .params(params)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(okhttp3.Call call, Exception e, int id) {
                        dismissLP();
                        RxToast.showShort("文件上传出错");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Type typeToken = new TypeToken<BaseResponse<UploadFileSingle>>(){}.getType();
                        BaseResponse<UploadFileSingle> bean = new Gson().fromJson(response, typeToken);
                        count++;
                        //Logger.e("count="+count,"size="+size);
                        if(count == size){
                            dismissLP();
                            RxToast.showShort(bean.getMsg());
                        }
                        //Logger.e("onResponse", bean.getData().getFilename());
                        sb.append(bean.getData().getFilename()).append(",");
                        //pathList.add(bean.getData().getFilename());
                        //Logger.e("onResponse","onResponse"+response);
                    }
                });*/
    }

    @OnClick(R.id.action_submit)
    public void action(View v){
        String order_id = getIntent().getStringExtra(Constants.ORDER_ID);
        String text = etComment.getText().toString().trim();
        int major_score = mRatingCount1;
       int service_score = mRatingCount2;
      String imgs = sb.toString();
        dietitianEval(order_id ,text,major_score,imgs,service_score);
    }

    public void dietitianEval(String order_id, String text, int major_score,String imgs, int service_score) {
        if(TextUtils.isEmpty(text)){
            RxToast.showShort("请填写文字评论后提交");
            return;
        }
      /*  if(TextUtils.isEmpty(imgs)){
            RxToast.showShort("请填写图片评论后提交");
            return;
        }*/

        OkGo.<BaseResponse<Object>>post(Constants.BASE_URL + Constants.DIETITIAN_EVAL_URL)
                .tag(this)
                .params(Constants.ORDER_ID, order_id)
                .params("text", text)
                .params("imgs", imgs)
                .params("major_score", major_score)
                .params("service_score", service_score)
                .execute(new JsonCallback<BaseResponse<Object>>(Constants.WITH_TOKEN,Constants.DIETITIAN_EVAL_URL) {
                    @Override
                    public void onSuccess(Response<BaseResponse<Object>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;
                        if(body.isState()) finish();
                        RxToast.showShort(body.getMsg());
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
          mMultiplePhotoSelector.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OkGo.getInstance().cancelTag(this);
    }
}