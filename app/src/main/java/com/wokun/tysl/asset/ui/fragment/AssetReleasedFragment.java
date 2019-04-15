package com.wokun.tysl.asset.ui.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shantoo.widget.toast.RxToast;
import com.wokun.tysl.R;
import com.wokun.tysl.asset.bean.AssetsReleaseInOrOut;
import com.wokun.tysl.base.BaseFragment;
import com.wokun.tysl.callback.DialogCallback;
import com.wokun.tysl.callback.JsonCallback;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.model.response.BaseResponse;
import com.wokun.tysl.utils.DecimalUtil;

import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 我的发布记录数据
 */
public class AssetReleasedFragment extends BaseFragment {

    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.tv3)
    TextView tv3;
    @BindView(R.id.tv4)
    TextView tv4;

    @BindView(R.id.tv5)
    TextView tv5;//可交易资产或可用余额
    @BindView(R.id.tv6)
    TextView tv6;//今日参考价

    @BindView(R.id.et1)
    EditText et1;//单价
    @BindView(R.id.et2)
    EditText et2;//数量

    @BindView(R.id.action_assets_release)
    TextView actionAssetsRelease;

    private int state;
    private double money, canUseNum;

    /**
     * 传入需要的参数，设置给arguments
     */
    public static AssetReleasedFragment newInstance(int argument) {
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.STATE, argument);
        AssetReleasedFragment contentFragment = new AssetReleasedFragment();
        contentFragment.setArguments(bundle);
        return contentFragment;
    }

    @Override
    public int createView() {
        return R.layout.fragment_released_for_sale;
    }

    @Override
    public void initViews() {
        state = getArguments().getInt(Constants.STATE);
        if (state == 1) {//买进
            tv1.setText("可用余额");
            tv3.setText("买入单价");
            tv4.setText("买入数量");
            et1.setHint("请填写买入单价（单位：元）");
            et2.setHint("请填写买入数量");
            actionAssetsRelease.setText("买进");
        } else if (state == 2) {//卖出
            tv1.setText("可交易资产");
            tv3.setText("卖出单价");
            tv4.setText("卖出数量");
            et1.setHint("请填写卖出单价（单位：元）");
            et2.setHint("请填写卖出数量");
            actionAssetsRelease.setText("卖出");
        }
    }

    @Override
    public void loadData() {
        OkGo.<BaseResponse<AssetsReleaseInOrOut>>post(Constants.BASE_URL + Constants.ASSETS_RELEASE_URL)
                .tag(this)
                .params(Constants.TYPE, state)
                .execute(new JsonCallback<BaseResponse<AssetsReleaseInOrOut>>(Constants.WITH_TOKEN, Constants.ASSETS_RELEASE_URL) {
                    @Override
                    public void onSuccess(Response<BaseResponse<AssetsReleaseInOrOut>> response) {
                        BaseResponse body = response.body();
                        if (body == null) return;
                        if (body.isState()) {
                            AssetsReleaseInOrOut data = (AssetsReleaseInOrOut) body.getData();
                            if (state == 1) {//买进
                                money = data.getMoney();
                                tv5.setText(String.valueOf(money));//初始化可用余额
                            } else if (state == 2) {//卖出
                                canUseNum = Double.valueOf(data.getCanUseNum());
                                tv5.setText(data.getCanUseNum());//初始化可交易资产
                            }
                            tv6.setText(data.getTodayPrice());//初始化今日参考价
                        }
                    }
                });
    }

    @OnClick(R.id.action_assets_release)
    public void actionAssetsRelease() {
        String can_use = tv5.getText().toString().trim();
        String unit_price = et1.getText().toString().trim();//单价
        String numbers = et2.getText().toString().trim();//买卖数量


        if (state == 1) {//买进
            if (TextUtils.isEmpty(unit_price)) {
                RxToast.showShort("请填写买入单价");
                return;
            }
            if (TextUtils.isEmpty(numbers)) {
                RxToast.showShort("请填写买入数量");
                return;
            }
            double payPrice = DecimalUtil.mul(Double.valueOf(unit_price), Integer.valueOf(numbers));
            if (money < payPrice) {
                RxToast.showShort("可用余额不足");
                return;
            }
        } else if (state == 2) {//卖出
            if (TextUtils.isEmpty(unit_price)) {
                RxToast.showShort("请填写卖出单价");
                return;
            }
            if (TextUtils.isEmpty(numbers)) {
                RxToast.showShort("请填写卖出数量");
                return;
            }

            if (Pattern.compile("^[-\\+]?[\\d]*$").matcher(numbers).matches() || Pattern.compile("^[-\\+]?[.\\d]*$").matcher(numbers).matches()) {
                if (Double.valueOf(numbers) > canUseNum) {
                    RxToast.showShort("剩余可用数量不足");
                    return;
                }
            }
        }

        OkGo.<BaseResponse<Object>>post(Constants.BASE_URL + Constants.ASSETS_DO_RELEASE_URL)
                .tag(this)
                .params(Constants.TYPE, state)
                .params(Constants.CAN_USE, can_use)
                .params(Constants.UNIT_PRICE, unit_price)
                .params(Constants.NUMBERS, numbers)
                .execute(new DialogCallback<BaseResponse<Object>>(getActivity(), Constants.WITH_TOKEN, Constants.ASSETS_DO_RELEASE_URL) {
                    @Override
                    public void onSuccess(Response<BaseResponse<Object>> response) {
                        BaseResponse body = response.body();
                        if (body == null) return;
                        if (body.isState()) {
                            RxToast.showShort(body.getMsg());
                            loadData();
                        }
                    }
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        OkGo.getInstance().cancelTag(this);
    }
}