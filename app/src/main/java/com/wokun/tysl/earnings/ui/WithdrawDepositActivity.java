package com.wokun.tysl.earnings.ui;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shantoo.common.utils.AppManager;
import com.shantoo.common.utils.UIUtil;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.tysl.R;
import com.wokun.tysl.base.BaseTransparencyBindingActivity;
import com.wokun.tysl.callback.JsonCallback;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.earnings.bean.WithdrawCash;
import com.wokun.tysl.earnings.controller.EarningsMgr;
import com.wokun.tysl.model.response.BaseResponse;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;

//提现
public class WithdrawDepositActivity extends BaseTransparencyBindingActivity {

    @BindString(R.string.tysl_withdraw_deposit)
    String title;
    @BindString(R.string.tysl_add_withdraw_deposit_account)
    String tyslAddWithdrawDepositAccount;
    @BindString(R.string.tysl_immediate_withdrawal)
    String tyslImmediateWithdrawal;

    @BindView(R.id.toolbar)
    WidgetBar widgetBar;

    @BindView(R.id.ic_account_image)
    ImageView icAccountImage;
    @BindView(R.id.bank_name)
    TextView bankName;
    @BindView(R.id.bank_last_number)
    TextView bankLastNumber;
    @BindView(R.id.balance)
    TextView tvBalance;

    @BindView(R.id.image1)
    ImageView ivImage1;
    @BindView(R.id.image2)
    ImageView ivImage2;
    @BindView(R.id.image3)
    ImageView ivImage3;
    @BindView(R.id.cxk)
    TextView tvCxk;

    @BindView(R.id.edit_money)
    EditText editMoney;

    @BindView(R.id.action_add_account)
    RelativeLayout rlAddAccount;
    @BindView(R.id.action_select_account)
    RelativeLayout rlSelectAccount;

    @BindView(R.id.action_immediately_withdrawal)
    TextView actionImmediatelyWithdrawal;

    @BindView(R.id.error_message)
    TextView errorMessage;

    private boolean hasAccount = false;
    private int account_id;
    private String balance;

    @Override
    public int createView() {
        return R.layout.activity_withdraw_deposit;
    }

    @Override
    public WidgetBar createToolBar() {
        return widgetBar.setWidgetBarTitle(title);
    }

    @Override
    public void init() {
        super.init();
        loadData();
    }

    @OnClick({R.id.action_add_account, R.id.action_select_account, R.id.action_clear_money, R.id.action_immediately_withdrawal})
    public void action(View v) {
        if (R.id.action_add_account == v.getId()) {//添加账户，进入选择账户类型页面
            startActivity(SelectAccountTypeActivity.class);
            AppManager.getAppManager().finishActivity(this);
        } else if (R.id.action_select_account == v.getId()) {//已有账户，进入账户列表页面
            Intent intent = new Intent();
            intent.setClass(this, AccountListActivity.class);
            startActivityForResult(intent, 99);
        } else if (R.id.action_clear_money == v.getId()) {//清空提现金额
            editMoney.setText("");
        } else if (R.id.action_immediately_withdrawal == v.getId()) {//立即提现
            String str = editMoney.getText().toString().trim();
            if (TextUtils.isEmpty(str)) {
                Toast.makeText(this, "请输入提现金额", Toast.LENGTH_SHORT).show();
                return;
            }
            int withdraw_cash = Integer.valueOf(str);//输入金额

            if (withdraw_cash > Integer.valueOf(balance)) {
                errorMessage.setVisibility(View.VISIBLE);
                actionImmediatelyWithdrawal.setBackgroundColor(UIUtil.getColor(R.color.colorDriver));
                actionImmediatelyWithdrawal.setEnabled(false);
            }else{
                errorMessage.setVisibility(View.GONE);
                actionImmediatelyWithdrawal.setEnabled(true);
                actionImmediatelyWithdrawal.setBackgroundColor(UIUtil.getColor(R.color.colorState));
            }

            EarningsMgr.getInstance().dietitianWithdraw(account_id, withdraw_cash);
        }
    }

    public void loadData() {
        OkGo.<BaseResponse<WithdrawCash>>post(Constants.BASE_URL + Constants.DIETITIAN_WITHDRAW_CASH_URL)
                .execute(new JsonCallback<BaseResponse<WithdrawCash>>(Constants.WITH_TOKEN,Constants.DIETITIAN_WITHDRAW_CASH_URL) {
                    @Override
                    public void onSuccess(Response<BaseResponse<WithdrawCash>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;
                        if (body.isState()) {
                            WithdrawCash data = (WithdrawCash) body.getData();
                            if (data.getHave() == 1) {//营养师有账号列表
                                hasAccount = true;
                                if (hasAccount) {//有设置过收益账号
                                    int account_type = data.getAccount_type();
                                    String account_type_name = data.getAccount_type_name();
                                    String account_code = data.getAccount_code();
                                    initData(account_type, account_type_name, account_code);

                                    account_id = data.getAccount_id();

                                    balance = data.getBalance();
                                    tvBalance.setText("可提现金额 " + balance + " 元");
                                    actionImmediatelyWithdrawal.setText(tyslImmediateWithdrawal);
                                    actionImmediatelyWithdrawal.setEnabled(true);
                                    actionImmediatelyWithdrawal.setBackgroundColor(UIUtil.getColor(R.color.colorState));
                                }
                            } else {//没有设置过收益账号
                                rlAddAccount.setVisibility(View.VISIBLE);
                                rlSelectAccount.setVisibility(View.GONE);
                                actionImmediatelyWithdrawal.setText(tyslAddWithdrawDepositAccount);
                                actionImmediatelyWithdrawal.setBackgroundColor(UIUtil.getColor(R.color.colorDriver));
                                actionImmediatelyWithdrawal.setEnabled(false);
                                tvBalance.setText("可提现金额 0 元");
                            }
                        }
                    }
                });
    }

    private void initData(int account_type, String account_type_name, String account_code) {
        rlAddAccount.setVisibility(View.GONE);
        rlSelectAccount.setVisibility(View.VISIBLE);

        if (account_type == 1) {
            icAccountImage.setImageResource(R.drawable.ic_alipay);
            ivImage1.setVisibility(View.GONE);
            ivImage2.setVisibility(View.GONE);
            ivImage3.setVisibility(View.GONE);
            tvCxk.setVisibility(View.GONE);

        } else if (account_type == 2) {
            icAccountImage.setImageResource(R.drawable.ic_bank_card);
            ivImage1.setVisibility(View.VISIBLE);
            ivImage2.setVisibility(View.VISIBLE);
            ivImage3.setVisibility(View.VISIBLE);
            tvCxk.setVisibility(View.VISIBLE);
        }

        bankName.setText(account_type_name);
        bankLastNumber.setText(account_code.replace("*", ""));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (99 == requestCode && 88 == resultCode) {
            int account_type = data.getIntExtra(Constants.ACCOUNT_TYPE, -1);
            String account_type_name = data.getStringExtra(Constants.ACCOUNT_TYPE_NAME);
            String account_code = data.getStringExtra(Constants.ACCOUNT_CODE);
            account_id = data.getIntExtra(Constants.ACCOUNT_ID, -1);
            initData(account_type, account_type_name, account_code);
        }
    }
}
