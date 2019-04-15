package com.wokun.tysl.asset.ui.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.itheima.view.BridgeWebView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shantoo.widget.toolbar.WidgetBar;
import com.shantoo.widget.view.ProgressWebView;
import com.wokun.tysl.R;
import com.wokun.tysl.TyslApp;
import com.wokun.tysl.base.BaseBindingActivity;
import com.wokun.tysl.callback.JsonCallback;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.model.bean.SingleParam;
import com.wokun.tysl.model.bean.User;
import com.wokun.tysl.model.response.BaseResponse;
import com.wokun.tysl.mypersonmoney.ui.MylastmoneyActivity;
import com.wokun.tysl.mypersonmoney.ui.MyyueActivity;
import com.wokun.tysl.ucenter.bean.ShejiaoBean;

import butterknife.BindView;
import butterknife.OnClick;

import static com.lzy.okgo.utils.HttpUtils.runOnUiThread;

/**
 * 云链健康
 * Created by Administrator on 2018\11\19 0019.
 */

public class AssertDsytActivity extends BaseBindingActivity {
    @BindView(R.id.ivLD_matchTip)ImageView ivLDMatchTip;
    @BindView(R.id.dsyt_yue)TextView dsytYue;
    @BindView(R.id.dsyt_mymoney)TextView dsytMymoney;


    @Override
    public int createView() {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        return R.layout.activity_dsty;
    }

    @Override
    public WidgetBar createToolBar() {
        mWidgetBar.setVisibility(View.GONE);
        return mWidgetBar;
    }

    @Override
    public void init() {
      //  showCustomizeDialog();
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                //  Initialize SharedPreferences
                SharedPreferences getPrefs = PreferenceManager.getDefaultSharedPreferences(AssertDsytActivity.this);
                //  Create a new boolean and preference and set it to truefirstAgree
                boolean isFirstStart = getPrefs.getBoolean("firstAgreen9", true);
                Log.e("isFirstStart", isFirstStart + "");
                //  If the activity has never started before...
                if (isFirstStart) {
                    Log.e("isFirstStart进来了2", "进来了2");
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(100);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        showCustomizeDialog();
                                    }
                                });

                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                    SharedPreferences.Editor e = getPrefs.edit();
                    e.putBoolean("firstAgreen9", false);
                    e.apply();
                } else {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(100);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        //    原来交易界面 AssetIndexActivity.class
/*
                                        final Intent i = new Intent(AssertDsytActivity.this, AssertDsytActivity.class);
                                        runOnUiThread(new Runnable() {
                                            @Override public void run() {
                                                startActivity(i);
                                            }
                                        });*/
                                    }
                                });
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                }
            }
        });
        t.start();

        Animation rotate = AnimationUtils.loadAnimation(this,R.anim.ld_rotate);
        ivLDMatchTip.startAnimation(rotate);

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

                            if(shejiaoBean.getIntegral()==null){
                                dsytYue.setText("0");
                            }else {
                            dsytYue.setText(shejiaoBean.getIntegral());}
                            if(shejiaoBean.getIntegral()==null){
                                dsytMymoney.setText("￥0");
                            }else {
                                dsytMymoney.setText("￥"+shejiaoBean.getMoney());}


                        }

                    }
                });


    }

    /**
     * 返回
     */
    @OnClick(R.id.back_white)
    public void actionBack() {
        finish();
    }

    /**
     * 交易大厅
     */
    @OnClick(R.id.dsyt_change)
    public void actionChange() {
        startActivity(AssetTransactionActivity.class);

    }
    /**
     * 我要发布
     */
    @OnClick(R.id.dsyt_myfabu)
    public void actionMyfabu() {
        startActivity(AssetReleaseActivity.class);
    }
    /**
     * 交易记录
     */
    @OnClick(R.id.dsyt_jilv)
    public void actionJilv() {
        startActivity(AssetMyReleaseRecordActivity.class);
    }
    /**
     * 交易规则
     * dsyt_rule
     */
    @OnClick(R.id.dsyt_rule)
    public void actionRule() {
        startActivity(AssetRulesActivity.class);
    }
    /**
     * 达事余额
     *
     */
    @OnClick(R.id.dsyt_yue)
    public void actionDsytyue() {
        boolean isNeedLogin = TyslApp.getInstance().isLogin();
        startActivity(MylastmoneyActivity.class, !isNeedLogin);
    }

    /**
     * 我的钱包
     *
     */
    @OnClick(R.id.dsyt_findyue)
    public void actionDsytfindyue(){boolean isNeedLogin = TyslApp.getInstance().isLogin();
    startActivity(MyyueActivity.class, !isNeedLogin);
    }





    /**
     * 发现余额
     * dsyt_findyue
     */
    @OnClick(R.id.dsyt_findyue)
    public void actionFindyue() {
        startActivity(AssertDsytfindmoney.class);
    }

    /**
     * 增值护照
     * dsyt_findyue
     */
    @OnClick(R.id.dsyt_zengzhi)
    public void actionAddhuZhao() {
        startActivity(AssertDsytAddmoney.class);
    }




    /**
     * 置换中心
     */
    @OnClick(R.id.dsyt_change_center)
    public void actionChangecenter() {

        startActivity(AssetIndexActivity.class);

    }


    private void showCustomizeDialog() {
    Log.e("isFirstStart进来了2", "进来了21");
        final AlertDialog.Builder customizeDialog = new AlertDialog.Builder(AssertDsytActivity.this);
        final View dialogView = LayoutInflater.from(AssertDsytActivity.this)
                .inflate(R.layout.dialog_xieyi, null);
        TextView del_sure = (TextView) dialogView.findViewById(R.id.del_sure);

       final BridgeWebView bridgeWebView1 = (BridgeWebView) dialogView.findViewById(R.id.bridge_web_view);
        OkGo.<BaseResponse<SingleParam>>post(Constants.BASE_URL + Constants.INTEGRAL_XIEYI)
                .tag(this)
                .execute(new JsonCallback<BaseResponse<SingleParam>>() {
                    @Override
                    public void onSuccess(Response<BaseResponse<SingleParam>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;
                        SingleParam data = (SingleParam) body.getData();
                        if(data==null)return;
                        bridgeWebView1.loadUrl(data.getUrl());
                    }
                });
        customizeDialog.setView(dialogView);
        customizeDialog.setCancelable(false);
        final AlertDialog s = customizeDialog.show();
        del_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s.dismiss();
            }
        });


    }

}
