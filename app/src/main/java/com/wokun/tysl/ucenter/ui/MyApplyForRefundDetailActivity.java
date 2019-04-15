package com.wokun.tysl.ucenter.ui;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.tysl.R;
import com.wokun.tysl.base.BaseBindingActivity;
import com.wokun.tysl.callback.JsonCallback;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.model.bean.RefundDetail;
import com.wokun.tysl.model.response.BaseResponse;

import butterknife.BindView;

//我的申请退款详情
public class MyApplyForRefundDetailActivity extends BaseBindingActivity {

    @BindView(R.id.tv_refund_amount)
    TextView tvRefundAmount; //退款金额
    @BindView(R.id.tv_account)
    TextView tvAccount;
    @BindView(R.id.tv_reason)
    TextView tvReason;
    @BindView(R.id.tv_create_time)
    TextView tvCreateTime;
    @BindView(R.id.tv_cancle_time)
    TextView tvCancleTime;
    @BindView(R.id.tv_state)
    TextView tvState;
    @BindView(R.id.tv_tips)
    TextView tvTips;

    @Override
    public int createView() {
        return R.layout.activity_myapplyfor_refund_detail;
    }

    @Override
    public WidgetBar createToolBar() {
        return mWidgetBar.setWidgetBarTitle("退款详情");
    }

    @Override
    public void init() {
        loadData(getIntent().getStringExtra(Constants.ORDER_ID));
    }

    private void loadData(String order_id) {
        mMultipleStatusView.showLoading();
        OkGo.<BaseResponse<RefundDetail>>post(Constants.BASE_URL + Constants.DIETITIAN_REFUND_DETAIL_URL)
                .tag(this)
                .params(Constants.ORDER_ID, order_id)
                .execute(new JsonCallback<BaseResponse<RefundDetail>>(Constants.WITH_TOKEN, Constants.DIETITIAN_REFUND_DETAIL_URL) {
                    @Override
                    public void onSuccess(Response<BaseResponse<RefundDetail>> response) {
                        BaseResponse body = response.body();
                        if (body == null) return;
                        if (body.isState()) {
                            mMultipleStatusView.showContent();
                            RefundDetail data = (RefundDetail) body.getData();
                            tvRefundAmount.setText("¥" + data.getRefund_amount() + "");
                            tvAccount.setText(data.getAccount());
                            tvReason.setText(data.getReason());
                            tvCreateTime.setText(data.getCreate_time());
                            tvCancleTime.setText(data.getCancle_time());
                            // state 退款状态0未退款1已退款2退款中
                            String state = "";
                            if (0 == data.getState()) {
                                state = "未退款";
                            } else if (1 == data.getState()) {
                                state = "已退款";
                            } else if (2 == data.getState()) {
                                state = "退款中";
                            }
                            tvState.setText(state);
                            if (!TextUtils.isEmpty(data.getTips())) {
                                tvTips.setText(data.getTips());
                                tvTips.setVisibility(View.VISIBLE);
                            }
                        }
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OkGo.getInstance().cancelTag(this);
    }
}