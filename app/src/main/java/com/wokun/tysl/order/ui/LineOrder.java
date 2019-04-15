package com.wokun.tysl.order.ui;

import android.content.Intent;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.tysl.R;
import com.wokun.tysl.TyslApp;
import com.wokun.tysl.base.SimpleRefreshAndLoadMoreFragment;
import com.wokun.tysl.callback.JsonCallback;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.dietician.bean.ServiceOrderBean;
import com.wokun.tysl.dietician.controler.DieticianServiceOrderMgr;
import com.wokun.tysl.goods.ui.activity.GoodsOrderDetailActivity;
import com.wokun.tysl.huiyuantotal.adapter.ChuangkeAdapter;
import com.wokun.tysl.huiyuantotal.bean.ChuankeBean;
import com.wokun.tysl.huiyuantotal.bean.ChuankeDetaiBean;
import com.wokun.tysl.huiyuantotal.bean.ChuankeResponse;
import com.wokun.tysl.model.response.BaseResponse;
import com.wokun.tysl.model.response.OrderListResponse;
import com.wokun.tysl.order.adapter.MyOrderServiceAdapter;
import com.wokun.tysl.order.adapter.ZhihuiOrderAdapter;
import com.wokun.tysl.order.bean.MyOrderBean;
import com.wokun.tysl.order.bean.ZhihuiOrderBean;
import com.wokun.tysl.order.bean.ZhihuiOrderDetaiBean;
import com.wokun.tysl.order.bean.ZhihuiOrderResponse;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

//店铺管理界面订单管理（线上订单、自提订单）
public class LineOrder extends SimpleRefreshAndLoadMoreFragment<ZhihuiOrderBean> {
    private ZhihuiOrderAdapter mAdapter;
    @Override
    public Request initRequest() {
        return  OkGo.<BaseResponse<ZhihuiOrderResponse>>post(Constants.BASE_URL + Constants.RETAIL_STORE_ORDER)
                .tag(this)
                .params(Constants.TYPE, 0);
    }

    @Override
    public BaseQuickAdapter<ZhihuiOrderBean, BaseViewHolder> initAdapter() {
       mAdapter = new ZhihuiOrderAdapter(R.layout.item_zhihui_chuangke_body, R.layout.item_zhihui_chuangke_header, R.layout.item_zhihui_chuangke_footer, null);
        return mAdapter;
    }

    @Override
    public void loadData(final boolean isRefresh) {

        mRequest.execute(new JsonCallback<BaseResponse<ZhihuiOrderResponse>>(Constants.WITH_TOKEN,Constants.RETAIL_STORE_ORDER) {
            @Override
            public void onSuccess(Response<BaseResponse<ZhihuiOrderResponse>> response) {
                BaseResponse body = response.body();
                if(body == null)return;
                if(body.isState()){
                    ZhihuiOrderResponse data = (ZhihuiOrderResponse) body.getData();
                    if(data==null){return;}
                    List<ZhihuiOrderBean> list = data.getOrder();
                    List<ZhihuiOrderBean> newData = new ArrayList<>();
                    if (list != null) {
                        for (ZhihuiOrderBean goodsOrder : list) {
                            newData.add(new ZhihuiOrderBean(mAdapter.HEADER,goodsOrder.getOrder_sn(),goodsOrder.getCreate_time(),goodsOrder.getOrder_state()));

                            ArrayList<ZhihuiOrderDetaiBean> subBeanList = goodsOrder.getSubOrder();
                            if (subBeanList != null) {
                                for (ZhihuiOrderDetaiBean goods : subBeanList) {
                                    newData.add(new ZhihuiOrderBean(mAdapter.BODY, goods));
                                }
                            }
                            newData.add(new ZhihuiOrderBean(mAdapter.FOOTER, goodsOrder.getUsername(), goodsOrder.getMobile(), goodsOrder.getOrder_id(),goodsOrder.getNums(),goodsOrder.getStore_order_reward()));
                        }
                    }
                    setData(isRefresh, newData);
                }
            }
        });

    }
}