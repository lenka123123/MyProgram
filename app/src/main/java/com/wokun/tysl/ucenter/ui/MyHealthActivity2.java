package com.wokun.tysl.ucenter.ui;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.tysl.R;
import com.wokun.tysl.TyslApp;
import com.wokun.tysl.article.ui.PinjiaServiceListActivity;
import com.wokun.tysl.base.BaseBindingActivity;
import com.wokun.tysl.base.SimpleRefreshAndLoadMoreActivity;
import com.wokun.tysl.callback.JsonCallback;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.dietician.adapter.DieticianDetailFieldAdapter;
import com.wokun.tysl.dietician.adapter.EvaAdapter;
import com.wokun.tysl.dietician.adapter.NutritionStationAdapter;
import com.wokun.tysl.dietician.bean.DietitianDetailBean;
import com.wokun.tysl.dietician.bean.EvalDataBean;
import com.wokun.tysl.dietician.bean.RecGoodsBean;
import com.wokun.tysl.goods.ui.activity.GoodsListActivity;
import com.wokun.tysl.model.response.BaseResponse;
import com.wokun.tysl.utils.ImageLoader;

import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;
import io.rong.imkit.RongIM;

//专属顾问
public class MyHealthActivity2 extends BaseBindingActivity{

    @BindString(R.string.tysl_my_health) String title;
      private  String dietitian_id;

    @BindView(R.id.head_logo)
    ImageView mheadlogo;
    @BindView(R.id.truename)
    TextView truename;
    @BindView(R.id.jobType)
    TextView mjobType;
    @BindView(R.id.order_amount)
    TextView order_amount;
    @BindView(R.id.personal_countall)
    TextView personal_countall;
    @BindView(R.id.haoping)
    TextView haoping;

    @BindView(R.id.zhuanshu_weixin)
    TextView zhuanshu_weixin;
    @BindView(R.id.zhuanshu_shouji)
    TextView zhuanshu_shouji;
    @BindView(R.id.zhuanshu_text)
    TextView zhuanshu_text;
    @BindView(R.id.zhuanshu_linyu)
    TextView zhuanshu_linyu;
    @BindView(R.id.empty_evaluate)
    TextView emptyEvaluate;

    @BindView(R.id.recycler_view_evaluate_number)
    RecyclerView recyclerViewEvaluateNumber;//服务评价列表
    @BindView(R.id.recycler_view_nutrition_station)
    RecyclerView recyclerViewNutritionStation;//营养小站
    @BindView(R.id.nutrition_station_num)
    TextView nutritionStationNum;

    private NutritionStationAdapter mNutritionStationAdapter;
    private DietitianDetailBean data;
    private EvaAdapter mEvaAdapter;
    private int dietitianid;
    @Override
    public int createView() {
        return R.layout.activity_center_personalpeople;
    }

    @Override
    public WidgetBar createToolBar() {
        return mWidgetBar.setWidgetBarTitle(title);
    }

    @Override
    public void init() {

        mEvaAdapter = new EvaAdapter(R.layout.item_eval, null);
        recyclerViewEvaluateNumber.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewEvaluateNumber.setAdapter(mEvaAdapter);
        recyclerViewEvaluateNumber.setNestedScrollingEnabled(false);


        mNutritionStationAdapter = new NutritionStationAdapter(R.layout.item_nutrition_station, null);
        recyclerViewNutritionStation.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerViewNutritionStation.setAdapter(mNutritionStationAdapter);
      recyclerViewNutritionStation.setNestedScrollingEnabled(false);


        Intent intent = getIntent();
       dietitian_id = intent.getStringExtra("dietitian_id");
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
                               ImageLoader.loadImage(data.getHeadLogo(),mheadlogo);
                                 truename.setText(data.getTrueName());
                                 mjobType.setText(data.getTypeName());
                                 order_amount.setText("￥"+data.getServiceFee()+"/30天");
                                  personal_countall.setText(data.getServicenNums());
                                  haoping.setText(data.getPraise()+"%");
                                  zhuanshu_shouji.setText(data.getMobile());
                                  zhuanshu_weixin.setText(data.getWechat());
                                   zhuanshu_text.setText(data.getProfile());
                                   zhuanshu_linyu.setText("擅长领域"+data.getField());
                            //服务评价
                       //     serviceEvaluate.setText("服务评价(" + data.getEvalNums() + ")");
                            List<EvalDataBean> list = data.getEvalData();
                            if (list == null || list.size() == 0) {
                                emptyEvaluate.setVisibility(View.VISIBLE);
                                recyclerViewEvaluateNumber.setVisibility(View.GONE);
                            } else {
                                mEvaAdapter.setNewData(data.getEvalData());
                            }

                            //
                            //营养小站
                            List<RecGoodsBean> dataBean = data.getRecGoodsData();
                            if (dataBean != null) {
                                nutritionStationNum.setText("共" + dataBean.size() + "款");
                                mNutritionStationAdapter.setNewData(dataBean);
                            }

                            dietitianid =  data.getDietitianId();
                        }

                    }
                }) ;
    }


    /** 营养小站更多*/
    @OnClick(R.id.action_nutrition_station)
    public void actionNutritionStation() {
        Intent intent = new Intent();
        intent.putExtra(Constants.DIETITIAN_ID, dietitian_id);
        intent.setClass(this, GoodsListActivity.class);
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

}