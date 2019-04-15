package com.wokun.tysl.earnings.controller;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shantoo.widget.toast.RxToast;
import com.wokun.tysl.TyslApp;
import com.wokun.tysl.callback.JsonCallback;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.earnings.bean.AccountList;
import com.wokun.tysl.earnings.ui.WithdrawDepositActivity;
import com.wokun.tysl.model.response.BaseResponse;
import com.wokun.tysl.model.response.GetBankResponse;
import com.wokun.tysl.utils.CheckUtil;

//提现
public class EarningsMgr {

    private EarningsMgr(){}

    private static class EarningsMgrHolder{
        private static EarningsMgr instance = new EarningsMgr();
    }

    public static EarningsMgr getInstance(){
        return EarningsMgrHolder.instance;
    }

    /**
     * 添加银行卡账户
     *
     * @param account_name 账户姓名
     * @param account_code 银行卡号
     * @param bank_id      银行名称id（银行卡有该参数，支付宝不需要传）
     * @param bank_name    银行名称（银行卡有该参数，支付宝不需要传）
     */
    public void addBankAccount(final Activity activity, String account_name, String account_code, int bank_id, String bank_name) {
        if (TextUtils.isEmpty(account_name) || TextUtils.isEmpty(account_code) ||
                TextUtils.isEmpty(bank_name) || account_code.length() < 16) {
            Toast.makeText(activity, "支付宝账户错误", Toast.LENGTH_SHORT).show();
            return;
        }

        /*User user = TyslApp.getInstance().getUser();
        String url = Constants.BASE_URL + Constants.UCENTER_GET_USER_INFO_URL;
        long timeStamp = System.currentTimeMillis();
        String sign = SignUtil.getSign(url, user.getUserId(), user.getAccessToken(), timeStamp);

        Request request = ItheimaHttp.newPostRequest(Constants.DIETITIAN_ADD_ACCOUNT_URL);
        request.putParams("user_id", user.getUserId())
                .putParams("time_stamp", timeStamp)
                .putParams("sign", sign)
                .putParams("account_name", account_name)
                .putParams("bank_id", bank_id)
                .putParams("bank_name", bank_name)
                .putParams("account_code", account_code);
        ItheimaHttp.send(request, new BaseHttpResponseListener<BaseListResponse<Object>>() {
            @Override
            public void onResponse(BaseListResponse<Object> response) {
                if (response.isState()) {
                    //AppManager.getAppManager().finishActivity(activity);
                    activity.finish();
                    Intent intent = new Intent();
                    intent.setClass(activity, WithdrawDepositActivity.class);
                    activity.startActivity(intent);
                }
                Toast.makeText(TyslApp.getContext(), response.getMsg(), Toast.LENGTH_SHORT).show();
            }
        });*/

        OkGo.<BaseResponse<Object>>post(Constants.BASE_URL + Constants.DIETITIAN_ADD_ACCOUNT_URL)//
                .tag(this)
                .params("account_name", account_name)
                .params("bank_id", bank_id)
                .params("bank_name", bank_name)
                .params("account_code", account_code)
                .execute(new JsonCallback<BaseResponse<Object>>(Constants.WITH_TOKEN,Constants.DIETITIAN_ADD_ACCOUNT_URL) {
                    @Override
                    public void onSuccess(Response<BaseResponse<Object>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;

                        if (body.isState()) {
                            //AppManager.getAppManager().finishActivity(activity);
                            activity.finish();
                            Intent intent = new Intent();
                            intent.setClass(activity, WithdrawDepositActivity.class);
                            activity.startActivity(intent);
                        }
                        RxToast.showShort(body.getMsg());
                    }
                });
    }

    /**
     * 添加支付宝账户
     *
     * @param account_name 账户姓名
     * @param account_code 支付宝账号
     */
    public void addAlipayAccount(final Activity activity, String account_name, String account_code) {
        if (!CheckUtil.isEmailLegal(account_code) && !CheckUtil.isChinaPhoneLegal(account_code)) {
            Toast.makeText(activity, "请输入有效的支付宝账号", Toast.LENGTH_SHORT).show();
            return;
        }

        OkGo.<BaseResponse<Object>>post(Constants.BASE_URL + Constants.DIETITIAN_ADD_ACCOUNT_URL)//
                .tag(this)
                .params(Constants.ACCOUNT_NAME, account_name)
                .params(Constants.ACCOUNT_CODE, account_code)
                .execute(new JsonCallback<BaseResponse<Object>>(Constants.WITH_TOKEN,Constants.DIETITIAN_ADD_ACCOUNT_URL) {
                    @Override
                    public void onSuccess(Response<BaseResponse<Object>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;

                        if (body.isState()) {
                            activity.finish();
                            Intent intent = new Intent();
                            intent.setClass(activity, WithdrawDepositActivity.class);
                            activity.startActivity(intent);
                        }
                        RxToast.showShort(body.getMsg());
                    }
                });
    }

    /**
     * 获取银行数据
     */
    public void getBank(final BaseQuickAdapter adapter) {
        /*Request request = ItheimaHttp.newPostRequest(Constants.OTHER_GET_BANK_URL);
        ItheimaHttp.send(request, new BaseHttpResponseListener<BaseResponse<GetBankResponse>>() {
            @Override
            public void onResponse(BaseResponse<GetBankResponse> response) {
                if (response.isState()) {
                    adapter.setNewData(response.getData().getBankList());
                }
            }
        });*/

        OkGo.<BaseResponse<GetBankResponse>>post(Constants.BASE_URL + Constants.OTHER_GET_BANK_URL)//
                .tag(this)
                .execute(new JsonCallback<BaseResponse<GetBankResponse>>() {
                    @Override
                    public void onSuccess(Response<BaseResponse<GetBankResponse>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;
                        if (body.isState()) {
                            GetBankResponse data = (GetBankResponse) body.getData();
                            adapter.setNewData(data.getBankList());
                        }
                    }
                });
    }

    /**
     * 我的收益账号列表
     */
    public void getAccountList(final BaseQuickAdapter adapter) {
        /*ItheimaHttp.send(RequestBuilder.with().buildTokenRequest(Constants.DIETITIAN_ACCOUNT_LIST_URL),
                new BaseHttpResponseListener<BaseResponse<AccountList>>() {
                    @Override
                    public void onResponse(BaseResponse<AccountList> response) {
                        if (response.isState()) {
                            if (response.getData().getHave() == 1) {//营养师有账号列表
                                adapter.setNewData(response.getData().getAccount_list());
                            }
                        }
                    }
                });*/

        OkGo.<BaseResponse<AccountList>>post(Constants.BASE_URL + Constants.DIETITIAN_ACCOUNT_LIST_URL)//
                .tag(this)
                .execute(new JsonCallback<BaseResponse<AccountList>>(Constants.WITH_TOKEN,Constants.DIETITIAN_ACCOUNT_LIST_URL) {
                    @Override
                    public void onSuccess(Response<BaseResponse<AccountList>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;
                        if (body.isState()) {
                            AccountList data = (AccountList) body.getData();
                            if (data.getHave() == 1) {//营养师有账号列表
                                adapter.setNewData(data.getAccount_list());
                            }
                        }
                    }
                });
    }

    /**
     * 提现接口
     *
     * @param accountId    结算账号id
     * @param withdrawCash 提现金额整数
     */
    public void dietitianWithdraw(int accountId, int withdrawCash) {
        if (withdrawCash <= 0) {
            Toast.makeText(TyslApp.getContext(), "提现金额必须大于0", Toast.LENGTH_SHORT).show();
            return;
        }
        OkGo.<BaseResponse<Object>>post(Constants.BASE_URL + Constants.DIETITIAN_WITHDRAW_URL)//
                .tag(this)
                .params(Constants.ACCOUNT_ID, accountId)
                .params(Constants.WITHDRAW_CASH, withdrawCash)
                .execute(new JsonCallback<BaseResponse<Object>>(Constants.WITH_TOKEN,Constants.DIETITIAN_WITHDRAW_URL) {
                    @Override
                    public void onSuccess(Response<BaseResponse<Object>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;
                        RxToast.showShort(body.getMsg());
                    }
                });
    }
}
