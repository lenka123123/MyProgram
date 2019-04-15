package com.wokun.tysl.goods.ui.activity;

import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hedgehog.ratingbar.RatingBar;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shantoo.common.utils.Logger;
import com.shantoo.widget.multiplephotoselector.MultiplePhotoSelector;
import com.shantoo.widget.multiplephotoselector.OnMultiplePhotoUpLoadListener;
import com.shantoo.widget.recyclerview.MItemDecoration;
import com.shantoo.widget.toast.RxToast;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.tysl.R;
import com.wokun.tysl.TyslApp;
import com.wokun.tysl.base.BaseBindingActivity;
import com.wokun.tysl.callback.JsonCallback;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.duotupianshangchuan.MyMultiplePhotoSelector;
import com.wokun.tysl.goods.adapter.GoodsEvaluateAdapter;
import com.wokun.tysl.goods.bean.GoodsBean;
import com.wokun.tysl.model.bean.SingleParam;
import com.wokun.tysl.model.bean.UploadFileSingle;
import com.wokun.tysl.model.bean.User;
import com.wokun.tysl.model.response.BaseResponse;
import com.wokun.tysl.other.controler.PhotoMgr;
import com.wokun.tysl.utils.SignUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;

//商品评价
public class GoodsEvaluateActivity extends BaseBindingActivity {

    @BindString(R.string.tysl_goods_evaluate)
    String title;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
/*
    @BindView(R.id.et_comment)
    EditText etComment;//文字评论*/

  /*  @BindView(R.id.multiple_photo_selector)
    MyMultiplePhotoSelector mMultiplePhotoSelector;//图片评论*/
  /*  @BindView(R.id.rating_bar1)RatingBar ratingBar1;//商家星级
    @BindView(R.id.rating_bar2)RatingBar ratingBar2;//服务星级
    @BindView(R.id.rating_bar3)RatingBar ratingBar3;//物流星级*/

    private StringBuilder sb = new StringBuilder();
    private int count,size;
    private  int mRatingCount1 ,mRatingCount2,mRatingCount3 ;
    private int mRatingCount4 = 5;
    private  String  text;
    private List<GoodsBean> list;
    private List<String> pathList = new ArrayList<>();
    private GoodsEvaluateAdapter adapter;

    @Override
    public int createView() {
        return R.layout.activity_goods_evaluate;
    }

    @Override
    public WidgetBar createToolBar() {
        return mWidgetBar.setWidgetBarTitle(title);
    }

    @Override
    public void init() {
        list = (List<GoodsBean>) getIntent().getSerializableExtra("data");
        adapter = new GoodsEvaluateAdapter(R.layout.item_goods_evaluate, list);
        adapter.setOnRatingChangeListener(new GoodsEvaluateAdapter.OnRatingChangeListener() {
            @Override
            public void onRatingChange(float RatingCount) {
                mRatingCount4 = (int)RatingCount;
              }
        });
         adapter.setmEditContextListener(new GoodsEvaluateAdapter.EditContextListener() {
             @Override
             public void onEditContext(String editcontext) {
                 text  =    editcontext.trim();
             }
         });
        adapter.setMultiplePhotoSelectorListener(new GoodsEvaluateAdapter.MultiplePhotoSelectorListener() {
            @Override
            public void onMultiplePhotoSelectorListener(List<String> photosPath) {
                if(photosPath!=null){
                    sb.replace(0,sb.length(),"");
                    count = 0;
                    size = photosPath.size()-1;
                    showLP();
                    for(int i=0;i<photosPath.size()-1;i++){
                        // PhotoMgr.getInstance().actionUploadFileMore();
                        List<File> list = new ArrayList<>();
                        list.add(new File(photosPath.get(i)));
                        pathList.clear();
                        actionUploadFileMore(Constants.UPLOAD_TYPE_ENCLOSURE,list);
                    }
                }
            }
        });

        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addItemDecoration(new MItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //先关闭一下
       /* mMultiplePhotoSelector.setOnMultiplePhotoUpLoadListener(new OnMultiplePhotoUpLoadListener() {
            @Override
            public void onMultiplePhotoUpLoad(List<String> photosPath) {
                if(photosPath!=null){
                    sb.replace(0,sb.length(),"");
                    count = 0;
                    size = photosPath.size()-1;
                    showLP();
                    for(int i=0;i<photosPath.size()-1;i++){
                       // PhotoMgr.getInstance().actionUploadFileMore();
                        List<File> list = new ArrayList<>();
                        list.add(new File(photosPath.get(i)));
                        pathList.clear();
                        actionUploadFileMore(Constants.UPLOAD_TYPE_ENCLOSURE,list);
                    }
                }
            }
        });*/
    }

  @OnClick(R.id.action_submit)
    public void action(View v) {
        if (R.id.action_submit == v.getId()) {//提交商品订单评价
            String order_id = getIntent().getStringExtra("order_id");
       //     String text = etComment.getText().toString().trim();


            StringBuilder builder = new StringBuilder();

            builder.append("[");
            for(int i=0;i<list.size();i++){
                builder.append("{");
                builder.append("\"goods_id\"").append(":").append(list.get(i).getGoodsId()).append(",");
                builder.append("\"evalution_score\"").append(":").append(mRatingCount4).append(",");
                builder.append("\"text\"").append(":").append("\""+text+"\"").append(",");
                builder.append("\"imgs\"").append(":").append("\""+sb.toString()+"\"");
                if(i==list.size()-1){
                    builder.append("}");
                }else{
                    builder.append("},");
                }
            }
            builder.append("]");
           Logger.e("builder",builder.toString());
            Logger.e("mRatingCount1",mRatingCount1+"");
           Logger.e("mRatingCount2",mRatingCount2+"");
            Logger.e("mRatingCount3",mRatingCount3+"");

            goodsEval(order_id,builder.toString(),mRatingCount1,mRatingCount2,mRatingCount3);
        }
    }

    //商品订单评价
    public void goodsEval(String order_id, String eval_info, int description_score,int service_score, int logistics_score) {
        if(TextUtils.isEmpty(order_id)){
            Toast.makeText(this, "订单id不存在", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(eval_info)){
            Toast.makeText(this, "请填写商品评价信息后提交", Toast.LENGTH_SHORT).show();
            return;
        }


        OkGo.<BaseResponse<Object>>post(Constants.BASE_URL + Constants.ORDER_ADD_EVAL_URL)
            .tag(this)
            .params("order_id", order_id)
            .params("eval_info", eval_info)
            .params("description_score", description_score)
            .params("service_score", service_score)
            .params("logistics_score", logistics_score)
            .execute(new JsonCallback<BaseResponse<Object>>(Constants.WITH_TOKEN,Constants.ORDER_ADD_EVAL_URL) {
                @Override
                public void onSuccess(Response<BaseResponse<Object>> response) {
                    BaseResponse body = response.body();
                    if(body == null)return;
                    if(body.isState()){
                        finish();
                    }
                    RxToast.showShort(body.getMsg());
                }
            });
    }

/*
    public BaseQuickAdapter<GoodsBean, BaseViewHolder> initAdapter() {
        GoodsEvaluateAdapter mAdapter = new GoodsEvaluateAdapter(R.layout.item_goods_evaluate, list);
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                GoodsBean bean = (GoodsBean) adapter.getData().get(position);
                if(R.id.action_submit == view.getId()){//提交

                    Log.e("点击了22","点击了2222");
                    //提交商品订单评价
                    String order_id = getIntent().getStringExtra("order_id");
                    // String text = etComment.getText().toString().trim();
                    StringBuilder builder = new StringBuilder();

                    builder.append("[");
                    for(int i=0;i<list.size();i++){
                        builder.append("{");
                        builder.append("\"goods_id\"").append(":").append(list.get(i).getGoodsId()).append(",");
                        builder.append("\"evalution_score\"").append(":").append(mRatingCount4).append(",");
                        builder.append("\"text\"").append(":").append("\""+text+"\"").append(",");
                        builder.append("\"imgs\"").append(":").append("\""+sb.toString()+"\"");
                        if(i==list.size()-1){
                            builder.append("}");
                        }else{
                            builder.append("},");
                        }
                    }
                    builder.append("]");
                    //Logger.e("builder",builder.toString());
                    //Logger.e("mRatingCount1",mRatingCount1+"");
                    //Logger.e("mRatingCount2",mRatingCount2+"");
                    //Logger.e("mRatingCount3",mRatingCount3+"");
                    goodsEval(order_id,builder.toString(),mRatingCount1,mRatingCount2,mRatingCount3);


                }
            }
        });

        return mAdapter;
    }*/









    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

          adapter.onActivityResult(requestCode, resultCode, data);
    }

    public void actionUploadFileMore(String type, final List<File> list){
        User user = TyslApp.getInstance().getUser();
        long time_stamp = System.currentTimeMillis();
        String url = Constants.BASE_URL + Constants.OTHER_UPLOAD_FILE_SINGLE_URL;
        String sign = SignUtil.getSign(url, user.getUserId(), user.getAccessToken(), time_stamp);
        Map<String,String> params = new HashMap<>();
        params.put("user_id",user.getUserId());
        params.put("time_stamp",time_stamp+"");
        params.put("sign",sign);
        params.put("type",type);
       Log.e("File22","File:"+list);

        for(int i=0;i<list.size();i++){
        OkGo.<BaseResponse<UploadFileSingle>>post(url)
                .tag(this)
                .params(params)
                .params(Constants.UPLOAD_FILE, list.get(i)) 	// 支持多文件同时添加上传SingleParam
                .execute(new JsonCallback<BaseResponse<UploadFileSingle>>(Constants.WITH_TOKEN,Constants.OTHER_UPLOAD_FILE_SINGLE_URL) {
                    @Override
                    public void onSuccess(Response<BaseResponse<UploadFileSingle>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;
                        if(body.isState()){
                          //  UploadFileSingle data = (UploadFileSingle) body.getData();

                            UploadFileSingle data = (UploadFileSingle) body.getData();
                            sb.append(data.getFilename()).append(",");
                            count++;
                            Logger.e("count="+count,"size="+size);
                            if(count == size){
                                dismissLP();
                            }
                            RxToast.showShort(body.getMsg());
                        }
                    }

                    @Override
                    public void onError(Response<BaseResponse<UploadFileSingle>> response) {
                        super.onError(response);
                        dismissLP();
                       Log.e("2421232", response.body()+"");
                        RxToast.showShort("文件上传出错");
                    }
                });

    }
    }
}
