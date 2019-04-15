package com.wokun.tysl.mypersonmoney.ui;

import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.tysl.R;
import com.wokun.tysl.TyslApp;
import com.wokun.tysl.base.BaseBindingActivity;
import com.wokun.tysl.callback.JsonCallback;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.model.bean.User;
import com.wokun.tysl.model.response.BaseResponse;
import com.wokun.tysl.ucenter.bean.ShejiaoBean;
import com.wokun.tysl.ucenter.ui.PayActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/9/6 0006.
 *
 * 我的钱包
 */

public class MyyueActivity extends BaseBindingActivity{
    @BindView(R.id.activity_yue)
    TextView activity_yue;
    @BindView(R.id.activity_yue_mingxi)
    RelativeLayout activity_yue_mingxi;
    @BindView(R.id.activity_yue_money)
    RelativeLayout activity_yue_money;

    @Override
    public int createView() {
        return R.layout.activity_my_yue;
    }

    @Override
    public WidgetBar createToolBar() {

        return mWidgetBar.setWidgetBarTitle("我的钱包");
    }

    @Override
    public void init() {
        Intent intent = getIntent();
        String yue = intent.getStringExtra("yue");
       // activity_yue.setText(yue);
        loadData();
    }

    private void loadData() {
        User user = TyslApp.getInstance().getUser();
        String id = user.getUserId();
        String token = user.getAccessToken();
        long time_stamp = System.currentTimeMillis();
        OkGo.<BaseResponse<ShejiaoBean>>post(Constants.BASE_URL + Constants.ASSETS_INDEX)//
                .tag(this)
                .params("user_id", user.getUserId())
                .execute(new JsonCallback<BaseResponse<ShejiaoBean>>(Constants.WITH_TOKEN,Constants.ASSETS_INDEX) {
                    @Override
                    public void onSuccess(Response<BaseResponse<ShejiaoBean>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;
                        if(body.isState()){
                            ShejiaoBean shejiaoBean  = (ShejiaoBean) body.getData();
                           // shejiao_lian.setText(shejiaoBean.getIntegral());
                            activity_yue.setText(shejiaoBean.getMoney());
                         //   shejiao_money.setText("￥"+shejiaoBean.getMoney());
                        }

                    }
                });




    }


    //余额明细
    @OnClick(R.id.activity_yue_mingxi)
    public void actionYueMingxi(View v) {
        startActivity(CashDetalisActivity.class);
    }
//账户充值
    @OnClick(R.id.activity_yue_money)
    public void actionZhanghuchongzhi(View v) {
        startActivity(PayActivity.class);
    }



}
