package com.wokun.tysl.goods.ui.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shantoo.common.utils.JSONUtil;
import com.shantoo.common.utils.Logger;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.tysl.R;
import com.wokun.tysl.base.BaseBindingActivity;
import com.wokun.tysl.callback.JsonCallback;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.goods.adapter.GoodsListAdapter;
import com.wokun.tysl.goods.bean.GoodsListBean;
import com.wokun.tysl.goods.bean.ZhiHuiSuccsessResponse;
import com.wokun.tysl.goods.response.GoodsListResponse;
import com.wokun.tysl.goodslinshou.ui.GoodsOrderDetailActivity2;
import com.wokun.tysl.main.MainActivity;
import com.wokun.tysl.model.response.BaseResponse;
import com.wokun.tysl.utils.ImageLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.wokun.tysl.TyslApp.getContext;

/**
 * Created by Administrator on 2018\10\11 0011.
 */

public class ZhihuiSuccessfulActivity extends BaseBindingActivity{
    private String order_id;
    private String buy_gid;
    @BindView(R.id.pay_name) TextView payName;
    @BindView(R.id.pay_phone) TextView payPhone;
    @BindView(R.id.pay_place) TextView payPlace;
    @BindView(R.id.pay_dingdanhao) TextView payDingdanhao;
    @BindView(R.id.pay_time) TextView pay_time;
    @BindView(R.id.pay_time2) TextView pay_time2;
    @BindView(R.id.pay_zitiandian) TextView pay_zitiandian;
    @BindView(R.id.pay_total1) TextView pay_total1;


    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView2;
    private List<GoodsListBean> goods_list;
    private GoodsListAdapter adapter;
    @Override
    public int createView() {
        return R.layout.activity_pay_success;
    }

    @Override
    public WidgetBar createToolBar() {
        return mWidgetBar.setWidgetBarTitle("付款成功");
    }

    @Override
    public void init() {
        Intent intent = getIntent();
        order_id = intent.getStringExtra("order_id");
        buy_gid = intent.getStringExtra("buy_gid");
         loadData();
         initData();
         loadaDataGoods();
    }

    private void loadaDataGoods() {
        mMultipleStatusView.showLoading();
        OkGo.<BaseResponse<GoodsListResponse>>post(Constants.BASE_URL + Constants.GOODS_LIST_URL)
                .tag(this)
                .params("order","s")
                .execute(new JsonCallback<BaseResponse<GoodsListResponse>>() {
                    @Override
                    public void onSuccess(Response<BaseResponse<GoodsListResponse>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;
                        if(body.isState()){
                            Logger.e(TAG, JSONUtil.toJSON(body));
                            Log.e("商品列表2",JSONUtil.toJSON(body));
                            mMultipleStatusView.showContent();
                            GoodsListResponse data = (GoodsListResponse) body.getData();
                            goods_list = data.getGoods_list();
                            if (goods_list != null && goods_list.size() > 0) {
                                adapter.setNewData(goods_list);
                            }
                        }

                    }
                });


    }
    private void initData() {
        mRecyclerView2.setLayoutManager(new GridLayoutManager(getContext(), 2));
        adapter = new GoodsListAdapter(R.layout.item_goods_list, null);
        mRecyclerView2.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                GoodsListBean bean = (GoodsListBean) adapter.getData().get(position);
                Intent intent = new Intent();
                intent.putExtra(Constants.GOODS_ID, goods_list.get(position).getGoodsId());
                intent.setClass(getContext(), GoodsDetailActivity.class);
                startActivity(intent);
            }
        });


    }
    private void loadData() {

        OkGo.<BaseResponse<ZhiHuiSuccsessResponse>>post(Constants.BASE_URL + Constants.RETAIL_PAY_RESULT)
                .tag(this)
                .params("order_id",order_id)
                .params("buy_gid",buy_gid)
                .execute(new JsonCallback<BaseResponse<ZhiHuiSuccsessResponse>>() {
                    @Override
                    public void onSuccess(Response<BaseResponse<ZhiHuiSuccsessResponse>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;

                        if(body.isState()){
                            Logger.e(TAG, JSONUtil.toJSON(body));
                            Log.e("商品列表",JSONUtil.toJSON(body));
                            ZhiHuiSuccsessResponse data = (ZhiHuiSuccsessResponse) body.getData();
                             if(data==null)  return;
                            ZhiHuiSuccsessResponse.OrderInBean orderInfo = data.getOrderInfo();
                                if(orderInfo==null)return;
                            String self_lifting = orderInfo.getSelf_lifting();
                            payName.setText(orderInfo.getName());
                            payPhone.setText(orderInfo.getMobile());
                            payDingdanhao.setText(orderInfo.getOrder_sn());
                            payPlace.setText(orderInfo.getShipping_address());
                            pay_total1.setText("￥"+orderInfo.getPay_price());
                            //不自提
                            if(self_lifting.equals("0")){
                                pay_time.setVisibility(View.INVISIBLE);
                                pay_time2.setVisibility(View.INVISIBLE);
                                pay_zitiandian.setText("收货地址：");

                            }
                            //自提
                            else if(self_lifting.equals("1")){
                                pay_time.setVisibility(View.VISIBLE);
                                pay_time2.setVisibility(View.VISIBLE);
                                pay_time2.setText(orderInfo.getLifting_time());
                            }


                            ZhiHuiSuccsessResponse.DuiDataBean tuiData = data.getTuiData();//弹窗
                             if(tuiData==null)  return;
                            Log.e("2222222222", "1111111112212131");
                            final AlertDialog.Builder customizeDialog = new AlertDialog.Builder(ZhihuiSuccessfulActivity.this);
                            final View dialogView = LayoutInflater.from(ZhihuiSuccessfulActivity.this)
                                    .inflate(R.layout.diaolog_pay_success, null);
                            ImageView pop_pay_delete = (ImageView) dialogView.findViewById(R.id.pop_pay_delete);
                            ImageView pop_pay_img1 = (ImageView) dialogView.findViewById(R.id.pop_pay_img1);
                            ImageView pop_pay_img2 = (ImageView) dialogView.findViewById(R.id.pop_pay_img2);
                            TextView pop_pay_txt1 = (TextView) dialogView.findViewById(R.id.pop_pay_txt1);
                            TextView pop_pay_see = (TextView) dialogView.findViewById(R.id.pop_pay_see);
                            customizeDialog.setView(dialogView);
                            final AlertDialog s = customizeDialog.show();
                            ImageLoader.loadImage(tuiData.getTui1_image(),pop_pay_img1);
                            ImageLoader.loadImage(tuiData.getTui2_image(),pop_pay_img2);
                            pop_pay_txt1.setText(tuiData.getTui2_desc());
                            pop_pay_see.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    s.dismiss();
                                    Log.e("去看看","去看看");
                                }
                            });


                            pop_pay_delete.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    s.dismiss();
                                }
                            });



                        }

                    }
                });




    }


    @OnClick({R.id.see_order, R.id.return_first})
    public void action(View v) {
        switch (v.getId()) {
            case R.id.see_order: //查看订单
                Intent intent = new Intent(ZhihuiSuccessfulActivity.this, GoodsOrderDetailActivity2.class);
                intent.putExtra(Constants.ORDER_ID,order_id);
                startActivity(intent);
                break;
           case R.id.return_first: //返回首页

             startActivity(MainActivity.class);
                break;
        }
    }

    }
