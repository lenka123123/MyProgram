package com.wokun.tysl.earnings.ui;

import android.view.View;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.tysl.R;
import com.wokun.tysl.base.BaseBindingActivity;
import com.wokun.tysl.callback.JsonCallback;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.earnings.bean.AccountList;
import com.wokun.tysl.model.bean.SingleParam;
import com.wokun.tysl.model.response.BaseResponse;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;

//我的收益
public class MyEarningsActivity extends BaseBindingActivity {

    @BindString(R.string.tysl_user_my_earnings) String title;
    @BindView(R.id.balance) TextView tvBalance;

    private boolean hasAccount = false;

    @Override
    public int createView() {
        return R.layout.activity_my_earnings;
    }

    @Override
    public WidgetBar createToolBar() {
        return mWidgetBar
                .setWidgetBarTitle(title);
        /*        .setMenu("账户设置",null)
                .setOnMenuClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (hasAccount) {
                            startActivity(AccountSettingsActivity.class);
                        } else {
                            startActivity(SelectAccountTypeActivity.class);
                        }
                    }
                },null);*/
    }

    @Override
    public void init() {
        loadData();
        getAccountList();
    }

    private void loadData() {
        OkGo.<BaseResponse<SingleParam>>post(Constants.BASE_URL + Constants.DIETITIAN_MY_PROFIT_URL)
                .execute(new JsonCallback<BaseResponse<SingleParam>>(Constants.WITH_TOKEN,Constants.DIETITIAN_MY_PROFIT_URL) {
                    @Override
                    public void onSuccess(Response<BaseResponse<SingleParam>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;
                        if (body.isState()) {
                            SingleParam data = (SingleParam) body.getData();
                            tvBalance.setText(data.getBalance());
                        }
                    }
                });
    }

    @OnClick({R.id.action_earnings_detail, R.id.action_withdraw_deposit_detail, R.id.action_withdraw_deposit, R.id.action_benefits_that,
            R.id.action_withdraw_deposit_card_list})
    public void action(View view) {
        if(R.id.action_earnings_detail == view.getId()){//收益明细
            startActivity(EarningsDetailedActivity.class);
        }else if(R.id.action_withdraw_deposit_detail == view.getId()){//提现明细
            startActivity(WithdrawDepositDetailActivity.class);
        }else if(R.id.action_withdraw_deposit == view.getId()){//提现
            //判断用户是否已经绑定支付宝或储蓄卡账号，如果已绑定账号则进入账户列表页面
            //如果没有绑定账号，则进入选择账户类型页面
            startActivity(WithdrawDepositActivity.class);
            /*if(hasAccount){
                startActivity(WithdrawDepositActivity.class);
            }else{
                startActivity(SelectAccountTypeActivity.class);
            }*/
        }else if(R.id.action_benefits_that == view.getId()){//收益说明
            startActivity(EarningsExplainActivity.class);
        }
        else if(R.id.action_withdraw_deposit_card_list == view.getId()){//收益说明
            startActivity(AccountListActivity.class);
        }
    }

    public void getAccountList() {
        OkGo.<BaseResponse<AccountList>>post(Constants.BASE_URL + Constants.DIETITIAN_ACCOUNT_LIST_URL)
                .execute(new JsonCallback<BaseResponse<AccountList>>(Constants.WITH_TOKEN,Constants.DIETITIAN_ACCOUNT_LIST_URL) {
                    @Override
                    public void onSuccess(Response<BaseResponse<AccountList>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;
                        if (body.isState()) {
                            AccountList data = (AccountList) body.getData();
                            if (data.getHave() == 1) {//营养师有账号列表
                                hasAccount = true;
                            }
                        }
                    }
                });
    }
}
