package com.wokun.tysl.dietician.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.hedgehog.ratingbar.RatingBar;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shantoo.widget.toast.RxToast;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.tysl.R;
import com.wokun.tysl.base.BaseBindingActivity;
import com.wokun.tysl.callback.JsonCallback;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.home.adapter.XueTangAdapter;
import com.wokun.tysl.model.bean.LookEval;
import com.wokun.tysl.model.response.BaseResponse;
import com.wokun.tysl.utils.ImageLoader;

import butterknife.BindString;
import butterknife.BindView;

//营养师查看评价页面
public class DietitianCheckEvaluate extends BaseBindingActivity {

    @BindString(R.string.tysl_check_evaluate)
    String title;

    @BindView(R.id.dietitian_avatar)ImageView dietitianAvatar;//用户头像
    @BindView(R.id.evalution_score1)RatingBar evalutionScore1;//专业评论星星
    @BindView(R.id.evalution_score2)RatingBar evalutionScore2;//服务评论星星

    @BindView(R.id.recycler_view)RecyclerView mRecyclerView;//图片评论

    @BindView(R.id.evalution_text)TextView evalutionText;//文字评论
    @BindView(R.id.evalution_time)TextView evalutionTime;//评论时间

    @Override
    public int createView() {
        return R.layout.activity_dietitian_check_evaluate;
    }

    @Override
    public WidgetBar createToolBar() {
        return mWidgetBar.setWidgetBarTitle(title);
    }

    @Override
    public void init() {
        String order_id = getIntent().getStringExtra(Constants.ORDER_ID);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        XueTangAdapter mAdapter = new XueTangAdapter(R.layout.item_image, null);
        mRecyclerView.setAdapter(mAdapter);
        loadData(order_id,"1");
    }

    private void loadData(String orderId,String sourceType){
        OkGo.<BaseResponse<Object>>post(Constants.BASE_URL + Constants.DIETITIAN_LOOK_EVAL_URL)
                .params(Constants.ORDER_ID, orderId)
                .params(Constants.SOURCE_TYPE, sourceType)
                .execute(new JsonCallback<BaseResponse<Object>>(Constants.WITH_TOKEN,Constants.DIETITIAN_LOOK_EVAL_URL) {
                    @Override
                    public void onSuccess(Response<BaseResponse<Object>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;
                        RxToast.showShort(body.getMsg());
                        if(body.isState()){
                            LookEval data = (LookEval) body.getData();
                            ImageLoader.loadImage(data.getDietitian_avatar(),dietitianAvatar);
                            evalutionScore1.setStarCount(data.getEvalution_score1());
                            evalutionScore2.setStarCount(data.getEvalution_score2());

                            evalutionText.setText(data.getEvalution_text());
                            evalutionTime.setText(data.getEvalution_time());
                        }
                    }
                });
    }
}
