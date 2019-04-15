package com.wokun.tysl.huiyuantotal.ui;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shantoo.widget.toast.RxToast;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.tysl.R;
import com.wokun.tysl.bankaccount.ui.BankTestActivity2;
import com.wokun.tysl.base.BaseBindingActivity;
import com.wokun.tysl.callback.DialogCallback;
import com.wokun.tysl.callback.JsonCallback;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.huiyuantotal.adapter.CardListadapter;
import com.wokun.tysl.huiyuantotal.bean.CardListBean;
import com.wokun.tysl.huiyuantotal.bean.CardListResponse;
import com.wokun.tysl.huiyuantotal.bean.WithDrawResponseBean;
import com.wokun.tysl.model.response.BaseResponse;
import com.wokun.tysl.smartretail.bean.StoreManageBean;

import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/7/26/026.
 */

public class TixianMoneyActivity extends BaseBindingActivity {
    @BindString(R.string.tysl_user_money)String title;
    @BindView(R.id.zhihui_add_bangdin) LinearLayout addbangdin;
    @BindView(R.id.zhihui_add_xianshi) LinearLayout addxianshi;
    @BindView(R.id.zhihui_add_tixian) LinearLayout addtixian;
    @BindView(R.id.cd_bank)
    TextView cd_bank;
    @BindView(R.id.cd_number)
    TextView cd_number;
    @BindView(R.id.keti_money)
    TextView keti_money;
    @BindView(R.id.cd_name)
    TextView cd_name;
    @BindView(R.id.comit)
    TextView comit;
    @BindView(R.id.get_money)
    EditText get_money;

    private      String keti;
    private   String mobile;
    private String b_id;

    @Override
    public int createView() {
        return R.layout.activity_zhihui_money;
    }

    @Override
    public WidgetBar createToolBar() {
        return mWidgetBar.setWidgetBarTitle(title);
    }

    @Override
    public void init() {
       loadData();
        comit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadCardcomit();
            }

            private void loadCardcomit() {

                int getMoney = Integer.parseInt(get_money.getText().toString().trim());

                if (getMoney<1){
                    RxToast.showShort("提现金额大于1");
                    return;
                }



                if(TextUtils.isEmpty(b_id)){
                          RxToast.showShort("银行卡不能为空");
                          return;
                      }



                OkGo.<BaseResponse<Object>>post(Constants.BASE_URL + Constants.RETAIL_DO_WITHDRAW)
                        .tag(this)
                        .params("bank_id",b_id)
                        .params("money",getMoney)
                        .execute(new DialogCallback<BaseResponse<Object>>(TixianMoneyActivity.this,Constants.WITH_TOKEN,Constants.RETAIL_DO_WITHDRAW) {
                            @Override
                            public void onSuccess(Response<BaseResponse<Object>> response) {
                                BaseResponse body = response.body();
                                if(body == null)return;
                                if (body.isState()) {
                                    RxToast.showShort(body.getMsg());

                                }
                            }
                        });













            }
        });
    }



    private void loadData() {

        OkGo.<BaseResponse<WithDrawResponseBean>>post(Constants.BASE_URL + Constants.RETAIL_WITHDRAW)
                .tag(this)
                .execute(new DialogCallback<BaseResponse<WithDrawResponseBean>>(this,Constants.WITH_TOKEN,Constants.RETAIL_WITHDRAW) {
                    @Override
                    public void onSuccess(Response<BaseResponse<WithDrawResponseBean>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;
                        if (body.isState()) {
                            RxToast.showShort(body.getMsg());

                            WithDrawResponseBean data = (WithDrawResponseBean) body.getData();
                               keti = data.getKeti();
                              mobile = data.getMobile();
                              keti_money.setText(keti);
                              cd_name.setText("账户提现:"+mobile);
                            b_id = data.getDefault_bank().getB_id();
                            Log.e("进来来dsa","进来来dsa"+ data.toString());
                            if(data.getHave()==1){
                                 addbangdin.setVisibility(View.GONE);
                                 addxianshi.setVisibility(View.VISIBLE);
                                   cd_bank.setText(data.getDefault_bank().getBank_name());
                                   cd_number.setText(data.getDefault_bank().getAccount_code());



                            }else{
                                addbangdin.setVisibility(View.VISIBLE);
                            }
                        }
                    }
                });


    }

    @OnClick(R.id.zhihui_card_bangdin)
    public void CardBangdi() {
        Log.e("点击进来","点击进来1");
    /*   // startActivity(TixianMoneyActivity2.class);
     //   LinearLayout addbangdin = (LinearLayout) findViewById(R.id.zhihui_add_bangdin);
         addbangdin.setVisibility(View.GONE);
       //  addxianshi = (LinearLayout) findViewById(R.id.zhihui_add_xianshi);
        addxianshi.setVisibility(View.VISIBLE);
     //   LinearLayout addtixian = (LinearLayout) findViewById(R.id. zhihui_add_tixian);
      //  addtixian.setVisibility(View.VISIBLE);
        addxianshi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("点击进来","点击进来2");
                startActivity(TixianMoneyActivity2.class);
            }
        });*/
     startActivity(BankTestActivity2.class);

    }

    @OnClick(R.id.zhihui_add_xianshi)
    public void TixianCardList() {
        addxianshi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("点击进来","点击进来2");
                startActivity(TixianMoneyActivity2.class);
            }
        });
    }

}
