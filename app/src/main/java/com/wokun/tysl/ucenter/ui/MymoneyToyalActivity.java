package com.wokun.tysl.ucenter.ui;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.tysl.R;
import com.wokun.tysl.TyslApp;
import com.wokun.tysl.asset.ui.activity.AssetMyDealActivity;
import com.wokun.tysl.asset.ui.activity.AssetMyReleaseRecordActivity;
import com.wokun.tysl.asset.ui.activity.AssetReleaseActivity;
import com.wokun.tysl.asset.ui.activity.AssetTransactionActivity;
import com.wokun.tysl.base.BaseBindingActivity;
import com.wokun.tysl.callback.JsonCallback;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.model.bean.User;
import com.wokun.tysl.model.response.BaseResponse;
import com.wokun.tysl.myfabu.ui.MyfabuAcivity;
import com.wokun.tysl.mypersonmoney.ui.MylastmoneyActivity;
import com.wokun.tysl.mypersonmoney.ui.MyyueActivity;
import com.wokun.tysl.ucenter.bean.ShejiaoBean;
import com.wokun.tysl.utils.SignUtil;
import com.wokun.tysl.zichanchange.ui.MyzichanChange;

import butterknife.BindColor;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/7/5/005.
 */

public class MymoneyToyalActivity  extends BaseBindingActivity {

  //  @BindString(R.string.tysl_edit_usermoney)String title;
  //  @BindColor(R.color.colorWhite)int color;
    @BindView(R.id.my_zichan_zhihuan)
    RelativeLayout my_zichan_zhihuan; //资产置换
    @BindView(R.id.shejiao_lian)
    TextView shejiao_lian;
    @BindView(R.id.ic_fanhui)
    ImageView ic_fanhui;

    @BindView(R.id.shejiao_money)
    TextView shejiao_money;
    @BindView(R.id.my_fabu)
    RelativeLayout myFabu; //我的发布
    @BindView(R.id.my_personmoeney)
    RelativeLayout myPersonmoeneyabu; //我的余额
    @Override
    public int createView() {
        return R.layout.activity_center_mymoney;
    }

 @Override
    public WidgetBar createToolBar() {
           mWidgetBar.setVisibility(View.GONE);
    /*       mWidgetBar.setMenuTextColor(color);
           mWidgetBar.setWidgetBarTitleTextColor(color);
           mWidgetBar.setWidgetBarTitle(title);

           mWidgetBar.setBackgroundColor(getResources().getColor(R.color.colorF83139));*/

           return mWidgetBar;
    }

    @Override
    public void init() {
              loadData();
             ic_fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
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
                                    shejiao_lian.setText(shejiaoBean.getIntegral());
                                    shejiao_money.setText("￥"+shejiaoBean.getMoney());
                        }

                    }
                });




    }

    /**资产置换*/
    @OnClick(R.id.my_zichan_zhihuan)
    public void actionZiChanChange(View v) {
        startActivity(MyzichanChange.class);
    }
    /**我的发布*/
    @OnClick(R.id.my_fabu)
    public void actionFabu(View v) {
        startActivity(AssetReleaseActivity.class);
    }
    /**发布记录*/
    @OnClick(R.id.my_fabu_jilu)
    public void actionFabujilu(View v) {
        startActivity(AssetMyReleaseRecordActivity.class);
    }
    /**交易大厅*/
    @OnClick(R.id.jiaoyi_dating)
    public void JiaoyiDating(View v) {
        startActivity(AssetTransactionActivity.class);
    }

    /**交易记录*/
    @OnClick(R.id.jiaoyijilu)
    public void JiaoyiJilu(View v) {
        startActivity(AssetMyDealActivity.class);
    }

    /**我的余额*/
    @OnClick(R.id.my_personmoeney)
    public void actionPersonMoney(View v) {
        Intent intent = new Intent(MymoneyToyalActivity.this, MyyueActivity.class);

        intent.putExtra("yue",shejiao_money.getText());

        startActivity(intent);
    }
     /**社交链明细*/

    @OnClick(R.id.shejiaolian_mingxi)
    public void actionShejiaolian(View v) {
        startActivity(MylastmoneyActivity.class);
    }

}
