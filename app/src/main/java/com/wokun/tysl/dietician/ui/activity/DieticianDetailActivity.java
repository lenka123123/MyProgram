/*
package com.wokun.tysl.dietician.ui.activity;

import android.content.Intent;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hedgehog.ratingbar.RatingBar;
import com.itheima.roundedimageview.RoundedImageView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shantoo.widget.imageview.SelectorImageView;
import com.shantoo.widget.multiplephotoselector.util.ImageUtils;
import com.shantoo.widget.toast.RxToast;
import com.shantoo.widget.toolbar.WidgetBar;
import com.shantoo.widget.utils.UITool;
import com.wokun.tysl.R;
import com.wokun.tysl.TyslApp;
import com.wokun.tysl.article.ui.DieticianArticleListActivity;
import com.wokun.tysl.article.ui.PinjiaServiceListActivity;
import com.wokun.tysl.base.BaseBindingActivity;
import com.wokun.tysl.callback.JsonCallback;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.dietician.adapter.DieticianDetailFieldAdapter;
import com.wokun.tysl.dietician.adapter.EvaAdapter;
import com.wokun.tysl.dietician.adapter.NutritionStationAdapter;
import com.wokun.tysl.dietician.bean.DietitianDetailBean;
import com.wokun.tysl.dietician.bean.EvalDataBean;
import com.wokun.tysl.dietician.bean.RecGoodsBean;
import com.wokun.tysl.goods.ui.activity.GoodsListActivity;
import com.wokun.tysl.login.ui.LoginActivity;
import com.wokun.tysl.model.bean.SingleParam;
import com.wokun.tysl.model.response.BaseResponse;
import com.wokun.tysl.utils.ImageLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

*/
/**
 * 营养师详情页
 * *//*

public class DieticianDetailActivity extends BaseBindingActivity implements View.OnClickListener {

    @BindView(R.id.dietician_head_img)
    RoundedImageView dieticianHeadImg;
   */
/* @BindView(R.id.service_num)
    TextView serviceNum;*//*

    @BindView(R.id.favorable_rate)
    TextView favorableRate;
    @BindView(R.id.dietician_type)
    TextView dieticianType;
    @BindView(R.id.dietician_name)
    TextView dieticianName;
*/
/*
    @BindView(R.id.dietician_area)
    TextView dieticianArea;*//*

*/
/*  @BindView(R.id.dietician_service_price)
    TextView dieticianServicePrice;*//*

    @BindView(R.id.dietician_mobile)
    TextView dieticianMobile;//手机号
    @BindView(R.id.dietician_wechat)
    TextView dieticianWechat;//微信号
    @BindView(R.id.article_nums)
    TextView articleNums;
    @BindView(R.id.pinfen)
    TextView pinfen;
    @BindView(R.id.xinxin)
    RatingBar xinxin;
    @BindView(R.id.yinyangshi_text)
    TextView yinyangshi_text;

    @BindView(R.id.item_artcils_text)
    TextView item_artcils_text;
    @BindView(R.id.item_artcils_img)
    ImageView item_artcils_img;
    @BindView(R.id.item_artcils_time)
    TextView item_artcils_time;
    @BindView(R.id.item_artcils_num)
    TextView item_artcils_num;



    @BindView(R.id.nutrition_station_num)
    TextView nutritionStationNum;
    @BindView(R.id.service_evaluate)
    TextView serviceEvaluate;

    @BindView(R.id.recycler_view_field)
    RecyclerView recyclerViewField;//营养师字段列表
    @BindView(R.id.recycler_view_nutrition_station)
    RecyclerView recyclerViewNutritionStation;//营养小站
    @BindView(R.id.yinyangshi_wenzhang)
    LinearLayout yinyangshi_wenzhang;//文章


    @BindView(R.id.recycler_view_evaluate_number)
    RecyclerView recyclerViewEvaluateNumber;//服务评价列表

    @BindView(R.id.empty_evaluate)
    TextView emptyEvaluate;
    @BindView(R.id.service_price2)
    TextView service_price2;
    @BindView(R.id.service_num2)
    TextView service_num2;


    @BindView(R.id.siv_polygon_collect)
    SelectorImageView sivPolygonCollect;


    private EvaAdapter mEvaAdapter;
    private DietitianDetailBean data;
    private String dietitian_id,profile;
    private int dietitian_user_id,favorite,dietitianid;
    private BottomSheetDialog buyServiceDialog;
    private NutritionStationAdapter mNutritionStationAdapter;
    private TextView serviceTime,serviceFee,selectServiceTime;
    private DieticianDetailFieldAdapter mDieticianDetailFieldAdapter;

    @Override
    public int createView() {
        return R.layout.activity_dietician_detail;
    }

    @Override
    public WidgetBar createToolBar() {
        mWidgetBar.setVisibility(View.GONE);
        return null;
    }

    @Override
    public void init() {
        //StatusBarCompat.translucentStatusBar(this);
        mMultipleStatusView.showLoading();

        dietitian_id = getIntent().getStringExtra(Constants.DIETITIAN_ID);
        View view = UITool.createView(R.layout.layout_buy_service);
        serviceTime = (TextView) view.findViewById(R.id.service_time);
        serviceFee = (TextView) view.findViewById(R.id.service_fee);
        selectServiceTime = (TextView) view.findViewById(R.id.select_service_time);

        view.findViewById(R.id.action_add).setOnClickListener(this);
        view.findViewById(R.id.action_reduce).setOnClickListener(this);
        view.findViewById(R.id.submit).setOnClickListener(this);
        buyServiceDialog = new BottomSheetDialog(this);
        buyServiceDialog.setContentView(view);

        mNutritionStationAdapter = new NutritionStationAdapter(R.layout.item_nutrition_station, null);
        recyclerViewNutritionStation.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerViewNutritionStation.setAdapter(mNutritionStationAdapter);

        mEvaAdapter = new EvaAdapter(R.layout.item_eval, null);
        recyclerViewEvaluateNumber.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewEvaluateNumber.setAdapter(mEvaAdapter);

        mDieticianDetailFieldAdapter = new DieticianDetailFieldAdapter(R.layout.item_dietician_detail_field, null);
        recyclerViewField.setLayoutManager(new GridLayoutManager(this, 4));
        recyclerViewField.setAdapter(mDieticianDetailFieldAdapter);

        recyclerViewNutritionStation.setNestedScrollingEnabled(false);
        recyclerViewEvaluateNumber.setNestedScrollingEnabled(false);
        recyclerViewField.setNestedScrollingEnabled(false);

        loadData();
       // xinxin.setStarCount(data.getPingfen());

    }

    private void loadData() {
        OkGo.<BaseResponse<DietitianDetailBean>>post(Constants.BASE_URL + Constants.DIETITIAN_DETAIL_URL)
                .tag(this)
                .params(Constants.DIETITIAN_ID, dietitian_id)
                .execute(new JsonCallback<BaseResponse<DietitianDetailBean>>(Constants.LOGIN_WITH_TOKEN, Constants.DIETITIAN_DETAIL_URL) {
                    @Override
                    public void onSuccess(Response<BaseResponse<DietitianDetailBean>> response) {
                        BaseResponse body = response.body();
                        if (body == null) return;
                        if (body.isState()) {
                            mMultipleStatusView.showContent();
                            data = (DietitianDetailBean) body.getData();
                            if (data == null) return;
                            favorite = data.getFavorite();
                            sivPolygonCollect.toggle(1 == favorite);
                            //营养师字段
                            mDieticianDetailFieldAdapter.setNewData(data.getField());

                            ImageLoader.loadImage(data.getHeadLogo(), dieticianHeadImg);
                            dietitian_user_id = data.getDietitianUserId();
                            dietitianid =  data.getDietitianId();
                            dieticianName.setText(data.getTrueName());
                            dieticianType.setText(data.getTypeName());
                        //    serviceNum.setText("服务数:" + data.getServicenNums() + "");
                            //favorableRate.setText("好评率:" + data.getPraise() + "");
                            favorableRate.setText(  data.getAreaInfo() + "");
                       //     dieticianArea.setText(data.getAreaInfo());
                            serviceFee.setText("￥" + data.getServiceFee() + "");
                            pinfen.setText(data.getPingfen()+"分");

                            service_price2.setText("￥"+ data.getServiceFee()+"/天");
                            service_num2.setText(data.getServicenNums()+"次");
                            Log.e("xinxin",data.getPingfen()+"");

                            //   mxinxin.setStarCount(data.getPingfen());
                       //     xinxin.clearDisappearingChildren();
                        */
/*    xinxin.post(new Runnable() {
                                @Override
                                public void run() {
                                    xinxin.setStarCount(2);
                                 //   xinxin.invalidate();
                                }
                            });*//*


                            yinyangshi_text.setText(data.getProfile());
                       //       dieticianServicePrice.setText("" + data.getServiceFee() + "元/30天");
                              selectServiceTime.setText(serviceFee.getText() + "/" + serviceTime.getText());
                             if (1 == data.getBuyService()) {//已购买服务后显示手机号和微信号
                                dieticianMobile.setText(data.getMobile());
                                dieticianWechat.setText(data.getWechat());
                            }
                                 //营养小站
                            List<RecGoodsBean> dataBean = data.getRecGoodsData();
                            if (dataBean != null) {
                                nutritionStationNum.setText("共" + dataBean.size() + "款");
                                mNutritionStationAdapter.setNewData(dataBean);
                            }

                            //发布的文章

                                 DietitianDetailBean.ArticlepeopleBean article = data.getArticle();
                                 Log.e("article",article+"");
                                if(data.getArticle()!=null){
                                    articleNums.setText("共" + data.getArticleNums() + "款");
                                    item_artcils_text.setText(data.getArticle().getDes());
                                    ImageLoader.loadImage(data.getArticle().getArticle_image(),item_artcils_img);
                                    item_artcils_time.setText(data.getArticle().getAdd_time());
                                    item_artcils_num.setText(data.getArticle().getClick_num()+"人浏览");

                                }if(data.getArticleNums().equals("0")){

                                yinyangshi_wenzhang.setVisibility(View.GONE);

                            }
                            //服务评价
                            serviceEvaluate.setText("服务评价(" + data.getEvalNums() + ")");
                            List<EvalDataBean> list = data.getEvalData();
                            if (list == null || list.size() == 0) {
                             emptyEvaluate.setVisibility(View.VISIBLE);
                            //    recyclerViewEvaluateNumber.setVisibility(View.GONE);
                            } else {
                                mEvaAdapter.setNewData(data.getEvalData());
                            }
                            profile = data.getProfile();
                        }
                    }
                });
    }

    */
/** 返回*//*

    @OnClick(R.id.action_back)
    public void actionBack() {
        finish();
    }

    */
/** 收藏营养师*//*

    @OnClick(R.id.action_polygon_collect)
    public void actionPolygonCollect() {
        if (data != null) {
            favorites(Constants.FAVORITES_TYPE_DIETICIAN, String.valueOf(data.getDietitianId()), sivPolygonCollect);
        }
    }

    */
/** 个人简介页面*//*

    @OnClick(R.id.action_dietician_individual_resume)
    public void actionDieticianIndividualResume() {
        Intent intent = new Intent();
        intent.putExtra(Constants.PROFILE, profile);
        intent.setClass(this, DieticianDndividualResumeActivity.class);
        startActivity(intent);
    }

    */
/** 营养小站更多*//*

    @OnClick(R.id.action_nutrition_station)
    public void actionNutritionStation() {
        Intent intent = new Intent();
        intent.putExtra(Constants.DIETITIAN_ID, dietitian_id);
        intent.setClass(this, GoodsListActivity.class);
        startActivity(intent);
    }

    */
/** 文章列表頁面*//*

    @OnClick(R.id.action_release_article)
    public void actionReleaseArticle() {
        Intent intent = new Intent();
        intent.putExtra(Constants.DIETITIAN_USER_ID, dietitian_user_id);
        intent.setClass(this, DieticianArticleListActivity.class);
        startActivity(intent);
    }
    */
/** 服务评价列表頁面*//*

    @OnClick(R.id.action_evaluate_number)
    public void actionReleaseNumber() {
        Intent intent = new Intent();

        intent.putExtra(Constants.DIETITIAN_ID, dietitianid);
        intent.setClass(this, PinjiaServiceListActivity.class);
        startActivity(intent);
    }

    */
/** 购买服务*//*

    @OnClick(R.id.action_buy_service)
    public void actionBuyService() {
        if (buyServiceDialog.isShowing()) {
            buyServiceDialog.dismiss();
        } else {
            buyServiceDialog.show();
        }
    }

    @Override
    public void onClick(View v) {
        if(R.id.action_add == v.getId()){//添加服务时长
            if(data==null)return;
            int add = Integer.valueOf(serviceTime.getText().toString().replace("天", ""));
            add += 30;
            serviceTime.setText(""+add + "天");
            double a = (add / 30) * Double.valueOf(data.getServiceFee());
            serviceTime.setText(serviceTime.getText());
            serviceFee.setText("￥" + String.format("%.2f", a)+"");
            selectServiceTime.setText(serviceFee.getText() + "/" + serviceTime.getText());
        }else if(R.id.action_reduce == v.getId()){//减少服务时长
            if(data==null)return;
            int reduce = Integer.valueOf(serviceTime.getText().toString().replace("天", ""));
            if (reduce <= 30) {return;}
            reduce -= 30;
            serviceTime.setText(""+reduce + "天");
            double b = (reduce / 30) * Double.valueOf(data.getServiceFee());
            serviceFee.setText("￥" + String.format("%.2f", b)+"");
            serviceTime.setText(serviceTime.getText());
            selectServiceTime.setText(serviceFee.getText() + "/" + serviceTime.getText());
        }else if(R.id.submit == v.getId()){//确定
            if (!TyslApp.getInstance().isLogin()) {
                startActivity(LoginActivity.class);
                return;
            }
            dietitianServiceOrder();
        }
    }

    */
/** 购买服务生成订单*//*

    private void dietitianServiceOrder() {
        if(data==null)return;
        final String service_price = data.getServiceFee();
        final String service_time = serviceTime.getText().toString().replace("天", "");
        final String service_pay = serviceFee.getText().toString().replace("￥", "");
        OkGo.<BaseResponse<SingleParam>>post(Constants.BASE_URL + Constants.DIETITIAN_SERVICE_ORDER_URL)//
                .tag(this)
                .params(Constants.DIETITIAN_ID, dietitian_id)
                .params(Constants.SERVICE_DAYS, service_time)
                .params(Constants.SERVICE_FEE, service_price)
                .params(Constants.PAY_MONEY, service_pay)
                .execute(new JsonCallback<BaseResponse<SingleParam>>(Constants.WITH_TOKEN,Constants.DIETITIAN_SERVICE_ORDER_URL) {
                    @Override
                    public void onSuccess(Response<BaseResponse<SingleParam>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;
                        if (body.isState()) {
                            SingleParam singleParam = (SingleParam) body.getData();
                            Intent intent = new Intent();
                            intent.putExtra(Constants.ORDER_NUMBER, singleParam.getOrderNumber());
                            intent.putExtra(Constants.SERVICE_TIME, service_time);
                            intent.putExtra(Constants.SERVICE_PRICE, service_price);
                            intent.putExtra(Constants.SERVICE_TOTAL_PRICE, service_pay);
                            intent.setClass(TyslApp.getContext(), DietitianBuyServiceActivity.class);
                            startActivity(intent);
                            buyServiceDialog.dismiss();
                        }
                        RxToast.showShort(body.getMsg());
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(11==requestCode){
            loadData();
        }
    }

    private void favorites(int type, String source_id, SelectorImageView sivCollect){
        if(0==favorite){//未收藏
            addFavorites(type,source_id,sivCollect);
        }else if(1==favorite){//已收藏
            deleteFavorites(type,source_id,sivCollect);
        }
    }

    //添加收藏
    public void addFavorites(final int type, String source_id, final SelectorImageView sivCollect){
        if(!TyslApp.getInstance().isLogin()){
            Intent intent = new Intent();
            intent.setClass(this,LoginActivity.class);
            startActivityForResult(intent,11);
            return;
        }

        OkGo.<BaseResponse<Object>>post(Constants.BASE_URL + Constants.FAVORITES_DELETE_URL)
                .tag(this)
                .params(Constants.TYPE, type)
                .params(Constants.SOURCE_ID, source_id)
                .execute(new JsonCallback<BaseResponse<Object>>(Constants.WITH_TOKEN,Constants.FAVORITES_DELETE_URL) {
                    @Override
                    public void onSuccess(Response<BaseResponse<Object>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;
                        if(body.isState()&&sivCollect!=null){
                            favorite = 1;
                            sivCollect.toggle(true);
                            RxToast.showShort(body.getMsg());
                        }
                    }
                });
    }

    //取消收藏
    public void deleteFavorites(int type,String source_id,final SelectorImageView sivCollect){
        if(!TyslApp.getInstance().isLogin()){
            Intent intent = new Intent();
            intent.setClass(this,LoginActivity.class);
            startActivityForResult(intent,11);
            return;
        }

        OkGo.<BaseResponse<Object>>post(Constants.BASE_URL + Constants.FAVORITES_ADD_URL)
                .tag(this)
                .params(Constants.TYPE, type)
                .params(Constants.SOURCE_ID, source_id)
                .execute(new JsonCallback<BaseResponse<Object>>(Constants.WITH_TOKEN,Constants.FAVORITES_ADD_URL) {
                    @Override
                    public void onSuccess(Response<BaseResponse<Object>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;
                        if(body.isState()&&sivCollect!=null){
                            favorite = 0;
                            sivCollect.toggle(false);
                            Toast.makeText(TyslApp.getContext(), body.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}*/
package com.wokun.tysl.dietician.ui.activity;

import android.content.Intent;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hedgehog.ratingbar.RatingBar;
import com.itheima.roundedimageview.RoundedImageView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shantoo.widget.imageview.SelectorImageView;
import com.shantoo.widget.multiplephotoselector.util.ImageUtils;
import com.shantoo.widget.toast.RxToast;
import com.shantoo.widget.toolbar.WidgetBar;
import com.shantoo.widget.utils.UITool;
import com.wokun.tysl.R;
import com.wokun.tysl.TyslApp;
import com.wokun.tysl.article.ui.DieticianArticleListActivity;
import com.wokun.tysl.article.ui.PinjiaServiceListActivity;
import com.wokun.tysl.base.BaseBindingActivity;
import com.wokun.tysl.callback.JsonCallback;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.dietician.adapter.DieticianDetailFieldAdapter;
import com.wokun.tysl.dietician.adapter.EvaAdapter;
import com.wokun.tysl.dietician.adapter.NutritionStationAdapter;
import com.wokun.tysl.dietician.bean.DietitianDetailBean;
import com.wokun.tysl.dietician.bean.EvalDataBean;
import com.wokun.tysl.dietician.bean.RecGoodsBean;
import com.wokun.tysl.duotupianshangchuan.MyRatingBar;
import com.wokun.tysl.goods.ui.activity.GoodsListActivity;
import com.wokun.tysl.login.ui.LoginActivity;
import com.wokun.tysl.model.bean.SingleParam;
import com.wokun.tysl.model.response.BaseResponse;
import com.wokun.tysl.store.ui.StoreIndexActivity;
import com.wokun.tysl.utils.ImageLoader;
import com.wokun.tysl.utils.ShareSDKUtil;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * 营养师详情页
 * */
public class DieticianDetailActivity extends BaseBindingActivity implements View.OnClickListener {

    @BindView(R.id.dietician_head_img)
    RoundedImageView dieticianHeadImg;
    /* @BindView(R.id.service_num)
     TextView serviceNum;*/
    @BindView(R.id.favorable_rate)
    TextView favorableRate;
    @BindView(R.id.dietician_type)
    TextView dieticianType;
    @BindView(R.id.dietician_name)
    TextView dieticianName;
    /*
        @BindView(R.id.dietician_area)
        TextView dieticianArea;*/
/*  @BindView(R.id.dietician_service_price)
    TextView dieticianServicePrice;*/
    @BindView(R.id.dietician_mobile)
    TextView dieticianMobile;//手机号
    @BindView(R.id.dietician_wechat)
    TextView dieticianWechat;//微信号
    @BindView(R.id.article_nums)
    TextView articleNums;
    @BindView(R.id.pinfen)
    TextView pinfen;
    @BindView(R.id.xinxin)
    MyRatingBar xinxin;

    @BindView(R.id.yinyangshi_text)
    TextView yinyangshi_text;

    @BindView(R.id.item_artcils_text)
    TextView item_artcils_text;
    @BindView(R.id.item_artcils_img)
    ImageView item_artcils_img;
    @BindView(R.id.item_artcils_time)
    TextView item_artcils_time;
    @BindView(R.id.item_artcils_num)
    TextView item_artcils_num;



    @BindView(R.id.nutrition_station_num)
    TextView nutritionStationNum;
    @BindView(R.id.service_evaluate)
    TextView serviceEvaluate;

    @BindView(R.id.recycler_view_field)
    RecyclerView recyclerViewField;//营养师字段列表
    @BindView(R.id.recycler_view_nutrition_station)
    RecyclerView recyclerViewNutritionStation;//营养小站
    @BindView(R.id.yinyangshi_wenzhang)
    LinearLayout yinyangshi_wenzhang;//文章


    @BindView(R.id.recycler_view_evaluate_number)
    RecyclerView recyclerViewEvaluateNumber;//服务评价列表

    @BindView(R.id.empty_evaluate)
    TextView emptyEvaluate;
    @BindView(R.id.service_price2)
    TextView service_price2;
    @BindView(R.id.service_num2)
    TextView service_num2;
    @BindView(R.id.share_yinyangshi)
    ImageView shareYinyangshi;


    @BindView(R.id.siv_polygon_collect)
    SelectorImageView sivPolygonCollect;


    private EvaAdapter mEvaAdapter;
    private DietitianDetailBean data;
    private String dietitian_id,profile;
    private int dietitian_user_id,favorite,dietitianid;
    private BottomSheetDialog buyServiceDialog;
    private NutritionStationAdapter mNutritionStationAdapter;
    private TextView serviceTime,serviceFee,selectServiceTime,txtName,txtType;
    private DieticianDetailFieldAdapter mDieticianDetailFieldAdapter;
    private ImageView imHead;
    private int serviceDay=30;
    private String share_url;
    private String trueName;
    private String typeName;
    private String headLogo;

    @Override
    public int createView() {
        return R.layout.activity_dietician_detail;
    }

    @Override
    public WidgetBar createToolBar() {
        mWidgetBar.setVisibility(View.GONE);
        return null;
    }

    @Override
    public void init() {
        //StatusBarCompat.translucentStatusBar(this);
        mMultipleStatusView.showLoading();

        dietitian_id = getIntent().getStringExtra(Constants.DIETITIAN_ID);
        View view = UITool.createView(R.layout.layout_buy_service);
        serviceTime = (TextView) view.findViewById(R.id.service_time);
        serviceFee = (TextView) view.findViewById(R.id.service_fee);
        txtName = (TextView) view.findViewById(R.id.txtName);
        txtType = (TextView) view.findViewById(R.id.txtType);
        imHead = (ImageView) view.findViewById(R.id.imHead);
        selectServiceTime = (TextView) view.findViewById(R.id.select_service_time);

        view.findViewById(R.id.action_add).setOnClickListener(this);
        view.findViewById(R.id.action_reduce).setOnClickListener(this);
        view.findViewById(R.id.submit).setOnClickListener(this);
        buyServiceDialog = new BottomSheetDialog(this,R.style.dialog);
        buyServiceDialog.setContentView(view);

        mNutritionStationAdapter = new NutritionStationAdapter(R.layout.item_nutrition_station, null);
        recyclerViewNutritionStation.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerViewNutritionStation.setAdapter(mNutritionStationAdapter);

        mEvaAdapter = new EvaAdapter(R.layout.item_eval, null);
        recyclerViewEvaluateNumber.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewEvaluateNumber.setAdapter(mEvaAdapter);

        mDieticianDetailFieldAdapter = new DieticianDetailFieldAdapter(R.layout.item_dietician_detail_field, null);
        recyclerViewField.setLayoutManager(new GridLayoutManager(this, 4));
        recyclerViewField.setAdapter(mDieticianDetailFieldAdapter);

        recyclerViewNutritionStation.setNestedScrollingEnabled(false);
        recyclerViewEvaluateNumber.setNestedScrollingEnabled(false);
        recyclerViewField.setNestedScrollingEnabled(false);

        loadData();
    }

    private void loadData() {
        OkGo.<BaseResponse<DietitianDetailBean>>post(Constants.BASE_URL + Constants.DIETITIAN_DETAIL_URL)
                .tag(this)
                .params(Constants.DIETITIAN_ID, dietitian_id)
                .execute(new JsonCallback<BaseResponse<DietitianDetailBean>>(Constants.LOGIN_WITH_TOKEN, Constants.DIETITIAN_DETAIL_URL) {
                    @Override
                    public void onSuccess(Response<BaseResponse<DietitianDetailBean>> response) {
                        BaseResponse body = response.body();
                        if (body == null) return;
                        if (body.isState()) {
                            mMultipleStatusView.showContent();
                            data = (DietitianDetailBean) body.getData();
                            if (data == null) return;
                            favorite = data.getFavorite();
                            sivPolygonCollect.toggle(1 == favorite);
                            //营养师字段
                            mDieticianDetailFieldAdapter.setNewData(data.getField());

                            ImageLoader.loadImage(data.getHeadLogo(), dieticianHeadImg);
                            dietitian_user_id = data.getDietitianUserId();
                            dietitianid =  data.getDietitianId();
                            share_url = data.getShare_url();
                            trueName = data.getTrueName();
                            typeName = data.getTypeName();
                            headLogo = data.getHeadLogo();
                            dieticianName.setText(data.getTrueName());
                            dieticianType.setText(data.getTypeName());
                            //    serviceNum.setText("服务数:" + data.getServicenNums() + "");
                            //favorableRate.setText("好评率:" + data.getPraise() + "");
                            favorableRate.setText(  data.getAreaInfo() + "");
                            //     dieticianArea.setText(data.getAreaInfo());
                            serviceFee.setText("￥" + data.getServiceFee() + "");
                            pinfen.setText(data.getPingfen()+"分");
                            txtName.setText(data.getTrueName());
                            txtType.setText(data.getTypeName());
                            ImageLoader.loadImage(data.getHeadLogo(),imHead);
                            service_price2.setText("￥"+ data.getServiceFee()+"/30天");
                            service_num2.setText(data.getServicenNums()+"次");
                            Log.e("xinxin",data.getPingfen()+"");

                            //   mxinxin.setStarCount(data.getPingfen());
                            //     xinxin.clearDisappearingChildren();
                            xinxin.setStarCount(data.getPingfen());


                            yinyangshi_text.setText(data.getProfile());


                            //       dieticianServicePrice.setText("" + data.getServiceFee() + "元/30天");
                            selectServiceTime.setText(serviceFee.getText() + "/" + serviceTime.getText());
                            if (1 == data.getBuyService()) {//已购买服务后显示手机号和微信号
                                dieticianMobile.setText(data.getMobile());
                                dieticianWechat.setText(data.getWechat());
                            }

                            //营养小站
                            List<RecGoodsBean> dataBean = data.getRecGoodsData();
                            if (dataBean != null) {
                                nutritionStationNum.setText("共" + dataBean.size() + "款");
                                mNutritionStationAdapter.setNewData(dataBean);
                            }

                            //发布的文章

                            DietitianDetailBean.ArticlepeopleBean article = data.getArticle();
                            Log.e("article",article+"");
                            if(data.getArticle()!=null){
                                articleNums.setText("共" + data.getArticleNums() + "款");
                                item_artcils_text.setText(data.getArticle().getDes());

                                ImageLoader.loadImage(data.getArticle().getArticle_image(),item_artcils_img);
                                item_artcils_time.setText(data.getArticle().getAdd_time());
                                item_artcils_num.setText(data.getArticle().getClick_num()+"人浏览");

                            }if(data.getArticleNums().equals("0")){

                                yinyangshi_wenzhang.setVisibility(View.GONE);

                            }
                            //服务评价
                            serviceEvaluate.setText("服务评价(" + data.getEvalNums() + ")");
                            List<EvalDataBean> list = data.getEvalData();
                            if (list == null || list.size() == 0) {
                                emptyEvaluate.setVisibility(View.VISIBLE);
                                //    recyclerViewEvaluateNumber.setVisibility(View.GONE);
                            } else {
                                mEvaAdapter.setNewData(data.getEvalData());
                            }
                            profile = data.getProfile();
                        }
                    }
                });
    }

    /** 返回*/
    @OnClick(R.id.action_back)
    public void actionBack() {
        finish();
    }
    /** 分享*/

    @OnClick(R.id.share_yinyangshi)
    public void actionShare() {


        //   ShareSDKUtil.showShare(TyslApp.getContext(), trueName, typeName, share_url, headLogo);
        Log.e("123243222","onComplete"+headLogo+"####"+trueName+"####"+typeName+"####"+share_url);
        OnekeyShare oks = new OnekeyShare();
        oks.disableSSOWhenAuthorize();
        oks.setSilent(true);
        oks.setImageUrl("");
        oks.setText(trueName);
        oks.setTitle(typeName);
        oks.setTitleUrl(share_url);

/*        oks.setImageUrl("https://hmls.hfbank.com.cn/hfapp-api/9.png");
        oks.setText("标题");
        oks.setTitle("我是共用的参数，这几个平台都有text参数要求，提取出来啦");
        oks.setTitleUrl("http://sharesdk.cn");*/

        oks.setCallback(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                Log.i("1234","onComplete");
                Toast.makeText(DieticianDetailActivity.this,"成功了",Toast.LENGTH_SHORT).show();
                OkGo.<BaseResponse<Object>>post(Constants.BASE_URL + Constants.INTEGRAL_SHARE)
                        .execute(new JsonCallback<BaseResponse<Object>>(Constants.WITH_TOKEN, Constants.INTEGRAL_SHARE) {
                            @Override
                            public void onSuccess(Response<BaseResponse<Object>> response) {
                                BaseResponse body = response.body();
                                if (body == null) return;
                                RxToast.showShort(body.getMsg());
                                if (body.isState()) {

                                }
                            }
                        });
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                Log.i("1234",throwable.getMessage());
                throwable.printStackTrace();

            }

            @Override
            public void onCancel(Platform platform, int i) {
                Log.i("1234","onCancel");

            }
        });

// 启动分享GUI
        oks.show(DieticianDetailActivity.this);
    }





    /** 收藏营养师*/
    @OnClick(R.id.action_polygon_collect)
    public void actionPolygonCollect() {
        if (data != null) {
            favorites(Constants.FAVORITES_TYPE_DIETICIAN, String.valueOf(data.getDietitianId()), sivPolygonCollect);
        }
    }

    /** 个人简介页面*/
    @OnClick(R.id.action_dietician_individual_resume)
    public void actionDieticianIndividualResume() {
        Intent intent = new Intent();
        intent.putExtra(Constants.PROFILE, profile);
        intent.setClass(this, DieticianDndividualResumeActivity.class);
        startActivity(intent);
    }

    /** 营养小站更多*/
    @OnClick(R.id.action_nutrition_station)
    public void actionNutritionStation() {
        Intent intent = new Intent();
        intent.putExtra(Constants.DIETITIAN_ID, dietitian_id);
        intent.setClass(this, GoodsListActivity.class);
        startActivity(intent);
    }

    /** 文章列表頁面*/
    @OnClick(R.id.action_release_article)
    public void actionReleaseArticle() {
        Intent intent = new Intent();
        intent.putExtra(Constants.DIETITIAN_USER_ID, dietitian_user_id);
        intent.setClass(this, DieticianArticleListActivity.class);
        startActivity(intent);
    }
    /** 服务评价列表頁面*/
    @OnClick(R.id.action_evaluate_number)
    public void actionReleaseNumber() {
        Intent intent = new Intent();

        intent.putExtra(Constants.DIETITIAN_ID, dietitianid);
        intent.setClass(this, PinjiaServiceListActivity.class);
        startActivity(intent);
    }

    /** 购买服务*/
    @OnClick(R.id.action_buy_service)
    public void actionBuyService() {
        if (buyServiceDialog.isShowing()) {
            buyServiceDialog.dismiss();
        } else {
            buyServiceDialog.show();
        }
    }

    @Override
    public void onClick(View v) {
        if(R.id.action_add == v.getId()){//添加服务时长
            if(data==null)return;
            serviceDay += 30;
            serviceTime.setText(serviceDay + "天");
            double a = (serviceDay / 30) * Double.valueOf(data.getServiceFee());
            serviceTime.setText(serviceTime.getText());
            serviceFee.setText("￥" + String.format("%.2f", a)+"");
            selectServiceTime.setText(serviceFee.getText() + "/" + serviceTime.getText());
        }else if(R.id.action_reduce == v.getId()){//减少服务时长
            if(data==null)return;
            if (serviceDay <= 30) {return;}
            serviceDay -= 30;
            serviceTime.setText(serviceDay + "天");
            double b = (serviceDay / 30) * Double.valueOf(data.getServiceFee());
            serviceFee.setText("￥" + String.format("%.2f", b)+"");
            serviceTime.setText(serviceTime.getText());
            selectServiceTime.setText(serviceFee.getText() + "/" + serviceTime.getText());
        }else if(R.id.submit == v.getId()){//确定
            if (!TyslApp.getInstance().isLogin()) {
                startActivity(LoginActivity.class);
                return;
            }
            dietitianServiceOrder();
        }
    }

    /** 购买服务生成订单*/
    private void dietitianServiceOrder() {
        if(data==null)return;
        final String service_price = data.getServiceFee();
        final String service_time = serviceTime.getText().toString().replace("天", "");
        final String service_pay = serviceFee.getText().toString().replace("￥", "");
        OkGo.<BaseResponse<SingleParam>>post(Constants.BASE_URL + Constants.DIETITIAN_SERVICE_ORDER_URL)//
                .tag(this)
                .params(Constants.DIETITIAN_ID, dietitian_id)
                .params(Constants.SERVICE_DAYS, service_time)
                .params(Constants.SERVICE_FEE, service_price)
                .params(Constants.PAY_MONEY, service_pay)
                .execute(new JsonCallback<BaseResponse<SingleParam>>(Constants.WITH_TOKEN,Constants.DIETITIAN_SERVICE_ORDER_URL) {
                    @Override
                    public void onSuccess(Response<BaseResponse<SingleParam>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;
                        if (body.isState()) {
                            SingleParam singleParam = (SingleParam) body.getData();
                            Intent intent = new Intent();
                            intent.putExtra(Constants.ORDER_NUMBER, singleParam.getOrderNumber());
                            intent.putExtra(Constants.SERVICE_TIME, service_time);
                            intent.putExtra(Constants.SERVICE_PRICE, service_price);
                            intent.putExtra(Constants.SERVICE_TOTAL_PRICE, service_pay);
                            intent.setClass(TyslApp.getContext(), DietitianBuyServiceActivity.class);
                            startActivity(intent);
                            buyServiceDialog.dismiss();
                        }
                        RxToast.showShort(body.getMsg());
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(11==requestCode){
            loadData();
        }
    }

    private void favorites(int type, String source_id, SelectorImageView sivCollect){
        if(0==favorite){//未收藏
            addFavorites(type,source_id,sivCollect);
        }else if(1==favorite){//已收藏
            deleteFavorites(type,source_id,sivCollect);
        }
    }

    //添加收藏
    public void addFavorites(final int type, String source_id, final SelectorImageView sivCollect){
        if(!TyslApp.getInstance().isLogin()){
            Intent intent = new Intent();
            intent.setClass(this,LoginActivity.class);
            startActivityForResult(intent,11);
            return;
        }

        OkGo.<BaseResponse<Object>>post(Constants.BASE_URL + Constants.FAVORITES_DELETE_URL)
                .tag(this)
                .params(Constants.TYPE, type)
                .params(Constants.SOURCE_ID, source_id)
                .execute(new JsonCallback<BaseResponse<Object>>(Constants.WITH_TOKEN,Constants.FAVORITES_DELETE_URL) {
                    @Override
                    public void onSuccess(Response<BaseResponse<Object>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;
                        if(body.isState()&&sivCollect!=null){
                            favorite = 1;
                            sivCollect.toggle(true);
                            RxToast.showShort(body.getMsg());
                        }
                    }
                });
    }

    //取消收藏
    public void deleteFavorites(int type,String source_id,final SelectorImageView sivCollect){
        if(!TyslApp.getInstance().isLogin()){
            Intent intent = new Intent();
            intent.setClass(this,LoginActivity.class);
            startActivityForResult(intent,11);
            return;
        }

        OkGo.<BaseResponse<Object>>post(Constants.BASE_URL + Constants.FAVORITES_ADD_URL)
                .tag(this)
                .params(Constants.TYPE, type)
                .params(Constants.SOURCE_ID, source_id)
                .execute(new JsonCallback<BaseResponse<Object>>(Constants.WITH_TOKEN,Constants.FAVORITES_ADD_URL) {
                    @Override
                    public void onSuccess(Response<BaseResponse<Object>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;
                        if(body.isState()&&sivCollect!=null){
                            favorite = 0;
                            sivCollect.toggle(false);
                            Toast.makeText(TyslApp.getContext(), body.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}