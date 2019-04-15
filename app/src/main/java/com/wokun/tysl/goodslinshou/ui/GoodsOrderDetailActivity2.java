package com.wokun.tysl.goodslinshou.ui;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
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
import com.wokun.tysl.goods.bean.OrderDetailBean;
import com.wokun.tysl.goods.ui.activity.CheckWuliuActivity;
import com.wokun.tysl.goods.ui.activity.GoodsEvaluateActivity;
import com.wokun.tysl.goodslinshou.adapter.OrderDetailGoodsListAdapter2;
import com.wokun.tysl.goodslinshou.bean.ZhihuiDetaiBean;
import com.wokun.tysl.goodslinshou.bean.ZhihuiGoodsDetailsResponse;
import com.wokun.tysl.goodslinshou.bean.ZhihuiOrderDetailBean;
import com.wokun.tysl.model.response.BaseResponse;

import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;

//智慧商品订单详情页
public class GoodsOrderDetailActivity2 extends BaseBindingActivity {

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
  /*  @BindView(R.id.rl_finish_time)
    RelativeLayout rlFinishTime;*/

    @BindView(R.id.state_zh)
    TextView tvStateZh;//订单状态
    @BindView(R.id.store_name)
    TextView tvStoreName; //店铺名称
    @BindView(R.id.tv_goods_amount)
    TextView tvGoodsAmount; //商品总价
    @BindView(R.id.tv_shipping_fee)
    TextView tvShippingFee; //邮费
 /*   @BindView(R.id.tv_buyer_leave_message)
    TextView tvBuyerLeaveMessage; //买家留言*/
    @BindView(R.id.tv_order_sn)
    TextView tvOrderSn; //订单编号
    @BindView(R.id.tv_add_time)
    TextView tvAddTime; //订单生成时间（添加时间）

    @BindView(R.id.tv1)
    ImageView tv1;//待发状态图片

    @BindView(R.id.iv_go1)
    ImageView iv_go1;


    @BindView(R.id.tv_shipments_time)
    TextView tv_shipments_time; //配送方式
/*    @BindView(R.id.tv_finish_time)
    TextView tvFinishTime; //成交时间*/

   /* @BindView(R.id.rl_goods_eval_integral)
    RelativeLayout rlGoodsEvalIntegral;*/
    @BindView(R.id.rl_goods_finish_integral)
    RelativeLayout rlGoodsFinishIntegral;
/*
    @BindView(R.id.tv_goods_eval_integral)
    TextView tvGoodsEvalIntegral; //商品评价送的积分数*/
    @BindView(R.id.tv_goods_finish_integral)
    TextView tvGoodsFinishIntegral; //商品确认收货送的积分

    @BindView(R.id.rl_action)
    RelativeLayout rlAction;

    @BindView(R.id.action_cancel_order)
    TextView actionCancelOrder; //取消订单
    @BindView(R.id.action_immediate_payment)
    TextView actionImmediatePayment; //提醒发货
    @BindView(R.id.action_evaluate)
    TextView actionEvaluate; //评价
    @BindView(R.id.action_check_wl)
    TextView actionCheckWl; //查看物流
    @BindView(R.id.action_confirm_receipt)
    TextView actionConfirmReceipt;//确认收货
    @BindView(R.id.zhihui_ziti_code)
    TextView zhihui_ziti_code;//待自提码
    @BindView(R.id.tv_payment_time)
    TextView tv_payment_time;//支付方式

    @BindView(R.id.zhihui_total_price)
    TextView zhihui_total_price;//支付总额



    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    protected int province_id;
    protected String name,mobile,sAddress;
    private OrderDetailGoodsListAdapter2 adapter;
    private ZhihuiGoodsDetailsResponse data;

    @Override
    public int createView() {
        return R.layout.activity_goods_order_detail2;
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
        adapter = new OrderDetailGoodsListAdapter2(R.layout.item_goods_order_detail, null);
        mRecyclerView.setAdapter(adapter);
        loadData();
    }

    private void loadData() {
       // showLP();
        OkGo.<BaseResponse<ZhihuiGoodsDetailsResponse>>post(Constants.BASE_URL + Constants.RETAIL_DETAIL_URL)
                .tag(this)
                .params(Constants.ORDER_ID, getIntent().getStringExtra(Constants.ORDER_ID))
                .execute(new DialogCallback<BaseResponse<ZhihuiGoodsDetailsResponse>>(this,Constants.WITH_TOKEN,Constants.RETAIL_DETAIL_URL) {
                    @Override
                    public void onSuccess(Response<BaseResponse<ZhihuiGoodsDetailsResponse>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;
                        if (body.isState()) {
                            mMultipleStatusView.showContent();
                            data = (ZhihuiGoodsDetailsResponse) body.getData();
                            if(data ==null){return;}
                            ZhihuiOrderDetailBean receiver_info = data.getOrder();
                            //快递
                            if(receiver_info.getSelf_lifting().equals("1")){}
                            // 自提
                            if(receiver_info.getSelf_lifting().equals("0")){}


                         //   AddressBean receiver_info = data.getReceiverInfo();
                            if (receiver_info == null) {//没有收货地址
                                selectAddress.setVisibility(View.VISIBLE);
                                hasAddress.setVisibility(View.GONE);
                            } else {//有收货地址
                                selectAddress.setVisibility(View.GONE);
                                hasAddress.setVisibility(View.VISIBLE);
                                name = receiver_info.getLink_man();
                                mobile = receiver_info. getMobile();
                                //province_id = receiver_info.getProvince_id();
                                iv_go1.setVisibility(View.GONE);
                                sAddress = receiver_info.getShipping_address();
                                setAddress(name, mobile, sAddress);
                            }

                            //商品信息
                            adapter.setNewData(data.getOrder().getSubOrder());

                        //    tvStateZh.setText(data.getStateZh());
                            tvStoreName.setText(data.getOrder().getStore_name());
                            tvGoodsAmount.setText("¥"+ data.getOrder().getPay_price()+"");
                            tvShippingFee.setText("¥"+"0");
                            tv_payment_time.setText(data.getOrder().getPay_type());
                            tv_shipments_time.setText(data.getOrder().getShipping_methods());
                            zhihui_total_price.setText("¥"+data.getOrder().getPay_price());

                            List<ZhihuiDetaiBean> subOrder = data.getOrder().getSubOrder();
                            int totleSum=0;
                            for (int i = 0; i <data.getOrder().getSubOrder().size(); i++) {
                               totleSum    +=  Integer.parseInt(subOrder.get(i).getGoods_num());

                            }
                            tvGoodsFinishIntegral.setText("实付款：");

                            //    tvBuyerLeaveMessage.setText(data.getBuyerLeaveMessage());
                             tvOrderSn.setText(data.getOrder().getOrder_sn());
                            tvAddTime.setText(data.getOrder().getCreate_time());
                            //    tvFinishTime.setText(data.getFinishTime());
                            rlGoodsFinishIntegral.setVisibility(View.VISIBLE);
                            //订单状态 0全部2待发货3待收货4待自提5待评价6已完成
                            if ("4".equals(data.getOrder().getOrder_state()) ) {//
                                rlAction.setVisibility(View.GONE);
                                actionCancelOrder.setVisibility(View.GONE);
                                actionImmediatePayment.setVisibility(View.GONE);//
                                rlPaymentTime.setVisibility(View.VISIBLE);
                                rlShipmentsTime.setVisibility(View.VISIBLE);
                                tvStateZh.setText("待自提");
                                zhihui_ziti_code.setText("取货码："+data.getOrder().getSelf_lifting_code());
                             tv1.setImageResource(R.drawable.zhihuistatedaiziti);
                            } else if ("2".equals(data.getOrder().getOrder_state())) {//待发货
                                rlAction.setVisibility(View.VISIBLE);
                                actionImmediatePayment.setVisibility(View.VISIBLE);
                                rlShipmentsTime.setVisibility(View.VISIBLE);
                                tvStateZh.setText("待发货");
                           //     rlFinishTime.setVisibility(View.GONE);
                                tv1.setImageResource(R.drawable.zhihuistatedaifahuo);
                            } else if ("3".equals(data.getOrder().getOrder_state())) {//待收货
                                rlAction.setVisibility(View.VISIBLE);
                                tvStateZh.setText("待收货");
                                tv1.setImageResource(R.drawable.zhihuistatedaishouhuo);
                                actionCheckWl.setVisibility(View.VISIBLE);
                                actionConfirmReceipt.setVisibility(View.GONE);
                                rlGoodsFinishIntegral.setVisibility(View.VISIBLE);
                          //      rlFinishTime.setVisibility(View.GONE);
                            } else if ("5".equals(data.getOrder().getOrder_state())) {//待评价
                                rlAction.setVisibility(View.VISIBLE);
                                actionEvaluate.setVisibility(View.VISIBLE);
                                tv1.setImageResource(R.drawable.zhihuistatedaipinjia);
                                tvStateZh.setText("待评价");
                            //    rlGoodsEvalIntegral.setVisibility(View.VISIBLE);
                            } else if ("6".equals(data.getOrder().getOrder_state())) {//已评价

                            }
                        }
                    }
                });
    }

    @OnClick({R.id.show_address, R.id.select_address, R.id.action_cancel_order, R.id.action_immediate_payment, R.id.action_evaluate, R.id.action_check_wl, R.id.action_confirm_receipt})
    public void action(View v) {
        if (R.id.show_address == v.getId() || R.id.select_address == v.getId()) {//选择收货地址
           /* Intent intent = new Intent();
            intent.setClass(this, AddressListActivity.class);
            startActivityForResult(intent, 99);*/
        }else if(R.id.action_cancel_order == v.getId()){//取消订单
         //   Toast.makeText(this, "取消订单", Toast.LENGTH_SHORT).show();

            OkGo.<BaseResponse<Object>>post(Constants.BASE_URL + Constants.ORDER_CANCEL_ORDER_URL)
                    .tag(this)
                    .params(Constants.ORDER_ID,getIntent().getStringExtra(Constants.ORDER_ID))
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

        }else if(R.id.action_immediate_payment == v.getId()){//提醒发货
            Toast.makeText(this, "提醒成功", Toast.LENGTH_SHORT).show();
        }else if(R.id.action_evaluate == v.getId()){//评价
              Intent intent = new Intent();
            intent.putExtra(Constants.DATA,data.getOrder().getSubOrder());
            intent.putExtra(Constants.ORDER_ID,getIntent().getStringExtra(Constants.ORDER_ID));
            intent.setClass(this,GoodsEvaluateActivity.class);
            startActivity(intent);
        }else if(R.id.action_check_wl == v.getId()){//查看物流
            Toast.makeText(this, "查看物流", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.putExtra("type",data.getOrder().getExpress_name());
            intent.putExtra("number",data.getOrder().getExpress_code());
            intent.setClass(this,CheckWuliuActivity.class);
            startActivity(intent);

        }else if(R.id.action_confirm_receipt == v.getId()){//确认收货
            Toast.makeText(this, "确认收货", Toast.LENGTH_SHORT).show();
            OkGo.<BaseResponse<Object>>post(Constants.BASE_URL + Constants.ORDER_CONFIRM_ACCEPT_URL)
                    .tag(this)
                    .params(Constants.ORDER_ID,getIntent().getStringExtra(Constants.ORDER_ID))
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