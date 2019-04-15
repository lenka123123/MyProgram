package com.wokun.tysl.goods.ui.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shantoo.widget.toast.RxToast;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.tysl.R;
import com.wokun.tysl.address.bean.AddressBean;
import com.wokun.tysl.address.ui.AddressListActivity;
import com.wokun.tysl.base.BaseBindingActivity;
import com.wokun.tysl.callback.DialogCallback;
import com.wokun.tysl.callback.JsonCallback;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.goods.adapter.OrderDetailGoodsListAdapter;
import com.wokun.tysl.goods.bean.GoodsOrderBean;
import com.wokun.tysl.goods.bean.OrderDetailBean;
import com.wokun.tysl.goods.controller.GoodsOrderMgr;
import com.wokun.tysl.goods.ui.fragment.GoodsOrderStateAll;
import com.wokun.tysl.model.response.BaseResponse;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;

//商品订单详情页
public class GoodsOrderDetailActivity extends BaseBindingActivity {

    @BindString(R.string.tysl_order_detail)
    String title;

    @BindView(R.id.show_address)
    RelativeLayout hasAddress;
    @BindView(R.id.select_address)
    RelativeLayout selectAddress;

    @BindView(R.id.tv_contacts)
    TextView tvContacts;
    @BindView(R.id.tv_order_placer_tel)
    TextView tvOrderPlacerTel;
    @BindView(R.id.tv_order_placer_address)
    TextView tvOrderPlacerAddress;

    @BindView(R.id.rl_payment_time)
    RelativeLayout rlPaymentTime;
    @BindView(R.id.rl_shipments_time)
    RelativeLayout rlShipmentsTime;
    @BindView(R.id.rl_finish_time)
    RelativeLayout rlFinishTime;

    @BindView(R.id.state_zh)
    TextView tvStateZh;//订单状态
    @BindView(R.id.store_name)
    TextView tvStoreName; //店铺名称
    @BindView(R.id.tv_goods_amount)
    TextView tvGoodsAmount; //商品总价
    @BindView(R.id.tv_shipping_fee)
    TextView tvShippingFee; //邮费
    @BindView(R.id.tv_buyer_leave_message)
    TextView tvBuyerLeaveMessage; //买家留言
    @BindView(R.id.tv_order_sn)
    TextView tvOrderSn; //订单编号
    @BindView(R.id.tv_add_time)
    TextView tvAddTime; //订单生成时间（添加时间）

    @BindView(R.id.tv_shipments_time)
    TextView tv_shipments_time; //发货时间
    @BindView(R.id.tv_finish_time)
    TextView tvFinishTime; //成交时间

    @BindView(R.id.rl_goods_eval_integral)
    RelativeLayout rlGoodsEvalIntegral;
    @BindView(R.id.rl_goods_finish_integral)
    RelativeLayout rlGoodsFinishIntegral;

    @BindView(R.id.tv_goods_eval_integral)
    TextView tvGoodsEvalIntegral; //商品评价送的积分数
    @BindView(R.id.tv_goods_finish_integral)
    TextView tvGoodsFinishIntegral; //商品确认收货送的积分

    @BindView(R.id.rl_action)
    RelativeLayout rlAction;

    @BindView(R.id.action_cancel_order)
    TextView actionCancelOrder; //取消订单
    @BindView(R.id.action_immediate_payment)
    TextView actionImmediatePayment; //立即付款
    @BindView(R.id.action_evaluate)
    TextView actionEvaluate; //评价
    @BindView(R.id.action_check_wl)
    TextView actionCheckWl; //查看物流
    @BindView(R.id.action_confirm_receipt)
    TextView actionConfirmReceipt;//确认收货

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    protected int province_id;
    protected String name,mobile,sAddress;
    private OrderDetailGoodsListAdapter adapter;
    private OrderDetailBean data;

    @Override
    public int createView() {
        return R.layout.activity_goods_order_detail;
    }

    @Override
    public WidgetBar createToolBar() {
        return mWidgetBar.setWidgetBarTitle(title);
    }

    @Override
    public void init() {
        mMultipleStatusView.showLoading();
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new OrderDetailGoodsListAdapter(R.layout.item_goods_order_detail, null);
        mRecyclerView.setAdapter(adapter);
        loadData();
    }

    private void loadData() {
       // showLP();
        OkGo.<BaseResponse<OrderDetailBean>>post(Constants.BASE_URL + Constants.ORDER_DETAIL_URL)
                .tag(this)
                .params(Constants.ORDER_ID, getIntent().getStringExtra(Constants.ORDER_ID))
                .execute(new DialogCallback<BaseResponse<OrderDetailBean>>(this,Constants.WITH_TOKEN,Constants.ORDER_DETAIL_URL) {
                    @Override
                    public void onSuccess(Response<BaseResponse<OrderDetailBean>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;
                        if (body.isState()) {
                            mMultipleStatusView.showContent();
                            data = (OrderDetailBean) body.getData();
                            if(data ==null){return;}
                            AddressBean receiver_info = data.getReceiverInfo();
                            if (receiver_info == null) {//没有收货地址
                                selectAddress.setVisibility(View.VISIBLE);
                                hasAddress.setVisibility(View.GONE);
                            } else {//有收货地址
                                selectAddress.setVisibility(View.GONE);
                                hasAddress.setVisibility(View.VISIBLE);
                                name = receiver_info.getContacts();
                                mobile = receiver_info.getTel();
                                //province_id = receiver_info.getProvince_id();

                                sAddress = receiver_info.getAddress();
                                setAddress(name, mobile, sAddress);
                            }
                            //商品信息
                            adapter.setNewData(data.getGoods());
                            tvStateZh.setText(data.getStateZh());
                            tvStoreName.setText(data.getStoreName());
                            tvGoodsAmount.setText("¥"+ data.getGoodsAmount()+"");
                            tvShippingFee.setText("¥"+ data.getShippingFee()+"");
                            tvBuyerLeaveMessage.setText(data.getBuyerLeaveMessage());
                            tvOrderSn.setText(data.getOrderSn());
                            tvAddTime.setText(data.getAddTime());
                            tvFinishTime.setText(data.getFinishTime());

                            //订单状态 0:全部1:待付款2:待发货3:待收货4:待评价5:已评价
                            if (1 == data.getOrderState()) {//待付款
                                rlAction.setVisibility(View.VISIBLE);
                                actionCancelOrder.setVisibility(View.VISIBLE);
                                actionImmediatePayment.setVisibility(View.VISIBLE);//立即付款
                                rlPaymentTime.setVisibility(View.GONE);
                                rlShipmentsTime.setVisibility(View.GONE);
                                rlFinishTime.setVisibility(View.GONE);
                            } else if (2 == data.getOrderState()) {//待发货
                                rlShipmentsTime.setVisibility(View.GONE);
                                rlFinishTime.setVisibility(View.GONE);
                            } else if (3 == data.getOrderState()) {//待收货
                                rlAction.setVisibility(View.VISIBLE);
                                actionCheckWl.setVisibility(View.VISIBLE);
                                actionConfirmReceipt.setVisibility(View.VISIBLE);
                                rlGoodsFinishIntegral.setVisibility(View.VISIBLE);
                                rlFinishTime.setVisibility(View.GONE);
                            } else if (4 == data.getOrderState()) {//待评价
                                rlAction.setVisibility(View.VISIBLE);
                                actionEvaluate.setVisibility(View.VISIBLE);
                                rlGoodsEvalIntegral.setVisibility(View.VISIBLE);
                            } else if (5 == data.getOrderState()) {//已评价

                            }
                        }
                    }
                });
    }

    @OnClick({R.id.show_address, R.id.select_address, R.id.action_cancel_order, R.id.action_immediate_payment, R.id.action_evaluate, R.id.action_check_wl, R.id.action_confirm_receipt})
    public void action(View v) {
        if (R.id.show_address == v.getId() || R.id.select_address == v.getId()) {//选择收货地址
            Intent intent = new Intent();
            intent.setClass(this, AddressListActivity.class);
            startActivityForResult(intent, 99);
        }else if(R.id.action_cancel_order == v.getId()){//取消订单
          //  Toast.makeText(this, "取消订单", Toast.LENGTH_SHORT).show();

            OkGo.<BaseResponse<Object>>post(Constants.BASE_URL + Constants.ORDER_CANCEL_ORDER_URL)
                    .tag(this)
                    .params(Constants.ORDER_ID,data.getOrderId())
                    .execute(new JsonCallback<BaseResponse<Object>>(Constants.WITH_TOKEN,Constants.ORDER_CANCEL_ORDER_URL) {
                        @Override
                        public void onSuccess(Response<BaseResponse<Object>> response) {
                            BaseResponse body = response.body();
                            if(body == null)return;

                            if(body.isState()){
                            }
                            RxToast.showShort(body.getMsg());
                        }
                    });


        }else if(R.id.action_immediate_payment == v.getId()){//立即付款
            Toast.makeText(this, "立即付款", Toast.LENGTH_SHORT).show();
            //GoodsOrderMgr.getInstance().onImmediatePayment(GoodsOrderStateAll.this,bean);




        }else if(R.id.action_evaluate == v.getId()){//评价
            Intent intent = new Intent();
            intent.putExtra(Constants.DATA,data.getGoods());
            intent.putExtra(Constants.ORDER_ID,data.getOrderId());
            intent.setClass(this,GoodsEvaluateActivity.class);
            startActivity(intent);
        }else if(R.id.action_check_wl == v.getId()){//查看物流
            Toast.makeText(this, "查看物流", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.putExtra("type",data.getShippingInfo().getExpressName());
            intent.putExtra("number",data.getShippingInfo().getExpressNumber());
            intent.setClass(this,CheckWuliuActivity.class);
            startActivity(intent);

        }else if(R.id.action_confirm_receipt == v.getId()){//确认收货
            Toast.makeText(this, "确认收货", Toast.LENGTH_SHORT).show();
         //   GoodsOrderMgr.getInstance().onConfirmReceipt(GoodsOrderStateAll.this,bean);
            OkGo.<BaseResponse<Object>>post(Constants.BASE_URL + Constants.ORDER_CONFIRM_ACCEPT_URL)
                    .tag(this)
                    .params(Constants.ORDER_ID, data.getOrderId())
                    .execute(new JsonCallback<BaseResponse<Object>>(Constants.WITH_TOKEN,Constants.ORDER_CONFIRM_ACCEPT_URL) {
                        @Override
                        public void onSuccess(Response<BaseResponse<Object>> response) {
                            BaseResponse body = response.body();
                            if(body == null)return;

                            if(body.isState()){
                            }
                            RxToast.showShort(body.getMsg());
                        }
                    });



        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 99 && resultCode == 20) {
            name = data.getStringExtra(Constants.CONTACTS);
            mobile = data.getStringExtra(Constants.TEL);
            sAddress = data.getStringExtra(Constants.ADDRESS);
            province_id = data.getIntExtra(Constants.PROVINCE_ID, 0);

            setAddress(name, mobile, sAddress);
            selectAddress.setVisibility(View.GONE);
            hasAddress.setVisibility(View.VISIBLE);
        }
    }

    private void setAddress(String contacts, String mobile, String address) {
        tvContacts.setText(contacts);
        tvOrderPlacerTel.setText(mobile);
        tvOrderPlacerAddress.setText(address);
    }
}